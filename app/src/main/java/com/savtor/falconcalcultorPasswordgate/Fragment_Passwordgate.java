package com.savtor.falconcalcultorPasswordgate;

import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorSetting.Fragment_Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 */
public class Fragment_Passwordgate extends Fragment {

    private EditText password_edittext;
    private LinearLayout password_clear_btn, password_confirm_btn;
    private TextView password_confirm_text;

    private int get_password_mode;
    private String pw_1st = "", pw_2nd = "";

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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.password_gate, container, false);
        Find_view(v);

        return v;
    }

    private void Find_view(View v){

        // 輸入框
        password_edittext = (EditText) v.findViewById(R.id.password_edittext);
        password_edittext.setHint("輸入密碼");
        password_edittext.requestFocus();
        password_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });


 

    }

    //=============================================================================================
    // [] 新建密碼模式
    private void Password_Save_mode(){

        if (pw_1st.equals("")){
            pw_1st = password_edittext.getText().toString();
            password_edittext.setText("");
            password_edittext.setHint("請再次輸入密碼");
        }else {
            pw_2nd = password_edittext.getText().toString();

            if (pw_1st.equals(pw_2nd)){

                mEditor = mSharedPreferences.edit();
                mEditor.putInt("Setting_password" ,  2);
                mEditor.putString("PASSWORD", pw_2nd);
                mEditor.commit();

                Toast.makeText(getContext(), "密碼設置成功", Toast.LENGTH_LONG).show();

                getFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Setting()).commit();

            }else {
                Toast.makeText(getContext(), "密碼設置不成功", Toast.LENGTH_LONG).show();
            }

        }

    }

    //=============================================================================================
    // [] 清除密碼模式
    private void Password_Clear_mode(){

        if (password_edittext.getText().toString().equals(PASSWORD)){

            mEditor = mSharedPreferences.edit();
            mEditor.putInt("Setting_password" ,  1);
            mEditor.putString("PASSWORD", "");
            mEditor.commit();
            Toast.makeText(getContext(), "輸入密碼正確", Toast.LENGTH_LONG).show();
            getFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Setting()).commit();
        }else {
            Toast.makeText(getContext(), "輸入密碼不正確", Toast.LENGTH_LONG).show();
            getFragmentManager().beginTransaction().replace(R.id.mFrameLayout, new Fragment_Setting()).commit();
        }

    }

    //=============================================================================================
    // [] 登入模式
    private void Login_mode(){

        if (password_edittext.getText().toString().equals(PASSWORD)){

            startActivity(new Intent().setClass(this.getActivity(), Activity_Main.class));
            this.getActivity().finish();
        }else {

        }

    }

    //=============================================================================================
    // [] 取出 SharedPreferences 內 PASSWORD
    private void get_sharedpreferences(){
        PASSWORD = mSharedPreferences.getString("PASSWORD", "");
    }

    //=============================================================================================
    // [] 處理 Keyboard
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
