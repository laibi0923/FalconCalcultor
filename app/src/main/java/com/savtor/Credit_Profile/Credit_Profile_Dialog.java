package com.savtor.Credit_Profile;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 5/1/2018.
 */
public class Credit_Profile_Dialog extends AlertDialog {
    // Dialog
    private View Dialog_View;

    private AlertDialog mAlertDialog;

    private LinearLayout  Dialog_Option_1, Dialog_Option_2, Dialog_Option_3, Dialog_Option_4, Dialog_Option_5;

    private TextView Dialog_Title, Dialog_Option_1_Text, Dialog_Option_2_Text, Dialog_Option_3_Text, Dialog_Option_4_Text, Dialog_Option_5_Text, Dialog_Dismiss;

    private ImageView Dialog_Option_1_Icon, Dialog_Option_2_Icon, Dialog_Option_3_Icon, Dialog_Option_4_Icon, Dialog_Option_5_Icon;

    public ImageView Source_Icon;

    public TextView Source_Title;

    private String Title;

    private int Case_id;

    protected Credit_Profile_Dialog(@NonNull Context context, String Title, ImageView Source_Icon, TextView Source_Title, int Case_id) {

        super(context);
        Dialog_View = LayoutInflater.from(context).inflate(R.layout.credit_profile_dialog, null);
        setView(Dialog_View);

        this.Source_Icon = Source_Icon;
        this. Source_Title = Source_Title;
        this.Title = Title;
        this.Case_id = Case_id;

        Find_View();

    }

    private void Init_Dialog_Value(){

        switch (Case_id){

            case R.id.product_type_icon:
                Dialog_Title.setText(Title);

                Dialog_Option_1.setOnClickListener(Item_OnclickListener);
                Dialog_Option_2.setOnClickListener(Item_OnclickListener);
                Dialog_Option_3.setOnClickListener(Item_OnclickListener);
                Dialog_Option_4.setOnClickListener(Item_OnclickListener);
                Dialog_Option_5.setOnClickListener(Item_OnclickListener);

                break;
        }

    }


    private void Find_View(){

        Dialog_Title = (TextView) Dialog_View.findViewById(R.id.dialog_title);

        Dialog_Option_1 = (LinearLayout) Dialog_View.findViewById(R.id.dialog_option1);
        Dialog_Option_1_Icon = (ImageView) Dialog_View.findViewById(R.id.dialog_option1_icon);
        Dialog_Option_1_Text = (TextView) Dialog_View.findViewById(R.id.dialog_option1_text);

        Dialog_Option_2 = (LinearLayout) Dialog_View.findViewById(R.id.dialog_option2);
        Dialog_Option_2_Icon = (ImageView) Dialog_View.findViewById(R.id.dialog_option2_icon);
        Dialog_Option_2_Text = (TextView) Dialog_View.findViewById(R.id.dialog_option2_text);

        Dialog_Option_3 = (LinearLayout) Dialog_View.findViewById(R.id.dialog_option3);
        Dialog_Option_3_Icon = (ImageView) Dialog_View.findViewById(R.id.dialog_option3_icon);
        Dialog_Option_3_Text = (TextView) Dialog_View.findViewById(R.id.dialog_option3_text);

        Dialog_Option_4 = (LinearLayout) Dialog_View.findViewById(R.id.dialog_option4);

        Dialog_Option_4_Icon = (ImageView) Dialog_View.findViewById(R.id.dialog_option4_icon);
        Dialog_Option_4_Text = (TextView) Dialog_View.findViewById(R.id.dialog_option4_text);

        Dialog_Option_5 = (LinearLayout) Dialog_View.findViewById(R.id.dialog_option5);
        Dialog_Option_5_Icon = (ImageView) Dialog_View.findViewById(R.id.dialog_option5_icon);
        Dialog_Option_5_Text = (TextView) Dialog_View.findViewById(R.id.dialog_option5_text);

        Dialog_Dismiss = (TextView) Dialog_View.findViewById(R.id.dialog_dismiss);
        Dialog_Dismiss.setOnClickListener(Item_OnclickListener);

    }

    private View.OnClickListener Item_OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.dialog_option1:
                    Source_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                    Source_Title.setText(getContext().getString(R.string.title_product_personal));
                    dismiss();
                    break;

                case R.id.dialog_option2:
                    Source_Icon.setImageResource(R.drawable.ic_domain_black_24dp);
                    Source_Title.setText(getContext().getString(R.string.title_product_mortgage));
                    dismiss();
                    break;

                case R.id.dialog_option3:
                    Source_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                    Source_Title.setText(getContext().getString(R.string.title_product_personal));
                    dismiss();
                    break;

                case R.id.dialog_option4:
                    Source_Icon.setImageResource(R.drawable.ic_directions_car_black_24dp);
                    Source_Title.setText(getContext().getString(R.string.title_product_car));
                    dismiss();
                    break;

                case R.id.dialog_option5:
                    Source_Icon.setImageResource(R.drawable.ic_credit_card_black_24dp);
                    Source_Title.setText(getContext().getString(R.string.title_product_card));
                    dismiss();
                    break;

                case R.id.dialog_dismiss:
                    dismiss();
                    break;
            }
        }
    };


}
