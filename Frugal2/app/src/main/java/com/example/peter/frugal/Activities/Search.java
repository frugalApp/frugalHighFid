package com.example.peter.frugal.Activities;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peter.frugal.Fragments.SearchItemFragment;
import com.example.peter.frugal.R;
import com.example.peter.frugal.model.Model;
import com.example.peter.frugal.model.ng.Item;

import java.util.ArrayList;

/*
    Not much of the search page is handled here. The apply search call is handled here for simplicity's sake
    If that logic were handling in its fragment, that would require a much more complex call to get the other
    fragment.
 */

public class Search extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    /*
        Get the text the user has given for a search string and filter out search results based on it
     */
    public void applySearch(View view) {
        Model.getModel().searchString = (String)((EditText)findViewById(R.id.search_words_bar)).getText().toString();
        SearchItemFragment thing = (SearchItemFragment)getSupportFragmentManager().findFragmentById(R.id.search_results);
        thing.updateAndShowMItems();
    }

    /*
        Start the advanced search activity. It does nothing except exist. Basically, it's art :D
     */
    public void doSearch(View view) {
        Intent intent = new Intent(this, SearchQuery.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
