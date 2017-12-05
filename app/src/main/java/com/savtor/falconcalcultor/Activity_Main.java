package com.savtor.falconcalcultor;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.savtor.falconcalcultorCalcultor.*;
import com.savtor.falconcalcultorDebitInfo.*;
import com.savtor.falconcalcultorSetting.*;
import com.savtor.falconcalcultorFavoutive.*;
//1
public class Activity_Main extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_toolbar();

        init_drawerlayout();

        init_navigationview();

    }

    final void init_toolbar(){
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
    }

    final void init_drawerlayout(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }



    final void init_navigationview(){
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.mNavView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_item1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fargement_DebitInfo()).commit();
                        break;

                    case R.id.nav_item2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Calcultor()).commit();
                        break;

                    case R.id.nav_item3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Favourite()).commit();
                        break;

                    case R.id.nav_item4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Setting()).commit();
                        break;

                    case R.id.nav_item5:
                        break;

                    case R.id.nav_item6:
                        break;
                }

                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mNavigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment1()).commit();
    }

}
