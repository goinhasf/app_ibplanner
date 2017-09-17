package com.example.kiko.ibstudentplannerapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by djkik on 9/8/2017.
 */

public class IBTasksRecyclerViewAdapter extends RecyclerView.Adapter <IBTasksRecyclerViewAdapter.IBTasksViewHolder> {

    public static final int ASSIGNMENTS_VIEW_TYPE = 0;
    public static final int REVISION_VIEW_TYPE = 1;
    private Context mContext;

    public ArrayList<IB>

    public IBTasksRecyclerViewAdapter(Context context) {

        mContext= context;
    }

    @Override
    public IBTasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutID = 0;
        switch (viewType){
            case ASSIGNMENTS_VIEW_TYPE:
                layoutID = R.layout.item_assignment;
                break;
            case REVISION_VIEW_TYPE:
                layoutID = R.layout.item_revision;
                break;
            default:
                throw new IllegalArgumentException("ViewType unknown");

        }

        return new IBTasksViewHolder(LayoutInflater.from(mContext).inflate(layoutID, parent, false));
    }

    @Override
    public void onBindViewHolder(IBTasksViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class IBTasksViewHolder extends RecyclerView.ViewHolder {

        private int viewType;

        private IBTasksViewHolder(View itemView) {
            super(itemView);

            viewType = getItemViewType();

            switch (viewType) {
                case ASSIGNMENTS_VIEW_TYPE:
                    break;
                case REVISION_VIEW_TYPE;
                    break;
                default:
                    throw new IllegalArgumentException("ViewType Unknown");
            }

        }





    }
}
