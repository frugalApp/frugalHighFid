package com.example.peter.frugal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peter.frugal.Activities.ItemView;
import com.example.peter.frugal.Activities.Search;
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Item c = ((ItemAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), ItemView.class);
        i.putExtra(ItemFragment.EXTRA_ITEM_ID, c.getId());
        startActivity(i);
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

            Item c = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.search_item_title);
            titleTextView.setText(c.title);
            TextView dataTextView = (TextView)convertView.findViewById(R.id.search_item_description);
            dataTextView.setText(c.description);
            ImageView imageTextView = (ImageView)convertView.findViewById(R.id.searchItemImage);
            if (c.images.size() > 0)
                imageTextView.setImageResource( c.images.get(0));

            return convertView;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((ItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

}
