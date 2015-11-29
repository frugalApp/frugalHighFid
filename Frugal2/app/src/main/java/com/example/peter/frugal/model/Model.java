package com.example.peter.frugal.model;

import android.graphics.Bitmap;

import com.example.peter.frugal.R;
import com.example.peter.frugal.model.ng.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Peter on 11/28/2015.
 */
public class Model {

    private static Model model;

    private Model() {
        //do nothing
        Item item = new Item();
        item.title = "Sup bitches";
        item.description = "Checking out my junk I see";
        item.itemType = Item.ITEM;
        item.favorited = true;
        item.images.add(R.drawable.picky);
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


    public ArrayList<Item> searchableItems = new ArrayList<Item>();

}
