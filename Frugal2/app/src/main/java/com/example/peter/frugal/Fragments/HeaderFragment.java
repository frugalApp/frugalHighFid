package com.example.peter.frugal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.peter.frugal.Activities.NewPost;
import com.example.peter.frugal.Activities.Search;
import com.example.peter.frugal.R;

/**
 * Created by Peter on 11/28/2015.
 */
public class HeaderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_header, container, false);
        ImageButton newPostButton = (ImageButton)v.findViewById(R.id.imageButton2);
        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPost.class);
                startActivity(intent);
            }
        });
        if (getActivity().getClass().getSimpleName().equals("NewPost")) {
            newPostButton.setEnabled(false);
            newPostButton.setVisibility(View.GONE);
        }
        return v;
    }
}
