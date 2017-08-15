package com.example.kiko.ibstudentplannerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

import javax.security.auth.Subject;

public class ChooseSubjectsActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivityLayout = (LinearLayout) findViewById(R.id.activity_chooser_layout);
        createSubjectChooser(mActivityLayout);

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

    private void createSubjectChooser(ViewGroup viewGroup) {

        for (int i = 0; i < 6; i++){
            int subjectGroupNumber = i+1;
            View view = (LinearLayout) getLayoutInflater().inflate(R.layout.subject_row_chooser, viewGroup, true);
            LinearLayout viewLayout = (LinearLayout) view;
            if (((LinearLayout) view).getChildAt(i) instanceof LinearLayout) {

                LinearLayout layout = (LinearLayout) viewLayout.getChildAt(i);
                EditText editText = (EditText) layout.getChildAt(0);
                editText.setHint("Group " + subjectGroupNumber + " Subject");
            }

        }

        Button button = new Button(this);
        button.setText("Accept");
        button.setOnClickListener(this);
        viewGroup.addView(button);
    }

    private IBSubject[] getSubjectData(){
        IBSubject[] subjectsArray = new IBSubject[6];

        for (int i = 0; i < 6; i++){
            View view = mActivityLayout.getChildAt(i);
            if (view instanceof LinearLayout){

                LinearLayout subjectRow = (LinearLayout) view;
                EditText subjectInput = (EditText) subjectRow.getChildAt(0);
                RadioGroup subjectLevelRadioGroup = (RadioGroup) subjectRow.getChildAt(1);
                RadioButton checkedLevelButton = (RadioButton) findViewById(subjectLevelRadioGroup.getCheckedRadioButtonId());

                String subjectName = subjectInput.getText().toString();
                String level = checkedLevelButton.getText().toString();
                int group = i+1;

                IBSubject subject = new IBSubject(group, subjectName, level);
                subjectsArray[i] = subject;

            }
        }
        Log.d("Getting Data from UI", "Successfully Gathered Data from UI");
        return subjectsArray;

    }

    @Override
    public void onClick(View v) {
        getSubjectData();
    }
}
