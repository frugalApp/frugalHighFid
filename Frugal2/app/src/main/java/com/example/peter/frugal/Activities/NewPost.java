package com.example.peter.frugal.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.peter.frugal.R;
import com.example.peter.frugal.model.Model;
import com.example.peter.frugal.model.ng.Item;

import java.util.ArrayList;

public class NewPost extends FragmentActivity {
    private static final int RESULT_LOAD_IMAGE_ONE = 2358;
    private static final int RESULT_LOAD_IMAGE_TWO = 3285;
    private static final int RESULT_LOAD_IMAGE_THREE = 8532;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_post, menu);
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



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private ImageButton picOne, picTwo, picThree;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_new_post, container, false);
            Model model = Model.getModel();
            model.newTempTiem = new Item();

            picOne = (ImageButton)rootView.findViewById(R.id.new_image_one);
            if (model.newTempTiem.images.size() > 0)
                picOne.setImageURI(model.newTempTiem.images.get(0));
            picOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_ONE);
                }
            });
            picTwo = (ImageButton)rootView.findViewById(R.id.new_image_two);
            if (model.newTempTiem.images.size() > 1)
                picOne.setImageURI(model.newTempTiem.images.get(1));
            picTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_TWO);
                }
            });
            picThree = (ImageButton)rootView.findViewById(R.id.new_image_three);
            if (model.newTempTiem.images.size() > 2)
                picOne.setImageURI(model.newTempTiem.images.get(2));
            picThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_THREE);
                }
            });
            Button post = (Button)rootView.findViewById(R.id.new_post_button);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item newItem = new Item();
                    newItem.images = new ArrayList<Uri>(Model.getModel().newTempTiem.images);
                    newItem.title = ((TextView)rootView.findViewById(R.id.new_post_title)).getText().toString();
                    newItem.poster = "pgrasso";
                    newItem.catagory = ((TextView)rootView.findViewById(R.id.new_post_catagoies)).getText().toString();
                    newItem.description = ((TextView)rootView.findViewById(R.id.new_post_description)).getText().toString();
                    newItem.timeFree = ((TextView)rootView.findViewById(R.id.new_post_time)).getText().toString();
                    newItem.itemType = Item.ITEM;
                    Model.getModel().searchableItems.add(newItem);
                    getActivity().finish();
                }
            });
            return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                Item item = Model.getModel().newTempTiem;
                if (requestCode == RESULT_LOAD_IMAGE_ONE) {
                    if (item.images.size() == 0) {
                        item.images.add(selectedImage);
                        picTwo.setVisibility(View.VISIBLE);
                        picTwo.setClickable(true);
                    }
                    else {
                        item.images.set(0, selectedImage);
                    }
                    picOne.setImageURI(selectedImage);
                }
                else if (requestCode == RESULT_LOAD_IMAGE_TWO) {
                    if (item.images.size() == 1) {
                        item.images.add(selectedImage);
                        picThree.setVisibility(View.VISIBLE);
                        picThree.setClickable(true);
                    }
                    else {
                        item.images.set(1, selectedImage);
                    }
                    picTwo.setImageURI(selectedImage);

                }
                else if (requestCode == RESULT_LOAD_IMAGE_THREE) {
                    if (item.images.size() == 2)
                        item.images.add(selectedImage);
                    else
                        item.images.set(2, selectedImage);
                    picThree.setImageURI(selectedImage);
                }
            }
        }
    }
}
