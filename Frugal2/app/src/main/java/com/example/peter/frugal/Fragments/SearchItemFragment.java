package com.example.peter.frugal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 */
public class SearchItemFragment extends ListFragment {
    ArrayList<Item> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItems = Model.getModel().searchableItems;
        ItemAdapter adapter = new ItemAdapter(mItems);
        setListAdapter(adapter);

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
            if (c.favorited) {
                watching.setImageResource(R.drawable.star_watching);
            }
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
        ((ItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
