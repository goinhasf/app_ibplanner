package com.example.kiko.ibstudentplannerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.kiko.ibstudentplannerapp.IB.IBTask;
import com.example.kiko.ibstudentplannerapp.IB.IBUser;

/**
 * Created by djkik on 9/4/2017.
 */

public class HomeFragment extends Fragment {

    private ImageButton mNewTaskButton;
    private RecyclerView mRecyclerView;
    private View mView;

    private boolean isVisibleView = false;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_home_layout, container, false);
        mNewTaskButton = (ImageButton) mView.findViewById(R.id.but_new_task);
        mNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNewTaskActivity.class);
                startActivity(intent);
            }
        });

        setVisibleViews(isVisibleView);

        return mView;
    }

    public void setRecyclerView (IBUser user){
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_assignments);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new IBTasksRecyclerViewAdapter(getContext(), MainActivity.mUserInstance));

    }

    public void addNewTask(IBTask newTask) {
        MainActivity.mUserInstance.getAllActiveTasks().add(newTask);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void setVisibleViews(boolean visibleViews) {
        isVisibleView = visibleViews;
        if (visibleViews) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }
}
