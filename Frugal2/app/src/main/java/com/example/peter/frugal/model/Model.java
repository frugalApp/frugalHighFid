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
        Item item = new Item();
        //Create dummy data
        //Data from post off craigslist free section or that one post about my car that I never sold or Always Sunny in Philly
        item.title = "Lovely sofa";
        item.description = "Looking for someone who can take over a lovely sofa. The sofa frame is solid and well-made. It is a pretty antique piece, and very comfortable.(Size: 33.5x78.8x35.4 inch)";
        item.timeFree = "Anytime";
        item.itemType = Item.ITEM;
        item.location = "McKeldin";
        item.poster = "Bethany";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.ugly_couch));
        searchableItems.add(item);

        item = new Item();
        item.title = "540i Bimmer";
        item.description = "It has wheels, an engine, some seats. y'know, things";
        item.itemType = Item.ITEM;
        item.poster = "Peter G";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.ffti));
        searchableItems.add(item);

        item = new Item();
        item.title = "Infant mittens";
        item.description = "Two pair infant mittens. Fits 9-15 month olds, give or take. ";
        item.itemType = Item.ITEM;
        item.timeFree = "Dec 8th";
        // item.favorited = true;
        item.poster = "Peter G";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.mittens));
        searchableItems.add(item);

        item = new Item();
        item.title = "KiTtEn MiTtEnS!!!!";
        item.description = "Is your cat making TOO MUCH NOISE all the time?? Is your cat constantly stomping around DRIVING YOU CRAAAZY?? Well come try Kitten Mittens. Finally, a comfortable, elegant mittens for cats!";
        item.itemType = Item.ITEM;
        item.timeFree = "Anytime";
        // item.favorited = true;
        item.poster = "Charlie Kelly";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.kitn_mtns_one));
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.kitn_mtns_two));
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.kitn_mtns_three));
        searchableItems.add(item);

        item = new Item();
        item.title = "free";
        item.description = "Items shown on picture. Please reply with pickup time. Must take everything";
        item.itemType = Item.ITEM;
        // item.favorited = true;
        item.poster = "Idiot";
        item.images.add(Uri.parse("android.resource://com.example.peter.frugal/" + R.drawable.so_descriptive));
        searchableItems.add(item);
    }


    /*
    Singleton com.example.peter.frugal.model is the easiest way to keep all the activities in sync with each other
    Not the best solution for the long term, but for the purposes of UI development, it's fine

    Passing data between activities gets complicated and requires checks to make sure things don't explode violently

    However, activities are wonderful in that using the app is easier in that we can utilize the "Back"
    button built into all android phones. So Singleton it is
     */
    public static Model getModel() {
        if (model == null) model = new Model();
        return model;
    }

    public Item newTempTiem = new Item();

    public ArrayList<Item> searchableItems = new ArrayList<Item>();

    public String searchString = "";


    public Item getItem(UUID id) {
        for (Item i: searchableItems) {
            if (i.getId().equals(id))
                return i;
        }
        return null;
    }
}
