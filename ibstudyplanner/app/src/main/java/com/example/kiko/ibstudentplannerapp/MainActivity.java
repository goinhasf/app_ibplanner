package com.example.kiko.ibstudentplannerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
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
import android.widget.Toast;

import com.example.kiko.ibstudentplannerapp.IB.IBSubject;
import com.example.kiko.ibstudentplannerapp.IB.IBUser;
import com.example.kiko.ibstudentplannerapp.IBPlannerUtils.IBPlannerContract;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ".MainActivity";
    private static final int LOADER_ID = 0;
    private Cursor mCursor;

    private static final String[] PROJECTION = {
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NUMBER_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NAME_COLUMN,
            IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_LEVEL_COLUMN
    };

    private static final int IB_SUBJECT_GROUP_NUMBER_COLUMN_INDEX = 0;
    private static final int IB_SUBJECT_GROUP_NAME_COLUMN_INDEX = 1;
    private static final int IB_SUBJECT_GROUP_LEVEL_COLUMN_INDEX = 2;

    private static final int TAB_HOME_POSITION = 0;
    private static final int TAB_CALENDAR_POSITION = 1;
    private static final int TAB_TASK_POSITION = 2;

    private static int mCurrentFragmentPosition = 0;

    private static IBUser mUserInstance;
    private FloatingActionButton fab;
    private MenuItem mUserProfileItem;

    private TabLayout mTabLayout;
    private ViewGroup mViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.bottom_navigation_bar);
        mViewGroup = (ViewGroup) findViewById(R.id.content_layout);

        //getLayoutInflater().inflate(R.layout.home_fragment_layout, mViewGroup, false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_main);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseSubjectsActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

       mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case TAB_HOME_POSITION:
                       getLayoutInflater().inflate(R.layout.home_fragment_layout, mViewGroup, false);
                       Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                       break;

                   case TAB_CALENDAR_POSITION:
                       getLayoutInflater().inflate(R.layout.calendar_fragment_layout, mViewGroup, false);
                       Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
                       break;

                   case TAB_TASK_POSITION:
                       getLayoutInflater().inflate(R.layout.task_fragment_layout, mViewGroup, false);
                       Toast.makeText(getApplicationContext(), "Task", Toast.LENGTH_SHORT).show();
                       break;
               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
           }
       });

    }

    private boolean isUserFirstTime() {
        if (mUserInstance != null) {
            fab.setVisibility(View.INVISIBLE);
            return false;
        } else {
            fab.setVisibility(View.VISIBLE);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id) {
            case LOADER_ID:
                return new CursorLoader(this, IBPlannerContract.UserIBDataEntry.CONTENT_URI, PROJECTION,
                    null,
                    null,
                    IBPlannerContract.UserIBDataEntry.IB_SUBJECT_GROUP_NUMBER_COLUMN + " ASC");

            default:
                throw new RuntimeException("Loader Not Implemented: " + LOADER_ID);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursor = data;
        if (mCursor != null){
            mUserInstance = new IBUser("Name");
            IBSubject[] arrayOfSubjects = new IBSubject[6];
            int arrayIndex = 0;
            while (mCursor.moveToNext() && arrayIndex < 6){
                int group = mCursor.getInt(IB_SUBJECT_GROUP_NUMBER_COLUMN_INDEX);
                String name = mCursor.getString(IB_SUBJECT_GROUP_NAME_COLUMN_INDEX);
                String level = mCursor.getString(IB_SUBJECT_GROUP_LEVEL_COLUMN_INDEX);
                IBSubject subject = new IBSubject(group, name, level);
                arrayOfSubjects[arrayIndex] = subject;
                arrayIndex++;
            }
            mCursor.moveToFirst();
            mUserInstance.setUserIBSubjects(arrayOfSubjects);
            Log.e(TAG, "User object SUCCESSFULLY initialized.");
        } else {
            isUserFirstTime();
            Log.e(TAG, "User object NOT initialized.");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursor = null;
    }

}
