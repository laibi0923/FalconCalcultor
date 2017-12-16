package com.savtor.falconcalcultorPasswordgate;

import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorSetting.Fragment_Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GhostLeo_DT on 9/12/2017.
 * MODE :
 * 1. Create password = 1
 * 2. Clear password = 2
 * 3. Login = 3
 */
public class Fragment_Passwordgate extends Fragment {

    private LinearLayout keybord_num0, keybord_num1, keybord_num2, keybord_num3, keybord_num4, keybord_num5,
            keybord_num6, keybord_num7, keybord_num8, keybord_num9, keybord_done, keybord_clear;

    private TextView keybord_num0_text, keybord_num1_text, keybord_num2_text, keybord_num3_text, keybord_num4_text, keybord_num5_text,
            keybord_num6_text, keybord_num7_text, keybord_num8_text, keybord_num9_text, keybord_done_text, keybord_clear_text,
            password_hint;

    private EditText password_display;

    private String current_input_password = "";

    private int get_password_mode;
    private String Password_create_first = "", Password_create_secord = "";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private String PASSWORD;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        get_sharedpreferences();

        Bundle getBundle = getArguments();

        if (getBundle != null){
            get_password_mode = getBundle.getInt("setup_password_mode");
        }


        if (get_password_mode == 1){
            Log.e("Password Mode", "create");
        }else if(get_password_mode == 2){
            Log.e("Password Mode", "clear");
        }if (get_password_mode == 3){
            Log.e("Password Mode", "login");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.password_gate, container, false);
        Find_view(v);

        return v;
    }

    //=============================================================================================
    // []
    private void Find_view(View v){

        password_display = (EditText) v.findViewById(R.id.password_display);

        password_hint = (TextView) v.findViewById(R.id.password_hint);
        password_hint.setText(getResources().getString(R.string.password_hint_input));

        keybord_num0 = (LinearLayout) v.findViewById(R.id.keybord_num0);
        keybord_num0.setOnClickListener(password_onclick_listener);
        keybord_num0_text = (TextView) keybord_num0.findViewById(R.id.sub_num_text);
        keybord_num0_text.setText("0");

        keybord_num1 = (LinearLayout) v.findViewById(R.id.keybord_num1);
        keybord_num1.setOnClickListener(password_onclick_listener);
        keybord_num1_text = (TextView) keybord_num1.findViewById(R.id.sub_num_text);
        keybord_num1_text.setText("1");

        keybord_num2 = (LinearLayout) v.findViewById(R.id.keybord_num2);
        keybord_num2.setOnClickListener(password_onclick_listener);
        keybord_num2_text = (TextView) keybord_num2.findViewById(R.id.sub_num_text);
        keybord_num2_text.setText("2");

        keybord_num3 = (LinearLayout) v.findViewById(R.id.keybord_num3);
        keybord_num3.setOnClickListener(password_onclick_listener);
        keybord_num3_text = (TextView) keybord_num3.findViewById(R.id.sub_num_text);
        keybord_num3_text.setText("3");

        keybord_num4 = (LinearLayout) v.findViewById(R.id.keybord_num4);
        keybord_num4.setOnClickListener(password_onclick_listener);
        keybord_num4_text = (TextView) keybord_num4.findViewById(R.id.sub_num_text);
        keybord_num4_text.setText("4");

        keybord_num5 = (LinearLayout) v.findViewById(R.id.keybord_num5);
        keybord_num5.setOnClickListener(password_onclick_listener);
        keybord_num5_text = (TextView) keybord_num5.findViewById(R.id.sub_num_text);
        keybord_num5_text.setText("5");

        keybord_num6 = (LinearLayout) v.findViewById(R.id.keybord_num6);
        keybord_num6.setOnClickListener(password_onclick_listener);
        keybord_num6_text = (TextView) keybord_num6.findViewById(R.id.sub_num_text);
        keybord_num6_text.setText("6");

        keybord_num7 = (LinearLayout) v.findViewById(R.id.keybord_num7);
        keybord_num7.setOnClickListener(password_onclick_listener);
        keybord_num7_text = (TextView) keybord_num7.findViewById(R.id.sub_num_text);
        keybord_num7_text.setText("7");

        keybord_num8 = (LinearLayout) v.findViewById(R.id.keybord_num8);
        keybord_num8.setOnClickListener(password_onclick_listener);
        keybord_num8_text = (TextView) keybord_num8.findViewById(R.id.sub_num_text);
        keybord_num8_text.setText("8");


        keybord_num9 = (LinearLayout) v.findViewById(R.id.keybord_num9);
        keybord_num9.setOnClickListener(password_onclick_listener);
        keybord_num9_text = (TextView) keybord_num9.findViewById(R.id.sub_num_text);
        keybord_num9_text.setText("9");

        keybord_clear = (LinearLayout) v.findViewById(R.id.keybord_clear);
        keybord_clear.setOnClickListener(password_onclick_listener);
        keybord_clear_text = (TextView) keybord_clear.findViewById(R.id.sub_num_text);
        keybord_clear_text.setText("Clear");

        keybord_done = (LinearLayout) v.findViewById(R.id.keybord_done);
        keybord_done.setOnClickListener(password_onclick_listener);
        keybord_done_text = (TextView) keybord_done.findViewById(R.id.sub_num_text);
        keybord_done_text.setText("Done");


    }

    //=============================================================================================
    // []
    private View.OnClickListener password_onclick_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.keybord_num0:
                    append_Password("0");
                    break;

                case R.id.keybord_num1:
                    append_Password("1");
                    break;

                case R.id.keybord_num2:
                    append_Password("2");
                    break;

                case R.id.keybord_num3:
                    append_Password("3");
                    break;

                case R.id.keybord_num4:
                    append_Password("4");
                    break;

                case R.id.keybord_num5:
                    append_Password("5");
                    break;

                case R.id.keybord_num6:
                    append_Password("6");
                    break;

                case R.id.keybord_num7:
                    append_Password("7");
                    break;

                case R.id.keybord_num8:
                    append_Password("8");
                    break;

                case R.id.keybord_num9:
                    append_Password("9");
                    break;

                case R.id.keybord_clear:
                    password_display.setText("");
                    current_input_password = "";

                    if (Password_create_first != ""){
                        password_hint.setText(getResources().getString(R.string.password_hint_create));
                    }else {
                        password_hint.setText(getResources().getString(R.string.password_hint_input));
                    }

                    break;

                case R.id.keybord_done:

                    if (get_password_mode == 1){
                        Password_Create_mode();
                    }else if(get_password_mode == 2){
                        Password_Clear_mode();
                    }if (get_password_mode == 3){
                        Login_mode();
                    }

                    break;
            }

        }

    };

    //=============================================================================================
    // []
    public void append_Password(String Append_String){

        if (current_input_password.length() > 10){
            password_hint.setText(getResources().getString(R.string.password_hint_overlength));
        }else {
            password_display.append(Append_String);
            current_input_password = password_display.getText().toString();
        }
    }

    //=============================================================================================
    // [] 新建密碼模式
    private void Password_Create_mode(){

        if (Password_create_first.equals("")){
            Password_create_first = current_input_password;
            current_input_password = "";
            password_display.setText("");
            password_hint.setText(getResources().getString(R.string.password_hint_create));
        }else {

            Password_create_secord = current_input_password;

            if (Password_create_first.equals(Password_create_secord)){

                mEditor = mSharedPreferences.edit();
                mEditor.putInt("Setting_password" ,  2);
                mEditor.putString("PASSWORD", current_input_password);
                mEditor.commit();

                password_hint.setText(getResources().getString(R.string.password_hint_create_success));
                keybord_done.setEnabled(false);
                Fragment_Transaction(new Fragment_Setting());

            }else {
                password_hint.setText(getResources().getString(R.string.password_hint_notcorrect));
            }

        }

    }

    //=============================================================================================
    // [] 清除密碼模式
    private void Password_Clear_mode(){

        if (current_input_password.equals(PASSWORD)){

            mEditor = mSharedPreferences.edit();
            mEditor.putInt("Setting_password" ,  1);
            mEditor.putString("PASSWORD", "");
            mEditor.commit();

            password_hint.setText(getResources().getString(R.string.password_hint_resetfinish));
            keybord_done.setEnabled(false);
            Fragment_Transaction(new Fragment_Setting());

        }else {
            password_hint.setText(getResources().getString(R.string.password_hint_notcorrect));
        }

    }

    //=============================================================================================
    // [] 登入模式
    private void Login_mode(){

        if (current_input_password.equals(PASSWORD)){

            password_hint.setText(getResources().getString(R.string.password_hint_correct));
            keybord_done.setEnabled(false);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent().setClass(getActivity(), Activity_Main.class));
                    getActivity().finish();
                }
            }, 1000);

        }else {
            password_hint.setText(getResources().getString(R.string.password_hint_notcorrect));
        }

    }

    //=============================================================================================
    // [] 取出 SharedPreferences 內 PASSWORD
    private void get_sharedpreferences(){
        PASSWORD = mSharedPreferences.getString("PASSWORD", "");
    }

    //=============================================================================================
    // [] 處理 Keyboard (無用)
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //=============================================================================================
    // []
    public void Fragment_Transaction(final Fragment mFragment){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                mFragmentTransaction.commit();

            }
        }, 1000);

    }

}
