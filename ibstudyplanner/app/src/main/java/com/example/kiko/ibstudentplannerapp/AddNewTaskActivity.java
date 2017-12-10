package com.example.kiko.ibstudentplannerapp;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.MenuPopupWindow;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;
import com.example.kiko.ibstudentplannerapp.IB.IBTask;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBPlannerContract;
import com.example.kiko.ibstudentplannerapp.databinding.ActivityAddNewTaskBinding;

import java.text.DateFormat;
import java.util.ArrayList;

public class AddNewTaskActivity extends AppCompatActivity {

    // TODO: Add variables for fields in IBTask so it can be sent back to MainActivity.
    public static final String TASK_NAME_KEY = "task_name";
    public static final String SUBJECT_NAME_KEY = "subject_name";
    public static final String DATE_DUE_KEY = "date_due";
    public static final String DATE_CURRENT = "current_date";

    private static final String ASSIGNMENT = "Assignment";
    private static final String REVISION = "Revision";

    private IBSubject[] mIBSubjects;

    private Context mContext;
    private TextView mDateField;
    private PopupWindow window;

    private ActivityAddNewTaskBinding binding;

    private Button datePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_add_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_task);

        datePickerButton = binding.btDatePicker;
        mDateField = binding.btDatePicker;

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                window = new PopupWindow(AddNewTaskActivity.this);
                window.setBackgroundDrawable(null);
                window.setElevation(6.0f);
                final DatePicker picker = new DatePicker(mContext);
                picker.setBackgroundColor(Color.WHITE);
                window.setContentView(picker);
                window.showAtLocation(binding.getRoot(), Gravity.CENTER, 0 , 0);
                window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        getPickedDate(picker.getDayOfMonth(), picker.getMonth(), picker.getYear());
                    }
                });
                window.setOutsideTouchable(true);
            }
        });

        binding.clRevisionLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()) {
                    window.dismiss();
                }
            }
        });

        mIBSubjects = MainActivity.mUserInstance.getUserIBSubjects();
        String[] subjectNames = new String[mIBSubjects.length];

        for (int i = 0; i< mIBSubjects.length; i++) {
            subjectNames[i] = mIBSubjects[i].getSubjectName();
        }

        Spinner spinner = (Spinner) findViewById(R.id.sp_subject_spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_layout, subjectNames) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                // Set the Text color
                tv.setTextColor(Color.parseColor("#263238"));

                return view;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.new_task_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // Make Bundle here
        startActivity(intent);

        ContentValues contentValues = new ContentValues();

        /* contentValues.put(TASK_NAME_KEY, );
        contentValues.put(SUBJECT_NAME_KEY, );
        contentValues.put(DATE_DUE_KEY, );
        contentValues.put(DATE_CURRENT, ); */

        new BackgroundAddNewTask(contentValues);

        return true;
    }

    private void getPickedDate(int day, int month, int year) {
        String datePicked = day + "/" + month + "/" + year;
        mDateField.setText(datePicked);
    }

    private ContentValues addTask() {
        ContentValues contentValues = new ContentValues();
        String subject = (String) binding.spSubjectSpinner.getSelectedItem();
        String dateDue = (String) binding.btDatePicker.getText();
        int revisionTimeHours;
        int revisionTimeMinutes;
        RadioButton checkedButton = (RadioButton) findViewById(binding.radioGroup.getCheckedRadioButtonId());
        String typeOfTask = (String) checkedButton.getText();

        switch (typeOfTask) {
            case ASSIGNMENT:
                // revisionTimeHours = Integer.parseInt();
                break;
            case REVISION:
                break;
        }

        return null;
    }

    private class BackgroundAddNewTask extends AsyncTask<Uri, Void, Uri> {


        private ContentValues contentValues;

        private BackgroundAddNewTask(ContentValues contentValues) {
            this.contentValues = contentValues;
        }

        @Override
        protected Uri doInBackground(Uri... params) {

            return getContentResolver()
                    .insert(IBPlannerContract.IBSubjectsTasksEntry.CONTENT_URI, contentValues);
        }

        @Override
        protected void onPostExecute(Uri uri) {
            super.onPostExecute(uri);
            Log.d(this.getClass().getSimpleName() + " Post Execute", uri.toString());
        }
    }

}





