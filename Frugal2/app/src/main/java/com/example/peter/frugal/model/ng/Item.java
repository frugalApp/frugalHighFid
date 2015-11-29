package com.example.peter.frugal.model.ng;

import android.graphics.Bitmap;
import android.location.Geocoder;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Peter on 11/28/2015.
 */
public class Item {
    public static final String EVENT = "EVENT";
    public static final String ITEM = "ITEM";


    public String title;
    public ArrayList<Integer> images = new ArrayList<Integer>();
    public Geocoder location;
    public String itemType;
    public boolean favorited;
    public String description;
    private UUID mId;

    public Item () {
        mId = UUID.randomUUID();
        images = new ArrayList<Integer>();
    }

    public UUID getId() {
        return mId;
    }
}
