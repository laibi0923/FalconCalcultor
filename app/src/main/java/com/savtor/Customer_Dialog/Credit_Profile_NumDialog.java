package com.savtor.Customer_Dialog;
import com.savtor.Credit_Profile.Credit_Profile_Main;
import com.savtor.falconcalcultor.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *      Created by GhostLeo_DT on 3/2/2018.
 *      Full Screen Dialog :   http://blog.csdn.net/small_lee/article/details/72780179
 */
public class Credit_Profile_NumDialog extends DialogFragment {

    private View setDialogView;

    private TextView Dialog_Title, Message_Display;

    private EditText Number_Display;

    private LinearLayout Backpop_Linear, Del_Linear;

    private Button Btn_1, Btn_2, Btn_3, Btn_4, Btn_5, Btn_6, Btn_7, Btn_8, Btn_9, Btn_10, Btn_11, Btn_12;

    private Button Post_Btn;

    private String Dialog_Title_Text, Tag;

    private double Max_Value;

    public static final String RESPONSE = "response";

    public Credit_Profile_NumDialog(String Dialog_Title_Text, String Tag, double Max_Value){
        this.Dialog_Title_Text = Dialog_Title_Text;
        this.Tag = Tag;
        this.Max_Value = Max_Value;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //	設定 Dialog Style
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.creditprofile_dialog_fornuminput, container, false);

        Find_Dialog_View(view);

        return view;
    }


/*================================================================================================
    *      Find Dialog View
 ================================================================================================ */
    private void Find_Dialog_View(View dialog_view){

        Dialog_Title = (TextView) dialog_view.findViewById(R.id.number_dialog_title);
        Dialog_Title.setText(Dialog_Title_Text);

        Number_Display = (EditText) dialog_view.findViewById(R.id.number_dissplay);
        Number_Display.setOnClickListener(View_OnClickListener);

        Backpop_Linear = (LinearLayout) dialog_view.findViewById(R.id.backpop_linear);
        Backpop_Linear.setOnClickListener(View_OnClickListener);

        Message_Display = (TextView) dialog_view.findViewById(R.id.message_display);

        Del_Linear = (LinearLayout) dialog_view.findViewById(R.id.del_linear);
        Del_Linear.setOnClickListener(View_OnClickListener);

        Btn_1 = (Button) dialog_view.findViewById(R.id.button1);
        Btn_1.setOnClickListener(Button_OnClickListener);
        Btn_2 = (Button) dialog_view.findViewById(R.id.button2);
        Btn_2.setOnClickListener(Button_OnClickListener);
        Btn_3 = (Button) dialog_view.findViewById(R.id.button3);
        Btn_3.setOnClickListener(Button_OnClickListener);

        Btn_4 = (Button) dialog_view.findViewById(R.id.button4);
        Btn_4.setOnClickListener(Button_OnClickListener);
        Btn_5 = (Button) dialog_view.findViewById(R.id.button5);
        Btn_5.setOnClickListener(Button_OnClickListener);
        Btn_6 = (Button) dialog_view.findViewById(R.id.button6);
        Btn_6.setOnClickListener(Button_OnClickListener);

        Btn_7 = (Button) dialog_view.findViewById(R.id.button7);
        Btn_7.setOnClickListener(Button_OnClickListener);
        Btn_8 = (Button) dialog_view.findViewById(R.id.button8);
        Btn_8.setOnClickListener(Button_OnClickListener);
        Btn_9 = (Button) dialog_view.findViewById(R.id.button9);
        Btn_9.setOnClickListener(Button_OnClickListener);

        Btn_10 = (Button) dialog_view.findViewById(R.id.button10);
        Btn_10.setOnClickListener(Button_OnClickListener);
        Btn_11 = (Button) dialog_view.findViewById(R.id.button11);
        Btn_11.setOnClickListener(Button_OnClickListener);
        Btn_12 = (Button) dialog_view.findViewById(R.id.button12);
        if (Tag != "PRODUCT_LOAN_TREMS"){
            Btn_12.setOnClickListener(Button_OnClickListener);
        }

        Post_Btn = (Button) dialog_view.findViewById(R.id.output_value_btn);
        Post_Btn.setOnClickListener(Button_OnClickListener);
    }


/*================================================================================================
*      Setup View Onclick Listener
================================================================================================ */
    protected View.OnClickListener View_OnClickListener = new View.OnClickListener(){
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.backpop_linear:
                Number_Display.setText("");
                dismiss();
                break;

            case R.id.del_linear:

                int keyCode = KeyEvent.KEYCODE_DEL;
                KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                Number_Display.dispatchKeyEvent(keyEvent);

                break;
        }

    }
};

/*================================================================================================
    *      Return Result to source
 ================================================================================================ */
    public String get_Result(){
        return Number_Display.getText().toString();
    }

/*================================================================================================
    *      Setup Button Onclick Listener
    *      Number 1-9 & Point Button
 ================================================================================================ */
    private Button.OnClickListener Button_OnClickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case R.id.button1:
                    Number_Display.append("1");
                    break;

                case R.id.button2:
                    Number_Display.append("2");
                    break;

                case R.id.button3:
                    Number_Display.append("3");
                    break;

                case R.id.button4:
                    Number_Display.append("4");
                    break;

                case R.id.button5:
                    Number_Display.append("5");
                    break;

                case R.id.button6:
                    Number_Display.append("6");
                    break;

                case R.id.button7:
                    Number_Display.append("7");
                    break;

                case R.id.button8:
                    Number_Display.append("8");
                    break;

                case R.id.button9:
                    Number_Display.append("9");
                    break;

                case R.id.button10:
                    break;

                case R.id.button11:
                    Number_Display.append("0");
                    break;

                case R.id.button12:
                    Number_Display.append(".");
                    break;

                case R.id.output_value_btn:

                    if (getTargetFragment() == null){
                        return;
                    }else{

                        double Temp_Value = 0;
                        if (!Number_Display.getText().toString().isEmpty() && Number_Display.getText().toString() != null){
                            Temp_Value = Double.valueOf(Number_Display.getText().toString());
                        }

                        Intent mIntent = new Intent();
                        mIntent.putExtra(RESPONSE, Temp_Value);
                        getTargetFragment().onActivityResult(Credit_Profile_Main.DIALOG_REQUEST_CODE, Activity.RESULT_OK, mIntent);
                        dismiss();
                    }
                    break;

            }

        }
    };

}
