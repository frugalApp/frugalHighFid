package com.example.peter.frugal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.frugal.Activities.ItemViewPager;
import com.example.peter.frugal.R;
import com.example.peter.frugal.model.Model;
import com.example.peter.frugal.model.ng.Item;

import java.util.ArrayList;

/**
 * Created by Peter on 11/28/2015.
 *
 * This is just an item renderer that allows us to filter out items by the item's title
 */
public class SearchItemFragment extends ListFragment {
    ArrayList<Item> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateMItems();

        ItemAdapter adapter = new ItemAdapter(mItems);
        setListAdapter(adapter);
    }

    //No reason for anyone else to call this.
    private void updateMItems() {
        mItems = new ArrayList<Item>();
        for (Item item : Model.getModel().searchableItems) {
            if (item.title.toLowerCase().indexOf(Model.getModel().searchString.trim().toLowerCase()) != -1)
                mItems.add(item);
        }
    }

    //If an update is needed to be called by an external source, then we are changing the displayed
    //data instead of modifying it and will need to reinstantiate the data
    public void updateAndShowMItems() {
        updateMItems();
        ItemAdapter adapter = new ItemAdapter(mItems);
        setListAdapter(adapter);
        ((ItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ItemAdapter extends ArrayAdapter<Item> {
        public ItemAdapter(ArrayList<Item> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.search_list_item, null);
            }

            //display the data for the current item on the list and add event listeners to them
            final Item c = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.search_item_title);
            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ItemViewPager.class);
                    i.putExtra(ItemFragment.EXTRA_ITEM_ID, c.getId());
                    startActivity(i);
                }
            });
            titleTextView.setText(c.title);
            TextView dataTextView = (TextView)convertView.findViewById(R.id.search_item_description);
            dataTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ItemViewPager.class);
                    i.putExtra(ItemFragment.EXTRA_ITEM_ID, c.getId());
                    startActivity(i);
                }
            });
            dataTextView.setText(c.description);
            ImageView imageTextView = (ImageView)convertView.findViewById(R.id.searchItemImage);
            imageTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ItemViewPager.class);
                    i.putExtra(ItemFragment.EXTRA_ITEM_ID, c.getId());
                    startActivity(i);
                }
            });
            final ImageButton watching = (ImageButton)convertView.findViewById(R.id.image_button_favorite);
            if (c.favorited)
                watching.setImageResource(R.drawable.star_watching);
            else
                watching.setImageResource(R.drawable.star_empty);
            watching.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c.favorited) {
                        c.favorited = false;
                        watching.setImageResource(R.drawable.star_empty);
                    }
                    else {
                        c.favorited = true;
                        watching.setImageResource(R.drawable.star_watching);
                    }
                }
            });
            if (c.images.size() > 0)
                imageTextView.setImageURI(c.images.get(0));

            return convertView;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //In case the data was modified in the ItemViewPager
        updateAndShowMItems();
    }
}
