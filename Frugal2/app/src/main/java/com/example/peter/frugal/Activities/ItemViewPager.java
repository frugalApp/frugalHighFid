package com.example.peter.frugal.Activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import com.example.peter.frugal.Fragments.ItemFragment;
import com.example.peter.frugal.R;
import com.example.peter.frugal.model.Model;
import com.example.peter.frugal.model.ng.Item;

import java.util.ArrayList;
import java.util.UUID;

/**
 * created by Peter Grasso
 */

public class ItemViewPager extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Item> items;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        items = Model.getModel().searchableItems;

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Item item = items.get(i);
                return ItemFragment.newInstance(item.getId());
            }

            @Override
            public int getCount() {
                return items.size();
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(ItemFragment.EXTRA_ITEM_ID);
        for (int i = 0; i <items.size(); i++) {
            if (items.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {}

            @Override
            public void onPageSelected(int i) {
                Item item = items.get(i);
                if (item.title != null) {
                    setTitle(item.title);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
    }
}
