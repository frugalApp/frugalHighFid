package com.example.peter.frugal.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.frugal.R;
import com.example.peter.frugal.model.Model;
import com.example.peter.frugal.model.ng.Item;

import java.text.DateFormat;
import java.util.UUID;

/**
 * Created by Peter on 11/28/2015.
 *
 * This gets its own class because this allows us to swipe between posts. SOOOOooo Faunceh
 *
 */
public class ItemFragment extends Fragment {
    public static final String EXTRA_ITEM_ID = "fdsanbifbaueiwfbreyuwfvberwi";

    private Item item;

    public static ItemFragment newInstance(UUID itemUid) {
        Bundle args = new Bundle();
        //Save what item to be shown for the next activity that will use this fragment
        //Done this way because I already had code that does this
        args.putSerializable(EXTRA_ITEM_ID, itemUid);

        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the data for the appropriate item
        UUID itemId = (UUID)getArguments().getSerializable(EXTRA_ITEM_ID);

        item = Model.getModel().getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_view, parent, false);

        TextView title = (TextView)v.findViewById(R.id.item_view_title);
        title.setText(item.title);
        ImageView mainImage = (ImageView)v.findViewById(R.id.item_view_main_image);
        ImageView secondImage = (ImageView)v.findViewById(R.id.item_view_main_image);
        ImageView threeImage = (ImageView)v.findViewById(R.id.item_view_main_image);

        if (item.images.size() > 0)
            mainImage.setImageURI(item.images.get(0));
        if (item.images.size() > 1)
            secondImage.setImageURI(item.images.get(1));
        if (item.images.size() > 2)
            secondImage.setImageURI(item.images.get(2));

        EditText description = (EditText)v.findViewById(R.id.view_item_description_box);
        String desc = " Posted by User:   " + item.poster + "  (100%)\n";
        desc += "Description: " + item.description + "\n";

        if (item.timeFree != null)
            desc += "Time: " + item.timeFree +"\n";
        else
            desc += "Time: contact for more info\n";
        if (item.location != null)
            desc += "Location: " + item.location + "\n";
        else
            desc += "Location: contact for more info\n";
        desc += "Number Watching: " + item.numberWatching + "\n";
        desc += "Date added: " + android.text.format.DateFormat.format("EEE, dd MMM yyyy", item.mDate) + "\n";
        desc += "Post ID: " + item.getUID();
        description.setText(desc);

        final ImageButton watching = (ImageButton)v.findViewById(R.id.item_view_favorite_button);
        watching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.favorited = !item.favorited;
                if (item.favorited)
                    watching.setImageResource(R.drawable.star_watching);
                else
                    watching.setImageResource(R.drawable.star_empty);
            }
        });

        if (item.favorited)
            watching.setImageResource(R.drawable.star_watching);
        return v;
    }
}
