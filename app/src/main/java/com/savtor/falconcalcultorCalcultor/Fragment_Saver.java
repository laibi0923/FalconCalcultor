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



/**
 * Created by GhostLeo_DT on 27/11/2017.
 */
public class Fragment_Saver extends Fragment {

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
    private Spinner Alert_Date_Spinner, Apply_Status_Spinner, Loan_Type_Spinner;
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

        Find_View_Data();

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

        Name_ED = (EditText) v.findViewById(R.id.save_name);
        Name_ED.setOnFocusChangeListener(edText_FocusChangeListener);

        Loan_Type_Spinner = (Spinner) v.findViewById(R.id.loan_type_spinner);
        ArrayAdapter<CharSequence> Loan_Type_list = ArrayAdapter.createFromResource(getActivity(), R.array.loan_type_spinner, android.R.layout.simple_spinner_dropdown_item);
        Loan_Type_Spinner.setAdapter(Loan_Type_list);
        Loan_Type_Spinner.setOnItemSelectedListener(Spinner_OnItemSelectedListener);    // [2]

        Apply_Status_Spinner = (Spinner) v.findViewById(R.id.apply_status_spinner);
        ArrayAdapter<CharSequence> Apply_Status_list = ArrayAdapter.createFromResource(getActivity(), R.array.apply_status_spinner, android.R.layout.simple_spinner_dropdown_item);
        Apply_Status_Spinner.setAdapter(Apply_Status_list);
        Apply_Status_Spinner.setOnItemSelectedListener(Spinner_OnItemSelectedListener); // [2]

        loan_num_linear = (LinearLayout) v.findViewById(R.id.loan_num_linear);
        loan_num_linear.setVisibility(View.GONE);
        Loan_num_ED = (EditText) v.findViewById(R.id.save_loan_num);

        saver_details_linear = (LinearLayout) v.findViewById(R.id.saver_details_linear);
        saver_details_linear.setVisibility(View.GONE);

        First_duedate_TV = (TextView) v.findViewById(R.id.saver_first_duedate);

        mDate_picker = (RelativeLayout) v.findViewById(R.id.saver_datepicker);
        mDate_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(First_duedate_TV).show();
            }
        });

        rGroup = (RadioGroup) v.findViewById(R.id.due_date_type);
        rGroup.setOnCheckedChangeListener(RG_onchecked_listener);   // [3]
        rGroup.setEnabled(false);

        Duedate_Type_normal_RB = (RadioButton) v.findViewById(R.id.normal_duedate_rb);

        Duedate_Type_eom_RB = (RadioButton) v.findViewById(R.id.eom_duedate_rb);

        Final_duedate_TV = (TextView) v.findViewById(R.id.saver_final_duedate);

        Alert_Date_Spinner = (Spinner) v.findViewById(R.id.alert_date_spinner);
        ArrayAdapter<CharSequence> Alert_Date_list = ArrayAdapter.createFromResource(getActivity(), R.array.alert_date_spinner, android.R.layout.simple_spinner_dropdown_item);
        Alert_Date_Spinner.setAdapter(Alert_Date_list);
        Alert_Date_Spinner.setOnItemSelectedListener(Spinner_OnItemSelectedListener);   // [2]

        Address_ED = (EditText) v.findViewById(R.id.save_address);

        Remark_ED = (EditText) v.findViewById(R.id.save_remarks);
        Remark_ED.setOnFocusChangeListener(edText_FocusChangeListener);



    }

    public void Find_View_Data(){

        Name_ED.setText(init_Name);

        // Loan Type
        if (init_LoanType == "0"){
            Loan_Type_Spinner.setSelection(0);
        }else if(init_LoanType == "1"){
            Loan_Type_Spinner.setSelection(1);
        }else if (init_LoanType == "2"){
            Loan_Type_Spinner.setSelection(2);

        }

        // Apply Status
        if (init_ApplyStatus == "0"){

            Apply_Status_Spinner.setSelection(0);

        }else if(init_ApplyStatus == "1"){

            Apply_Status_Spinner.setSelection(1);

        }else if (init_ApplyStatus == "2" ){

            Apply_Status_Spinner.setSelection(2);

        }else if (init_ApplyStatus == "3"){

            Apply_Status_Spinner.setSelection(3);

        }


        Loan_num_ED.setText(init_LoanNum);

        First_duedate_TV.setText(init_FirstDueDate);




        Final_duedate_TV.setText(init_FinalDueDate);

        // Alert Spinner
        if (init_AlertDate == "0"){
            Alert_Date_Spinner.setSelection(0);
        }else if (init_AlertDate == "1"){
            Alert_Date_Spinner.setSelection(1);
        }else if (init_AlertDate == "2"){
            Alert_Date_Spinner.setSelection(2);
        }else if (init_AlertDate == "3"){
            Alert_Date_Spinner.setSelection(3);
        }else if (init_AlertDate == "4"){
            Alert_Date_Spinner.setSelection(4);
        }


        Address_ED.setText(init_Address);

        Remark_ED.setText(init_Remarks);



//        Duedate_Type_normal_RB.setEnabled(false);
//        Duedate_Type_eom_RB.setEnabled(false);
        Loan_Type_Spinner.setSelection(0);
        Alert_Date_Spinner.setSelection(0);


    }


    // 2. 處理 Spinner
    public AdapterView.OnItemSelectedListener Spinner_OnItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (parent.getId()){

                case R.id.loan_type_spinner:
                    loan_type_position = position;
                    break;

                case R.id.alert_date_spinner:
                    alert_date_position = position;
                    break;

                case R.id.apply_status_spinner:

                    if(position == 2){
                        saver_details_linear.setVisibility(View.VISIBLE);
                        loan_num_linear.setVisibility(View.VISIBLE);
                    }else {
                        saver_details_linear.setVisibility(View.GONE);
                        loan_num_linear.setVisibility(View.GONE);
                    }

                    apply_status_position = position;

                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            loan_type_position = 99;
            alert_date_position = 99;
            apply_status_position = 99;
        }

    };


    // 3. 處理 RadioGroup
    private RadioGroup.OnCheckedChangeListener RG_onchecked_listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){

                case R.id.normal_duedate_rb:
                    Final_duedate_TV.setText(calendar_final_duedate.get(Calendar.YEAR) + "/" + (calendar_final_duedate.get(Calendar.MONTH) + 1) + "/" + calendar_final_duedate.get(Calendar.DAY_OF_MONTH));
                    break;

                case R.id.eom_duedate_rb:
                    Final_duedate_TV.setText(calendar_final_duedate.get(Calendar.YEAR) + "/" + (calendar_final_duedate.get(Calendar.MONTH) + 1) + "/" + calendar_final_duedate.getActualMaximum(Calendar.DAY_OF_MONTH));
                    break;
            }

        }
    };


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
					
                    rGroup.check(R.id.eom_duedate_rb);

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


    // 7.獲取用戶輸入資料
    public void get_input_values(){

        final Calendar today =  Calendar.getInstance();
        String CreateDate = today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) +1) + "/" + today.get(Calendar.DAY_OF_MONTH);
        Create_Date = CreateDate;

        Loan_Amount = bundle_amount;

        Loan_Trems = bundle_trems;

        Loan_Rate = bundle_rate;

        Name = Name_ED.getText().toString();

        Loan_Num = Loan_num_ED.getText().toString();

        // 由於 Loan Type, Apply Status, Alert Date 己於 Spinner 設置, 故不用再重新獲取
        Apply_Status = apply_status_position;

        Loan_Type = loan_type_position;

        if(First_duedate_TV.getText().toString() == "Please Select your first duedate"){
            First_duedate = "";
        }else {
            First_duedate = First_duedate_TV.getText().toString();
        }

        if(Final_duedate_TV.getText().toString() == "Will display your final duedate"){
            Final_duedate = "";
        }else {
            Final_duedate = Final_duedate_TV.getText().toString();
        }


        if(Duedate_Type_normal_RB.isChecked()){
            Duedate_Type = "1";
        }else if(Duedate_Type_eom_RB.isChecked()){
            Duedate_Type = "2";
        }else {
            Duedate_Type = "0";
        }

        Alert_date_Type = alert_date_position;

        Address = Address_ED.getText().toString();

        Remarks = Remark_ED.getText().toString();

    }

    // 8.用戶輸入資料加入 Favourite Data Base
    public void insert_to_fav_DB(){

        // [8]
        get_input_values();

        Favouite_Item fav_item = new Favouite_Item(Create_Date, Name, Loan_Amount, Loan_Trems, Loan_Rate, Apply_Status, Loan_Type, First_duedate, Final_duedate, Duedate_Type, Alert_date_Type, Remarks, Loan_Num, Address);

        favourite_dataBasic.inster(fav_item);

        getActivity().getSupportFragmentManager().popBackStack();
        Toast.makeText(getContext(), "This record have been save", Toast.LENGTH_LONG).show();
    }
}
