package com.savtor.falconcalcultorCalcultor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import com.savtor.falconcalaultorDatabase.*;
import com.savtor.falconcalcultor.*;
import android.widget.*;
import android.text.*;
import android.support.v4.content.*;



/**1
 * Created by GhostLeo_DT on 27/11/2017.
 */
public class Fragment_Saver extends Fragment {

    private String This_Fragment_Name = "Fragment_Saver";

    private View subview_loantype, subview_applytype, subview_loannum, subview_firstdue, subview_finaldue, subview_alertdate, subview_address, subview_phone, subview_remarks;
	private EditText loanname_edittext, loannum_edittext, address_edittext, phone_edittext, remarks_edittext;
	private ImageView loantype_icon, applytype_icon, loannum_icon, firstdue_icon, finaldue_icon, alert_icon, address_icon, phone_icon, remarks_icon;
	private TextView loantype_textview, applytype_textview, firstdue_textview, firstdue_result_textview, finaldue_textview, finaldue_result_textview, alert_textview;
    private LinearLayout duedate_linear, firstdue_linear, finaldue_linear, loantype_linear, applytype_linear, alert_linear;

    private Calendar Calendar_finaldue;

    private String get_createdate, get_name, get_loantype, get_applystatus, get_loannum, get_firstdue, get_finaldue, get_alertdate, get_address, get_phone, get_remark,  get_duedate_type;
    private double get_loanamount, get_loanrate;
    private int get_loantrems;

    private float bundle_amount, bundle_rate;
	private int bundle_trems;

    private String init_Name, init_LoanType, init_ApplyStatus, init_LoanNum, init_FirstDueDate, init_FirstDueDate_Result, init_FinalDueDate, init_FinalDueDate_Result, init_DueDate_Type, init_AlertDate, init_Address, init_Phone, init_Remarks;
    private String init_Name_text, init_LoanNum_text, init_Addrrss_Text, init_Phone_Text, init_Remarks_Text;
    private String Edit_Mode;

    private Favourite_DataBasic favourite_dataBasic;


    View dialog_view;

    LinearLayout choose_one, choose_two, choose_three, choose_four, choose_five;
    TextView choose_one_text, choose_two_text , choose_three_text, choose_four_text, choose_five_text, choose_dialog_title, choose_dialog_cancellbtn;
    ImageView choose_one_image, choose_two_image, choose_three_image, choose_four_image, choose_five_image;
    String choose_result;

    String db_get_applystatus, db_get_alertdate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

		Bundle mBundle = getArguments();

        bundle_amount = mBundle.getFloat("loan_amount");
        bundle_trems = mBundle.getInt("loan_trems");
        bundle_rate = mBundle.getFloat("loan_rate");

        Edit_Mode = mBundle.getString("EDIT_MODE");

        if (Edit_Mode == "true"){

            Edit_Mode_On(mBundle.getInt("DB_ID"));
			
        }else if (Edit_Mode == "false"){

            Edit_Mode_Off();

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calcultor_saver, container, false);

        Find_View(v);
		
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.saver_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			
			case R.id.save_to_fav:

                // [8]
                insert_to_fav_DB();

				break;
		}
		return super.onOptionsItemSelected(item);
	}

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

	
	
    //=============================================================================================
    // [?] 加入畫面內容
    public void Find_View(View v){

		loanname_edittext = (EditText) v.findViewById(R.id.save_name);
		loanname_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
        loanname_edittext.requestFocus();
		loanname_edittext.setHint(init_Name);
		loanname_edittext.setText(init_Name_text);
		
		subview_loantype = v.findViewById(R.id.subview_loantype);
		loantype_icon = (ImageView) subview_loantype.findViewById(R.id.sub_image);
		loantype_icon.setImageResource(R.drawable.ic_person_black_24dp);
		loantype_textview = (TextView) subview_loantype.findViewById(R.id.sub_textview);
		loantype_textview.setText(init_LoanType);
        loantype_linear = (LinearLayout) subview_loantype.findViewById(R.id.sub_linear);
        loantype_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                find_dialog_view();

                choose_one_text.setText(getResources().getString(R.string.loantype_personal));
                choose_one_image.setImageResource(R.drawable.ic_person_black_24dp);

                choose_two_text.setText(getResources().getString(R.string.loantype_mort));
                choose_two_image.setImageResource(R.drawable.ic_domain_black_24dp);

                choose_three_text.setText(getResources().getString(R.string.loantype_car));
                choose_three_image.setImageResource(R.drawable.ic_directions_car_black_24dp);

                choose_four.setVisibility(View.GONE);

                choose_five.setVisibility(View.GONE);

                show_alert_dialog("貸款類別", dialog_view, loantype_textview, loantype_icon, 1);

            }
        });

        subview_applytype = v.findViewById(R.id.subview_applytype);
		applytype_icon = (ImageView) subview_applytype.findViewById(R.id.sub_image);
		applytype_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_assignment_black_24dp));
		applytype_textview = (TextView) subview_applytype.findViewById(R.id.sub_textview);
		applytype_textview.setText(init_ApplyStatus);
        applytype_linear = (LinearLayout) subview_applytype.findViewById(R.id.sub_linear);
        applytype_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                find_dialog_view();

                choose_one_text.setText("未申請");

                choose_two_text.setText(getResources().getString(R.string.applytype_pending));

                choose_three_text.setText(getResources().getString(R.string.applytype_approval));

                choose_four_text.setText(getResources().getString(R.string.applytype_reject));

                choose_five_text.setText(getResources().getString(R.string.applytype_cancel));


                show_alert_dialog("批核狀況", dialog_view, applytype_textview, applytype_icon, 2);

            }
        });

        subview_loannum = v.findViewById(R.id.subview_loannum);
        subview_loannum.setVisibility(View.GONE);
		loannum_icon = (ImageView) subview_loannum.findViewById(R.id.sub_image);
		loannum_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_all_inclusive_black_24dp));
		loannum_edittext = (EditText) subview_loannum.findViewById(R.id.sub_edittext);
		loannum_edittext.setSingleLine(true);
		loannum_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		loannum_edittext.setHint(init_LoanNum);
		loannum_edittext.setText(init_LoanNum_text);

        duedate_linear = (LinearLayout) v.findViewById(R.id.duedate_linear);
        duedate_linear.setVisibility(View.GONE);


        subview_firstdue = v.findViewById(R.id.subview_firstdue);
        firstdue_icon = (ImageView) subview_firstdue.findViewById(R.id.sub_image);
		firstdue_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_event_black_24dp));
		firstdue_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview);
		firstdue_textview.setText(init_FirstDueDate);
		firstdue_result_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview_result);
		firstdue_result_textview.setText(init_FirstDueDate_Result);

        firstdue_linear = (LinearLayout) subview_firstdue.findViewById(R.id.sub_linear);
        firstdue_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_date_dialog().show();
            }
        });

		subview_finaldue = v.findViewById(R.id.subview_finaldue);
		finaldue_icon = (ImageView) subview_finaldue.findViewById(R.id.sub_image);
		finaldue_textview = (TextView) subview_finaldue.findViewById(R.id.sub_textview);
		finaldue_textview.setText(init_FinalDueDate);
		finaldue_result_textview = (TextView) subview_finaldue.findViewById(R.id.sub_textview_result);
		finaldue_result_textview.setText(init_FinalDueDate_Result);
        finaldue_result_textview.setTextColor(getResources().getColor(R.color.accent));

        finaldue_linear = (LinearLayout) subview_finaldue.findViewById(R.id.sub_linear);
        finaldue_linear.setClickable(false);
		
		subview_alertdate = v.findViewById(R.id.subview_alertdate);
		alert_icon = (ImageView) subview_alertdate.findViewById(R.id.sub_image);
		alert_icon.setImageResource(R.drawable.ic_alarm_black_24dp);
		alert_textview = (TextView) subview_alertdate.findViewById(R.id.sub_textview);
		alert_textview.setText(init_AlertDate);
        alert_linear = (LinearLayout) subview_alertdate.findViewById(R.id.sub_linear);
        alert_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                find_dialog_view();


                choose_one_text.setText("不用提醒我");

                choose_two_text.setText(getResources().getString(R.string.alertdate_3day));

                choose_three_text.setText(getResources().getString(R.string.alertdate_5day));

                choose_four_text.setText(getResources().getString(R.string.alertdate_7day));

                choose_five.setVisibility(View.GONE);

                show_alert_dialog("設置提醒", dialog_view, alert_textview, alert_icon, 3);

            }
        });

		
		subview_address = v.findViewById(R.id.subview_address);
		address_icon = (ImageView) subview_address.findViewById(R.id.sub_image);
		address_icon.setImageResource(R.drawable.ic_location_on_black_24dp);
		address_edittext = (EditText) subview_address.findViewById(R.id.sub_edittext);
		address_edittext.setSingleLine(true);
		address_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		address_edittext.setHint(init_Address);
		address_edittext.setText(init_Addrrss_Text);
		
		subview_phone = v.findViewById(R.id.subview_phone);
		phone_icon = (ImageView) subview_phone.findViewById(R.id.sub_image);
		phone_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_call_black_24dp));
		phone_edittext = (EditText) subview_phone.findViewById(R.id.sub_edittext);
		phone_edittext.setSingleLine(true);
		phone_edittext.setInputType(InputType.TYPE_CLASS_PHONE);
		phone_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		phone_edittext.setHint(init_Phone);
		phone_edittext.setText(init_Phone_Text);
		
		subview_remarks = v.findViewById(R.id.subview_remarks);
		remarks_icon = (ImageView) subview_remarks.findViewById(R.id.sub_image);
		remarks_icon.setImageResource(R.drawable.ic_create_black_24dp);
		remarks_edittext = (EditText) subview_remarks.findViewById(R.id.sub_edittext);
		remarks_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		remarks_edittext.setHint(init_Remarks);
		remarks_edittext.setText(init_Remarks_Text);

        if(db_get_applystatus.equals("2")){
            subview_Visibility();
        }else {
            subview_Gone();
        }

        if (db_get_alertdate.equals("0")){
            alert_textview.setTextColor(getResources().getColor(R.color.normal_textcolor));
        }else{
            alert_textview.setTextColor(getResources().getColor(R.color.accent));
        }

    }

    //=============================================================================================
    // [?]
    public void find_dialog_view(){

        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);

        choose_dialog_title = (TextView) dialog_view.findViewById(R.id.dialog_sav_title);

        choose_one = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch1);
        choose_one_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview1);
        choose_one_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image1);

        choose_two = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch2);
        choose_two_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview2);
        choose_two_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image2);

        choose_three = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch3);
        choose_three_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview3);
        choose_three_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image3);

        choose_four = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch4);
        choose_four_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview4);
        choose_four_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image4);


        choose_five = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch5);
        choose_five_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview5);
        choose_five_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image5);

        choose_dialog_cancellbtn = (TextView) dialog_view.findViewById(R.id.dialog_sav_cancelbtn);

    }

    //=============================================================================================
    // [?]
    private void show_alert_dialog(String Dialog_Title, View Dialogview, final TextView text, final ImageView icon, final int Case_id){

        final AlertDialog mAlertDialog = new AlertDialog.Builder(getContext()).create();
        mAlertDialog.setView(Dialogview);

        choose_dialog_title.setText(Dialog_Title);

        choose_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Case_id){

                    case 1:
                        choose_result = choose_one_text.getText().toString();
                        icon.setImageResource(R.drawable.ic_person_black_24dp);
                        text.setText(choose_result);
                        break;

                    case 2:
                        choose_result = getResources().getString(R.string.init_applystatuse);
                        text.setText(choose_result);
                        subview_Gone();
                        break;

                    case 3:
                        choose_result = getResources().getString(R.string.init_alertdate);
                        text.setText(choose_result);
                        text.setTextColor(getResources().getColor(R.color.normal_textcolor));
                        break;

                    case 4:
                        finaldue_result_textview.setText(Calendar_finaldue.get(Calendar.YEAR) + "/" + (Calendar_finaldue.get(Calendar.MONTH) + 1) + "/" + Calendar_finaldue.get(Calendar.DAY_OF_MONTH));
                        break;
                }

                mAlertDialog.dismiss();

            }
        });

        choose_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Case_id){

                    case 1:
                        choose_result = choose_two_text.getText().toString();
                        icon.setImageResource(R.drawable.ic_domain_black_24dp);
                        text.setText(choose_result);
                        break;

                    case 2:
                        choose_result = choose_two_text.getText().toString();
                        text.setText(choose_result);
                        subview_Gone();
                        break;

                    case 3:
                        choose_result = choose_two_text.getText().toString();
                        text.setText(choose_result);
                        text.setTextColor(getResources().getColor(R.color.accent));
                        break;

                    case 4:
                        finaldue_result_textview.setText(Calendar_finaldue.get(Calendar.YEAR) + "/" + (Calendar_finaldue.get(Calendar.MONTH) + 1) + "/" + Calendar_finaldue.getActualMaximum(Calendar.DAY_OF_MONTH));
                        break;
                }
                mAlertDialog.dismiss();
            }
        });

        choose_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Case_id){

                    case 1:
                        choose_result = choose_three_text.getText().toString();
                        icon.setImageResource(R.drawable.ic_directions_car_black_24dp);
                        text.setText(choose_result);
                        break;

                    case 2:
                        choose_result = choose_three_text.getText().toString();
                        text.setText(choose_result);
                        subview_Visibility();
                        break;

                    case 3:
                        choose_result = choose_three_text.getText().toString();
                        text.setText(choose_result);
                        text.setTextColor(getResources().getColor(R.color.accent));
                        break;

                    case 4:
                        break;
                }
                mAlertDialog.dismiss();
            }
        });

        choose_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Case_id){

                    case 1:
                        choose_result = choose_four_text.getText().toString();
                        icon.setImageResource(R.drawable.ic_domain_black_24dp);
                        text.setText(choose_result);
                        break;

                    case 2:
                        choose_result = choose_four_text.getText().toString();
                        text.setText(choose_result);
                        subview_Gone();
                        break;

                    case 3:
                        choose_result = choose_four_text.getText().toString();
                        text.setText(choose_result);
                        text.setTextColor(getResources().getColor(R.color.accent));
                        break;

                    case 4:
                        break;

                }
                mAlertDialog.dismiss();
            }
        });

        choose_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Case_id){

                    case 1:
                        choose_result = choose_five_text.getText().toString();
                        icon.setImageResource(R.drawable.ic_domain_black_24dp);
                        text.setText(choose_result);
                        break;

                    case 2:
                        choose_result = choose_five_text.getText().toString();
                        text.setText(choose_result);
                        subview_Gone();
                        break;

                    case 3:
                        choose_result = choose_five_text.getText().toString();
                        text.setText(choose_result);
                        break;

                    case 4:
                        break;

                }
                mAlertDialog.dismiss();
            }
        });

        choose_dialog_cancellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.show();
    }

    //=============================================================================================
    // [?] 取出本日日子
    public String get_today(){

        Calendar mCalendar = Calendar.getInstance();
        String today = mCalendar.get(Calendar.YEAR) + "/" + (mCalendar.get(Calendar.MONTH) + 1) + "/" + mCalendar.get(Calendar.DAY_OF_MONTH);
        return today;

    }

    //=============================================================================================
    // [?]
    protected Dialog show_date_dialog(){

        Dialog mDialog = null;

        final Calendar mCalendar = Calendar.getInstance();


        mDialog = new DatePickerDialog(getActivity(), R.style.myDateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                firstdue_result_textview.setText(mCalendar.get(Calendar.YEAR) + "/" + (mCalendar.get(Calendar.MONTH) + 1) + "/" + mCalendar.get(Calendar.DAY_OF_MONTH));

                Calendar_finaldue = mCalendar;

                if (month == 1 && dayOfMonth == mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) || dayOfMonth == 30){

                    Calendar_finaldue.add(Calendar.MONTH, bundle_trems - 1);

                    find_dialog_view();
                    choose_one_text.setText("Normal");
                    choose_two_text.setText("End of months");
                    choose_three.setVisibility(View.GONE);
                    choose_four.setVisibility(View.GONE);;
                    choose_five.setVisibility(View.GONE);

                    show_alert_dialog("日期設置", dialog_view, finaldue_result_textview, finaldue_icon, 4);

                }else if(dayOfMonth == 31){

                    Calendar_finaldue.add(Calendar.MONTH, bundle_trems - 1);
                    finaldue_result_textview.setText(Calendar_finaldue.get(Calendar.YEAR) + "/" + (Calendar_finaldue.get(Calendar.MONTH) + 1) + "/" + Calendar_finaldue.getActualMaximum(Calendar.DAY_OF_MONTH));

                }else {

                    Calendar_finaldue.add(Calendar.MONTH, bundle_trems - 1);
                    finaldue_result_textview.setText(Calendar_finaldue.get(Calendar.YEAR) + "/" + (Calendar_finaldue.get(Calendar.MONTH) + 1) + "/" + Calendar_finaldue.get(Calendar.DAY_OF_MONTH));
                }

            }
        },mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

        return mDialog;
    }

    //=============================================================================================
    // [?] 當畫而失去焦點時處理動作
	public View.OnFocusChangeListener edText_FocusChangeListener = new View.OnFocusChangeListener(){
		@Override
		public void onFocusChange(View v, boolean hasFocus)
		{
			// TODO: Implement this method
			if(!hasFocus){
                // [6]
				hideKeyboard(v);
			}
		}
	};

    //=============================================================================================
    // [?] 處理 Keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //=============================================================================================
    // [?] 獲取用戶輸入資料
    public void get_input_values(){

        final Calendar today =  Calendar.getInstance();
        get_createdate = today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) +1) + "/" + today.get(Calendar.DAY_OF_MONTH);
		
		get_name = loanname_edittext.getText().toString();

        if(loantype_textview.getText() == getResources().getString(R.string.init_loantype)){
            get_loantype = "0";
        }else if (loantype_textview.getText() == getResources().getString(R.string.loantype_personal)){
            get_loantype = "1";
        }else if (loantype_textview.getText() == getResources().getString(R.string.loantype_mort)){
            get_loantype = "2";
        }else if (loantype_textview.getText() == getResources().getString(R.string.loantype_car)){
            get_loantype = "3";
        }

        if (applytype_textview.getText() == getResources().getString(R.string.init_applystatuse)){
            get_applystatus = "0";
        }else if(applytype_textview.getText() == getResources().getString(R.string.applytype_pending)){
            get_applystatus = "1";
        }else if(applytype_textview.getText() == getResources().getString(R.string.applytype_approval)){
            get_applystatus = "2";
        }else if(applytype_textview.getText() == getResources().getString(R.string.applytype_reject)){
            get_applystatus = "3";
        }else if(applytype_textview.getText() == getResources().getString(R.string.applytype_cancel)){
            get_applystatus = "4";
        }

		get_loannum = loannum_edittext.getText().toString();
		
		get_loanamount = bundle_amount;
		get_loantrems = bundle_trems;
		get_loanrate = bundle_rate;
		
		get_firstdue = firstdue_result_textview.getText().toString();
		get_finaldue = finaldue_result_textview.getText().toString();

        if(alert_textview.getText() == getResources().getString(R.string.init_alertdate)){
            get_alertdate = "0";
        }else if(alert_textview.getText() == getResources().getString(R.string.alertdate_3day)){
            get_alertdate = "1";
        }else if(alert_textview.getText() == getResources().getString(R.string.alertdate_5day)){
            get_alertdate = "2";
        }else if(alert_textview.getText() == getResources().getString(R.string.alertdate_7day)){
            get_alertdate = "3";
        }

		get_address = address_edittext.getText().toString();
		get_phone = phone_edittext.getText().toString();
		get_remark = remarks_edittext.getText().toString();

    }

    //=============================================================================================
    // [8] 用戶輸入資料加入 Favourite Data Base
    public void insert_to_fav_DB(){

        favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

        get_input_values();

        Favouite_Item fav_item = new Favouite_Item(
				1,
                get_createdate,
                get_name,
                get_loantype,
                get_applystatus,
                get_loannum,
                get_loanamount,
                get_loantrems,
                get_loanrate,
                get_firstdue,
                get_finaldue,
                get_duedate_type,
				get_alertdate,
                get_address,
                get_phone,
                get_remark);

        favourite_dataBasic.inster(fav_item);

        favourite_dataBasic.close();

        getActivity().getSupportFragmentManager().popBackStack();
        Toast.makeText(getContext(), "This record have been save", Toast.LENGTH_LONG).show();
    }

    //=============================================================================================
    // [?] 判斷用戶是否選取 "已批核" 選項
    public void subview_Visibility(){
        subview_loannum.setVisibility(View.VISIBLE);
        duedate_linear.setVisibility(View.VISIBLE);
        firstdue_result_textview.setText(get_today());
    }

    //=============================================================================================
    // [?] 判斷用戶是否選取 "已批核" 以外選項
    public void subview_Gone(){
        subview_loannum.setVisibility(View.GONE);
        duedate_linear.setVisibility(View.GONE);
        loannum_edittext.setText("");
        firstdue_result_textview.setText("");
        finaldue_result_textview.setText("");
        alert_textview.setText(getResources().getString(R.string.init_alertdate));
    }

    //=============================================================================================
    // [?]
    public void Edit_Mode_On(int databasic_ID){

        Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode On");
        favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

        init_Name = getResources().getString(R.string.init_loanname);
        init_Name_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getName());

        String db_get_loantype = favourite_dataBasic.query(databasic_ID).getLoan_Type();

        if (db_get_loantype.equals("0")){
            init_LoanType = getResources().getString(R.string.init_loantype);
        }else  if(db_get_loantype.equals("1")){
            init_LoanType = getResources().getString(R.string.loantype_personal);
        }else if(db_get_loantype.equals("2")){
            init_LoanType = getResources().getString(R.string.loantype_mort);
        }else if(db_get_loantype.equals("3")){
            init_LoanType = getResources().getString(R.string.loantype_car);
        }

        db_get_applystatus = String.valueOf(favourite_dataBasic.query(databasic_ID).getApply_status());
        if (db_get_applystatus.equals("0")){
            init_ApplyStatus = getResources().getString(R.string.init_applystatuse);
        }else if(db_get_applystatus.equals("1")){
            init_ApplyStatus = getResources().getString(R.string.applytype_pending);
        }else if(db_get_applystatus.equals("2")){
            init_ApplyStatus = getResources().getString(R.string.applytype_approval);
        }else if(db_get_applystatus.equals("3")){
            init_ApplyStatus = getResources().getString(R.string.applytype_reject);
        }else if(db_get_applystatus.equals("4")){
            init_ApplyStatus = getResources().getString(R.string.applytype_cancel);
        }

        init_LoanNum = getResources().getString(R.string.init_loannum);
        init_LoanNum_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoanNum());

        init_FirstDueDate = getResources().getString(R.string.init_firstdue);
        init_FirstDueDate_Result = String.valueOf(favourite_dataBasic.query(databasic_ID).getFirst_dueddate());
        init_FinalDueDate = getResources().getString(R.string.init_finalduee);
        init_FinalDueDate_Result = String.valueOf(favourite_dataBasic.query(databasic_ID).getFinal_dueddate());

        db_get_alertdate = String.valueOf(favourite_dataBasic.query(databasic_ID).getAlert_date());
        if(db_get_alertdate.equals("0")){
            init_AlertDate = getResources().getString(R.string.init_alertdate);
        }else if(db_get_alertdate.equals("1")){
            init_AlertDate = getResources().getString(R.string.alertdate_3day);
        }else if(db_get_alertdate.equals("2")){
            init_AlertDate = getResources().getString(R.string.alertdate_5day);
        }else if(db_get_alertdate.equals("3")){
            init_AlertDate = getResources().getString(R.string.alertdate_7day);
        }

        init_DueDate_Type = String.valueOf(favourite_dataBasic.query(databasic_ID).getDuedate_type());

        init_Address = getResources().getString(R.string.init_address);
        init_Phone = getResources().getString(R.string.init_phone);
        init_Remarks = getResources().getString(R.string.init_remark);

        init_Addrrss_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getAddress());
        init_Phone_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getPhone());
        init_Remarks_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getRemarks());

        favourite_dataBasic.close();

    }

    //=============================================================================================
    // [?]
    public void Edit_Mode_Off(){

        Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode Off");

        init_Name = getResources().getString(R.string.init_loanname);
        init_LoanType = getResources().getString(R.string.init_loantype);
        init_ApplyStatus = getResources().getString(R.string.init_applystatuse);
        init_LoanNum = getResources().getString(R.string.init_loannum);

        init_FirstDueDate = getResources().getString(R.string.init_firstdue);
        init_FinalDueDate = getResources().getString(R.string.init_finalduee);
//            init_DueDate_Type = getResources().getString(R.string.init);
        init_AlertDate = getResources().getString(R.string.init_alertdate);

        init_Address = getResources().getString(R.string.init_address);
        init_Phone = getResources().getString(R.string.init_phone);
        init_Remarks = getResources().getString(R.string.init_remark);

    }

}
