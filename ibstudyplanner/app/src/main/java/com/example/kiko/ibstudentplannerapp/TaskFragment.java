package com.example.kiko.ibstudentplannerapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by djkik on 9/5/2017.
 */

public class TaskFragment extends Fragment {

    public TaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_fragment_layout, container, false);

        return view;
    }
}
