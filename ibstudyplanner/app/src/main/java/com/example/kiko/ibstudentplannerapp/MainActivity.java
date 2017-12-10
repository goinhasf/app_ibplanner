package com.example.kiko.ibstudentplannerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;
import com.example.kiko.ibstudentplannerapp.IB.IBUser;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.FakeData;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBDbHelper;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBPlannerContract;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = ".MainActivity";
    private static final int LOADER_ID = 0;
    private static final String RESTART_KEY = "restart_key";

    private Cursor mCursor;
    private Loader<Cursor> mCursorLoader;

    public static final String SUBJECTS_NAME_KEY = "subjects";
    public static final String SUBJECTS_LEVEL_KEY = "levels";

    public static final String username = FakeData.USERNAME;

    public static String[] subjectNameKEY = {
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_NAME_COLUMN
    };

    public static String[] subjectLevelKEY = {
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_1_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_2_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_3_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_4_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_5_LEVEL_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_6_LEVEL_COLUMN
    };

    private static final int IB_SUBJECT_USERNAME_COLUMN_INDEX = 0;

    public static IBUser mUserInstance;
    private FloatingActionButton fab;
    private MenuItem mUserProfileItem;

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private CalendarFragment calendarFragment;
    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseSubjectsActivity.class));
            }
        });

        mCursorLoader = getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        homeFragment = new HomeFragment();
        calendarFragment = new CalendarFragment();
        taskFragment = new TaskFragment();

        switchFragment(homeFragment);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.tab_home) {
                    switchFragment(homeFragment);
                } else if (itemID == R.id.tab_calendar) {
                    switchFragment(calendarFragment);
                } else if (itemID == R.id.tab_task) {
                    switchFragment(taskFragment);

                }
                return true;
            }
        });

        mCursorLoader.forceLoad();

    }

    private boolean isUserFirstTime() {
        if (mUserInstance != null) {
            fab.setVisibility(View.INVISIBLE);
            homeFragment.setVisibleViews(true);
            return false;
        } else {
            fab.setVisibility(View.VISIBLE);
            homeFragment.setVisibleViews(false);
            return true;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mUserProfileItem = menu.findItem(R.id.user_profile);
        if (isUserFirstTime()) {
            mUserProfileItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.user_profile) {
            Intent intent = new Intent(this, ChooseSubjectsActivity.class);
            Bundle bundle = new Bundle();
            String[] subjectNames = new String[6];
            String[] subjectLevels = new String[6];
            int index = 0;
            for (IBSubject subject : mUserInstance.getUserIBSubjects()) {
                subjectNames[index] = subject.getSubjectName();
                subjectLevels[index] = subject.getLevel();
                index++;
            }
            bundle.putStringArray(SUBJECTS_NAME_KEY, subjectNames);
            bundle.putStringArray(SUBJECTS_LEVEL_KEY, subjectLevels);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;

        } else if (id == R.id.refresh_menu_item){
            FakeData.deleteAllData(this);
            mCursorLoader.reset();

            mUserInstance = null;
            isUserFirstTime();
            mUserProfileItem.setVisible(false);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        IBDbHelper helper = new IBDbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        switch (id) {
            case LOADER_ID:
                return new CursorLoader(this, IBPlannerContract.UserIBDataEntry.CONTENT_URI, null,
                        null,
                        null,
                        IBPlannerContract.UserIBDataEntry._ID + " ASC");

            default:
                throw new RuntimeException("Loader Not Implemented: " + LOADER_ID);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if (data.getCount() != 0){

                mCursor = data;
                while (mCursor.moveToNext()) {
                    mUserInstance = new IBUser();
                    mUserInstance.setName(mCursor.getString(1));
                    IBSubject[] subjects = new IBSubject[6];
                    Log.d(TAG, Integer.toString(mCursor.getCount()));
                    for (int i = 0; i < 6; i++) {
                        String subjectName = mCursor.getString(mCursor.getColumnIndex(subjectNameKEY[i]));
                        String level = mCursor.getString(mCursor.getColumnIndex(subjectLevelKEY[i]));
                        subjects[i] = new IBSubject(i, subjectName, level);
                    }
                    mUserInstance.setUserIBSubjects(subjects);
                    // Sends the data over to the recycler view at HomeFragment
                    homeFragment.setRecyclerView(mUserInstance);
                    homeFragment.setVisibleViews(true);
                }
                mCursor.close();
                Log.e(TAG, "User object SUCCESSFULLY initialized.");
            }
        } else {
            isUserFirstTime();
            Log.e(TAG, "User object NOT initialized.");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == mCursorLoader.getId()) {
            mCursor = null;
        }
    }


    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
