package com.example.kiko.ibstudentplannerapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageButton;

/**
 * Created by djkik on 9/4/2017.
 */

public class HomeFragment extends Fragment {

    private boolean dropDownMode;
    private ImageButton dropDownButton;
    private FrameLayout dropDownContainer;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home_layout, container, false);

        return view;
    }



}
