package com.example.kiko.ibstudentplannerapp;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kiko.ibstudentplannerapp.IB.IBTask;
import com.example.kiko.ibstudentplannerapp.IB.IBUser;
import com.example.kiko.ibstudentplannerapp.databinding.ItemAssignmentBinding;
import com.example.kiko.ibstudentplannerapp.databinding.ItemRevisionBinding;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by djkik on 9/8/2017.
 */

public class IBTasksRecyclerViewAdapter extends RecyclerView.Adapter<IBTasksRecyclerViewAdapter.IBTasksViewHolder> {

    public static final int ASSIGNMENTS_VIEW_TYPE = 0;
    public static final int REVISION_VIEW_TYPE = 1;

    private Context mContext;
    private ArrayList<IBTask> tasks;

    private boolean mTasksEmpty = false;

    private int currentViewType;

    public IBTasksRecyclerViewAdapter(Context context, IBUser user) {

        mContext = context;
        try {
            tasks = user.getAllActiveTasks();
        } catch (NullPointerException e) {
            tasks = new ArrayList<>();
            mTasksEmpty = true;
        }

    }

    @Override
    public IBTasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutID;

        currentViewType = viewType;

        switch (viewType) {
            case ASSIGNMENTS_VIEW_TYPE:
                layoutID = R.layout.item_assignment;
                break;
            case REVISION_VIEW_TYPE:
                layoutID = R.layout.item_revision;
                break;
            default:
                throw new IllegalArgumentException("ViewType unknown");

        }

        return new IBTasksViewHolder(LayoutInflater.from(mContext)
                .inflate(layoutID, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(IBTasksViewHolder holder, int position) {
        if (mTasksEmpty) {
            TextView textView = new TextView(mContext);

            textView.setText(R.string.no_tasks);
            textView.setTextSize(24.0f);

            holder.mViewGroup.removeAllViews();
            holder.mViewGroup.addView(textView);

            return;
        }

        if (currentViewType == REVISION_VIEW_TYPE) {

            ItemRevisionBinding itemRevisionBinding = DataBindingUtil.inflate(LayoutInflater
                            .from(mContext), R.layout.item_revision, holder.mViewGroup, false);

            IBTask.IBRevision currentTask = (IBTask.IBRevision) tasks.get(position);
            itemRevisionBinding.tvSubject.setText(currentTask.getSubjectName());
            itemRevisionBinding.tvDateDue.setText(currentTask.getSubjectName());
            itemRevisionBinding.tvRevisionTime.setText(currentTask.getRevisionTimeHoursMinutes());
            itemRevisionBinding.tvTimeAt.setText(currentTask.getTimeAt());

        } else if (currentViewType == ASSIGNMENTS_VIEW_TYPE) {

            ItemAssignmentBinding itemAssignmentBinding = DataBindingUtil.inflate(LayoutInflater
                            .from(mContext), R.layout.item_revision, holder.mViewGroup, false);

            IBTask.IBAssignment currentTask = (IBTask.IBAssignment) tasks.get(position);
            itemAssignmentBinding.tvDateDue.setText(currentTask.getDateDue());
            itemAssignmentBinding.tvSubject.setText(currentTask.getSubjectName());
            itemAssignmentBinding.pbTimeProgress.setProgress(currentTask.getProgress());

        } else {
            throw new NullPointerException("No view type has been assigned");
        }


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class IBTasksViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup mViewGroup;
        private ConstraintLayout mItemAssignmentLayout;
        private ConstraintLayout mItemRevisionLayout;

        private IBTasksViewHolder(View itemView, int viewType) {
            super(itemView);
            mViewGroup = (ViewGroup) itemView;

            switch (viewType) {
                case ASSIGNMENTS_VIEW_TYPE:
                    mItemAssignmentLayout = (ConstraintLayout) itemView;
                    break;
                case REVISION_VIEW_TYPE:
                    mItemRevisionLayout = (ConstraintLayout) itemView;
                    break;
                default:
                    throw new IllegalArgumentException("ViewType Unknown");
            }

        }


    }
}
