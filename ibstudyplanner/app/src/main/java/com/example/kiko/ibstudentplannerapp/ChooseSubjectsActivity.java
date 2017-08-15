package com.example.kiko.ibstudentplannerapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;

import java.util.ArrayList;

public class ChooseSubjectsActivity extends AppCompatActivity implements View.OnClickListener{

    private View mContentView;

    private EditText mSubject1EditText;
    private EditText mSubject2EditText;
    private EditText mSubject3EditText;
    private EditText mSubject4EditText;
    private EditText mSubject5EditText;
    private EditText mSubject6EditText;

    private RadioGroup mSubject1RadioGroup;
    private RadioGroup mSubject2RadioGroup;
    private RadioGroup mSubject3RadioGroup;
    private RadioGroup mSubject4RadioGroup;
    private RadioGroup mSubject5RadioGroup;
    private RadioGroup mSubject6RadioGroup;

    private Button mAcceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createSubjectChooser();
        mContentView = findViewById(R.id.activity_chooser_layout);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            supportNavigateUpTo(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSubjectChooser() {

        mSubject1EditText = (EditText) findViewById(R.id.et_subject_1);
        mSubject2EditText = (EditText) findViewById(R.id.et_subject_2);
        mSubject3EditText = (EditText) findViewById(R.id.et_subject_3);
        mSubject4EditText = (EditText) findViewById(R.id.et_subject_4);
        mSubject5EditText = (EditText) findViewById(R.id.et_subject_5);
        mSubject6EditText = (EditText) findViewById(R.id.et_subject_6);

        mSubject1RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_1);
        mSubject2RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_2);
        mSubject3RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_3);
        mSubject4RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_4);
        mSubject5RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_5);
        mSubject6RadioGroup = (RadioGroup) findViewById(R.id.rg_subject_level_6);

        mAcceptButton = (Button) findViewById(R.id.bt_accept_button);
        mAcceptButton.setOnClickListener(this);


    }

    private ArrayList<IBSubject> getSubjectData(){
        ArrayList<IBSubject> subjectsArray = new ArrayList<IBSubject>();
        for (int i = 0; i < 6; i++){
            LinearLayout content = (LinearLayout) mContentView;
            View rowView = content.getChildAt(i);
            if (rowView instanceof LinearLayout){

                LinearLayout subjectRowView = (LinearLayout) rowView;
                final EditText subjectEditText = (EditText) subjectRowView.getChildAt(0);
                RadioGroup radioGroup = (RadioGroup) subjectRowView.getChildAt(1);
                RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                int group = i+1;
                String subjectName = subjectEditText.getText().toString();
                String level = checkedRadioButton.getText().toString();
                if (subjectName.isEmpty() || subjectName.length() < 2){
                    try {
                        throw new Exception("Subject Name cannot be empty");
                    } catch (Exception e) {
                        Snackbar.make(mContentView, "Subject Name cannot be empty", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    IBSubject newSubject = new IBSubject(group, subjectName, level);
                    subjectsArray.add(newSubject);
                    Snackbar.make(mContentView, "Successfully Gathered Data from UI", Snackbar.LENGTH_LONG).show();
                }

            }
        }

        return subjectsArray;

    }

    @Override
    public void onClick(View v) {


    }
}
