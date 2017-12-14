package com.savtor.falconcalcultor;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultorCalcultor.*;
import com.savtor.falconcalcultorDebitInfo.*;
import com.savtor.falconcalcultorPasswordgate.Fragment_Passwordgate;
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

        // 修改 statusbar, navigationbar 顏色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.StatusBarColor));
            window.setNavigationBarColor(getResources().getColor(R.color.NavigationBarColor));
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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

				
				if(!item.isChecked()){
					switch (item.getItemId()){

						case R.id.nav_item1:
							Fragment_Transaction(new Fragment1());
							break;

						case R.id.nav_item2:
							Fragment_Transaction(new Fragment_Calcultor());
							break;

						case R.id.nav_item3:
							Fragment_Transaction(new Fragment_Favourite());
							break;

						case R.id.nav_item4:
							Fragment_Transaction(new Fragment1());
							break;

						case R.id.nav_item5:
							Fragment_Transaction(new Fragment_Setting());
							break;

						case R.id.nav_item6:
							Fragment_Transaction(new Fragment_DebitInfo());
							break;
					}
					
					item.setChecked(true);
				}
                
				mDrawerLayout.closeDrawers();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment1()).commit();
        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    public void Fragment_Transaction(final Fragment mFragment){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

				FragmentManager mFragmentManager = getSupportFragmentManager();
				FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

				mFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
				mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
				mFragmentTransaction.commit();
				
            }
        },800);

    }

}
