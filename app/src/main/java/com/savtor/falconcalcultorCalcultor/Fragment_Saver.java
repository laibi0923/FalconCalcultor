package com.savtor.falconcalcultorCalcultor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
	
	EditText loanname_edittext, loannum_edittext, address_edittext, phone_edittext, remarks_edittext;
	View subview_loantype, subview_applytype, subview_loannum, subview_firstdue, subview_finaldue, subview_alertdate, subview_address, subview_phone, subview_remarks;
	ImageView loantype_icon, applytype_icon, loannum_icon, firstdue_icon, finaldue_icon, alert_icon, address_icon, phone_icon, remarks_icon;
	TextView loantype_textview, applytype_textview, firstdue_textview, firstdue_result_textview, finaldue_textview, finaldue_result_textview, alert_textview;
	
	
	

    private String This_Fragment_Name = "Fragment_Saver";

    private String Create_Date, Name, Loan_Num, First_duedate, Final_duedate, Duedate_Type, Address, Remarks;
    private double Loan_Amount, Loan_Rate;
    private int Loan_Trems, Alert_date_Type, Apply_Status, Loan_Type;
    private int apply_status_position, alert_date_position, loan_type_position;

	private EditText Name_ED, Remark_ED;
    private TextView First_duedate_TV;
    private TextView Final_duedate_TV;

    private EditText Loan_num_ED, Address_ED;

    private RadioButton Duedate_Type_normal_RB, Duedate_Type_eom_RB;
    private RadioGroup rGroup;
    
    RelativeLayout mDate_picker;
    LinearLayout saver_details_linear, loan_num_linear;
	
	private int bundle_trems;
    private float bundle_amount, bundle_rate;


    private String init_Name, init_LoanType, init_ApplyStatus, init_LoanNum, init_FirstDueDate, init_FinalDueDate, init_DueDate_Type, init_AlertDate, init_Address, init_Remarks;
    private String Edit_Mode;

    private Favourite_DataBasic favourite_dataBasic;

    Calendar calendar_final_duedate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);

		
		Bundle mBundle = getArguments();

        bundle_amount = mBundle.getFloat("loan_amount");
        bundle_trems = mBundle.getInt("loan_trems");
        bundle_rate = mBundle.getFloat("loan_rate");

        Edit_Mode = mBundle.getString("EDIT_MODE");

        if (Edit_Mode == "true"){

            Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode On");

            int databasic_ID  = mBundle.getInt("DB_ID");



            init_Name = String.valueOf(favourite_dataBasic.query(databasic_ID).getName());
            init_LoanType = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoan_Type());
            init_ApplyStatus = String.valueOf(favourite_dataBasic.query(databasic_ID).getApply_status());
            init_LoanNum = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoanNum());
            init_FirstDueDate = String.valueOf(favourite_dataBasic.query(databasic_ID).getFirst_dueddate());
            init_AlertDate = String.valueOf(favourite_dataBasic.query(databasic_ID).getAlert_date());
            init_Address = String.valueOf(favourite_dataBasic.query(databasic_ID).getAddress());
            init_Remarks = String.valueOf(favourite_dataBasic.query(databasic_ID).getRemarks());
            init_DueDate_Type = String.valueOf(favourite_dataBasic.query(databasic_ID).getDuedate_type());

        }else if (Edit_Mode == "false"){

            Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode Off");

            init_Name = "";
            init_LoanType = "0";
            init_ApplyStatus = "0";
            init_LoanNum = "";
            init_FirstDueDate = "Please Select your first duedate";
            init_FinalDueDate = "Will display your final duedate";
            init_AlertDate = "0";
            init_Address ="";
            init_Remarks = "";
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

        if (favourite_dataBasic != null){
            Log.e("DATA BASIC ACTION : ","數據庫關閉, 共" + favourite_dataBasic.getCount() + "條紀錄");  // [Log.e]
            favourite_dataBasic.close();
        }
    }

	
	
    //=============================================================================================
    // [?] 加入畫面內容
    public void Find_View(View v){
		
		loanname_edittext = (EditText) v.findViewById(R.id.save_name);
		loanname_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		
		subview_loantype = v.findViewById(R.id.subview_loantype);
		loantype_icon = (ImageView) subview_loantype.findViewById(R.id.sub_image);
		loantype_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		loantype_textview = (TextView) subview_loantype.findViewById(R.id.sub_textview);
		loantype_textview.setText("私人貸款");
		
		subview_applytype = v.findViewById(R.id.subview_applytype);
		applytype_icon = (ImageView) subview_applytype.findViewById(R.id.sub_image);
		applytype_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		applytype_textview = (TextView) subview_applytype.findViewById(R.id.sub_textview);
		applytype_textview.setText("批核狀況");
		
		subview_loannum = v.findViewById(R.id.subview_loannum);
		loannum_icon = (ImageView) subview_loannum.findViewById(R.id.sub_image);
		loannum_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		loannum_edittext = (EditText) subview_loannum.findViewById(R.id.sub_edittext);
		loannum_edittext.setSingleLine(true);
		loannum_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		loannum_edittext.setHint("貸款編號");
		
		subview_firstdue = v.findViewById(R.id.subview_firstdue);
		firstdue_icon = (ImageView) subview_firstdue.findViewById(R.id.sub_image);
		firstdue_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		firstdue_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview);
		firstdue_textview.setText("首期供款日");
		firstdue_result_textview = (TextView) subview_firstdue.findViewById(R.id.sub_textview_result);
		firstdue_result_textview.setText("");
		
		subview_finaldue = v.findViewById(R.id.subview_finaldue);
		finaldue_icon = (ImageView) subview_finaldue.findViewById(R.id.sub_image);
		finaldue_icon.setColorFilter(getContext().getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
		finaldue_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		finaldue_textview = (TextView) subview_finaldue.findViewById(R.id.sub_textview);
		finaldue_textview.setText("最後供款日");
		finaldue_result_textview = (TextView) subview_finaldue.findViewById(R.id.sub_textview_result);
		finaldue_result_textview.setText("");
		
		subview_alertdate = v.findViewById(R.id.subview_alertdate);
		alert_icon = (ImageView) subview_alertdate.findViewById(R.id.sub_image);
		alert_icon.setImageResource(R.drawable.ic_alarm_black_24dp);
		alert_textview = (TextView) subview_alertdate.findViewById(R.id.sub_textview);
		alert_textview.setText("設置提醒");
		
		subview_address = v.findViewById(R.id.subview_address);
		address_icon = (ImageView) subview_address.findViewById(R.id.sub_image);
		address_icon.setImageResource(R.drawable.ic_location_on_black_24dp);
		address_edittext = (EditText) subview_address.findViewById(R.id.sub_edittext);
		address_edittext.setSingleLine(true);
		address_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		address_edittext.setHint("地址");
		
		subview_phone = v.findViewById(R.id.subview_phone);
		phone_icon = (ImageView) subview_phone.findViewById(R.id.sub_image);
		phone_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black_24dp));
		phone_edittext = (EditText) subview_phone.findViewById(R.id.sub_edittext);
		phone_edittext.setSingleLine(true);
		phone_edittext.setInputType(InputType.TYPE_CLASS_PHONE);
		phone_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		phone_edittext.setHint("電話");
		
		subview_remarks = v.findViewById(R.id.subview_remarks);
		remarks_icon = (ImageView) subview_remarks.findViewById(R.id.sub_image);
		remarks_icon.setImageResource(R.drawable.ic_create_black_24dp);
		remarks_edittext = (EditText) subview_remarks.findViewById(R.id.sub_edittext);
		remarks_edittext.setOnFocusChangeListener(edText_FocusChangeListener);
		remarks_edittext.setHint("備註");
    }


    // 4.處理 Fist Due Date 及計算 Final Due Date
    protected Dialog onCreateDialog(final TextView btn) {

        Dialog mDialog = null;

        final Calendar c = Calendar.getInstance(); //當前時間

        mDialog = new DatePickerDialog(getActivity(), R.style.myDateDialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                btn.setText(year + "/" + (month + 1) + "/" + dayOfMonth);

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                calendar_final_duedate = c;
                
				
				Toast.makeText(getContext(), c.get(Calendar.MONTH) + "," + dayOfMonth + "," + year + "/" + c.getActualMaximum(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();

				
                if (month == 1 && dayOfMonth == c.getActualMaximum(Calendar.DAY_OF_MONTH) || dayOfMonth == 30){
					
					// 28 Feb & duedate = 30
					calendar_final_duedate.add(Calendar.MONTH, bundle_trems - 1);
					
                    Duedate_Type_normal_RB.setEnabled(true);
                    Duedate_Type_eom_RB.setEnabled(true);
					
					rGroup.clearCheck();
                    
                    Final_duedate_TV.setText("Will display your final duedate");

                }else if(dayOfMonth == 31){

					// duedate = 31
					calendar_final_duedate.add(Calendar.MONTH, bundle_trems - 1);
					
                    Duedate_Type_normal_RB.setEnabled(false);
                    Duedate_Type_eom_RB.setEnabled(true);
					
                    //rGroup.check(R.id.eom_duedate_rb);

                    Final_duedate_TV.setText(calendar_final_duedate.get(Calendar.YEAR) + "/" + (calendar_final_duedate.get(Calendar.MONTH) + 1) + "/" + calendar_final_duedate.getActualMaximum(Calendar.DAY_OF_MONTH));

                }
                else{
					
					// other due date
					calendar_final_duedate.add(Calendar.MONTH, bundle_trems - 1);
					
                    Duedate_Type_normal_RB.setEnabled(false);
                    Duedate_Type_eom_RB.setEnabled(false);
                    
					rGroup.clearCheck();
					
                    Final_duedate_TV.setText(calendar_final_duedate.get(Calendar.YEAR) + "/" + (calendar_final_duedate.get(Calendar.MONTH) + 1) + "/" + calendar_final_duedate.get(Calendar.DAY_OF_MONTH));
                }

            }

        }, c.get(Calendar.YEAR), // 年份
                c.get(Calendar.MONTH), // 月份
                c.get(Calendar.DAY_OF_MONTH) // 天数
        );

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


	String get_createdate, get_name, get_loantype, get_applystatus, get_loannum, get_firstdue, get_finaldue, get_alertdate, get_address, get_phone, get_remark;
    // 7.獲取用戶輸入資料
    public void get_input_values(){

        final Calendar today =  Calendar.getInstance();
        get_createdate = today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) +1) + "/" + today.get(Calendar.DAY_OF_MONTH);
		
		get_name = loanname_edittext.getText().toString();
		get_loantype = loantype_textview.getText().toString();
		get_applystatus = applytype_textview.getText().toString();
		get_loannum = Loan_num_ED.getText().toString();
		get_firstdue = firstdue_result_textview.getText().toString();
		get_finaldue = finaldue_result_textview.getText().toString();
		get_alertdate = alert_textview.getText().toString();
		get_address = address_edittext.getText().toString();
		get_phone = phone_edittext.getText().toString();
		get_remark = Remark_ED.getText().toString();
		
    }

    // [8] 用戶輸入資料加入 Favourite Data Base
    public void insert_to_fav_DB(){

        // [8]
        get_input_values();

        Favouite_Item fav_item = new Favouite_Item(Create_Date, Name, Loan_Amount, Loan_Trems, Loan_Rate, Apply_Status, Loan_Type, First_duedate, Final_duedate, Duedate_Type, Alert_date_Type, Remarks, Loan_Num, Address);

        favourite_dataBasic.inster(fav_item);

        getActivity().getSupportFragmentManager().popBackStack();
        Toast.makeText(getContext(), "This record have been save", Toast.LENGTH_LONG).show();
    }
}
