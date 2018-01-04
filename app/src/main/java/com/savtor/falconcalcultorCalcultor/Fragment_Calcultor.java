package com.savtor.falconcalcultorCalcultor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorSchedule.Schedule_Fragment;
import android.graphics.drawable.*;



/**
 * Created by GhostLeo_DT on 24/11/2017.
 */
public class Fragment_Calcultor extends Fragment {

    private String Dialog_Title, Sub_Title;
    private double result_Installment, result_Total_Interest, result_Total_Payment;
    private int myCaseID;
    private double current_LoanAmount, current_LoanRate;
    private int current_LoanTrems;
    private float x = 0;

	private Drawable edit_icon;
	
    private ImageView addtofav_btn;
    private TextView loanAmount_tv, loanTrems_tv, loanRate_tv, installment_tv, total_insterest_tv, total_payment_tv;
    private LinearLayout loan_amount, loan_trems, loan_rate;
    private LinearLayout schedule_btn, description_btn;

    private int DB_ID;
    private String Edit_Mode;

    private TextView choose_dialog_title, choose_dialog_cancellbtn, dialog_TextView, marquee_TextView;
    private View dialog_view;
    private TextInputLayout dialog_Textinput;
    private EditText calcul_EdTExt;

    Calcultor mCalcultor = new Calcultor();

    private int get_setting_password, get_setting_language, get_setting_decimal;
    private SharedPreferences mSharedPreferences;

    private String dec_point = "%.0f";

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);

        get_SharedPreferences();
        get_Bundle_Arguments();
	}
	

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cal_main, container, false);

        Find_View(v);

        return v;
    }


    //=============================================================================================
    // [1] Get setting value in Bundle
    private void get_Bundle_Arguments(){

        Bundle mBundle = getArguments();

        if (mBundle != null){
            DB_ID  = mBundle.getInt("DB_ID", 0);
            Edit_Mode = mBundle.getString("EDIT_MODE", "false");
            if ( Edit_Mode == "true"){ Edit_Mode_On(DB_ID); }
            else if (Edit_Mode == "false"){ Edit_Mode_Off(); }
        }else {
            Edit_Mode_Off();
        }

    }

    //=============================================================================================
    // [2] Get setting value in sharedpreferences
    private void get_SharedPreferences(){
        mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);

        get_setting_password = mSharedPreferences.getInt("Setting_password", 1);
        get_setting_language = mSharedPreferences.getInt("Setting_language", 1);
        get_setting_decimal = mSharedPreferences.getInt("Setting_decimal" , 1);

        if (get_setting_decimal == 1){
            dec_point = "%.0f";
        }else if(get_setting_decimal == 2){
            dec_point = "%.2f";
        }
    }

    //=============================================================================================
    // [3] Edit Mode On
    private void Edit_Mode_On(int databasic_ID){

        Edit_Mode = "true";

        Favourite_DataBasic databasic = new Favourite_DataBasic(getActivity(), "Fragment_Calcultor");

        current_LoanAmount = databasic.query(databasic_ID).getLoan_Amount();
        current_LoanTrems = databasic.query(databasic_ID).getTrems();
        current_LoanRate = databasic.query(databasic_ID).getLoan_Rate();
        result_Installment = mCalcultor.getMonthlyInstallment(current_LoanAmount, current_LoanTrems, current_LoanRate);
        result_Total_Interest = mCalcultor.getTotalInsterest(current_LoanAmount, current_LoanTrems, result_Installment);
        result_Total_Payment = mCalcultor.getTotalPayment(result_Installment,current_LoanTrems);

        randomAnim();

        edit_icon = getResources().getDrawable(R.drawable.ic_create_black_24dp);

    }

    //=============================================================================================
    // [4] Edit Mode Off
    private void Edit_Mode_Off(){

        Edit_Mode = "false";

        current_LoanAmount = 0;
        current_LoanTrems = 12;
        current_LoanRate = 54;
        result_Installment = 0;
        result_Total_Interest = 0;
        result_Total_Payment = 0;

        edit_icon = getResources().getDrawable(R.drawable.ic_add_black_24dp);

    }


    //=============================================================================================
    // [5] 加入畫面內容
    private void Find_View(View v){

        loan_amount = (LinearLayout) v.findViewById(R.id.loanAmount_linear);
        loan_amount.setOnClickListener(DataInput_OnclickListener);   // [7-1]

        loan_trems = (LinearLayout) v.findViewById(R.id.loanTrems_linear);
        loan_trems.setOnClickListener(DataInput_OnclickListener);    // [7-1]

        loan_rate = (LinearLayout) v.findViewById(R.id.loanRate_linear);
        loan_rate.setOnClickListener(DataInput_OnclickListener);     // [7-1]

        addtofav_btn = (ImageView) v.findViewById(R.id.addtofav_btn);
        addtofav_btn.setImageDrawable(edit_icon);
        addtofav_btn.setOnClickListener(Action_OnclickListener);    // [7-2]

        loanAmount_tv = (TextView) v.findViewById(R.id.loanAmount_tv);
        loanAmount_tv.setText(String.format("%1$.2f", current_LoanAmount));

        loanTrems_tv = (TextView) v.findViewById(R.id.loanTrems_tv);
        loanTrems_tv.setText(String.valueOf(current_LoanTrems));

        loanRate_tv = (TextView) v.findViewById(R.id.loanRate_tv);
        loanRate_tv.setText(String.format("%.2f", current_LoanRate));

        installment_tv = (TextView) v.findViewById(R.id.installment_tv);
		installment_tv.setText(String.format(dec_point, result_Installment));
		
        total_insterest_tv = (TextView) v.findViewById(R.id.total_interest_tv);
		total_insterest_tv.setText(String.format(dec_point, result_Total_Interest));
		
        total_payment_tv = (TextView) v.findViewById(R.id.total_payment_tv);
		total_payment_tv.setText(String.format(dec_point, result_Total_Payment));

        schedule_btn = (LinearLayout) v.findViewById(R.id.schedule_btn);
        schedule_btn.setOnClickListener(Action_OnclickListener);    // [7-2]

        description_btn = (LinearLayout) v.findViewById(R.id.description_btn);
        description_btn.setOnClickListener(Action_OnclickListener);

        marquee_TextView = (TextView) v.findViewById(R.id.marquee_textview);
        marquee_TextView.setSelected(true);
    }


    //=============================================================================================
    // [6] 加入 Dialog 畫面內容
    public void find_dialog_view(){

        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.cal_dialog, null);

        choose_dialog_title = (TextView) dialog_view.findViewById(R.id.dialog_cal_title);

        dialog_Textinput = (TextInputLayout) dialog_view.findViewById(R.id.calcultor_textinput);
        dialog_Textinput.setHint(Sub_Title);
        dialog_Textinput.requestFocus();

        calcul_EdTExt = (EditText) dialog_Textinput.findViewById(R.id.calcultor_dialog_edittext);
        calcul_EdTExt.requestFocus();

        choose_dialog_cancellbtn = (TextView) dialog_view.findViewById(R.id.dialog_cal_btn);

    }

    //=============================================================================================
    // [6] 加入 Dialog 畫面內容
    public void find_description_dialog_view(){

        dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.cal_description_dislog, null);

        choose_dialog_title = (TextView) dialog_view.findViewById(R.id.dialog_cal_title);

        dialog_TextView = (TextView) dialog_view.findViewById(R.id.dialog_cal_textview);

        choose_dialog_cancellbtn = (TextView) dialog_view.findViewById(R.id.dialog_cal_btn);

    }

    //=============================================================================================
    // [7-1] 點擊事件
    private View.OnClickListener DataInput_OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.loanAmount_linear:
                    Dialog_Title = getResources().getString(R.string.calcultor_loanamount_title);
                    Sub_Title = getResources().getString(R.string.calcultor_loanamount_subtitle);
                    myCaseID = 1;
                    break;

                case R.id.loanTrems_linear:
                    Dialog_Title = getResources().getString(R.string.calcultor_loantrems_title);
                    Sub_Title = getResources().getString(R.string.calcultor_loantrems_subtitle);
                    myCaseID = 2;
                    break;

                case R.id.loanRate_linear:
                    Dialog_Title = getResources().getString(R.string.calcultor_loanrate_title);
                    Sub_Title = getResources().getString(R.string.calcultor_loanrate_subtitle);
                    myCaseID = 3;
                    break;
            }

            find_dialog_view();
            show_alert_dialog(Dialog_Title, Sub_Title, myCaseID, dialog_view);  // [8]
        }
    };

    // [7-2] 點擊事件
    private View.OnClickListener Action_OnclickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.addtofav_btn:

                    if(Double.parseDouble(total_insterest_tv.getText().toString()) > 1){

                        Bundle mBundle = new Bundle();
                        mBundle.putDouble("loan_amount", current_LoanAmount);
                        mBundle.putInt("loan_trems", current_LoanTrems);
                        mBundle.putDouble("loan_rate", current_LoanRate);
                        mBundle.putString("EDIT_MODE", Edit_Mode);
                        mBundle.putInt("DB_ID", DB_ID);

                        Fragment mFragment = new Fragment_Credit_Profile();
                        mFragment.setArguments(mBundle);

                        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                        mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();

                    }else {
                        Toast.makeText(getContext(), getResources().getString(R.string.calcultor_toast_calfirst), Toast.LENGTH_LONG).show();
                    }


                    break;

                case R.id.schedule_btn:

                    if(Double.parseDouble(total_insterest_tv.getText().toString()) > 1){

                        Bundle mBundle = new Bundle();

                        mBundle.putDouble("loan_amount", current_LoanAmount);
                        mBundle.putInt("loan_trems", current_LoanTrems);
                        mBundle.putDouble("loan_rate", current_LoanRate);
                        mBundle.putDouble("loan_installment", result_Installment);

//                    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                        FragmentManager mFragmentManager = getFragmentManager();

                        Fragment mFragment = new Schedule_Fragment();

                        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.setCustomAnimations(R.anim.enter_form_bottom, R.anim.exit_from_top, R.anim.enter_form_top, R.anim.exit_form_down);
                        mFragment.setArguments(mBundle);
                        mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                        mFragmentTransaction.commit();

                    }else {

                        Toast.makeText(getContext(), getResources().getString(R.string.calcultor_toast_calfirst), Toast.LENGTH_SHORT).show();

                    }

                    break;

                case R.id.description_btn:
                    find_description_dialog_view();
                    show_description_dialog(getString(R.string.calcultor_dialog_description_title), dialog_view);
                    break;

            }

        }
    };


    //=============================================================================================
    // [8] 利用 Alert Dialog 處理用戶輸入資料
    private void show_alert_dialog(String Dialog_Title, String Sub_Title, final int myCaseID, View Dialogview){

        final AlertDialog mAlertDialog = new AlertDialog.Builder(getContext()).create();
        mAlertDialog.setView(Dialogview);

        choose_dialog_title.setText(Dialog_Title);

        if(myCaseID == 2){
            //            僅限輸入數字
            calcul_EdTExt.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else {
            //            僅限輸入數字 (含小數點)
            calcul_EdTExt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        choose_dialog_cancellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //      核對輸入資料
                switch (myCaseID){

                    case 1:

                        if(calcul_EdTExt.getText().toString().isEmpty()){
                            break;
                        }else if(Double.parseDouble(calcul_EdTExt.getText().toString()) > 10000000){
                            Toast.makeText(getActivity(), getResources().getString(R.string.calcultor_toast_poorvalue), Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                current_LoanAmount = Double.parseDouble(calcul_EdTExt.getText().toString());
                                loanAmount_tv.setText(String.format("%1$.2f", current_LoanAmount));
                            }catch (NumberFormatException ee){ee.printStackTrace();}
                        }

                        break;

                    case 2:

                        if(calcul_EdTExt.getText().toString().isEmpty()){
                            break;
                        }else if(calcul_EdTExt.getText().toString().length() > 8){
                            Toast.makeText(getActivity(), getResources().getString(R.string.calcultor_toast_poorvalue), Toast.LENGTH_SHORT).show();
                        }else if(Integer.parseInt(calcul_EdTExt.getText().toString()) < 3 || Integer.parseInt(calcul_EdTExt.getText().toString()) > 96) {
                            Toast.makeText(getActivity(), getResources().getString(R.string.calcultor_toast_poorvalue), Toast.LENGTH_SHORT).show();
                        }else {
                            current_LoanTrems = Integer.parseInt(calcul_EdTExt.getText().toString());
                            loanTrems_tv.setText(String.valueOf(current_LoanTrems));
                        }

                        break;

                    case 3:

                        if(calcul_EdTExt.getText().toString().isEmpty()){
                            break;
                        }else if(Double.parseDouble(calcul_EdTExt.getText().toString()) > 60 || Double.parseDouble(calcul_EdTExt.getText().toString()) < 1 ){
                            Toast.makeText(getActivity(), getResources().getString(R.string.calcultor_toast_poorvalue), Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                current_LoanRate = Double.parseDouble(calcul_EdTExt.getText().toString());
                                loanRate_tv.setText(String.format("%.2f", current_LoanRate));
                            }catch (NumberFormatException ee){ee.printStackTrace();}
                        }

                        break;

                }

                result_Installment = mCalcultor.getMonthlyInstallment(current_LoanAmount, current_LoanTrems, current_LoanRate);
                result_Total_Interest = mCalcultor.getTotalInsterest(current_LoanAmount, current_LoanTrems, result_Installment);
                result_Total_Payment = mCalcultor.getTotalPayment(result_Installment, current_LoanTrems);


                if (result_Total_Interest < 1){
                    total_insterest_tv.setText("0.00");
                }else {
                    total_insterest_tv.setText(String.format(dec_point, result_Total_Interest));
                }

                if (result_Total_Payment < 1) {
                    total_payment_tv.setText("0.00");
                }else {
                    total_payment_tv.setText(String.format(dec_point, result_Total_Payment));
                }

                if (result_Installment < 1){
                    installment_tv.setText("0.00");
                }else {
                    randomAnim();   // [9]
                }

                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.show();
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    //=============================================================================================
    // [8] 利用 Alert Dialog 處理用戶輸入資料
    private void show_description_dialog(String Dialog_Title, View Dialogview){

        final AlertDialog mAlertDialog = new AlertDialog.Builder(getContext()).create();
        mAlertDialog.setView(Dialogview);

        choose_dialog_title.setText(Dialog_Title);

        choose_dialog_cancellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAlertDialog.dismiss();

            }
        });

        mAlertDialog.show();
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //=============================================================================================
    // [9] 計算完畢後動畫
    private void randomAnim(){

        x = 0;

        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                }catch (InterruptedException e){ e.printStackTrace();}

                while (x <= result_Installment){
                    Message msg = mHandler.obtainMessage();
                    x += result_Installment / current_LoanTrems;
                    msg.what = 1;
                    msg.sendToTarget();

                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){ e.printStackTrace();}
                }
            }
        });
        mThread.start();

    }

    //=============================================================================================
    // [10] 計算完畢後動畫
    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            if(msg.what == 1) {

                if(x > result_Installment){
                    
                    installment_tv.setText(String.format(dec_point, result_Installment));

                }else {
                    installment_tv.setText(String.format(dec_point,x));
                }
            }else if(msg.what == 2){
                installment_tv.setText(String.format(dec_point, result_Installment));
            }

            super.handleMessage(msg);
        }
    };

}   //=============================================================================================

