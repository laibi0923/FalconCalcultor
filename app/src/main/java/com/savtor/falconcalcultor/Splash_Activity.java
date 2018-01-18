package com.savtor.falconcalcultor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.savtor.Password_Gate.Passwordgate_Main;


/**
 * Created by GhostLeo_DT on 22/11/2017.
 */

public class Splash_Activity extends AppCompatActivity {

    private String PASSWORD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        // 修改 statusbar, navigationbar 顏色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.blue_grey_900));
            window.setNavigationBarColor(getResources().getColor(R.color.blue_grey_900));
        }


        SharedPreferences mSharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        PASSWORD = mSharedPreferences.getString("PASSWORD", "");

        getSupportFragmentManager().beginTransaction().replace(R.id.splash_frame, new Splash_Fragment()).commit();


        if(PASSWORD.equals("")){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent().setClass(Splash_Activity.this, Activity_Main.class));
                    finish();
                }
            },3000);

        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment mFragment = new Passwordgate_Main();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("setup_password_mode" , 3);
                    mFragment.setArguments(mBundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.splash_frame, mFragment).commit();
                }
            },3000);

        }




    }
}

