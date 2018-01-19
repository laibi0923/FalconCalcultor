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

import com.savtor.Alarm_Notification.Testing_Notification;
import com.savtor.Credit_Profile.Product_Type;
import com.savtor.Debit_Info.DebitInfo_Main;
import com.savtor.Falcon_Calcultor.Calcultor_Main;
import com.savtor.Credit_Profile.Credit_Profile_Main;
import com.savtor.Wish_List.WishList_Main;
import com.savtor.Setting.Fragment_Setting;
import android.widget.*;
import android.view.*;
import android.view.inputmethod.*;

/**
 * Created by GhostLeo_DT on 21/11/2017.
 */


public class Activity_Main extends AppCompatActivity {


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    public NavigationView mNavView;

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
            window.setStatusBarColor(getResources().getColor(R.color.blue_grey_900));
//            window.setNavigationBarColor(getResources().getColor(R.color.blue_grey_900));
        }

    }

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		 InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		// TODO: Implement this method
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null){
				getCurrentFocus().clearFocus();
				imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		return super.onTouchEvent(event);
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


//                        case R.id.nav_item1:
//                            Fragment_Transaction(new Fragment1());
//                            break;

                        case R.id.nav_item2:
                            Fragment_Transaction(new Calcultor_Main());
                            break;

                        case R.id.nav_item3:
                            Fragment_Transaction(new Credit_Profile_Main());
                            break;

                        case R.id.nav_item4:
                            Fragment_Transaction(new WishList_Main());
                            break;

                        case R.id.nav_item5:
                            Fragment_Transaction(new Fragment_Setting());
                            break;

                        case R.id.nav_item6:
                            Fragment_Transaction(new Product_Type());
                            break;

                        case R.id.nav_item7:
                            Fragment_Transaction(new Testing_Notification());
                            break;
                    }

                    item.setChecked(true);
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Calcultor_Main()).commit();
        mNavView.getMenu().getItem(0).setChecked(true);
    }

    public void Fragment_Transaction(final Fragment mFragment) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FragmentManager mFragmentManager = getSupportFragmentManager();
                mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.setCustomAnimations(R.anim.fade_in, 0);
                mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                mFragmentTransaction.commit();

            }
        }, 0);

    }


}
