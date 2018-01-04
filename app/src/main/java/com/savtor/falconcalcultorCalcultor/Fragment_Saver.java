//package com.savtor.falconcalcultorCalcultor;
//
//import android.app.*;
//import android.os.*;
//import android.support.annotation.*;
//import android.support.v4.app.*;
//import android.support.v4.widget.TextViewCompat;
//import android.support.v7.app.*;
//import android.text.*;
//import android.util.*;
//import android.view.*;
//import android.view.inputmethod.*;
//import android.widget.*;
//import com.savtor.falconcalaultorDatabase.*;
//import com.savtor.falconcalcultor.*;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AlertDialog;
//import com.savtor.falconcalcultorFavoutive.*;
//import com.savtor.AlarmNotification.*;
//
//
///**
// * Created by GhostLeo_DT on 27/11/2017.
// */
//public class Fragment_Saver extends Fragment {
//
//    private String This_Fragment_Name = "Saver Fragment";
//
//	//    For Find View
//    private View SV_LoanType, SV_ApplyType, SV_LoanNum, SV_FirstDue, SV_FinalDue, SV_AlertType, SV_AlertTime, SV_Address, SV_PhoneNum, SV_Remarks, SV_Loan_Amount, SV_Loan_Rate, SV_Loan_Trems, SV_Loan_Installment;
//	private EditText ED_LoanName, ED_LoanNum, ED_LoanAddress, ED_PhoneNum, ED_Remarks;
//	private ImageView IV_LoanType, IV_Apply_Type, IV_Loan_Num, IV_FirstDue, IV_FinalDue, IV_Alert, IV_Address, IV_PhoneNum, IV_Remarks, IV_Loan_Amount, IV_Loan_Rate, IV_Loan_Trems, IV_Loan_Installment;
//	private TextView TV_LoanType, TV_ApplyType, TV_FirstDue, TV_Final_FinalDue,  TV_AlertType,  TV_AlertTime, TV_Loan_Amount, TV_Loan_Rate, TV_Loan_Trems, TV_Loan_Installment;
//    private TextView TV_FirstDue_Result, TV_FinalDue_Result, TV_AlertType_Result, TV_AlertTime_Result, TV_Loan_Amount_Result, TV_Loan_Rate_Result, TV_Loan_Trems_Result, TV_Loan_Installment_Result;
//    private LinearLayout Linear_DueDate, Linear_FirstDue, Linear_FinalDue, Linear_LoanType, Linear_ApplyType, Linear_AlertType, Linear_AlertTime;
//
//    //    For Find Dialog View
//    private AlertDialog mAlertDialog;
//    private LinearLayout choose_one, choose_two, choose_three, choose_four, choose_five;
//    private TextView choose_one_text, choose_two_text , choose_three_text, choose_four_text, choose_five_text, choose_dialog_title, choose_dialog_cancellbtn;
//    private ImageView choose_one_image, choose_two_image, choose_three_image, choose_four_image, choose_five_image;
//
//    private Calendar firstdue_Calendar, finaldue_Calender, Alarm_Calendar;
//
//    private String get_createdate, get_name, get_loannum, get_firstdue, get_finaldue, get_alerttime, get_address, get_phone, get_remark;
//    private double get_loanamount, get_loanrate;
//
//    private int get_loantype, get_applystatus, get_loantrems, get_alertdate_type;
//
//    private int set_remind_date;
//
//
//    private String restore_LoanName_text, restore_LoanType_text, restore_ApplyStatus_text, restore_LoanNum_text,
//                   restore_FirstDueDate_text, restore_FinalDueDate_text, restore_AlertDate_text, restore_AlertTime_text,
//                   restore_Address_text, restore_Phone_text, restore_Remarks_text, restore_Loan_Amount, restore_Loan_Rate, restore_Loan_Trems, restore_Loan_Installment;
//
//    private String Edit_Mode;
//
//    private Favourite_DataBasic favourite_dataBasic;
//
//    private double bundle_amount, bundle_rate;
//    private int bundle_trems;
//    private int DB_ID;
//
//    private int db_get_loantype, db_get_applystatus, db_get_alertdate;
//
//    private View dialog_view;
//
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        setHasOptionsMenu(true);
//
//		Bundle mBundle = getArguments();
//
//        if(mBundle != null){
//            bundle_amount = mBundle.getDouble("loan_amount");
//            bundle_trems = mBundle.getInt("loan_trems");
//            bundle_rate = mBundle.getDouble("loan_rate");
//
//            Edit_Mode = mBundle.getString("EDIT_MODE");
//            DB_ID = mBundle.getInt("DB_ID");
//
//            if (Edit_Mode == "true"){
//
//                Edit_Mode_On(DB_ID);
//
//            }else if (Edit_Mode == "false"){
//
//                Edit_Mode_Off();
//
//            }
//
//        } else{
//			Edit_Mode = "false";
//			Edit_Mode_Off();
//		}
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.saver_main, container, false);
//
//        Find_View(v);
//
//        return v;
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.saver_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item)
//	{
//		// TODO: Implement this method
//		switch(item.getItemId()){
//
//			case R.id.save_to_fav:
//
//                Falcon_AlramManager mAlramManager = new Falcon_AlramManager();
//
//                // [8]
//                if (Edit_Mode == "true"){
//
//                    update_to_fav_DB();
//
//					// Cancel Alram by Database id number
//					// Setup new Alram if Alert type not is 0
//					// Alram request code = Databasic id number
//					// Get Year / Month / Date / Hour / Mintus / Secord / Trems / Alert date / Name / Installment
//					if (TV_AlertTime_Result != null){
//
//                        mAlramManager = new Falcon_AlramManager();
//
//                        // 先取消該條紀錄所有 Alram
//                        mAlramManager.Cancel_Alram(getContext(), DB_ID, bundle_trems);
//
//                        // 輸入新定義 Alram
//                        mAlramManager.Setup_Alram(getContext(),
//                                DB_ID,
//                                firstdue_Calendar.get(Calendar.YEAR),
//                                firstdue_Calendar.get(Calendar.MONTH),
//                                firstdue_Calendar.get(Calendar.DAY_OF_MONTH),
//                                Alarm_Calendar.get(Calendar.HOUR_OF_DAY),
//                                Alarm_Calendar.get(Calendar.MINUTE),
//                                bundle_trems,
//                                set_remind_date,
//                                get_name,
//                                TV_Loan_Amount_Result.getText().toString());
//                    }
//
//                    getActivity().getSupportFragmentManager().popBackStack();
//
//                    Toast.makeText(getContext(), getString(R.string.Toast_String_update), Toast.LENGTH_SHORT).show();
//
//                }else if (Edit_Mode == "false"){
//
//                    insert_to_fav_DB();
//
//					// Setup new Alram if Alert type not is 0
//					// Alram request code = Database max id number
//					favourite_dataBasic = new Favourite_DataBasic(getContext(), This_Fragment_Name);
//					DB_ID = favourite_dataBasic.query_max_id();
//					favourite_dataBasic.close();
//
//                    Log.e("Max ID = ", DB_ID + "");
//
//                    if (TV_AlertTime_Result.getText() != null){
//
//                        mAlramManager.Setup_Alram(getContext(),
//                                DB_ID,
//								firstdue_Calendar.get(Calendar.YEAR),
//								firstdue_Calendar.get(Calendar.MONTH),
//								firstdue_Calendar.get(Calendar.DAY_OF_MONTH),
//								Alarm_Calendar.get(Calendar.HOUR_OF_DAY),
//								Alarm_Calendar.get(Calendar.MINUTE),
//                                bundle_trems,
//                                set_remind_date,
//                                get_name,
//                                "1939");
//
//                        Log.e("first due date", new SimpleDateFormat("yyyy/MM/dd").format(firstdue_Calendar.getTime()) + "");
//                    }
//
//
//
//                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
//                    mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
//                    mFragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
//                    mFragmentTransaction.replace(R.id.mFrameLayout, new Fragment_Favourite());
//                    mFragmentTransaction.commit();
//
//                    Toast.makeText(getContext(), getString(R.string.Toast_String_insert), Toast.LENGTH_SHORT).show();
//                }
//
//				break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//    @Override
//    public void onDestroy() {
//
//        super.onDestroy();
//    }
//
//
//    //=============================================================================================
//    // [1] 加入畫面內容
//    public void Find_View(View v){
//
//		ED_LoanName = (EditText) v.findViewById(R.id.save_name);
//        ED_LoanName.setOnFocusChangeListener(edText_FocusChangeListener);
//        ED_LoanName.requestFocus();
//        ED_LoanName.setHint(getString(R.string.loanname_hint));
//		ED_LoanName.setText(restore_LoanName_text);
//
////        Loan Amount
//        SV_Loan_Amount = v.findViewById(R.id.include_loan_amount);
//
//        IV_Loan_Amount = (ImageView) SV_Loan_Amount.findViewById(R.id.sub_image);
//        IV_Loan_Amount.setImageResource(R.drawable.ic_data_usage_black_24dp);
//        TV_Loan_Amount = (TextView) SV_Loan_Amount.findViewById(R.id.sub_textview);
//        TV_Loan_Amount.setText("貸款銀碼");
//        TV_Loan_Amount_Result = (TextView) SV_Loan_Amount.findViewById(R.id.sub_textview_result);
//        TV_Loan_Amount_Result.setText(restore_Loan_Amount);
//
////        Loan Rate
//        SV_Loan_Rate = v.findViewById(R.id.include_loan_rate);
//
//        IV_Loan_Rate = (ImageView) SV_Loan_Rate.findViewById(R.id.sub_image);
//        IV_Loan_Rate.setImageResource(R.drawable.ic_data_usage_black_24dp);
//        TV_Loan_Rate = (TextView) SV_Loan_Rate.findViewById(R.id.sub_textview);
//        TV_Loan_Rate.setText("利率");
//        TV_Loan_Rate_Result = (TextView) SV_Loan_Rate.findViewById(R.id.sub_textview_result);
//        TV_Loan_Rate_Result.setText(restore_Loan_Rate);
//
////        Loan Trems
//        SV_Loan_Trems = v.findViewById(R.id.include_loan_trems);
//
//        IV_Loan_Trems = (ImageView) SV_Loan_Trems.findViewById(R.id.sub_image);
//        IV_Loan_Trems.setImageResource(R.drawable.ic_data_usage_black_24dp);
//        TV_Loan_Trems = (TextView) SV_Loan_Trems.findViewById(R.id.sub_textview);
//        TV_Loan_Trems.setText("期數");
//        TV_Loan_Trems_Result = (TextView) SV_Loan_Trems.findViewById(R.id.sub_textview_result);
//        TV_Loan_Trems_Result.setText(restore_Loan_Trems);
//
////        Loan Installment
//        SV_Loan_Installment = v.findViewById(R.id.include_loan_installment);
//
//        IV_Loan_Installment = (ImageView) SV_Loan_Installment.findViewById(R.id.sub_image);
//        IV_Loan_Installment.setImageResource(R.drawable.ic_data_usage_black_24dp);
//        TV_Loan_Installment = (TextView) SV_Loan_Installment.findViewById(R.id.sub_textview);
//        TV_Loan_Installment.setText("每月供款");
//        TV_Loan_Installment_Result = (TextView) SV_Loan_Installment.findViewById(R.id.sub_textview_result);
//        TV_Loan_Installment_Result.setText(restore_Loan_Installment);
//
//
////        Loan Type
//		SV_LoanType = v.findViewById(R.id.subview_loantype);
//
//		IV_LoanType = (ImageView) SV_LoanType.findViewById(R.id.sub_image);
//        IV_LoanType.setImageResource(R.drawable.ic_person_black_24dp);
//
//		TV_LoanType = (TextView) SV_LoanType.findViewById(R.id.sub_textview);
//        TV_LoanType.setText(restore_LoanType_text);
//
//        Linear_LoanType = (LinearLayout) SV_LoanType.findViewById(R.id.sub_linear);
//        Linear_LoanType.setTag(1);
//        Linear_LoanType.setOnClickListener(Show_Dialog_OnClickListener);
//
//
////        Apply Type
//        SV_ApplyType = v.findViewById(R.id.subview_applytype);
//
//		IV_Apply_Type = (ImageView) SV_ApplyType.findViewById(R.id.sub_image);
//        IV_Apply_Type.setImageDrawable(getResources().getDrawable(R.drawable.ic_assignment_black_24dp));
//
//		TV_ApplyType = (TextView) SV_ApplyType.findViewById(R.id.sub_textview);
//        TV_ApplyType.setText(restore_ApplyStatus_text);
//
//        Linear_ApplyType = (LinearLayout) SV_ApplyType.findViewById(R.id.sub_linear);
//        Linear_ApplyType.setTag(2);
//        Linear_ApplyType.setOnClickListener(Show_Dialog_OnClickListener);
//
////        Loan Number
//        SV_LoanNum = v.findViewById(R.id.subview_loannum);
//
//		IV_Loan_Num = (ImageView) SV_LoanNum.findViewById(R.id.sub_image);
//        IV_Loan_Num.setImageDrawable(getResources().getDrawable(R.drawable.ic_count));
//
//		ED_LoanNum = (EditText) SV_LoanNum.findViewById(R.id.sub_edittext);
//        ED_LoanNum.setSingleLine(true);
//        ED_LoanNum.setOnFocusChangeListener(edText_FocusChangeListener);
//        ED_LoanNum.setHint(getString(R.string.loannum_hint));
//        ED_LoanNum.setText(restore_LoanNum_text);
//
////        ***********
//        Linear_DueDate = (LinearLayout) v.findViewById(R.id.duedate_linear);
//
////      First  Due Date
//        SV_FirstDue = v.findViewById(R.id.subview_firstdue);
//
//        IV_FirstDue = (ImageView) SV_FirstDue.findViewById(R.id.sub_image);
//        IV_FirstDue.setImageDrawable(getResources().getDrawable(R.drawable.ic_event_black_24dp));
//
//		TV_FirstDue = (TextView) SV_FirstDue.findViewById(R.id.sub_textview);
//        TV_FirstDue.setText(getString(R.string.firstdue_hint));
//
//		TV_FirstDue_Result = (TextView) SV_FirstDue.findViewById(R.id.sub_textview_result);
//        TV_FirstDue_Result.setText(restore_FirstDueDate_text);
//
//        Linear_FirstDue = (LinearLayout) SV_FirstDue.findViewById(R.id.sub_linear);
//        Linear_FirstDue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show_date_dialog().show();
//            }
//        });
//
////      Final Due Date
//		SV_FinalDue = v.findViewById(R.id.subview_finaldue);
//
//		IV_FinalDue = (ImageView) SV_FinalDue.findViewById(R.id.sub_image);
//		TV_Final_FinalDue = (TextView) SV_FinalDue.findViewById(R.id.sub_textview);
//        TV_Final_FinalDue.setText(getString(R.string.finaldue_hint));
//
//		TV_FinalDue_Result = (TextView) SV_FinalDue.findViewById(R.id.sub_textview_result);
//        TV_FinalDue_Result.setText(restore_FinalDueDate_text);
//        TV_FinalDue_Result.setTextColor(getResources().getColor(R.color.deep_teal_200));
//
//        Linear_FinalDue = (LinearLayout) SV_FinalDue.findViewById(R.id.sub_linear);
//        Linear_FinalDue.setClickable(false);
//
////        ******************************************************************************************
////        未開放功能
////        ******************************************************************************************
//
//        SV_AlertType = v.findViewById(R.id.subview_alertdate);
////		subview_alertdate.setVisibility(View.GONE);
//
//		IV_Alert = (ImageView) SV_AlertType.findViewById(R.id.sub_image);
//		IV_Alert.setImageResource(R.drawable.ic_alarm_black_24dp);
//
//		TV_AlertType = (TextView) SV_AlertType.findViewById(R.id.sub_textview);
//		TV_AlertType.setText(getString(R.string.alertdate_hint));
//
//        TV_AlertType_Result = (TextView) SV_AlertType.findViewById(R.id.sub_textview_result);
//        TV_AlertType_Result.setTextColor(getResources().getColor(R.color.deep_teal_200));
//		TV_AlertType_Result.setText(restore_AlertDate_text);
//
//        Linear_AlertType = (LinearLayout) SV_AlertType.findViewById(R.id.sub_linear);
//        Linear_AlertType.setTag(3);
//        Linear_AlertType.setOnClickListener(Show_Dialog_OnClickListener);
//
//
////        Alert Time
//        SV_AlertTime = v.findViewById(R.id.subview_alerTime);
////        SV_AlertTime.setVisibility(View.GONE);
//
//        TV_AlertTime = (TextView) SV_AlertTime.findViewById(R.id.sub_textview);
//        TV_AlertTime.setText(getString(R.string.alertdate_hint));
//
//        TV_AlertTime_Result = (TextView) SV_AlertTime.findViewById(R.id.sub_textview_result);
//        TV_AlertTime_Result.setTextColor(getResources().getColor(R.color.deep_teal_200));
//		TV_AlertTime_Result.setText(restore_AlertTime_text);
//
//        Linear_AlertTime = (LinearLayout) SV_AlertTime.findViewById(R.id.sub_linear);
//        Linear_AlertTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                show_time_dialog().show();
//
//            }
//        });
//
//
////        ******************************************************************************************
////        ******************************************************************************************
////        ******************************************************************************************
//
////        Address
//		SV_Address = v.findViewById(R.id.subview_address);
//
//		IV_Address = (ImageView) SV_Address.findViewById(R.id.sub_image);
//        IV_Address.setImageResource(R.drawable.ic_location_on_black_24dp);
//
//		ED_LoanAddress = (EditText) SV_Address.findViewById(R.id.sub_edittext);
//        ED_LoanAddress.setSingleLine(true);
//        ED_LoanAddress.setOnFocusChangeListener(edText_FocusChangeListener);
//        ED_LoanAddress.setHint(getString(R.string.address_hint));
//        ED_LoanAddress.setText(restore_Address_text);
//
////        Phone Num
//		SV_PhoneNum = v.findViewById(R.id.subview_phone);
//
//		IV_PhoneNum = (ImageView) SV_PhoneNum.findViewById(R.id.sub_image);
//        IV_PhoneNum.setImageDrawable(getResources().getDrawable(R.drawable.ic_call_black_24dp));
//
//		ED_PhoneNum = (EditText) SV_PhoneNum.findViewById(R.id.sub_edittext);
//        ED_PhoneNum.setSingleLine(true);
//        ED_PhoneNum.setInputType(InputType.TYPE_CLASS_PHONE);
//        ED_PhoneNum.setOnFocusChangeListener(edText_FocusChangeListener);
//        ED_PhoneNum.setHint(getString(R.string.phoneNum_hint));
//        ED_PhoneNum.setText(restore_Phone_text);
//
////        Remarks
//		SV_Remarks = v.findViewById(R.id.subview_remarks);
//
//		IV_Remarks = (ImageView) SV_Remarks.findViewById(R.id.sub_image);
//        IV_Remarks.setImageResource(R.drawable.ic_create_black_24dp);
//
//		ED_Remarks = (EditText) SV_Remarks.findViewById(R.id.sub_edittext);
//        ED_Remarks.setOnFocusChangeListener(edText_FocusChangeListener);
//        ED_Remarks.setHint(getString(R.string.remark_hint));
//        ED_Remarks.setText(restore_Remarks_text);
//
//        if(db_get_applystatus == 2){
//            subview_Visibility();
//        }else {
//            subview_Gone();
//        }
//
//    }
//
//    //=============================================================================================
//    // [2] 加入 Dialog 畫面內容
//    public void Find_Dialog_View(){
//
////        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);
//
//        choose_dialog_title = (TextView) dialog_view.findViewById(R.id.dialog_sav_title);
//
//        choose_one = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch1);
//        choose_one_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview1);
//        choose_one_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image1);
//
//        choose_two = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch2);
//        choose_two_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview2);
//        choose_two_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image2);
//
//        choose_three = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch3);
//        choose_three_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview3);
//        choose_three_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image3);
//
//        choose_four = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch4);
//        choose_four_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview4);
//        choose_four_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image4);
//
//
//        choose_five = (LinearLayout) dialog_view.findViewById(R.id.dialog_sav_ch5);
//        choose_five_text = (TextView) dialog_view.findViewById(R.id.dialog_sav_ch1_textview5);
//        choose_five_image = (ImageView) dialog_view.findViewById(R.id.dialog_sav_ch1_image5);
//
//        choose_dialog_cancellbtn = (TextView) dialog_view.findViewById(R.id.dialog_sav_cancelbtn);
//
//    }
//
//    //=============================================================================================
//    // []
//    private View.OnClickListener Show_Dialog_OnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            switch (Integer.parseInt(v.getTag().toString())){
//
//                case 1:
//                    Loan_Type_Dialog(getString(R.string.loamtype_dialog_title));
//                    break;
//
//                case 2:
//                    Apply_Status_Type_Dialog(getString(R.string.applytype_dialog_title));
//                    break;
//
//                case 3:
//                    Alert_Date_Type_Dialog(getString(R.string.alertdate_dialog_title));
//                    break;
//            }
//
//        }
//    };
//
//    //=============================================================================================
//    // [3] 點擊 Loan Type 時顯示此 Dialog
//    private void Loan_Type_Dialog(String Dialog_Title){
//
//        mAlertDialog = new AlertDialog.Builder(getContext()).create();
//
//        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);
//        mAlertDialog.setView(dialog_view);
//
//        Find_Dialog_View();
//
//        choose_dialog_title.setText(Dialog_Title);
//
//        choose_one_image.setImageResource(R.drawable.ic_person_black_24dp);
//        choose_two_image.setImageResource(R.drawable.ic_domain_black_24dp);
//        choose_three_image.setImageResource(R.drawable.ic_directions_car_black_24dp);
//
//        choose_one_text.setText(getString(R.string.loantype_personal));
//        choose_two_text.setText(R.string.loantype_mort);
//        choose_three_text.setText(R.string.loantype_car);
//
//        choose_one.setOnClickListener(Loan_Type_OnclickListener);
//        choose_two.setOnClickListener(Loan_Type_OnclickListener);
//        choose_three.setOnClickListener(Loan_Type_OnclickListener);
//        choose_four.setVisibility(View.GONE);
//
//        choose_five.setVisibility(View.GONE);
//
//        choose_dialog_cancellbtn.setOnClickListener(Loan_Type_OnclickListener);
//
//        mAlertDialog.show();
//
//    }
//
//    private View.OnClickListener Loan_Type_OnclickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            /*   Loan Type
//             *   0 = Personal Loan
//             *   1 = Mort Loan
//             *   2 = Car Loan
//             */
//            get_loantype = 0;
//
//            switch (v.getId()){
//
//                case R.id.dialog_sav_ch1:
//                    IV_LoanType.setImageResource(R.drawable.ic_person_black_24dp);
//                    TV_LoanType.setText(getString(R.string.loantype_personal));
//                    get_loantype = 0;
//                    break;
//
//                case R.id.dialog_sav_ch2:
//                    IV_LoanType.setImageResource(R.drawable.ic_domain_black_24dp);
//                    TV_LoanType.setText(R.string.loantype_mort);
//                    get_loantype = 1;
//                    break;
//
//                case R.id.dialog_sav_ch3:
//                    IV_LoanType.setImageResource(R.drawable.ic_directions_car_black_24dp);
//                    TV_LoanType.setText(getString(R.string.loantype_car));
//                    get_loantype = 2;
//                    break;
//
//                case R.id.dialog_sav_cancelbtn:
//                    mAlertDialog.dismiss();
//                    break;
//            }
//
//            mAlertDialog.dismiss();
//
//        }
//    };
//
//
//
//    //=============================================================================================
//    // [3] 點擊 Apply Status Type 時顯示此 Dialog
//    private void Apply_Status_Type_Dialog(String Dialog_Title){
//
//        mAlertDialog = new AlertDialog.Builder(getContext()).create();
//
//        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);
//        mAlertDialog.setView(dialog_view);
//
//        Find_Dialog_View();
//
//        choose_dialog_title.setText(Dialog_Title);
//
//        choose_one_text.setText(R.string.applytype_notyet);
//        choose_two_text.setText(R.string.applytype_pending);
//        choose_three_text.setText(R.string.applytype_approval);
//        choose_four_text.setText(R.string.applytype_reject);
//        choose_five_text.setText(R.string.applytype_cancel);
//
//        choose_one.setOnClickListener(Apply_Status_Type_OnclickListener);
//        choose_two.setOnClickListener(Apply_Status_Type_OnclickListener);
//        choose_three.setOnClickListener(Apply_Status_Type_OnclickListener);
//        choose_four.setOnClickListener(Apply_Status_Type_OnclickListener);
//        choose_five.setOnClickListener(Apply_Status_Type_OnclickListener);
//
//        choose_dialog_cancellbtn.setOnClickListener(Apply_Status_Type_OnclickListener);
//
//        mAlertDialog.show();
//    }
//
//    private View.OnClickListener Apply_Status_Type_OnclickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            /*  Loan Apply Type
//             *  0 = Apply not yet
//             *  1 = Pending
//             *  2 = Approval
//             *  3 = Reject
//             *  4 = Cancel
//             */
//
//            switch (v.getId()){
//
//                case R.id.dialog_sav_ch1:
//                    TV_ApplyType.setText(R.string.applystatuse_hint);
//                    get_applystatus = 0;
//                    subview_Gone();
//                    break;
//
//                case R.id.dialog_sav_ch2:
//                    TV_ApplyType.setText(R.string.applytype_pending);
//                    get_applystatus = 1;
//                    subview_Gone();
//                    break;
//
//                case R.id.dialog_sav_ch3:
//                    TV_ApplyType.setText(R.string.applytype_approval);
//                    get_applystatus = 2;
//                    subview_Visibility();
//                    break;
//
//                case R.id.dialog_sav_ch4:
//                    TV_ApplyType.setText(R.string.applytype_reject);
//                    get_applystatus = 3;
//                    subview_Gone();
//                    break;
//
//                case R.id.dialog_sav_ch5:
//                    TV_ApplyType.setText(R.string.applytype_cancel);
//                    get_applystatus = 4;
//                    subview_Gone();
//                    break;
//
//                case R.id.dialog_sav_cancelbtn:
//                    mAlertDialog.dismiss();
//                    break;
//
//            }
//
//            mAlertDialog.dismiss();
//
//        }
//    };
//
//
//    //=============================================================================================
//    // [3] 點擊 Alert Date Type 時顯示此 Dialog
//    private void Alert_Date_Type_Dialog(String Dialog_Title){
//
//        mAlertDialog = new AlertDialog.Builder(getContext()).create();
//
//        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);
//        mAlertDialog.setView(dialog_view);
//
//        Find_Dialog_View();
//
//        choose_dialog_title.setText(Dialog_Title);
//
//        choose_one_text.setText(R.string.alertdate_dontalert);
//        choose_two_text.setText(R.string.alertdate_3day);
//        choose_three_text.setText(R.string.alertdate_5day);
//        choose_four_text.setText(R.string.alertdate_7day);
//
//        choose_one.setOnClickListener(Alert_Date_Type_OnclickListener);
//        choose_two.setOnClickListener(Alert_Date_Type_OnclickListener);
//        choose_three.setOnClickListener(Alert_Date_Type_OnclickListener);
//        choose_four.setOnClickListener(Alert_Date_Type_OnclickListener);
//        choose_five.setVisibility(View.GONE);
//
//        choose_dialog_cancellbtn.setOnClickListener(Alert_Date_Type_OnclickListener);
//
//        mAlertDialog.show();
//    }
//
//    private View.OnClickListener Alert_Date_Type_OnclickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            /*  Alert Date Type
//             *  0 = Dont Reminding
//             *  1 = Reminding befor 0 Days
//             *  2 = Reminding befor 3 Days
//             *  3 = Reminding befor 5 Days
//             */
//
//            switch (v.getId()){
//
//                case R.id.dialog_sav_ch1:
//                    TV_AlertType_Result.setText("");
//                    get_alertdate_type = 0;
//					TV_AlertTime_Result.setText("");
//                    SV_AlertTime.setVisibility(View.GONE);
//                    set_remind_date = 0;
//                    break;
//
//                case R.id.dialog_sav_ch2:
//                    TV_AlertType_Result.setText(R.string.alertdate_3day);
//                    get_alertdate_type = 1;
//                    show_time_dialog().show();
//					SV_AlertTime.setVisibility(View.VISIBLE);
//                    set_remind_date = 0;
//                    break;
//
//                case R.id.dialog_sav_ch3:
//                    TV_AlertType_Result.setText(R.string.alertdate_5day);
//                    get_alertdate_type = 2;
//                    show_time_dialog().show();
//					SV_AlertTime.setVisibility(View.VISIBLE);
//                    set_remind_date = 3;
//                    break;
//
//                case R.id.dialog_sav_ch4:
//                    TV_AlertType_Result.setText(R.string.alertdate_7day);
//                    get_alertdate_type = 3;
//                    show_time_dialog().show();
//					SV_AlertTime.setVisibility(View.VISIBLE);
//                    set_remind_date = 5;
//                    break;
//
//                case R.id.dialog_sav_cancelbtn:
//                    mAlertDialog.dismiss();
//                    break;
//
//            }
//
//            mAlertDialog.dismiss();
//
//        }
//    };
//
//
//    //=============================================================================================
//    // [3] 點擊 Date Date Type 時顯示此 Dialog
//    private void Date_Type_Dialog(String Dialog_Title){
//
//        mAlertDialog = new AlertDialog.Builder(getContext()).create();
//
//        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.saver_dialog, null);
//        mAlertDialog.setView(dialog_view);
//
//        Find_Dialog_View();
//
//        choose_dialog_title.setText(Dialog_Title);
//
//        choose_one_text.setText(R.string.calcultor_dialog_normal);
//        choose_two_text.setText(R.string.calcultor_dialog_endofmonth);
//
//        choose_one.setOnClickListener(Date_Type_OnclickListener);
//        choose_two.setOnClickListener(Date_Type_OnclickListener);
//        choose_three.setVisibility(View.GONE);
//        choose_four.setVisibility(View.GONE);
//        choose_five.setVisibility(View.GONE);
//
//		choose_dialog_cancellbtn.setOnClickListener(Date_Type_OnclickListener);
//
//        mAlertDialog.show();
//    }
//
//    private View.OnClickListener Date_Type_OnclickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            switch (v.getId()){
//
//                case R.id.dialog_sav_ch1:
//                    TV_FirstDue_Result.setText(firstdue_Calendar.get(Calendar.YEAR) + "/" + (firstdue_Calendar.get(Calendar.MONTH) + 1) + "/" + firstdue_Calendar.get(Calendar.DAY_OF_MONTH));
//                    finaldue_Calender.add(Calendar.MONTH, bundle_trems -1);
//                    TV_FinalDue_Result.setText(finaldue_Calender.get(Calendar.YEAR) + "/" + (finaldue_Calender.get(Calendar.MONTH) + 1)+ "/" + finaldue_Calender.get(Calendar.DAY_OF_MONTH));
//					SV_AlertType.setVisibility(View.VISIBLE);
//                    break;
//
//                case R.id.dialog_sav_ch2:
//                    TV_FirstDue_Result.setText(firstdue_Calendar.get(Calendar.YEAR) + "/" + (firstdue_Calendar.get(Calendar.MONTH) + 1) + "/" + firstdue_Calendar.get(Calendar.DAY_OF_MONTH));
//                    finaldue_Calender.add(Calendar.MONTH, bundle_trems -1);
//                    TV_FinalDue_Result.setText(finaldue_Calender.get(Calendar.YEAR) + "/" + (finaldue_Calender.get(Calendar.MONTH) + 1)+ "/" + finaldue_Calender.getActualMaximum(Calendar.DAY_OF_MONTH));
//					SV_AlertType.setVisibility(View.VISIBLE);
//                    break;
//
//				case R.id.dialog_sav_cancelbtn:
//                    mAlertDialog.dismiss();
//                    break;
//            }
//
//            mAlertDialog.dismiss();
//
//        }
//    };
//
//    //=============================================================================================
//    // [4] 點擊 First Due Date 時顯示此 Dialog
//    protected Dialog show_date_dialog(){
//
//        Dialog mDialog = null;
//
//        firstdue_Calendar = Calendar.getInstance();
//        finaldue_Calender = Calendar.getInstance();
//
//        mDialog = new DatePickerDialog(getActivity(), R.style.myDateDialogTheme, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                firstdue_Calendar.set(Calendar.YEAR, year);
//                firstdue_Calendar.set(Calendar.MONTH, month);
//                firstdue_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//				finaldue_Calender.set(Calendar.YEAR, year);
//                finaldue_Calender.set(Calendar.MONTH, month);
//                finaldue_Calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                if (month == 1 && dayOfMonth == firstdue_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH) || dayOfMonth == 30){
//
//                    Date_Type_Dialog("");
//
//                }else if(dayOfMonth == 31){
//
//                    TV_FirstDue_Result.setText(firstdue_Calendar.get(Calendar.YEAR) + "/" + (firstdue_Calendar.get(Calendar.MONTH) + 1) + "/" + firstdue_Calendar.get(Calendar.DAY_OF_MONTH));
//
//                    finaldue_Calender.add(Calendar.MONTH, bundle_trems - 1);
//                    TV_FinalDue_Result.setText(finaldue_Calender.get(Calendar.YEAR) + "/" + (finaldue_Calender.get(Calendar.MONTH) + 1) + "/" + finaldue_Calender.getActualMaximum(Calendar.DAY_OF_MONTH));
//                    SV_AlertType.setVisibility(View.VISIBLE);
//
//                }else {
//
//                    TV_FirstDue_Result.setText(firstdue_Calendar.get(Calendar.YEAR) + "/" + (firstdue_Calendar.get(Calendar.MONTH) + 1) + "/" + firstdue_Calendar.get(Calendar.DAY_OF_MONTH));
//
//                    finaldue_Calender.add(Calendar.MONTH, bundle_trems - 1);
//                    TV_FinalDue_Result.setText(finaldue_Calender.get(Calendar.YEAR) + "/" + (finaldue_Calender.get(Calendar.MONTH) + 1) + "/" + finaldue_Calender.get(Calendar.DAY_OF_MONTH));
//                    SV_AlertType.setVisibility(View.VISIBLE);
//
//                }
//
//            }
//        },firstdue_Calendar.get(Calendar.YEAR), firstdue_Calendar.get(Calendar.MONTH), firstdue_Calendar.get(Calendar.DAY_OF_MONTH));
//
//        return mDialog;
//    }
//
//    //=============================================================================================
//    // []
//    protected Dialog show_time_dialog(){
//
//        Dialog mDialog = null;
//
//        Alarm_Calendar = Calendar.getInstance();
//
//        mDialog = new TimePickerDialog(getActivity(), R.style.myTimeDialogTheme, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                Alarm_Calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                Alarm_Calendar.set(Calendar.MINUTE, minute);
//
//                SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");
//                String gettime = SDF.format(Alarm_Calendar.getTime());
//                TV_AlertTime_Result.setText(gettime);
//
//            }
//			}, Alarm_Calendar.get(Calendar.HOUR_OF_DAY), Alarm_Calendar.get(Calendar.MINUTE), true);
//
//        return mDialog;
//    }
//
//    //=============================================================================================
//    // [5] 當畫而失去焦點時處理動作
//	public View.OnFocusChangeListener edText_FocusChangeListener = new View.OnFocusChangeListener(){
//		@Override
//		public void onFocusChange(View v, boolean hasFocus)
//		{
//			// TODO: Implement this method
//			if(!hasFocus){
//                // [6]
//				hideKeyboard(v);
//			}
//		}
//	};
//
//    //=============================================================================================
//    // [7] 處理 Keyboard
//    public void hideKeyboard(View view) {
//        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
////    =============================================================================================
////     [8] 獲取用戶輸入資料
//    public void get_input_values(){
//
//        final Calendar today =  Calendar.getInstance();
//        get_createdate = today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.DAY_OF_MONTH);
//
//		get_name = ED_LoanName.getText().toString();
////        get_loantype 已於 Dialog 內獲取
////        get_loantype 已於 Dialog 內獲取
//		get_loannum = ED_LoanNum.getText().toString();
//
//		get_loanamount = bundle_amount;
//		get_loantrems = bundle_trems;
//		get_loanrate = bundle_rate;
//
//		get_firstdue = TV_FirstDue_Result.getText().toString();
//		get_finaldue = TV_FinalDue_Result.getText().toString();
//
////        get_alertdate_type  已於 Dialog 內獲取
//        get_alerttime = TV_AlertTime_Result.getText().toString();
//
//		get_address = ED_LoanAddress.getText().toString();
//		get_phone = ED_PhoneNum.getText().toString();
//		get_remark = ED_Remarks.getText().toString();
//
//    }
//
//    //=============================================================================================
//    // [9] 判斷用戶是否選取 "已批核" 選項
//    public void subview_Visibility(){
//        SV_LoanNum.setVisibility(View.VISIBLE);
//        Linear_DueDate.setVisibility(View.VISIBLE);
//        TV_FirstDue_Result.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
//    }
//
//    //=============================================================================================
//    // [10] 判斷用戶是否選取 "已批核" 以外選項
//    public void subview_Gone(){
//        SV_LoanNum.setVisibility(View.GONE);
//        Linear_DueDate.setVisibility(View.GONE);
//        SV_AlertType.setVisibility(View.GONE);
//        SV_AlertTime.setVisibility(View.GONE);
//
//        ED_LoanNum.setText("");
//        TV_FirstDue_Result.setText("");
//        TV_FinalDue_Result.setText("");
//        TV_AlertType_Result.setText("");
//        TV_AlertTime_Result.setText("");
//    }
//
//    //=============================================================================================
//    // [11] 開啟修改模式
//    public void Edit_Mode_On(int databasic_ID){
//
//        Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode On");
//        favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);
//
//		restore_LoanName_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getName());
//
//        restore_Loan_Amount = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoan_Amount());
//        restore_Loan_Rate = String.valueOf(favourite_dataBasic.query(databasic_ID).getLoan_Rate());
//        restore_Loan_Trems = String.valueOf(favourite_dataBasic.query(databasic_ID).getTrems());
//        restore_Loan_Installment = "";
//
//        db_get_loantype = favourite_dataBasic.query(databasic_ID).getLoan_Type();
//        if (db_get_loantype == 0){
//            restore_LoanType_text = getResources().getString(R.string.loantype_personal);
//			get_loantype = 0;
//        }else  if(db_get_loantype == 1){
//            restore_LoanType_text = getResources().getString(R.string.loantype_mort);
//			get_loantype = 1;
//        }else if(db_get_loantype == 2){
//            restore_LoanType_text = getResources().getString(R.string.loantype_car);
//			get_loantype = 2;
//        }
//
//        db_get_applystatus = favourite_dataBasic.query(databasic_ID).getApply_status();
//        if (db_get_applystatus == 0){
//            restore_ApplyStatus_text = getResources().getString(R.string.applystatuse_hint);
//			get_applystatus = 0;
//        }else if(db_get_applystatus == 1){
//            restore_ApplyStatus_text = getResources().getString(R.string.applytype_pending);
//			get_applystatus = 1;
//        }else if(db_get_applystatus == 2){
//            restore_ApplyStatus_text = getResources().getString(R.string.applytype_approval);
//			get_applystatus = 2;
//        }else if(db_get_applystatus == 3){
//            restore_ApplyStatus_text = getResources().getString(R.string.applytype_reject);
//			get_applystatus = 3;
//        }else if(db_get_applystatus == 4){
//            restore_ApplyStatus_text = getResources().getString(R.string.applytype_cancel);
//			get_applystatus = 4;
//        }
//
//        restore_LoanNum_text = favourite_dataBasic.query(databasic_ID).getLoanNum();
//
//        restore_FirstDueDate_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getFirst_dueddate());
//
//        restore_FinalDueDate_text = String.valueOf(favourite_dataBasic.query(databasic_ID).getFinal_dueddate());
//
//        db_get_alertdate = favourite_dataBasic.query(databasic_ID).getAlert_date();
//        if(db_get_alertdate == 0){
//            restore_AlertDate_text = "";
//			get_alertdate_type = 0;
//        }else if(db_get_alertdate == 1){
//            restore_AlertDate_text = getString(R.string.alertdate_3day);
//			get_alertdate_type = 1;
//        }else if(db_get_alertdate == 2){
//            restore_AlertDate_text = getString(R.string.alertdate_5day);
//			get_alertdate_type = 2;
//        }else if(db_get_alertdate == 3){
//            restore_AlertDate_text = getString(R.string.alertdate_7day);
//			get_alertdate_type = 3;
//        }
//
//        //init_DueDate_Type = String.valueOf(favourite_dataBasic.query(databasic_ID).getAlert_time());
//
//		restore_AlertTime_text = favourite_dataBasic.query(databasic_ID).getAlert_time();
//
//        restore_Address_text = favourite_dataBasic.query(databasic_ID).getAddress();
//        restore_Phone_text = favourite_dataBasic.query(databasic_ID).getPhone();
//        restore_Remarks_text = favourite_dataBasic.query(databasic_ID).getRemarks();
//
//        favourite_dataBasic.close();
//
//    }
//
//    //=============================================================================================
//    // [12] 關閉修改模式
//    public void Edit_Mode_Off(){
//
//        Log.e("Edit Mode : ", This_Fragment_Name + "Edit Mode Off");
//
//		restore_LoanName_text ="";
//
//        restore_LoanType_text = getResources().getString(R.string.loantype_hint);
//
//        restore_ApplyStatus_text = getResources().getString(R.string.applystatuse_hint);
//
//        restore_LoanNum_text = "";
//
//        restore_Loan_Amount = "0";
//        restore_Loan_Rate = "0";
//        restore_Loan_Trems = "0";
//
//        restore_Loan_Installment = "0";
//
//        restore_FirstDueDate_text = "";
//        restore_FinalDueDate_text = "";
//
//        restore_AlertDate_text = "";
//
//		restore_AlertTime_text = "";
//
//        restore_Address_text = "";
//        restore_Phone_text = "";
//        restore_Remarks_text = "";
//
//        db_get_loantype = 0;
//        db_get_alertdate = 0;
//        db_get_applystatus = 0;
//    }
//
//    //=============================================================================================
//    // [13] 用戶輸入資料加入 Favourite Data Base
//    public void insert_to_fav_DB(){
//
//        get_input_values();
//
//        if (get_name.isEmpty()){
//            Toast.makeText(getContext(), "Please enter name", Toast.LENGTH_LONG).show();
//        }else{
//
//            favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);
//
//            Favouite_Item fav_item = new Favouite_Item(
//                    1,
//                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),
//                    get_name,
//                    get_loantype,
//                    get_applystatus,
//                    get_loannum,
//                    get_loanamount,
//                    get_loantrems,
//                    get_loanrate,
//                    get_firstdue,
//                    get_finaldue,
//                    get_alertdate_type,
//                    get_alerttime, //HH:mm:ss
//                    get_address,
//                    get_phone,
//                    get_remark);
//
//            favourite_dataBasic.inster(fav_item);
//
//            favourite_dataBasic.close();
//
//        }
//    }
//
//    //=============================================================================================
//    // [14] 用戶輸入資料修改 Favourite Data Base
//    public void update_to_fav_DB(){
//
//        get_input_values();
//
//        if (get_name.isEmpty()){
//            Toast.makeText(getContext(), "Please enter name", Toast.LENGTH_LONG).show();
//        }else {
//
//            favourite_dataBasic = new Favourite_DataBasic(getActivity(), This_Fragment_Name);
//
//            Favouite_Item fav_item = new Favouite_Item(
//                    DB_ID,
//                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),
//                    get_name,
//                    get_loantype,
//                    get_applystatus,
//                    get_loannum,
//                    get_loanamount,
//                    get_loantrems,
//                    get_loanrate,
//                    get_firstdue,
//                    get_finaldue,
//                    get_alertdate_type,
//                    get_alerttime,
//                    get_address,
//                    get_phone,
//                    get_remark);
//
//            favourite_dataBasic.update(fav_item);
//
//            favourite_dataBasic.close();
//
//        }
//    }
//
//
//
//}
//
//
