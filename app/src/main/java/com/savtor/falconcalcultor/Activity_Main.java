package com.savtor.falconcalcultor;

import android.os.Build;
import android.os.Bundle;
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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.savtor.falconcalcultorCalcultor.Fragment_Calcultor;
import com.savtor.falconcalcultorDebitInfo.Fragment_DebitInfo;
import com.savtor.falconcalcultorFavoutive.Fragment_Favourite;
import com.savtor.falconcalcultorSetting.Fragment_Setting;
import android.widget.*;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class Activity_Main extends AppCompatActivity {

	
//    @BindView(R.id.mToolbar)Toolbar mToolbar;
//    @BindView(R.id.mFrameLayout)FrameLayout mFrameLayout;
//    @BindView(R.id.mNavView)NavigationView mNavView;
//    @BindView(R.id.mDrawerLayout)DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    public NavigationView mNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

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


    final void init_toolbar() {
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
		TextView mToolbar_title = (TextView) mToolbar.findViewById(R.id.toolbar_TextView);
		mToolbar_title.setText(getResources().getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_HOME_AS_UP);
    }

    final void init_drawerlayout() {
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


    final void init_navigationview() {

        mNavView = (NavigationView) findViewById(R.id.mNavView);

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (!item.isChecked()) {
                    switch (item.getItemId()) {

						/*
                        case R.id.nav_item1:
                            Fragment_Transaction(new Fragment1());
                            break;*/

                        case R.id.nav_item2:
                            Fragment_Transaction(new Fragment_Calcultor());
                            break;

                        case R.id.nav_item3:
                            Fragment_Transaction(new Fragment_Favourite());
                            break;

                        /*case R.id.nav_item4:
                            Fragment_Transaction(new Fragment1());
                            break;*/

                        case R.id.nav_item5:
                            Fragment_Transaction(new Fragment_Setting());
                            break;

                        /*case R.id.nav_item6:
                            Fragment_Transaction(new Fragment_DebitInfo());
                            break;*/
                    }

                    item.setChecked(true);
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Calcultor()).commit();
        mNavView.getMenu().getItem(0).setChecked(true);
    }

    public void Fragment_Transaction(final Fragment mFragment) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FragmentManager mFragmentManager = getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

                mFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                mFragmentTransaction.commit();

            }
        }, 800);

    }

}
