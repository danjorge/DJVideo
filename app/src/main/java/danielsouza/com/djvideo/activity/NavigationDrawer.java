package danielsouza.com.djvideo.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;

import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.adapter.ListViewSideMenuAdapter;

/**
 * Created by daniel.souza on 06/02/2017.
 */

public class NavigationDrawer extends AppCompatActivity {

    NavigationView navView;
    DrawerLayout drawerLayout;
    ListView listView;
    ListViewSideMenuAdapter adapter;
    Toolbar toolbar;
    protected FrameLayout frameLayout;
    ActionBarDrawerToggle mDrawerToggle;
    MaterialSpinner spinner;

    protected void onCreateDrawer() {

        listView = (ListView) findViewById(R.id.listViewDirectoryId);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        spinner = (MaterialSpinner) findViewById(R.id.spinner);

    }

    void toggleMenu(){
        if(drawerLayout.isDrawerOpen(navView)){
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(navView);
        }
    }
}
