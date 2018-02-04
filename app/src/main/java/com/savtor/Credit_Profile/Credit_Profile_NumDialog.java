package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by GhostLeo_DT on 3/2/2018.
 *      Full Screen Dialog :   http://blog.csdn.net/small_lee/article/details/72780179
 */
public class Credit_Profile_NumDialog extends Dialog {

    private View setDialogView;

    private TextView Dialog_Title, Message_Display, Source_View;

    private TextView Number_Display;

    private LinearLayout Backpop_Linear, Del_Linear, Done_Linear;

    private Button Btn_1, Btn_2, Btn_3, Btn_4, Btn_5, Btn_6, Btn_7, Btn_8, Btn_9, Btn_10, Btn_11, Btn_12;

    private String Title;

    private double Max_Amount = 10000000;

    public static final String TAG = "FullScreenDialog";

    protected Credit_Profile_NumDialog (Context context, String Title, TextView Source_View){

        super(context, R.style.FullScreenDialogStyle);

        setDialogView = LayoutInflater.from(context).inflate(R.layout.zzzz_number_dialog, null);

        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setContentView(setDialogView);

        Find_View(setDialogView);

        this.Source_View = Source_View;

        this.Title = Title;
    }


/*================================================================================================
    *      Find Dialog View
 ================================================================================================ */
    private void Find_View(View dialog_view){

        Dialog_Title = (TextView) dialog_view.findViewById(R.id.dialog_title);
        Dialog_Title.setText("1234");

        Number_Display = (TextView) dialog_view.findViewById(R.id.number_dissplay);
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
        Btn_12.setOnClickListener(Button_OnClickListener);

        Done_Linear = (LinearLayout) dialog_view.findViewById(R.id.done_linear);
    }

/*================================================================================================
*      Setup View Onclick Listener
================================================================================================ */
    protected View.OnClickListener View_OnClickListener = new View.OnClickListener(){
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.backpop_linear:
                dismiss();
                break;

            case R.id.del_btnn:
                break;

            case R.id.done_linear:

                if (Double.parseDouble(Number_Display.getText().toString()) > Max_Amount){
                    Message_Display.setText("輸入不正確");
                }else {
                    Source_View.setText(Number_Display.getText().toString());
                    dismiss();
                }

                break;
        }

    }
};

/*================================================================================================
    *      Setup Button Onclick Listener
    *      Number 1-9 & Point Button
 ================================================================================================ */
    protected Button.OnClickListener Button_OnClickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case R.id.button1:
                    break;

                case R.id.button2:
                    break;

                case R.id.button3:
                    break;

                case R.id.button4:
                    break;

                case R.id.button5:
                    break;

                case R.id.button6:
                    break;

                case R.id.button7:
                    break;

                case R.id.button8:
                    break;

                case R.id.button9:
                    break;

                case R.id.button10:
                    break;

                case R.id.button11:
                    break;

                case R.id.button12:
                    break;

            }

        }
    };

}
