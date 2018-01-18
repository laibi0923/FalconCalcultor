package com.savtor.Credit_Profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.view.View.*;

import android.widget.*;
import com.savtor.falconcalcultor.*;
import com.savtor.Alarm_Notification.*;
import com.savtor.Credit_Database.Favouite_Item;
import com.savtor.Credit_Database.Favourite_DataBasic;
import com.savtor.Wish_List.WishList_Main;

/**
 * Created by GhostLeo_DT on 4/1/2018.
 */
public class Credit_Profile_Main extends Fragment {

    // Fragment View
    private EditText Product_Name, Product_Loan_Number, Address, PhoneNo, Remarks;

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

    private LinearLayout Dialog_Option_1, Dialog_Option_2, Dialog_Option_3, Dialog_Option_4, Dialog_Option_5, Dialog_Edittext;

    private TextView Dialog_Title, Dialog_Option_1_Text, Dialog_Option_2_Text, Dialog_Option_3_Text, Dialog_Option_4_Text, Dialog_Option_5_Text, Dialog_Done, Dialog_Dismiss;

    private ImageView Dialog_Option_1_Icon, Dialog_Option_2_Icon, Dialog_Option_3_Icon, Dialog_Option_4_Icon, Dialog_Option_5_Icon;

    private EditText Dialog_Option_Edittext;
	
	// Init String
    private String Init_Product_Name, Init_Product_Type, Init_Product_Status, Init_Loan_Number,
                   Init_Loan_Amount, Init_Loan_Rate, Init_Loan_Trems, Init_Loan_Installment,
                   Init_First_Due, Init_Final_Due, Init_Setup_Alarm, Init_Alarm_Time,
                   Init_Address, Init_Phone, Init_Remarks;

    private int Init_Product_Icon;

    // Calendar
    private Calendar Last_Modify_Calendar, First_Due_Calendar, Final_Due_Calendar, Times_Calendar;

    // DataBase Update / Intert
    private String PRODUCT_NAME, PRODUCT_CODE, STATUS_CODE, LOAN_NUM, FIRST_DUE, FINAL_DUE, ALARM_TIME, EOM_DUEDATE, ADDRESS, PHONE, REMARKS, LAST_MODIFY;
    private double LOAN_AMOUNT, LOAN_RATE, LOAN_INSTALLMENT;
    private int DB_ID, LOAN_TREMS, SETUP_ALARM, REQUEST_CODE;

    private Favourite_DataBasic DataBasic;

    private Bundle mBundle;

    private String This_Fragment_Name = "Credit_Profile_Main";
    private double Max_Amount = 10000000, Max_Rate = 60;
    private int Max_Trems = 90;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);

        mBundle = getArguments();

        First_Due_Calendar = Calendar.getInstance();
        Final_Due_Calendar = Calendar.getInstance();
        Times_Calendar = Calendar.getInstance();
        Last_Modify_Calendar = Calendar.getInstance();

        LAST_MODIFY = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        PRODUCT_CODE = "Personal";
        STATUS_CODE = "NotApply";
        LOAN_AMOUNT = 0;
        LOAN_RATE = 0;
        LOAN_TREMS = 12;
        LOAN_INSTALLMENT = 0;
        FIRST_DUE = LAST_MODIFY;
        FINAL_DUE = "";
        SETUP_ALARM = 99;
        ALARM_TIME = "";
        ADDRESS = "";
        PHONE = "";
        REMARKS = "";
		REQUEST_CODE = 0;

        if (mBundle != null && mBundle.getString("From").equals("Calcultor")){

            LOAN_AMOUNT =  mBundle.getDouble("Amount", 0.00);
            LOAN_RATE = mBundle.getDouble("Rate", 0.00);
            LOAN_TREMS =  mBundle.getInt("Trems", 0);
            LOAN_INSTALLMENT = mBundle.getDouble("Installment", 0.00);

        }else if (mBundle != null && mBundle.getString("From").equals("Favoutive")){

            DB_ID = mBundle.getInt("DB_ID");
            DataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

            LAST_MODIFY = DataBasic.query(DB_ID).getCreate_Date();
            PRODUCT_NAME = DataBasic.query(DB_ID).getProduct_Name();
            PRODUCT_CODE = DataBasic.query(DB_ID).getProduct_Type();
            STATUS_CODE = DataBasic.query(DB_ID).getProduct_Status();
            LOAN_NUM = DataBasic.query(DB_ID).getLoan_No();
            LOAN_AMOUNT = DataBasic.query(DB_ID).getLoan_Amount();
            LOAN_RATE = DataBasic.query(DB_ID).getLoan_Rate();
            LOAN_TREMS = DataBasic.query(DB_ID).getLoan_Trems();
            LOAN_INSTALLMENT = DataBasic.query(DB_ID).getLoan_Installment();
            FIRST_DUE = DataBasic.query(DB_ID).getFirst_Due();
			FINAL_DUE = DataBasic.query(DB_ID).getFinal_Due();
            EOM_DUEDATE = DataBasic.query(DB_ID).getEOM_DueDate();
            SETUP_ALARM = DataBasic.query(DB_ID).getSetup_Alarm();
            ALARM_TIME = DataBasic.query(DB_ID).getAlarm_Time();
            ADDRESS = DataBasic.query(DB_ID).getAddress();
            PHONE = DataBasic.query(DB_ID).getPhone_No();
            REMARKS = DataBasic.query(DB_ID).getRemarks();
			REQUEST_CODE = DataBasic.query(DB_ID).getRequestCode();
			Log.e("Restore", "Request Code = " + REQUEST_CODE);

            DataBasic.close();

			if(FIRST_DUE != null){
				First_Due_Calendar.set(Calendar.YEAR, Integer.parseInt(FIRST_DUE.substring(0, 4)));
				First_Due_Calendar.set(Calendar.MONTH, Integer.parseInt(FIRST_DUE.substring(5 ,7)) - 1);
				First_Due_Calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(FIRST_DUE.substring(8, 10)));
				Log.e("Restore", "First due = " + First_Due_Calendar.getTime());
			}
            
            if (ALARM_TIME.length() != 0){
                Times_Calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ALARM_TIME.substring(0, 2)));
                Times_Calendar.set(Calendar.MINUTE, Integer.parseInt(ALARM_TIME.substring(3, 5)));
				Log.e("Restore", "Alarm time = " + new SimpleDateFormat("HH:mm").format(Times_Calendar.getTime()));
            }

        }

        Init_Product_Name = PRODUCT_NAME;
        if (PRODUCT_CODE.equals("Personal")){
            Init_Product_Icon = R.drawable.ic_person_black_24dp;
            Init_Product_Type = getString(R.string.title_product_personal);
        }else if (PRODUCT_CODE.equals("Mortgage")){
            Init_Product_Icon = R.drawable.ic_domain_black_24dp;
            Init_Product_Type = getString(R.string.title_product_mortgage);
        }else if (PRODUCT_CODE.equals("Revolving")){
            Init_Product_Icon = R.drawable.ic_person_black_24dp;
            Init_Product_Type = getString(R.string.title_product_revolving);
        }else if (PRODUCT_CODE.equals("Car")){
            Init_Product_Icon = R.drawable.ic_directions_car_black_24dp;
            Init_Product_Type = getString(R.string.title_product_car);
        }else if (PRODUCT_CODE.equals("Card")) {
            Init_Product_Icon = R.drawable.ic_credit_card_black_48dp;
            Init_Product_Type = getString(R.string.title_product_card);
        }

        if (STATUS_CODE.equals("NotApply")){
            Init_Product_Status = getString(R.string.product_status_notapply);
        }else if (STATUS_CODE.equals("Pending")){
            Init_Product_Status = getString(R.string.product_status_pending);
        }else if (STATUS_CODE.equals("Approval")){
            Init_Product_Status = getString(R.string.product_status_approval);
        }else if (STATUS_CODE.equals("Reject")){
            Init_Product_Status = getString(R.string.product_status_reject);
        }else if (STATUS_CODE.equals("Cancel")){
            Init_Product_Status = getString(R.string.product_status_cancel);
        }
        Init_Loan_Number = LOAN_NUM;
        Init_Loan_Amount = String.format("%.2f", LOAN_AMOUNT);
        Init_Loan_Rate = String.format("%.2f", LOAN_RATE);
        Init_Loan_Trems = String.valueOf(LOAN_TREMS);
        Init_Loan_Installment = String.format("%.2f", LOAN_INSTALLMENT);

        if(FIRST_DUE == null){
            Init_First_Due = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        }else {
            Init_First_Due = FIRST_DUE;
        }

        if(FINAL_DUE == null){
            Init_Final_Due = "";
        }else{
            Init_Final_Due = FINAL_DUE;
        }

        if (SETUP_ALARM == 99){
            Init_Setup_Alarm = getString(R.string.alarm_dontset);
        }else if (SETUP_ALARM == 0){
            Init_Setup_Alarm = getString(R.string.alarm_0_day);
        }else if (SETUP_ALARM == 3){
            Init_Setup_Alarm = getString(R.string.alarm_3_day);
        }else if (SETUP_ALARM == 5){
            Init_Setup_Alarm = getString(R.string.alarm_5_day);
        }

        Init_Alarm_Time = ALARM_TIME;
        Init_Address = ADDRESS;
        Init_Phone = PHONE;
        Init_Remarks = REMARKS;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.saver_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.save_to_fav:

                if (Product_Name.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.totast_enter_name), Toast.LENGTH_SHORT).show();
                }else {

					Falcon_AlramManager mAlarmManager = new Falcon_AlramManager();
                    if(REQUEST_CODE != 0){
						// 不論使用者取消或調整時間， 都先取消舊有提示
						mAlarmManager.Cancel_Alram(getContext(), REQUEST_CODE, LOAN_TREMS);
					}

                    LAST_MODIFY = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Last_Modify_Calendar.getTime());
					// 新創 Request Code
					REQUEST_CODE = (int) -Last_Modify_Calendar.getTimeInMillis();
					
                    DataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

                    Favouite_Item fav_item = new Favouite_Item(
                            DB_ID,
                            LAST_MODIFY,
                            Product_Name.getText().toString(),
                            PRODUCT_CODE,
                            STATUS_CODE,
                            Product_Loan_Number.getText().toString(),
                            LOAN_AMOUNT,
                            LOAN_TREMS,
                            LOAN_RATE,
                            LOAN_INSTALLMENT,
                            FIRST_DUE,
                            FINAL_DUE,
                            EOM_DUEDATE,
                            SETUP_ALARM,
                            ALARM_TIME, //HH:mm:ss
                            Address.getText().toString(),
                            PhoneNo.getText().toString(),
                            Remarks.getText().toString(),
                            REQUEST_CODE); 
							
                    if(mBundle != null && mBundle.getString("From") == "Favoutive"){
                        DataBasic.update(fav_item);
                        Toast.makeText(getContext(), getString(R.string.toast_upadte_completed), Toast.LENGTH_SHORT).show();
                    }else {
                        DataBasic.inster(fav_item);
                        Toast.makeText(getContext(), getString(R.string.toast_save_completed), Toast.LENGTH_SHORT).show();
                    }

                    DataBasic.close();
					
                    if(ALARM_TIME.length() != 0 && SETUP_ALARM != 99){
                        // 設置新 Alram
                        mAlarmManager.Setup_Alram(getContext(),
                                REQUEST_CODE,
                                First_Due_Calendar,
                                Times_Calendar,
                                EOM_DUEDATE,
                                LOAN_TREMS,
                                SETUP_ALARM,
                                Product_Name.getText().toString(),
                                LOAN_INSTALLMENT);
                    }

                    //  保存後跳頁至心水清單
                    ((Activity_Main)getActivity()).Fragment_Transaction(new WishList_Main());
                    ((Activity_Main)getActivity()).mNavView.getMenu().getItem(2).setChecked(true);

			/*
			 *	Testing Get Value
			*/
                    Log.e("LAST_MODIFY", new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "");
                    Log.e("PRODUCT_NAME", Product_Name.getText().toString());
                    Log.e("PRODUCT_CODE", PRODUCT_CODE + "");
                    Log.e("STATUS_CODE", STATUS_CODE + "");
                    Log.e("LOAN_NUM", Product_Loan_Number.getText().toString());
                    Log.e("LOAN_AMOUNT", LOAN_AMOUNT + "");
                    Log.e("LOAN_RATE", LOAN_RATE + "");
                    Log.e("LOAN_TREMS", LOAN_TREMS + "");
                    Log.e("LOAN_INSTALLMENT", LOAN_INSTALLMENT + "");
                    Log.e("FIRST_DUE", FIRST_DUE + "");
                    Log.e("EOM_DUEDATE", EOM_DUEDATE + "");
                    Log.e("SETUP_ALARM", SETUP_ALARM + "");
                    Log.e("ALARM_TIME", ALARM_TIME + "");
                    Log.e("ADDRESS", Address.getText().toString());
                    Log.e("PHONE_NO", PhoneNo.getText().toString());
                    Log.e("REMARKS", Remarks.getText().toString());
                    Log.e("REQUEST CODE", REQUEST_CODE + "");
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.credit_profile, container, false);

        Find_View(v);
		Change_View();

        return v;
    }

	private void Change_View(){

		if (STATUS_CODE.equals("Approval")){
            Subview_Loan_Number.setVisibility(View.VISIBLE);
            Due_Date_Linear.setVisibility(View.VISIBLE);
        }else {
            Subview_Loan_Number.setVisibility(View.GONE);
            Due_Date_Linear.setVisibility(View.GONE);
        }
		
		if(FINAL_DUE == null){
			Subview_Setup_Alarm.setVisibility(View.GONE);
		}else {
			Subview_Setup_Alarm.setVisibility(View.VISIBLE);
		}
		
		if(SETUP_ALARM != 99){
			Subview_Alarm_Time.setVisibility(View.VISIBLE);
		}else {
			Subview_Alarm_Time.setVisibility(View.GONE);
		}
		
		if (PRODUCT_CODE.equals("Card")){
            Subview_Product_Status.setVisibility(View.GONE);
            Subview_Loan_Trems.setVisibility(View.GONE);
            Subview_Final_due.setVisibility(View.GONE);
            Subview_Alarm_Time.setVisibility(View.GONE);
            Subview_Address.setVisibility(View.GONE);
            Subview_Phone.setVisibility(View.GONE);
            // Show
            Subview_Loan_Number.setVisibility(View.VISIBLE);
            Due_Date_Linear.setVisibility(View.VISIBLE);
            Subview_First_due.setVisibility(View.VISIBLE);
            //  Change Title
            Loan_Amount_Title.setText(getString(R.string.title_card_amount));
            Loan_Rate_Title.setText(getString(R.string.title_card_rate));
            Loan_Installment_Title.setText(getString(R.string.title_card_installment));
            Product_Loan_Number.setHint(getString(R.string.title_card_number));
            First_Due_Title.setText(getString(R.string.title_card_duedate));
		}
		
	}

    private void Reset_Value(){

        Product_Name.setText("");

        Init_Product_Status = getString(R.string.product_status_notapply);

        Product_Status_Result.setText(getString(R.string.product_status_notapply));
        Product_Loan_Number.setText("");

        Loan_Amount_Result.setText("$ 0.00");
        Loan_Rate_Result.setText("0.00 %p.a.");
        Loan_Trems_Result.setText("12個月");
        Loan_Installment_Result.setText("$ 0.00");

        First_Due_Result.setText(new SimpleDateFormat("yyy/MM/dd").format(new Date()));
        Final_Due_Result.setText("");
        Setup_Alarm_Result.setText(getString(R.string.alarm_dontset));
        Alarm_Time_Result.setText("");

        Address.setText("");
        PhoneNo.setText("");
        Remarks.setText("");

        STATUS_CODE = "NotApply";
		LOAN_AMOUNT = 0;
		LOAN_RATE = 0;
        LOAN_TREMS = 12;
		LOAN_INSTALLMENT = 0;
		FINAL_DUE = "";
		SETUP_ALARM = 99;
		ALARM_TIME = "";
		ADDRESS = "";
		PHONE = "";
		REMARKS = "";
    }

/*================================================================================================
 *                                      Find View
================================================================================================ */
    private void Find_View (View v){

        Product_Name = (EditText) v.findViewById(R.id.product_name);
        Product_Name.setHint(R.string.hints_product_name);
        Product_Name.setText(Init_Product_Name);

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
        Product_Loan_Number_Icon = (ImageView) Subview_Loan_Number.findViewById(R.id.sub_image);
        Product_Loan_Number_Icon.setImageResource(R.drawable.ic_count);
        Product_Loan_Number = (EditText) Subview_Loan_Number.findViewById(R.id.sub_edittext);
        Product_Loan_Number.setHint(R.string.hints_loan_number);
        Product_Loan_Number.setText(Init_Loan_Number);

        Subview_Loan_Amount = v.findViewById(R.id.loan_amount);
        Loan_Amount_Linear = (LinearLayout) v.findViewById(R.id.loan_amount_linear);
        Loan_Amount_Linear.setOnClickListener(Item_OnClickListener);
        Loan_Amount_Icon = (ImageView) Subview_Loan_Amount.findViewById(R.id.sub_image);
        Loan_Amount_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Amount_Title = (TextView) Subview_Loan_Amount.findViewById(R.id.sub_textview);
        Loan_Amount_Title.setText(getString(R.string.title_loan_amount));
        Loan_Amount_Result = (TextView) Subview_Loan_Amount.findViewById(R.id.sub_textview_result);
        Loan_Amount_Result.setText("$ " + Init_Loan_Amount);

        Subview_Loan_Rate = v.findViewById(R.id.loan_rate);
        Loan_Rate_Linear = (LinearLayout) v.findViewById(R.id.loan_rate_linear);
        Loan_Rate_Linear.setOnClickListener(Item_OnClickListener);
        Loan_Rate_Icon = (ImageView) Subview_Loan_Rate.findViewById(R.id.sub_image);
        Loan_Rate_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Rate_Title = (TextView) Subview_Loan_Rate.findViewById(R.id.sub_textview);
        Loan_Rate_Title.setText(getString(R.string.title_loan_rate));
        Loan_Rate_Result = (TextView) Subview_Loan_Rate.findViewById(R.id.sub_textview_result);
        Loan_Rate_Result.setText(Init_Loan_Rate + " %p.a.");

        Subview_Loan_Trems = v.findViewById(R.id.loan_trems);
        Loan_Trems_Linear = (LinearLayout) v.findViewById(R.id.loan_trems_linear);
        Loan_Trems_Linear.setOnClickListener(Item_OnClickListener);
        Loan_Trems_Icon = (ImageView) Subview_Loan_Trems.findViewById(R.id.sub_image);
        Loan_Trems_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Trems_Title = (TextView) Subview_Loan_Trems.findViewById(R.id.sub_textview);
        Loan_Trems_Title.setText(getString(R.string.title_loan_trems));
        Loan_Trems_Result = (TextView) Subview_Loan_Trems.findViewById(R.id.sub_textview_result);
        Loan_Trems_Result.setText(Init_Loan_Trems + " 個月");

        Subview_Loan_Installment = v.findViewById(R.id.loan_installment);
        Loan_Installment_Linear = (LinearLayout) v.findViewById(R.id.loan_installment_linear);
        Loan_Installment_Linear.setOnClickListener(Item_OnClickListener);
        Loan_Installment_Icon = (ImageView) Subview_Loan_Installment.findViewById(R.id.sub_image);
        Loan_Installment_Icon.setImageResource(R.drawable.ic_data_usage_black_24dp);
        Loan_Installment_Title = (TextView) Subview_Loan_Installment.findViewById(R.id.sub_textview);
        Loan_Installment_Title.setText(getString(R.string.title_loan_installment));
        Loan_Installment_Result = (TextView) Subview_Loan_Installment.findViewById(R.id.sub_textview_result);
        Loan_Installment_Result.setText("$ " + Init_Loan_Installment);

        Due_Date_Linear = (LinearLayout) v.findViewById(R.id.duedate_linear);

        Subview_First_due = v.findViewById(R.id.firstdue_date);
        FirstDue_Linear = (LinearLayout) v.findViewById(R.id.firstdue_linear);
		FirstDue_Linear.setOnClickListener(Item_OnClickListener);
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
        Setup_Alarm_Linear = (LinearLayout) v.findViewById(R.id.setup_alarm_linear);
		Setup_Alarm_Linear.setOnClickListener(Item_OnClickListener);
        Setup_Alarm_Icon = (ImageView) Subview_Setup_Alarm.findViewById(R.id.sub_image);
        Setup_Alarm_Icon.setImageResource(R.drawable.ic_alarm_black_24dp);
        Setup_Alarm_Title = (TextView) Subview_Setup_Alarm.findViewById(R.id.sub_textview);
        Setup_Alarm_Title.setText(getString(R.string.title_setup_alarm));
        Setup_Alarm_Result = (TextView) Subview_Setup_Alarm.findViewById(R.id.sub_textview_result);
        Setup_Alarm_Result.setText(Init_Setup_Alarm);

        Subview_Alarm_Time = v.findViewById(R.id.alarm_time);
        Alarm_Time_Linear = (LinearLayout) v.findViewById(R.id.alarm_time_linear);
        Alarm_Time_Linear.setOnClickListener(Item_OnClickListener);
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
        PhoneNo = (EditText) Subview_Phone.findViewById(R.id.sub_edittext);
        PhoneNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        PhoneNo.setHint(R.string.hints_phone);
        PhoneNo.setText(Init_Phone);

        Subview_Remarks = v.findViewById(R.id.remarks);
        Remarks_Icon = (ImageView) Subview_Remarks.findViewById(R.id.sub_image);
        Remarks_Icon.setImageResource(R.drawable.ic_create_black_24dp);
        Remarks = (EditText) Subview_Remarks.findViewById(R.id.sub_edittext);
        Remarks.setHint(R.string.hints_remarks);
        Remarks.setText(Init_Remarks);
        
    }

    private void Find_Dialog_View(){

        Dialog_View = LayoutInflater.from(getActivity()).inflate(R.layout.credit_profile_dialog, null);

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

        Dialog_Edittext = (LinearLayout) Dialog_View.findViewById(R.id.dialog_edittext);
        Dialog_Edittext.setVisibility(View.GONE);

        Dialog_Option_Edittext = (EditText) Dialog_View.findViewById(R.id.dialog_option_edittext);

        Dialog_Done = (TextView) Dialog_View.findViewById(R.id.dialog_done);
        Dialog_Done.setVisibility(View.GONE);

        Dialog_Dismiss = (TextView) Dialog_View.findViewById(R.id.dialog_dismiss);
    }

	private OnClickListener Item_OnClickListener = new OnClickListener(){

		@Override
		public void onClick(View item)
		{
			// TODO: Implement this method
			switch(item.getId()){
/*================================================================================================
 *                                       Product Type
 *               Option : Personal / Mortgage / Revolving / Car / Card
================================================================================================ */
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

                            if (PRODUCT_CODE == "Card"){
                                Change_View();
                                Reset_Value();
                            }


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

                            if (PRODUCT_CODE == "Card"){
                                Change_View();
                                Reset_Value();
                            }

                            PRODUCT_CODE = "Mortgage";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Icon.setImageResource(R.drawable.ic_cached_black_24dp);
                    Dialog_Option_3_Text.setText(getString(R.string.title_product_revolving));
					Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.drawable.ic_cached_black_24dp);
                            Product_Type_Name.setText(getString(R.string.title_product_revolving));

                            if (PRODUCT_CODE == "Card"){
                                Change_View();
                                Reset_Value();
                            }

                            PRODUCT_CODE = "Revolving";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Icon.setImageResource(R.mipmap.ic_car_48);
                    Dialog_Option_4_Text.setText(getString(R.string.title_product_car));
					Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Product_Type_Icon.setImageResource(R.mipmap.ic_car_48);
                            Product_Type_Name.setText(getString(R.string.title_product_car));

                            if (PRODUCT_CODE == "Card"){
                                Change_View();
                                Reset_Value();
                            }

                            PRODUCT_CODE = "Car";
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_5_Icon.setImageResource(R.drawable.ic_credit_card_black_24dp);
                    Dialog_Option_5_Text.setText(getString(R.string.title_product_card));
					Dialog_Option_5.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Product_Type_Icon.setImageResource(R.drawable.ic_credit_card_black_48dp);
                            Product_Type_Name.setText(getString(R.string.title_product_card));

                            Reset_Value();
                           
							PRODUCT_CODE = "Card";
                            STATUS_CODE = "Approval";
                            LOAN_TREMS = 1;
                            EOM_DUEDATE = "false";
                            SETUP_ALARM = 99;
                            ALARM_TIME = "";
							
							Change_View();

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Edittext.setVisibility(View.GONE);

                    Dialog_Dismiss.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

					mAlertDialog.show();
					break;
/*================================================================================================
 *                                       Product Status
 *               Option : NotApply / Pending / Approval / Reject / Cancel
================================================================================================ */
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

                    Dialog_Edittext.setVisibility(View.GONE);

                    Dialog_Dismiss.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

                    mAlertDialog.show();
					break;
/*================================================================================================
 *                                       Loan Amount
================================================================================================ */
				case R.id.loan_amount_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.dialog_title_amount));

                    Dialog_Option_1_Icon.setVisibility(View.GONE);
                    Dialog_Option_1_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_1_Text.setText("$ 10,000.00");
                    Dialog_Option_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 快速輸入
                            LOAN_AMOUNT = 10000;
                            Loan_Amount_Result.setText("$ " + LOAN_AMOUNT);

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Icon.setVisibility(View.GONE);
                    Dialog_Option_2_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_2_Text.setText("$ 30,000.00");
                    Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 快速輸入
                            LOAN_AMOUNT = 30000;
                            Loan_Amount_Result.setText("$ " + LOAN_AMOUNT);


                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Icon.setVisibility(View.GONE);
                    Dialog_Option_3_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_3_Text.setText("$ 50,000.00");
                    Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 快速輸入
                            LOAN_AMOUNT = 50000;
                            Loan_Amount_Result.setText("$ " + LOAN_AMOUNT);

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Icon.setVisibility(View.GONE);
                    Dialog_Option_4_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_4_Text.setText("$ 100,000.00");
                    Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 快速輸入
                            LOAN_AMOUNT = 100000;
                            Loan_Amount_Result.setText("$ " + LOAN_AMOUNT);

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_5.setVisibility(View.GONE);
                    Dialog_Edittext.setVisibility(View.VISIBLE);
                    Dialog_Option_Edittext.setHint(getString(R.string.dialog_hints_loanammount));
                    Dialog_Option_Edittext.requestFocus();

                    Dialog_Done.setVisibility(View.VISIBLE);
                    Dialog_Done.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Dialog_Option_Edittext.getText().toString().isEmpty()){

                            }else if(Double.parseDouble(Dialog_Option_Edittext.getText().toString()) > Max_Amount) {
                                Toast.makeText(getContext(), getString(R.string.toast_max_amount), Toast.LENGTH_SHORT).show();
                                
                            }else{
								LOAN_AMOUNT = Double.parseDouble(Dialog_Option_Edittext.getText().toString());
								Loan_Amount_Result.setText("$ " + String.format("%.2f", LOAN_AMOUNT));
							}

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
/*================================================================================================
 *                                       Loan Rate
================================================================================================ */
				case R.id.loan_rate_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getResources().getString(R.string.dialog_title_rate));

                    Dialog_Option_1.setVisibility(View.GONE);
                    Dialog_Option_2.setVisibility(View.GONE);
                    Dialog_Option_3.setVisibility(View.GONE);
                    Dialog_Option_4.setVisibility(View.GONE);
                    Dialog_Option_5.setVisibility(View.GONE);

                    Dialog_Edittext.setVisibility(View.VISIBLE);
                    Dialog_Option_Edittext.setHint(getString(R.string.dialog_hints_loanrate));
                    Dialog_Option_Edittext.requestFocus();

                    Dialog_Done.setVisibility(View.VISIBLE);
                    Dialog_Done.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Dialog_Option_Edittext.getText().toString().isEmpty()){
                                mAlertDialog.dismiss();
                            }else {

                                if (Double.parseDouble(Dialog_Option_Edittext.getText().toString()) > Max_Rate){
                                    Toast.makeText(getContext(), getString(R.string.toast_max_rate), Toast.LENGTH_SHORT).show();
                               
								}else {
									LOAN_RATE = Double.parseDouble(Dialog_Option_Edittext.getText().toString());
                                    Loan_Rate_Result.setText(String.format("%.2f", LOAN_RATE) + " % p.a.");
                                    
                                }

                                mAlertDialog.dismiss();
                            }

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
/*================================================================================================
 *                                       Loan Rate
================================================================================================ */
				case R.id.loan_trems_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.dialog_title_trems));

                    Dialog_Option_1_Icon.setVisibility(View.GONE);
                    Dialog_Option_1_Text.setText("12 個月");
                    Dialog_Option_1_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Loan_Trems_Result.setText("12 個月");
                            LOAN_TREMS = 12;

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Icon.setVisibility(View.GONE);
                    Dialog_Option_2_Text.setText("24 個月");
                    Dialog_Option_2_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Loan_Trems_Result.setText("24 個月");
                            LOAN_TREMS = 24;

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Icon.setVisibility(View.GONE);
                    Dialog_Option_3_Text.setText("36 個月");
                    Dialog_Option_3_Text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Loan_Trems_Result.setText("36 個月");
                            LOAN_TREMS = 36;

                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4.setVisibility(View.GONE);
                    Dialog_Option_5.setVisibility(View.GONE);

                    Dialog_Edittext.setVisibility(View.VISIBLE);
                    Dialog_Option_Edittext.setHint(getString(R.string.dialog_hints_trems));
                    Dialog_Option_Edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Dialog_Option_Edittext.requestFocus();

                    Dialog_Done.setVisibility(View.VISIBLE);
                    Dialog_Done.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Dialog_Option_Edittext.getText().toString().isEmpty()){
                                mAlertDialog.dismiss();
                            }else if(Integer.parseInt(Dialog_Option_Edittext.getText().toString()) > Max_Trems){
                                Toast.makeText(getContext(), getString(R.string.toast_max_trems), Toast.LENGTH_SHORT).show();
                            }else {
								LOAN_TREMS = Integer.parseInt(Dialog_Option_Edittext.getText().toString());
                                Loan_Trems_Result.setText(LOAN_TREMS + "個月");
                            }

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
/*================================================================================================
 *                                       Loan Installment
================================================================================================ */
				case R.id.loan_installment_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.dialog_title_installment));

                    Dialog_Option_1.setVisibility(View.GONE);
                    Dialog_Option_2.setVisibility(View.GONE);
                    Dialog_Option_3.setVisibility(View.GONE);
                    Dialog_Option_4.setVisibility(View.GONE);
                    Dialog_Option_5.setVisibility(View.GONE);

                    Dialog_Edittext.setVisibility(View.VISIBLE);
                    Dialog_Option_Edittext.setHint(getString(R.string.dialog_hints_loaninstallment));
                    Dialog_Option_Edittext.requestFocus();

                    Dialog_Done.setVisibility(View.VISIBLE);
                    Dialog_Done.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Dialog_Option_Edittext.getText().toString().isEmpty()){

                            }else {

								LOAN_INSTALLMENT = Double.parseDouble(Dialog_Option_Edittext.getText().toString());
                                Loan_Installment_Result.setText("$ " + String.format("%.2f", LOAN_INSTALLMENT));
								
                            }

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
/*================================================================================================
 *                                       First Due
================================================================================================ */
				case R.id.firstdue_linear:

					Dialog mDialog = new DatePickerDialog(getActivity(), R.style.myDateDialogTheme, new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
							{
								// TODO: Implement this method
								First_Due_Calendar.set(Calendar.YEAR, year);
								First_Due_Calendar.set(Calendar.MONTH, month);
								First_Due_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								
								Final_Due_Calendar.set(Calendar.YEAR, year);
								Final_Due_Calendar.set(Calendar.MONTH, month);
								Final_Due_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								
								if(month == 1 && dayOfMonth == First_Due_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH) || dayOfMonth == 30){
									// show dialog
									mAlertDialog = new AlertDialog.Builder(getContext()).create();
									Find_Dialog_View();

									mAlertDialog.setView(Dialog_View);

									Dialog_Title.setText(getString(R.string.title_product_status));
									
									Dialog_Option_1_Text.setText(R.string.dialog_duedate_normal);
									Dialog_Option_1.setOnClickListener(new OnClickListener() {

											@Override
											public void onClick(View v)
											{
												// TODO: Implement this method
												First_Due_Result.setText(First_Due_Calendar.get(Calendar.YEAR) + "/" +
                                                                        (First_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
																		 First_Due_Calendar.get(Calendar.DAY_OF_MONTH));

												Final_Due_Calendar.add(Calendar.MONTH, LOAN_TREMS - 1 );
												Final_Due_Result.setText(Final_Due_Calendar.get(Calendar.YEAR) +"/" +
                                                                        (Final_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
																		 Final_Due_Calendar.get(Calendar.DAY_OF_MONTH));

                                                FIRST_DUE = new SimpleDateFormat("yyyy/MM/dd").format(First_Due_Calendar.getTime());
												FINAL_DUE = new SimpleDateFormat("yyyy/MM/dd").format(Final_Due_Calendar.getTime());
                                                EOM_DUEDATE = "false";
												mAlertDialog.dismiss();
											}
										});
										
									Dialog_Option_2_Text.setText(R.string.dialog_duedate_eom);
									Dialog_Option_2.setOnClickListener(new OnClickListener() {

											@Override
											public void onClick(View v)
											{
												// TODO: Implement this method
												First_Due_Result.setText(First_Due_Calendar.get(Calendar.YEAR) + "/" +
                                                                        (First_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
																		 First_Due_Calendar.get(Calendar.DAY_OF_MONTH));

												Final_Due_Calendar.add(Calendar.MONTH, LOAN_TREMS - 1 );
												Final_Due_Result.setText(Final_Due_Calendar.get(Calendar.YEAR) +"/" +
                                                                        (Final_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
																		 Final_Due_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

                                                FIRST_DUE = new SimpleDateFormat("yyyy/MM/dd").format(First_Due_Calendar.getTime());
												FINAL_DUE = new SimpleDateFormat("yyyy/MM/dd").format(Final_Due_Calendar.getTime());
                                                EOM_DUEDATE = "true";
												mAlertDialog.dismiss();
											}
										});
										
									Dialog_Option_3.setVisibility(View.GONE);
									Dialog_Option_4.setVisibility(View.GONE);
									Dialog_Option_5.setVisibility(View.GONE);
										
									Dialog_Dismiss.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												mAlertDialog.dismiss();
											}
										});
										
									mAlertDialog.show();
										
									Subview_Setup_Alarm.setVisibility(View.VISIBLE);
									
								} else if(dayOfMonth == 31){
									
									First_Due_Result.setText(First_Due_Calendar.get(Calendar.YEAR) + "/" +
                                                            (First_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
															 First_Due_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

									Final_Due_Calendar.add(Calendar.MONTH, LOAN_TREMS - 1 );
									Final_Due_Result.setText(Final_Due_Calendar.get(Calendar.YEAR) +"/" +
                                                            (Final_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
															 Final_Due_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

                                    FIRST_DUE = new SimpleDateFormat("yyyy/MM/dd").format(First_Due_Calendar.getTime());
									FINAL_DUE = new SimpleDateFormat("yyyy/MM/dd").format(Final_Due_Calendar.getTime());
                                    EOM_DUEDATE = "true";
									Subview_Setup_Alarm.setVisibility(View.VISIBLE);
									
								} else{
									
									First_Due_Result.setText(First_Due_Calendar.get(Calendar.YEAR) + "/" +
                                                            (First_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
															 First_Due_Calendar.get(Calendar.DAY_OF_MONTH));
															 
									Final_Due_Calendar.add(Calendar.MONTH, LOAN_TREMS - 1 );
									Final_Due_Result.setText(Final_Due_Calendar.get(Calendar.YEAR) +"/" +
                                                            (Final_Due_Calendar.get(Calendar.MONTH ) + 1) + "/" +
															 Final_Due_Calendar.get(Calendar.DAY_OF_MONTH));

                                    FIRST_DUE = new SimpleDateFormat("yyyy/MM/dd").format(First_Due_Calendar.getTime());
									FINAL_DUE = new SimpleDateFormat("yyyy/MM/dd").format(Final_Due_Calendar.getTime());
                                    EOM_DUEDATE = "false";
									Subview_Setup_Alarm.setVisibility(View.VISIBLE);
								}
							}
						},First_Due_Calendar.get(Calendar.YEAR), First_Due_Calendar.get(Calendar.MONTH), First_Due_Calendar.get(Calendar.DAY_OF_MONTH));
					
						mDialog.show();
					break;
/*================================================================================================
 *                                       Setup Alarm
================================================================================================ */
				case R.id.setup_alarm_linear:

                    mAlertDialog = new AlertDialog.Builder(getContext()).create();
                    Find_Dialog_View();

                    mAlertDialog.setView(Dialog_View);

                    Dialog_Title.setText(getString(R.string.title_setup_alarm));

                    Dialog_Option_1_Text.setText(R.string.alarm_dontset);
                    Dialog_Option_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_dontset);
							Subview_Alarm_Time.setVisibility(View.GONE);
							Alarm_Time_Result.setText("");

                            SETUP_ALARM = 99;
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_2_Text.setText(R.string.alarm_0_day);
                    Dialog_Option_2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_0_day);
                            Subview_Alarm_Time.setVisibility(View.VISIBLE);
							show_time_dialog().show();

                            SETUP_ALARM = 0;
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_3_Text.setText(R.string.alarm_3_day);
                    Dialog_Option_3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_3_day);
                            Subview_Alarm_Time.setVisibility(View.VISIBLE);
							show_time_dialog().show();

                            SETUP_ALARM = 3;
                            mAlertDialog.dismiss();
                        }
                    });

                    Dialog_Option_4_Text.setText(R.string.alarm_5_day);
                    Dialog_Option_4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Setup_Alarm_Result.setText(R.string.alarm_5_day);
                            Subview_Alarm_Time.setVisibility(View.VISIBLE);
							show_time_dialog().show();

                            SETUP_ALARM = 5;
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
/*================================================================================================
 *                                       Alarm Times
================================================================================================ */
                case R.id.alarm_time_linear:
					show_time_dialog().show();
                    break;
			}
		}
		
	};
	
	protected Dialog show_time_dialog(){

        Dialog mDialog = null;

        mDialog = new TimePickerDialog(getActivity(), R.style.myTimeDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    Times_Calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    Times_Calendar.set(Calendar.MINUTE, minute);

                    ALARM_TIME = new SimpleDateFormat("HH:mm").format(Times_Calendar.getTime());
                    Alarm_Time_Result.setText(ALARM_TIME);

                }
			}, Times_Calendar.get(Calendar.HOUR_OF_DAY), Times_Calendar.get(Calendar.MINUTE), true);

        return mDialog;
    } 

}