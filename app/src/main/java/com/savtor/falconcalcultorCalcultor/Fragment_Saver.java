package com.savtor.falconcalcultorCalcultor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private  LinearLayout duedate_linear, firstdue_linear, finaldue_linear;

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

            Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode On");
            favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

            int databasic_ID  = mBundle.getInt("DB_ID");

			init_Name = getResources().getString(R.string.init_loanname);
            init_Name_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getName());
			
            init_LoanType = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoan_Type());
            init_ApplyStatus = String.valueOf(favourite_dataBasic.query(databasic_ID).getApply_status());
            init_LoanNum = getResources().getString(R.string.init_loannum);
			init_LoanNum_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoanNum());
			
			init_FirstDueDate = getResources().getString(R.string.init_firstdue);
            init_FirstDueDate_Result = String.valueOf(favourite_dataBasic.query(databasic_ID).getFirst_dueddate());
			init_FinalDueDate = getResources().getString(R.string.init_finalduee);
			init_FinalDueDate_Result = String.valueOf(favourite_dataBasic.query(databasic_ID).getFinal_dueddate());
            init_AlertDate = String.valueOf(favourite_dataBasic.query(databasic_ID).getAlert_date());
			init_DueDate_Type = String.valueOf(favourite_dataBasic.query(databasic_ID).getDuedate_type());
			
            init_Address = getResources().getString(R.string.init_address);
            init_Phone = getResources().getString(R.string.init_phone);
            init_Remarks = getResources().getString(R.string.init_remark);
			
			init_Addrrss_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getAddress());
			init_Phone_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getPhone());
			init_Remarks_Text = String.valueOf(favourite_dataBasic.query(databasic_ID).getRemarks());

            favourite_dataBasic.close();
			
        }else if (Edit_Mode == "false"){

            Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode Off");

            init_Name = getResources().getString(R.string.init_loanname);
            init_LoanType = getResources().getString(R.string.init_loantype);
            init_ApplyStatus = getResources().getString(R.string.init_applystatuse);
            init_LoanNum = getResources().getString(R.string.init_loannum);
			
            init_FirstDueDate = getResources().getString(R.string.init_firstdue);
			init_FirstDueDate_Result = get_today();
            init_FinalDueDate = getResources().getString(R.string.init_finalduee);
//            init_DueDate_Type = getResources().getString(R.string.init);
            init_AlertDate = getResources().getString(R.string.init_alertdate);
			
            init_Address = getResources().getString(R.string.init_address);
            init_Phone = getResources().getString(R.string.init_phone);
            init_Remarks = getResources().getString(R.string.init_remark);

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calcultor_saver, container, false);

        Find_View(v);   // [1]
		
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

//        if (favourite_dataBasic != null){
//            Log.e("DATA BASIC ACTION : ","數據庫關閉, 共" + favourite_dataBasic.getCount() + "條紀錄");  // [Log.e]
//            favourite_dataBasic.close();
//        }
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
		loantype_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		loantype_textview = (TextView) subview_loantype.findViewById(R.id.sub_textview);
		loantype_textview.setText(init_LoanType);
		
		subview_applytype = v.findViewById(R.id.subview_applytype);
		applytype_icon = (ImageView) subview_applytype.findViewById(R.id.sub_image);
		applytype_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		applytype_textview = (TextView) subview_applytype.findViewById(R.id.sub_textview);
		applytype_textview.setText(init_ApplyStatus);
		
		subview_loannum = v.findViewById(R.id.subview_loannum);
//        subview_loannum.setVisibility(View.GONE);
		loannum_icon = (ImageView) subview_loannum.findViewById(R.id.sub_image);
		loannum_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		loannum_edittext = (EditText) subview_loannum.findViewById(R.id.sub_edittext);
		loannum_edittext.setSingleLine(true);
		loannum_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		loannum_edittext.setHint(init_LoanNum);
		loannum_edittext.setText(init_LoanNum_text);

        duedate_linear = (LinearLayout) v.findViewById(R.id.duedate_linear);
//        duedate_linear.setVisibility(View.GONE);


        subview_firstdue = v.findViewById(R.id.subview_firstdue);

        firstdue_icon = (ImageView) subview_firstdue.findViewById(R.id.sub_image);
		firstdue_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		firstdue_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview);
		firstdue_textview.setText(init_FirstDueDate);
		firstdue_result_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview_result);
		firstdue_result_textview.setText(init_FirstDueDate_Result);

        firstdue_linear = (LinearLayout) subview_firstdue.findViewById(R.id.sub_linear);
        firstdue_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
//                Toast.makeText(getContext(), "HI", Toast.LENGTH_LONG).show();
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
    }

    public String get_today(){

        Calendar mCalendar = Calendar.getInstance();
        String today = mCalendar.get(Calendar.YEAR) + "/" + (mCalendar.get(Calendar.MONTH) + 1) + "/" + mCalendar.get(Calendar.DAY_OF_MONTH);
        return today;

    }


    protected Dialog showDialog(int CaseID){

        switch (CaseID){

            case 1:

                show_date_dialog().show();

                break;

        }

        return null;
    }


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

                    //finaldue_result_textview.setText(Calendar_finaldue.get(Calendar.YEAR) + "/" + (Calendar_finaldue.get(Calendar.MONTH) + 1) + "/" + Calendar_finaldue.get(Calendar.DAY_OF_MONTH));

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

    // 5.當畫而失去焦點時處理動作
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

    // 6.處理 Keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // 7.獲取用戶輸入資料
    public void get_input_values(){

        final Calendar today =  Calendar.getInstance();
        get_createdate = today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) +1) + "/" + today.get(Calendar.DAY_OF_MONTH);
		
		get_name = loanname_edittext.getText().toString();
		get_loantype = loantype_textview.getText().toString();
		get_applystatus = applytype_textview.getText().toString();
		get_loannum = loannum_edittext.getText().toString();
		
		get_loanamount = bundle_amount;
		get_loantrems = bundle_trems;
		get_loanrate = bundle_rate;
		
		get_firstdue = firstdue_result_textview.getText().toString();
		get_finaldue = finaldue_result_textview.getText().toString();
		
		get_alertdate = alert_textview.getText().toString();
		get_address = address_edittext.getText().toString();
		get_phone = phone_edittext.getText().toString();
		get_remark = remarks_edittext.getText().toString();

    }

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
}
