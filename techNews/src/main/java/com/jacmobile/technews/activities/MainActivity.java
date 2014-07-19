package com.jacmobile.technews.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.jacmobile.technews.R;
import com.jacmobile.technews.fragments.Home;
import com.jacmobile.technews.fragments.ReadFragment;

import java.util.ArrayList;
import java.util.List;

import com.jacmobile.technews.util.interaction.DrawerAdapter;
import com.jacmobile.technews.util.interaction.DrawerItem;
import com.jacmobile.technews.util.listeners.NewsItemSelectedListener;
import com.jacmobile.technews.util.networking.RssUrls;


public class MainActivity extends Activity implements NewsItemSelectedListener
{
    private int fragmentIndex;
    //	Navigation Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private DrawerAdapter adapter;
    private List<DrawerItem> mDrawerItems;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getFragmentManager();
        setNavDrawer(savedInstanceState);
    }

    private void setNavDrawer(Bundle savedInstanceState)
    {
        mDrawerItems = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setBackgroundResource(R.drawable.black_gradient);

        mDrawerItems.add(new DrawerItem(getString(R.string.drawer_news)));
        mDrawerItems.add(new DrawerItem(getString(R.string.drawer_latest), android.R.drawable.ic_menu_today));
        mDrawerItems.add(new DrawerItem(getString(R.string.drawer_options)));
        mDrawerItems.add(new DrawerItem(getString(R.string.drawer_settings), android.R.drawable.ic_menu_preferences));
        mDrawerItems.add(new DrawerItem(getString(R.string.drawer_help), android.R.drawable.ic_menu_help));

        adapter = new DrawerAdapter(this, R.layout.custom_drawer_item, mDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
                getActionBar().setTitle(mTitle);
                // call onPrepareOptionsMenu()
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)
            {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            if (mDrawerItems.get(0).isSpinner() & mDrawerItems.get(1).getTitle() != null) {
                SelectItem(2);
            } else if (mDrawerItems.get(0).getTitle() != null) {
                SelectItem(1);
            } else {
                SelectItem(0);
            }
        }
    }

    private MenuItem mSpinnerItem1 = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.dashboard, menu);
        mSpinnerItem1 = menu.findItem(R.id.action_feeds);
        View view1 = mSpinnerItem1.getActionView();

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(
                getApplicationContext(), R.array.action_list, R.layout.feed_spinner_item);
        if (view1 instanceof Spinner) {
            final Spinner spinner = (Spinner) view1;
            spinner.setAdapter(mSpinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    Home fragment = null;
                    switch (position) {
                        case 0:
                            fragment = Home.getInstance(RssUrls.NY_TIMES_TECH_RSS);
                            break;
                        case 1:
                            fragment = Home.getInstance(RssUrls.TECH_CRUNCH_ALL_RSS);
                            break;
                        case 2:
                            fragment = Home.getInstance(RssUrls.TECH_DIRT_RSS);
                            break;
                        case 3:
                            fragment = Home.getInstance(RssUrls.HACKER_NEWS_RSS);
                            break;
                        case 4:
                            fragment = Home.getInstance(RssUrls.WIRED_RSS);
                            break;
                        case 5:
                            fragment = Home.getInstance(RssUrls.HACK_A_DAY_RSS);
                            break;

                    }

                    if (fragment != null) {
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0)
                {
                }
            });

        }

        return true;
    }

    private Integer[] navTitles = {R.string.drawer_home, R.string.drawer_settings, R.string.drawer_help};
    private Integer[] navImages = {android.R.drawable.ic_menu_today, android.R.drawable.ic_menu_preferences, android.R.drawable.ic_menu_help};

    public void SelectItem(int position)
    {
        Home fragment = null;
        switch (position) {
            case 1:
                fragmentIndex = position;
                fragment = Home.getInstance(RssUrls.TECH_DIRT_RSS);
                break;
            case 3:
                fragmentIndex = position;
                Toast.makeText(this, "functionality unimplented", Toast.LENGTH_SHORT).show();
//                fragment = new Home();
                break;
            case 4:
                fragmentIndex = position;
                Toast.makeText(this, "functionality unimplented", Toast.LENGTH_SHORT).show();
//                fragment = new Home();
                break;

        }

        if (fragment != null) {
            if (fragmentIndex != 1) {
                mFragmentManager.beginTransaction().addToBackStack(fragment.getTag())
                        .replace(R.id.content_frame, fragment)
                        .commit();
            } else {
                mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(mDrawerItems.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onNewsItemClicked(String url)
    {
        ReadFragment readFragment = ReadFragment.getInstance(url);
        mFragmentManager.beginTransaction().addToBackStack(readFragment.getTag())
                .replace(R.id.content_frame, readFragment)
                .commit();

    }

    @Override
    public void setTitle(CharSequence title)
    {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
//		Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
//		Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
//		The action bar home/up action should open or close the drawer.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            if (mDrawerItems.get(position).getTitle() == null) {
                SelectItem(position);
            }
        }
    }
}
