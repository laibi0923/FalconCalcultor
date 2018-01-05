package com.savtor.falconcalcultorCalcultor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savtor.falconcalcultor.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.view.View.*;
import android.app.*;



/**
 * Created by GhostLeo_DT on 4/1/2018.
 */
public class Fragment_Credit_Profile extends Fragment {

    // Fragment View
    private EditText Product_Name, Product_Loan_Number, Address, Phone, Remarks;

    private TextView Product_Type_Name, Product_Status_Title, Product_Status_Result,
                     Loan_Amount_Title, Loan_Rate_Title, Loan_Trems_Title, Loan_Installment_Title, Loan_Amount_Result, Loan_Rate_Result, Loan_Trems_Result, Loan_Installment_Result,
                     First_Due_Title, First_Due_Result, Final_Due_Title, Final_Due_Result, Setup_Alarm_Title, Setup_Alarm_Result, Alarm_Time_Title, Alarm_Time_Result;

    private ImageView Product_Type_Icon, Product_Status_Icon, Product_Loan_Number_Icon,
                      Loan_Amount_Icon, Loan_Rate_Icon, Loan_Trems_Icon, Loan_Installment_Icon,
                      First_Due_Icon, Setup_Alarm_Icon,
                      Address_Icon, Phone_Icon, Remarks_Icon;

    private View Subview_Product_Status, Subview_Loan_Number, Subview_Loan_Amount, Subview_Loan_Rate, Subview_Loan_Trems, Subview_Loan_Installment,
                 Subview_First_due, Subview_Final_due, Subview_Setup_Alarm, Subview_Alarm_Time, Subview_Address, Subview_Phone, Subview_Remarks;

    private LinearLayout Due_Date_Linear, Product_Status_Linear, Loan_Amount_Linear, Loan_Rate_Linear, Loan_Trems_Linear, Loan_Installment_Linear, FirstDue_Linear, Setup_Alarm_Linear, Alarm_Time_Linear;

    // Dialog
    private View Dialog_View;

    private AlertDialog mAlertDialog;

    private LinearLayout Dialog_Option_1, Dialog_Option_2, Dialog_Option_3, Dialog_Option_4, Dialog_Option_5;

    private TextView Dialog_Title, Dialog_Option_1_Text, Dialog_Option_2_Text, Dialog_Option_3_Text, Dialog_Option_4_Text, Dialog_Option_5_Text, Dialog_Dismiss;

    private ImageView Dialog_Option_1_Icon, Dialog_Option_2_Icon, Dialog_Option_3_Icon, Dialog_Option_4_Icon, Dialog_Option_5_Icon;
	
	// Init String
    private String Init_Product_Type, Init_Product_Status, Init_Loan_Number,
                   Init_Loan_Amount, Init_Loan_Rate, Init_Loan_Trems, Init_Loan_Installment,
                   Init_First_Due, Init_Final_Due, Init_Setup_Alarm, Init_Alarm_Time,
                   Init_Address, Init_Phone, Init_Remarks;

    private int Init_Product_Icon;

    // Calendar
    private Calendar First_Due_Calendar, Final_Due_Calendar, Times_Calendar;

    // DataBase Update / Intert
    private String PRODUCT_CODE, STATUS_CODE;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.saver_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.credit_profile, container, false);

        First_Due_Calendar = Calendar.getInstance();

        Init_Product_Type = getString(R.string.title_product_personal);
        Init_Product_Icon = R.drawable.ic_person_black_24dp;
        Init_Product_Status = getString(R.string.product_status_notapply);
        Init_Loan_Number = "";
        Init_Loan_Amount = "0";
        Init_Loan_Rate = "0";
        Init_Loan_Trems = "0";
        Init_Loan_Installment = "0";
        Init_First_Due = new SimpleDateFormat("yyy/MM/dd").format(new Date());
        Init_Final_Due = "";
        Init_Setup_Alarm = "";
        Init_Alarm_Time = "";
        Init_Address = "";
        Init_Phone = "";
        Init_Remarks = "";

        Find_View(v);

//        默認隱藏
//        Subview_Loan_Number.setVisibility(View.GONE);
//        Subview_Setup_Alarm.setVisibility(View.GONE);
//        Subview_Alarm_Time.setVisibility(View.GONE);
//        Due_Date_Linear.setVisibility(View.GONE);

        return v;
    }


    //=============================================================================================
    // [1] 加入畫面內容
    private void Find_View (View v){

        Product_Name = (EditText) v.findViewById(R.id.product_name);
        Product_Name.setHint(R.string.hints_product_name);

        Product_Type_Icon = (ImageView) v.findViewById(R.id.product_type_icon);
        Product_Type_Icon.setImageResource(Init_Product_Icon);
		Product_Type_Icon.setOnClickListener(Item_OnClickListener);

        Product_Type_Name = (TextView) v.findViewById(R.id.product_type_name);
        Product_Type_Name.setText(Init_Product_Type);

        Subview_Product_Status = v.findViewById(R.id.product_status);
        Product_Status_Linear = (LinearLayout) v.findViewById(R.id.product_status_linear);
        Product_Status_Linear.setOnClickListener(Item_OnClickListener);
        Product_Status_Icon = (ImageView) Subview_Product_Status.findViewById(R.id.sub_image);
        Product_Status_Icon.setImageResource(R.drawable.ic_assignment_black_24dp);
		Product_Status_Icon.setOnClickListener(Item_OnClickListener);
        Product_Status_Title = (TextView) Subview_Product_Status.findViewById(R.id.sub_textview);
        Product_Status_Title.setText(R.string.title_product_status);
        Product_Status_Result = (TextView) Subview_Product_Status.findViewById(R.id.sub_textview_result);
        Product_Status_Result.setText(Init_Product_Status);

        Subview_Loan_Number = v.findViewById(R.id.loan_number);
		Subview_Loan_Number.setOnClickListener(Item_OnClickListener);
        Product_Loan_Number_Icon = (ImageView) Subview_Loan_Number.findViewById(R.id.sub_image);
        Product_Loan_Number_Icon.setImageResource(R.drawable.ic_count);
        Product_Loan_Number = (EditText) Subview_Loan_Number.findViewById(R.id.sub_edittext);
        Product_Loan_Number.setHint(R.string.hints_loan_number);

        Subview_Loan_Amount = v.findViewById(R.id.loan_amount);
        Loan_Amount_Icon = (ImageView) Subview_Loan_Amount.findViewById(R.id.sub_image);
        Loan_Amount_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Amount_Title = (TextView) Subview_Loan_Amount.findViewById(R.id.sub_textview);
        Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
        Loan_Amount_Result = (TextView) Subview_Loan_Amount.findViewById(R.id.sub_textview_result);
        Loan_Amount_Result.setText(Init_Loan_Amount);

        Subview_Loan_Rate = v.findViewById(R.id.loan_rate);
		Subview_Loan_Rate.setOnClickListener(Item_OnClickListener);
        Loan_Rate_Icon = (ImageView) Subview_Loan_Rate.findViewById(R.id.sub_image);
        Loan_Rate_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Rate_Title = (TextView) Subview_Loan_Rate.findViewById(R.id.sub_textview);
        Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
        Loan_Rate_Result = (TextView) Subview_Loan_Rate.findViewById(R.id.sub_textview_result);
        Loan_Rate_Result.setText(Init_Loan_Rate);

        Subview_Loan_Trems = v.findViewById(R.id.loan_trems);
		Subview_Loan_Trems.setOnClickListener(Item_OnClickListener);
        Loan_Trems_Icon = (ImageView) Subview_Loan_Trems.findViewById(R.id.sub_image);
        Loan_Trems_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Trems_Title = (TextView) Subview_Loan_Trems.findViewById(R.id.sub_textview);
        Loan_Trems_Title.setText(getString(R.string.title_loan_trems));
        Loan_Trems_Result = (TextView) Subview_Loan_Trems.findViewById(R.id.sub_textview_result);
        Loan_Trems_Result.setText(Init_Loan_Trems);

        Subview_Loan_Installment = v.findViewById(R.id.loan_installment);
		Subview_Loan_Installment.setOnClickListener(Item_OnClickListener);
        Loan_Installment_Icon = (ImageView) Subview_Loan_Installment.findViewById(R.id.sub_image);
        Loan_Installment_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Installment_Title = (TextView) Subview_Loan_Installment.findViewById(R.id.sub_textview);
        Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
        Loan_Installment_Result = (TextView) Subview_Loan_Installment.findViewById(R.id.sub_textview_result);
        Loan_Installment_Result.setText(Init_Loan_Installment);

        Due_Date_Linear = (LinearLayout) v.findViewById(R.id.duedate_linear);

        Subview_First_due = v.findViewById(R.id.firstdue_date);
		Subview_First_due.setOnClickListener(Item_OnClickListener);
        First_Due_Icon = (ImageView) Subview_First_due.findViewById(R.id.sub_image);
        First_Due_Icon.setImageResource(R.drawable.ic_event_black_24dp);
        First_Due_Title = (TextView) Subview_First_due.findViewById(R.id.sub_textview);
        First_Due_Title.setText(getString(R.string.title_first_due));
        First_Due_Result = (TextView) Subview_First_due.findViewById(R.id.sub_textview_result);
        First_Due_Result.setText(Init_First_Due);

        Subview_Final_due = v.findViewById(R.id.finaldue_date);
        Final_Due_Title = (TextView) Subview_Final_due.findViewById(R.id.sub_textview);
        Final_Due_Title.setText(getString(R.string.title_final_due));
        Final_Due_Result = (TextView) Subview_Final_due.findViewById(R.id.sub_textview_result);
        Final_Due_Result.setText(Init_Final_Due);

        Subview_Setup_Alarm = v.findViewById(R.id.setup_alarm);
		Subview_Setup_Alarm.setOnClickListener(Item_OnClickListener);
        Setup_Alarm_Icon = (ImageView) Subview_Setup_Alarm.findViewById(R.id.sub_image);
        Setup_Alarm_Icon.setImageResource(R.drawable.ic_alarm_black_24dp);
        Setup_Alarm_Title = (TextView) Subview_Setup_Alarm.findViewById(R.id.sub_textview);
        Setup_Alarm_Title.setText(getString(R.string.title_setup_alarm));
        Setup_Alarm_Result = (TextView) Subview_Setup_Alarm.findViewById(R.id.sub_textview_result);
        Setup_Alarm_Result.setText(Init_Setup_Alarm);

        Subview_Alarm_Time = v.findViewById(R.id.alarm_time);
		Subview_Alarm_Time.setOnClickListener(Item_OnClickListener);
        Alarm_Time_Title = (TextView) Subview_Alarm_Time.findViewById(R.id.sub_textview);
        Alarm_Time_Title.setText(getString(R.string.title_setup_time));
        Alarm_Time_Result = (TextView) Subview_Alarm_Time.findViewById(R.id.sub_textview_result);
        Alarm_Time_Result.setText(Init_Alarm_Time);

        Subview_Address = v.findViewById(R.id.address);
        Address_Icon = (ImageView) Subview_Address.findViewById(R.id.sub_image);
        Address_Icon.setImageResource(R.drawable.ic_location_on_black_24dp);
        Address = (EditText) Subview_Address.findViewById(R.id.sub_edittext);
        Address.setHint(R.string.hints_address);
        Address.setText(Init_Address);

        Subview_Phone = v.findViewById(R.id.phone);
        Phone_Icon = (ImageView) Subview_Phone.findViewById(R.id.sub_image);
        Phone_Icon.setImageResource(R.drawable.ic_call_black_24dp);
        Phone = (EditText) Subview_Phone.findViewById(R.id.sub_edittext);
        Phone.setHint(R.string.hints_phone);
        Phone.setText(Init_Phone);

        Subview_Remarks = v.findViewById(R.id.remarks);
        Remarks_Icon = (ImageView) Subview_Remarks.findViewById(R.id.sub_image);
        Remarks_Icon.setImageResource(R.drawable.ic_create_black_24dp);
        Remarks = (EditText) Subview_Remarks.findViewById(R.id.sub_edittext);
        Remarks.setHint(R.string.hints_remarks);
        Remarks.setText(Init_Remarks);

    }

    private void Find_Dialog_View(){

        Dialog_View = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);

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
    }
    	
	private OnClickListener Item_OnClickListener = new OnClickListener(){

		@Override
		public void onClick(View item)
		{
			// TODO: Implement this method
			switch(item.getId()){
				
				case R.id.product_type_icon:

					mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

					Dialog_Title.setText(getString(R.string.title_product_type));

                    Dialog_Option_1_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                    Dialog_Option_1_Text.setText(getString(R.string.title_product_personal));
					Dialog_Option_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_personal));

                            //  Show
                            Subview_Product_Status.setVisibility(View.VISIBLE);
                            Subview_Loan_Trems.setVisibility(View.VISIBLE);
                            Subview_Final_due.setVisibility(View.VISIBLE);
                            Subview_Address.setVisibility(View.VISIBLE);
                            Subview_Phone.setVisibility(View.VISIBLE);
                            // Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            //  Change
                            Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
                            Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
                            Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
                            Product_Loan_Number.setHint(R.string.hints_loan_number);

                            PRODUCT_CODE = "Personal";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Icon.setImageResource(R.drawable.ic_domain_black_24dp);
                    Dialog_Option_2_Text.setText(getString(R.string.title_product_mortgage));
					Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.drawable.ic_domain_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_mortgage));

                            //  Show
                            Subview_Product_Status.setVisibility(View.VISIBLE);
                            Subview_Loan_Trems.setVisibility(View.VISIBLE);
                            Subview_Final_due.setVisibility(View.VISIBLE);
                            Subview_Address.setVisibility(View.VISIBLE);
                            Subview_Phone.setVisibility(View.VISIBLE);
                            // Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            //  Change
                            Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
                            Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
                            Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
                            Product_Loan_Number.setHint(R.string.hints_loan_number);

                            PRODUCT_CODE = "Mortgage";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                    Dialog_Option_3_Text.setText(getString(R.string.title_product_revolving));
					Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.drawable.ic_person_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_revolving));

                            //  Show
                            Subview_Product_Status.setVisibility(View.VISIBLE);
                            Subview_Loan_Trems.setVisibility(View.VISIBLE);
                            Subview_Final_due.setVisibility(View.VISIBLE);
                            Subview_Address.setVisibility(View.VISIBLE);
                            Subview_Phone.setVisibility(View.VISIBLE);
                            // Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            //  Change
                            Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
                            Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
                            Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
                            Product_Loan_Number.setHint(R.string.hints_loan_number);

                            PRODUCT_CODE = "Revolving";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Icon.setImageResource(R.drawable.ic_directions_car_black_24dp);
                    Dialog_Option_4_Text.setText(getString(R.string.title_product_car));
					Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.drawable.ic_directions_car_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_car));

                            //  Show
                            Subview_Product_Status.setVisibility(View.VISIBLE);
                            Subview_Loan_Trems.setVisibility(View.VISIBLE);
                            Subview_Final_due.setVisibility(View.VISIBLE);
                            Subview_Address.setVisibility(View.VISIBLE);
                            Subview_Phone.setVisibility(View.VISIBLE);
                            // Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            //  Change
                            Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
                            Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
                            Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
                            Product_Loan_Number.setHint(R.string.hints_loan_number);

                            PRODUCT_CODE = "Car";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_5_Icon.setImageResource(R.drawable.ic_credit_card_black_24dp);
                    Dialog_Option_5_Text.setText(getString(R.string.title_product_card));
					Dialog_Option_5.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Product_Type_Icon.setImageResource(R.drawable.ic_credit_card_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_card));

                            //  Hide
                            Subview_Product_Status.setVisibility(View.GONE);
                            Subview_Loan_Trems.setVisibility(View.GONE);
                            Subview_Final_due.setVisibility(View.GONE);
                            Subview_Address.setVisibility(View.GONE);
                            Subview_Phone.setVisibility(View.GONE);
                            // Show
                            Subview_Loan_Number.setVisibility(View.VISIBLE);
                            //  Clear
                            Product_Status_Result.setText("");
                            Final_Due_Result.setText("");
                            Address.setText("");
                            Phone.setText("");
                            //  Change
                            Loan_Amount_Title.setText("信用額");
                            Loan_Rate_Title.setText("最低還款利率");
                            Loan_Installment_Title.setText("最低還款額");
                            Product_Loan_Number.setHint("信用卡號碼 (選填)");

                            PRODUCT_CODE = "Card";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Dismiss.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

					mAlertDialog.show();
					break;

//					================================================================================================
				case R.id.product_status_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.title_product_status));

                    Dialog_Option_1_Text.setText(R.string.product_status_notapply);
                    Dialog_Option_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Status_Result.setText(R.string.product_status_notapply);

                            //  Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            Due_Date_Linear.setVisibility(View.GONE);
                            //  Clear
                            First_Due_Result.setText("");
                            Final_Due_Result.setText("");
                            Alarm_Time_Result.setText("");
                            Product_Loan_Number.setText("");

                            STATUS_CODE = "NotApply";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Text.setText(R.string.product_status_pending);
                    Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Status_Result.setText(R.string.product_status_pending);

                            //  Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            Due_Date_Linear.setVisibility(View.GONE);
                            //  Clear
                            First_Due_Result.setText("");
                            Final_Due_Result.setText("");
                            Alarm_Time_Result.setText("");
                            Product_Loan_Number.setText("");

                            STATUS_CODE = "Pending";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Text.setText(R.string.product_status_approval);
                    Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Status_Result.setText(R.string.product_status_approval);

                            //  Show
                            Subview_Loan_Number.setVisibility(View.VISIBLE);
                            Due_Date_Linear.setVisibility(View.VISIBLE);
                            // Change
                            First_Due_Result.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

                            STATUS_CODE = "Approval";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Text.setText(R.string.product_status_reject);
                    Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Status_Result.setText(R.string.product_status_reject);

                            //  Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            Due_Date_Linear.setVisibility(View.GONE);
                            //  Clear
                            First_Due_Result.setText("");
                            Final_Due_Result.setText("");
                            Alarm_Time_Result.setText("");
                            Product_Loan_Number.setText("");

                            STATUS_CODE = "Reject";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_5_Text.setText(R.string.product_status_cancel);
                    Dialog_Option_5.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Status_Result.setText(R.string.product_status_cancel);

                            //  Hide
                            Subview_Loan_Number.setVisibility(View.GONE);
                            Due_Date_Linear.setVisibility(View.GONE);
                            //  Clear
                            First_Due_Result.setText("");
                            Final_Due_Result.setText("");
                            Alarm_Time_Result.setText("");
                            //  Clear
                            First_Due_Result.setText("");
                            Final_Due_Result.setText("");
                            Alarm_Time_Result.setText("");
                            Product_Loan_Number.setText("");

                            STATUS_CODE = "Cancel";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Dismiss.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

                    mAlertDialog.show();
					break;
//					================================================================================================
				case R.id.loan_amount:
					break;
//                ================================================================================================
				case R.id.loan_rate:
					break;
//                ================================================================================================
				case R.id.loan_trems:
					break;
//                ================================================================================================
				case R.id.loan_installment:
					break;
//                ================================================================================================
				case R.id.firstdue_date:
					break;
//                ================================================================================================
				case R.id.setup_alarm:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.title_setup_alarm));

                    Dialog_Option_1_Text.setText(R.string.alarm_dontset);
                    Dialog_Option_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_dontset);
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Text.setText(R.string.alarm_0_day);
                    Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_0_day);
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Text.setText(R.string.alarm_3_day);
                    Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_3_day);
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Text.setText(R.string.alarm_5_day);
                    Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_5_day);
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_5.setVisibility(View.GONE);

                    Dialog_Dismiss.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

                    mAlertDialog.show();
					break;
//                ================================================================================================
                case R.id.alarm_time:
                    break;
			}
		}
		
	};

}
