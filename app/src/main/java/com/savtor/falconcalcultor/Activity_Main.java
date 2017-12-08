package com.savtor.falconcalcultor;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultorCalcultor.*;
import com.savtor.falconcalcultorDebitInfo.*;
import com.savtor.falconcalcultorSetting.*;
import com.savtor.falconcalcultorFavoutive.*;

public class Activity_Main extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private Favourite_DataBasic favourite_dataBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_toolbar();

        init_drawerlayout();

        init_navigationview();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (favourite_dataBasic != null){
//            Log.e("DATA BASIC ACTION : ","數據庫關閉, 共" + favourite_dataBasic.getCount() + "條紀錄");  // [Log.e]
//            favourite_dataBasic.close();
//        }
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

                Fragment mFragment = null;

                switch (item.getItemId()){

                    case R.id.nav_item1:
                        mFragment = new Fragement_DebitInfo();
                        break;

                    case R.id.nav_item2:
                        mFragment = new Fragment_Calcultor();
                        break;

                    case R.id.nav_item3:
                        mFragment = new Fragment_Favourite();
                        break;

                    case R.id.nav_item4:
                        mFragment = new Fragment_Setting();
                        break;

                    case R.id.nav_item5:
                        break;

                    case R.id.nav_item6:
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout,mFragment).commit();

                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mNavigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment1()).commit();
    }

}
