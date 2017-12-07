package com.savtor.falconcalcultor;

import com.savtor.falconcalcultorSetting.*;
import com.savtor.falconcalcultorDebitInfo.*;
import com.savtor.falconcalcultorCalcultor.*;

import android.os.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;
//1

public class MainActivity extends AppCompatActivity 
{
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		init_toolbar();
		init_drawerlayout();
		init_navigationview();

		getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragement_DebitInfo()).commit();
    }


	public void init_drawerlayout(){

		mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
		ActionBarDrawerToggle mdrawertoggle = new ActionBarDrawerToggle(
			this,
			mDrawerLayout,
			mToolbar,
			R.string.drawer_open,
			R.string.drawer_close);

		mDrawerLayout.setDrawerListener(mdrawertoggle);
		mdrawertoggle.syncState();
	}

	public void init_toolbar(){

		mToolbar = (Toolbar) findViewById(R.id.mToolbar);
		setSupportActionBar(mToolbar);
	}

	public void init_navigationview(){

		mNavigationView = (NavigationView) findViewById(R.id.mNavView);

		mNavigationView.getMenu().getItem(0).setChecked(true);


		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				Fragment mFragment ;

				@Override
				public boolean onNavigationItemSelected(MenuItem p1)
				{
					// TODO: Implement this method


					switch (p1.getItemId()){

						case R.id.nav_item1:
							mFragment = new Fragement_DebitInfo();
							break;

						case R.id.nav_item2:
							mFragment = new Fragment_Calcultor();
							break;

						case R.id.nav_item3:
							break;

						case R.id.nav_item4:
							mFragment = new Fragment_Setting();
							break;

						case R.id.nav_item5:
							break;

						case R.id.nav_item6:
							break;
					}

					getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mFragment).commit();
					p1.setChecked(true);
					mDrawerLayout.closeDrawers();

					return true;
				}
			});
	}
}

