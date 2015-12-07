package com.example.peter.frugal.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.peter.frugal.R;
import com.example.peter.frugal.model.ng.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Peter on 11/28/2015.
 */
public class Model {

    private static Model model;

    private Model() {
        //do nothing
        Item item = new Item();
        item.title = "And I was like";
        item.description = "Yoooooooooooooooooo, what the fuck? It's like Satan made a webcomic";
        item.itemType = Item.ITEM;
        item.favorited = true;
        item.poster = "Peter G";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.ffti));
        for (int i = 0; i < 50; i++)
            searchableItems.add(item);
    }


    /*
    Singleton com.example.peter.frugal.model is the easiest way to keep all the activities in sync with each other
    Not the best solution for the long term, but for the purposes of UI development, it's fine
     */
    public static Model getModel() {
        if (model == null) model = new Model();
        return model;
    }

    public Item newTempTiem = new Item();

    public ArrayList<Item> searchableItems = new ArrayList<Item>();


    public Item getItem(UUID id) {
        for (Item i: searchableItems) {
            if (i.getId().equals(id))
                return i;
        }
        return null;
    }
}
