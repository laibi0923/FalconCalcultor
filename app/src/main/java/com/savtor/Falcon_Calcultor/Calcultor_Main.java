package com.savtor.Falcon_Calcultor;

import android.annotation.*;
import android.content.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.savtor.Credit_Profile.*;
import com.savtor.Customer_Dialog.*;
import com.savtor.Payment_Schedule.*;
import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 24/11/2017.
 */
public class Calcultor_Main extends Fragment {

    private String Dialog_Title, Sub_Title;
    private double result_Installment, result_Total_Interest, result_Total_Payment;
    private int myCaseID;
    private double Loan_Amount, Loan_Rate;
    private int Loan_Trems;
    private float x = 0;

	private Drawable edit_icon;
	
    private ImageView addtofav_btn;
    private TextView loanAmount_tv, loanTrems_tv, loanRate_tv, installment_tv, total_insterest_tv, total_payment_tv;
    private LinearLayout loan_amount, loan_trems, loan_rate;
    private LinearLayout schedule_btn, description_btn;

    private TextView choose_dialog_title, choose_dialog_cancellbtn, dialog_TextView, marquee_TextView;
    private View dialog_view;
    private TextInputLayout dialog_Textinput;
    private EditText calcul_EdTExt;

    Calcultor mCalcultor = new Calcultor();

    private int get_setting_password, get_setting_language, get_setting_decimal;
    private SharedPreferences mSharedPreferences;

    private String dec_point = "%.2f";
	
	private double Max_Trems;

	private Credit_Profile_NumDialog Number_Dialog;
	
	public static int DIALOG_REQUEST_CODE;
	
	private String FRAGMENT_TAG;
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);

        get_SharedPreferences();

        Loan_Amount = 0;
        Loan_Trems = 12;
        Loan_Rate = 54;
        result_Installment = 0;
        result_Total_Interest = 0;
        result_Total_Payment = 0;

        edit_icon = getResources().getDrawable(R.drawable.ic_add_black_24dp);
	}

	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cal_main, container, false);

        Find_View(v);

        return v;
    }
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
			case 0:
				double loan_amount_result = data.getDoubleExtra(Credit_Profile_NumDialog.RESPONSE, 0.00);
				loanAmount_tv.setText("$ " + String.format(dec_point, loan_amount_result));
				break;
				
			case 1:
				double loan_trems_result = data.getDoubleExtra(Credit_Profile_NumDialog.RESPONSE, 0);
				loanAmount_tv.setText("$ " + String.format(dec_point, loan_trems_result));
				break;
				
			case 2:
				double loan_rate_result = data.getDoubleExtra(Credit_Profile_NumDialog.RESPONSE, 0.00);
				loanAmount_tv.setText("$ " + String.format(dec_point, loan_rate_result));
				break;
		}
	}
	

/*================================================================================================
 *                               Get setting value in sharedpreferences
================================================================================================ */
    private void get_SharedPreferences(){
        mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);

        get_setting_password = mSharedPreferences.getInt("Setting_password", 1);
        get_setting_language = mSharedPreferences.getInt("Setting_language", 1);
        get_setting_decimal = mSharedPreferences.getInt("Setting_decimal" , 2);

        if (get_setting_decimal == 1){
            dec_point = "%.0f";
        }else if(get_setting_decimal == 2){
            dec_point = "%.2f";
        }
    }

/*================================================================================================
 *                                      Find View
================================================================================================ */
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
        loanAmount_tv.setText("$ " + String.format("%1$.2f", Loan_Amount));

        loanTrems_tv = (TextView) v.findViewById(R.id.loanTrems_tv);
        loanTrems_tv.setText(String.valueOf(Loan_Trems) + " 個月");

        loanRate_tv = (TextView) v.findViewById(R.id.loanRate_tv);
        loanRate_tv.setText(String.format("%.2f", Loan_Rate) + " % p.a.");

        installment_tv = (TextView) v.findViewById(R.id.installment_tv);
		installment_tv.setText("$ " + String.format(dec_point, result_Installment));
		
        total_insterest_tv = (TextView) v.findViewById(R.id.total_interest_tv);
		total_insterest_tv.setText("$ " + String.format(dec_point, result_Total_Interest));
		
        total_payment_tv = (TextView) v.findViewById(R.id.total_payment_tv);
		total_payment_tv.setText("$ " + String.format(dec_point, result_Total_Payment));

        schedule_btn = (LinearLayout) v.findViewById(R.id.schedule_btn);
        schedule_btn.setOnClickListener(Action_OnclickListener);    // [7-2]

        description_btn = (LinearLayout) v.findViewById(R.id.description_btn);
        description_btn.setOnClickListener(Action_OnclickListener);

        marquee_TextView = (TextView) v.findViewById(R.id.marquee_textview);
        marquee_TextView.setSelected(true);
		marquee_TextView.setVisibility(View.GONE);
    }

/*================================================================================================
 *                                   加入 Dialog 畫面內容
================================================================================================ */
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
					
					DIALOG_REQUEST_CODE = 0;
                    FRAGMENT_TAG = "PRODUCT_LOAN_AMOUNT";
                    Number_Dialog = new Credit_Profile_NumDialog(getString(R.string.title_loan_amount), FRAGMENT_TAG, Max_Trems);
                    Number_Dialog.setTargetFragment(Calcultor_Main.this, DIALOG_REQUEST_CODE);
                    Number_Dialog.show(getFragmentManager(), FRAGMENT_TAG);
					
                    break;

                case R.id.loanTrems_linear:
					
					DIALOG_REQUEST_CODE = 1;
                    FRAGMENT_TAG = "PRODUCT_LOAN_TREMS";
                    Number_Dialog = new Credit_Profile_NumDialog(getString(R.string.title_loan_trems), FRAGMENT_TAG, Max_Trems);
                    Number_Dialog.setTargetFragment(Calcultor_Main.this, DIALOG_REQUEST_CODE);
                    Number_Dialog.show(getFragmentManager(), FRAGMENT_TAG);
                    
                    break;

                case R.id.loanRate_linear:
					
					DIALOG_REQUEST_CODE = 2;
                    FRAGMENT_TAG = "PRODUCT_LOAN_RATE";
                    Number_Dialog = new Credit_Profile_NumDialog(getString(R.string.title_loan_rate), FRAGMENT_TAG, Max_Trems);
                    Number_Dialog.setTargetFragment(Calcultor_Main.this, DIALOG_REQUEST_CODE);
                    Number_Dialog.show(getFragmentManager(), FRAGMENT_TAG);
					
                    break;
            }
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
                        mBundle.putString("From", "Calcultor");
                        mBundle.putDouble("Amount", Loan_Amount);
                        mBundle.putInt("Trems", Loan_Trems);
                        mBundle.putDouble("Rate", Loan_Rate);
                        mBundle.putDouble("Installment", result_Installment);

                        Fragment mFragment = new Product_Type();
                        mFragment.setArguments(mBundle);
						
						((Activity_Main)getActivity()).Fragment_Transaction(mFragment);
						((Activity_Main)getActivity()).mNavView.getMenu().getItem(1).setChecked(true);

                    }else {
                        Toast.makeText(getContext(), getResources().getString(R.string.calcultor_toast_calfirst), Toast.LENGTH_LONG).show();
                    }

                    break;

                case R.id.schedule_btn:

                    if(Double.parseDouble(total_insterest_tv.getText().toString()) > 1){

                        Bundle mBundle = new Bundle();

                        mBundle.putDouble("loan_amount", Loan_Amount);
                        mBundle.putInt("loan_trems", Loan_Trems);
                        mBundle.putDouble("loan_rate", Loan_Rate);
                        mBundle.putDouble("loan_installment", result_Installment);

                        Fragment mFragment = new Schedule_Fragment();
                        mFragment.setArguments(mBundle);
						((Activity_Main)getActivity()).Fragment_Transaction(mFragment);

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
                                Loan_Amount = Double.parseDouble(calcul_EdTExt.getText().toString());
                                loanAmount_tv.setText("$" + String.format("%1$.2f", Loan_Amount));
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
                            Loan_Trems = Integer.parseInt(calcul_EdTExt.getText().toString());
                            loanTrems_tv.setText(String.valueOf(Loan_Trems) + " 個月");
                        }

                        break;

                    case 3:

                        if(calcul_EdTExt.getText().toString().isEmpty()){
                            break;
                        }else if(Double.parseDouble(calcul_EdTExt.getText().toString()) > 60 || Double.parseDouble(calcul_EdTExt.getText().toString()) < 1 ){
                            Toast.makeText(getActivity(), getResources().getString(R.string.calcultor_toast_poorvalue), Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                Loan_Rate = Double.parseDouble(calcul_EdTExt.getText().toString());
                                loanRate_tv.setText(String.format("%.2f", Loan_Rate) + " % p.a.");
                            }catch (NumberFormatException ee){ee.printStackTrace();}
                        }

                        break;

                }

                result_Installment = mCalcultor.getMonthlyInstallment(Loan_Amount, Loan_Trems, Loan_Rate);
                result_Total_Interest = mCalcultor.getTotalInsterest(Loan_Amount, Loan_Trems, result_Installment);
                result_Total_Payment = mCalcultor.getTotalPayment(result_Installment, Loan_Trems);


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

/*================================================================================================
 *                                      計算完畢後動畫
================================================================================================ */
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
                    x += result_Installment / Loan_Trems;
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

/*================================================================================================
 *                                      計算完畢後動畫
================================================================================================ */
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

