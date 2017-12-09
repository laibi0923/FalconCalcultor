package com.savtor.falconcalcultorPasswordgate;

import com.savtor.falconcalcultor.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by GhostLeo_DT on 9/12/2017.
 */
public class Fragment_Passwordgate extends Fragment {

    private EditText password_edittext;
    private LinearLayout password_cancel_btn, password_confirm_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.password_gate, container, false);
        Find_view(v);

        return v;
    }

    private void Find_view(View v){

        password_edittext = (EditText) v.findViewById(R.id.password_edittext);
        password_edittext.requestFocus();
        password_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        password_cancel_btn = (LinearLayout) v.findViewById(R.id.password_clear_btn);
        password_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_edittext.setText("");
            }
        });

        password_confirm_btn = (LinearLayout) v.findViewById(R.id.password_confirm_btn);
        password_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //=============================================================================================
    // [7] 處理 Keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
