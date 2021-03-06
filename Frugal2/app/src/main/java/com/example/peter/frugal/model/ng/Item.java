package com.example.peter.frugal.model.ng;

import android.graphics.Bitmap;
import android.location.Geocoder;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Peter on 11/28/2015.
 */
public class Item {
    public static final String EVENT = "EVENT";
    public static final String ITEM = "ITEM";

    private static int UIDCounter = 0;


    public String title;
    public ArrayList<Uri> images = new ArrayList<Uri>();
    public String location;
    public String catagory;
    public Date mDate;
    public String itemType;
    public String poster;
    public int numberWatching;
    public String timeFree;
    public boolean favorited;
    public String description;
    private int UID;
    private UUID mId;

    public Item () {
        mId = UUID.randomUUID();
        UID = UIDCounter++;
        images = new ArrayList<Uri>();
        mDate = new Date();
        favorited = false;
        numberWatching = (int)(Math.round(Math.random() * 20) % 20);
    }

    public UUID getId() {
        return mId;
    }

    public int getUID() { return UID; }

    public boolean filterOut(ArrayList<String> words) {
        boolean toReturn = true;

        return toReturn;
    }
}
