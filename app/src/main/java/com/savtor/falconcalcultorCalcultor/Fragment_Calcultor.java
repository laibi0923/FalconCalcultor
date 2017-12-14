package com.savtor.falconcalcultorCalcultor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.text.*;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.*;
import android.widget.*;
import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorSchedule.Schedule_Fragment;

import android.graphics.drawable.*;
import android.support.v4.view.*;
import android.view.animation.*;
import android.animation.*;


/**
 * Created by GhostLeo_DT on 24/11/2017.
 */
public class Fragment_Calcultor extends Fragment {

    private String Dialog_Title, Sub_Title;
    private double result_installment, result_total_interest, result_total_payment;
    private int myCaseID;
    private double current_LoanAmount, current_LoanRate;
    private int current_LoanTrems;
    private float x = 0;

	Drawable edit_icon;
	
    private ImageView addtofav_btn;
    private TextView loanAmount_tv, loanTrems_tv, loanRate_tv, installment_tv, total_insterest_tv, total_payment_tv;
    private LinearLayout loan_amount, loan_trems, loan_rate;
    private LinearLayout schedule_btn;

    private int DB_ID;
    private String Edit_Mode;

    private TextView choose_dialog_title, choose_dialog_cancellbtn;
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

        mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        get_sharedpreferences();

		Bundle mBundle = getArguments();

        if (mBundle != null){

            DB_ID  = mBundle.getInt("DB_ID");
            Edit_Mode = mBundle.getString("EDIT_MODE");

            if ( Edit_Mode == "true"){
                Edit_Mode_On(DB_ID);
            }else if (Edit_Mode == "false"){
                Edit_Mode_Off();
            }
			
        }else {
            Edit_Mode_Off();
        }

        getActivity().getWindow().setAllowEnterTransitionOverlap(false);
	}
	

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cal_main, container, false);

        Find_View(v);

        return v;
    }








	//=============================================================================================
    // [?] Edit Mode On
    private void Edit_Mode_On(int databasic_ID){

        Edit_Mode = "true";

        Favourite_DataBasic databasic = new Favourite_DataBasic(getActivity(), "Fragment_Calcultor");

        current_LoanAmount = databasic.query(databasic_ID).getLoan_Amount();
        current_LoanTrems = databasic.query(databasic_ID).getTrems();
        current_LoanRate = databasic.query(databasic_ID).getLoan_Rate();
		result_installment = mCalcultor.getMonthlyInstallment(current_LoanAmount, current_LoanTrems, current_LoanRate);
		result_total_interest = mCalcultor.getTotalInsterest(current_LoanAmount, current_LoanTrems, result_installment);
		result_total_payment = mCalcultor.getTotalPayment(result_installment,current_LoanTrems);

		randomAnim();

        edit_icon = getResources().getDrawable(R.drawable.ic_create_black_24dp);

    }

    //=============================================================================================
    // [?] Edit Mode Off
    private void Edit_Mode_Off(){

        Edit_Mode = "false";

        current_LoanAmount = 0;
        current_LoanTrems = 12;
        current_LoanRate = 54;
		result_installment = 0;
		result_total_interest = 0;
		result_total_payment = 0;

        edit_icon = getResources().getDrawable(R.drawable.ic_add_black_24dp);

    }

    //=============================================================================================
    // [?] Get setting value in sharedpreferences
    public void get_sharedpreferences(){
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
    // [?] 加入畫面內容
    public void Find_View(View v){

        loan_amount = (LinearLayout) v.findViewById(R.id.loanAmount_linear);
        loan_amount.setOnClickListener(linearLayout_OnclickListener);   //[?]


        loan_trems = (LinearLayout) v.findViewById(R.id.loanTrems_linear);
        loan_trems.setOnClickListener(linearLayout_OnclickListener);    //[?]

        loan_rate = (LinearLayout) v.findViewById(R.id.loanRate_linear);
        loan_rate.setOnClickListener(linearLayout_OnclickListener);     //[?]

        addtofav_btn = (ImageView) v.findViewById(R.id.addtofav_btn);
        addtofav_btn.setImageDrawable(edit_icon);
        addtofav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Double.parseDouble(total_insterest_tv.getText().toString()) > 1){


                    Bundle mBundle = new Bundle();
                    mBundle.putDouble("loan_amount", current_LoanAmount);
                    mBundle.putInt("loan_trems", current_LoanTrems);
                    mBundle.putDouble("loan_rate", current_LoanRate);
                    mBundle.putString("EDIT_MODE", Edit_Mode);
                    mBundle.putInt("DB_ID", DB_ID);

                    Fragment mFragment = new Fragment_Saver();
                    mFragment.setArguments(mBundle);

                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();

                }else {
                    Toast.makeText(getContext(), "請先計算供款", Toast.LENGTH_LONG).show();
                }

            }
        });

        loanAmount_tv = (TextView) v.findViewById(R.id.loanAmount_tv);
        loanAmount_tv.setText(String.format("%1$.2f", current_LoanAmount));

        loanTrems_tv = (TextView) v.findViewById(R.id.loanTrems_tv);
        loanTrems_tv.setText(String.valueOf(current_LoanTrems));

        loanRate_tv = (TextView) v.findViewById(R.id.loanRate_tv);
        loanRate_tv.setText(String.format("%.2f", current_LoanRate));

        installment_tv = (TextView) v.findViewById(R.id.installment_tv);
		installment_tv.setText(String.format(dec_point, result_installment));
		
        total_insterest_tv = (TextView) v.findViewById(R.id.total_interest_tv);
		total_insterest_tv.setText(String.format(dec_point, result_total_interest));
		
        total_payment_tv = (TextView) v.findViewById(R.id.total_payment_tv);
		total_payment_tv.setText(String.format(dec_point, result_total_payment));

        schedule_btn = (LinearLayout) v.findViewById(R.id.schedule_btn);
        schedule_btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {

                if(Double.parseDouble(total_insterest_tv.getText().toString()) > 1){

                    Bundle mBundle = new Bundle();

                    mBundle.putDouble("loan_amount", current_LoanAmount);
                    mBundle.putInt("loan_trems", current_LoanTrems);
                    mBundle.putDouble("loan_rate", current_LoanRate);
                    mBundle.putDouble("loan_installment", result_installment);

                    Fragment mFragment = new Schedule_Fragment();
                    mFragment.setArguments(mBundle);

                    FragmentManager mFragmentManager = getFragmentManager();
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.setCustomAnimations(R.anim.enter_form_bottom, R.anim.exit_from_top, R.anim.enter_form_top, R.anim.exit_form_down);
                    mFragmentTransaction.replace(R.id.mFrameLayout, mFragment);
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();

                }else {

                    Toast.makeText(getContext(), "請先計算供款", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    //=============================================================================================
    // [?] 加入 Dialog 畫面內容
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
    // [?] 點擊事件
    private View.OnClickListener linearLayout_OnclickListener = new View.OnClickListener() {
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
            show_alert_dialog(Dialog_Title, Sub_Title, myCaseID, dialog_view);
        }
    };


    //=============================================================================================
    // [?] 利用 Alert Dialog 處理用戶輸入資料
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
                            Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
                        }else if(Integer.parseInt(calcul_EdTExt.getText().toString()) < 3 || Integer.parseInt(calcul_EdTExt.getText().toString()) > 96) {
                            Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
                        }else {
							current_LoanTrems = Integer.parseInt(calcul_EdTExt.getText().toString());
                            loanTrems_tv.setText(String.valueOf(current_LoanTrems));
                        }

                        break;

                    case 3:

                        if(calcul_EdTExt.getText().toString().isEmpty()){
                            break;
                        }else if(Double.parseDouble(calcul_EdTExt.getText().toString()) > 60 || Double.parseDouble(calcul_EdTExt.getText().toString()) < 1 ){
                            Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
								current_LoanRate = Double.parseDouble(calcul_EdTExt.getText().toString());
                                loanRate_tv.setText(String.format("%.2f", current_LoanRate));
                            }catch (NumberFormatException ee){ee.printStackTrace();}
                        }

                        break;

                }

                result_installment = mCalcultor.getMonthlyInstallment(current_LoanAmount, current_LoanTrems, current_LoanRate);
				result_total_interest = mCalcultor.getTotalInsterest(current_LoanAmount, current_LoanTrems, result_installment);
				result_total_payment = mCalcultor.getTotalPayment(result_installment, current_LoanTrems);
				
				
                if (result_total_interest < 1){
                    total_insterest_tv.setText("0.00");
                }else {
                    total_insterest_tv.setText(String.format(dec_point, result_total_interest));
                }

                if (result_total_payment < 1) {
                    total_payment_tv.setText("0.00");
                }else {
                    total_payment_tv.setText(String.format(dec_point, result_total_payment));
                }

                if (result_installment < 1){
                    installment_tv.setText("0.00");
                }else {
                    randomAnim();
                }

                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.show();
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    //=============================================================================================
    // [?] 計算完畢後動畫
    private void randomAnim(){

        x = 0;

        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                }catch (InterruptedException e){ e.printStackTrace();}

                while (x <= result_installment){
                    Message msg = mHandler.obtainMessage();
                    x += result_installment/current_LoanTrems;
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
    // [?] 計算完畢後動畫
    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            if(msg.what == 1) {

                if(x > result_installment){
                    
                    installment_tv.setText(String.format(dec_point,result_installment));

                }else {
                    installment_tv.setText(String.format(dec_point,x));
                }
            }else if(msg.what == 2){
                installment_tv.setText(String.format(dec_point,result_installment));
            }

            super.handleMessage(msg);
        }
    };

    
	
    

}

