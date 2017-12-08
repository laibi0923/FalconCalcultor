package com.savtor.falconcalcultorCalcultor;

import android.content.*;
import android.os.*;
import android.renderscript.Sampler;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.text.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.savtor.falconcalcultor.*;
import java.util.*;
import com.savtor.falconcalaultorDatabase.*;
import android.graphics.drawable.*;


/**1
 * Created by GhostLeo_DT on 24/11/2017.
 */
public class Fragment_Calcultor extends Fragment {

    double even_interest,  even_balance,  even_principle;
    private String Dialog_Title, Sub_Title;
    private Double result_installment;
    private int myCaseID;
    private double getLoanAmount, getLoanRate, getInstallment;
    private int getLoanTrems;
    private float x = 0;


    private String init_Loan_Amount, init_Loan_Trems, init_Loan_Rate;
    String dec_point = "%.0f";
	
	Drawable edit_icon;
	
    private ImageView addtofav_btn;
    private TextView loanAmount_tv, loanTrems_tv, loanRate_tv, installment_tv, total_insterest_tv, total_payment_tv;
    private LinearLayout loan_amount, loan_trems, loan_rate;
    private RecyclerView calcultor_recycleView;

    int databasic_ID;
    private String Edit_Mode;

    Calcultor mCalcultor = new Calcultor();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);

		Bundle mBundle = getArguments();

		if(mBundle != null){

            Edit_Mode = mBundle.getString("EDIT_MODE");

            if (Edit_Mode == "true"){

                databasic_ID  = mBundle.getInt("DB_ID");

                Favourite_DataBasic databasic = new Favourite_DataBasic(getActivity(), "Fragment_Calcultor");

                init_Loan_Amount = String.valueOf(databasic.query(databasic_ID).getLoan_Amount());
                init_Loan_Trems = String.valueOf(databasic.query(databasic_ID).getTrems());
                init_Loan_Rate = String.valueOf(databasic.query(databasic_ID).getLoan_Rate());

                edit_icon = getResources().getDrawable(R.drawable.ic_create_black_24dp);

            }

		}else{

            Edit_Mode = "false";
            init_Loan_Amount = "0.0";
            init_Loan_Trems = "12";
            init_Loan_Rate = "54.00";
			edit_icon = getResources().getDrawable(R.drawable.ic_add_black_24dp);
			
		}
		
	}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calcultor, container, false);

        Find_View(v);

        return v;
    }



    //=============================================================================================
    // [1] 加入畫面內容
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
                Fragment x = new Fragment_Saver();
                Bundle mBundle = new Bundle();
                mBundle.putDouble("loan_amount", Double.parseDouble(loanAmount_tv.getText().toString()));
                mBundle.putInt("loan_trems", Integer.parseInt(loanTrems_tv.getText().toString()));
                mBundle.putDouble("loan_rate", Double.parseDouble(loanRate_tv.getText().toString()));
                mBundle.putString("EDIT_MODE", Edit_Mode);
                mBundle.putInt("DB_ID", databasic_ID);
                x.setArguments(mBundle);

                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mFrameLayout, x).commit();
            }
        });  //[?]

        loanAmount_tv = (TextView) v.findViewById(R.id.loanAmount_tv);
        loanAmount_tv.setText(init_Loan_Amount);

        loanTrems_tv = (TextView) v.findViewById(R.id.loanTrems_tv);
        loanTrems_tv.setText(init_Loan_Trems);

        loanRate_tv = (TextView) v.findViewById(R.id.loanRate_tv);
        loanRate_tv.setText(init_Loan_Rate);

        installment_tv = (TextView) v.findViewById(R.id.installment_tv);

        total_insterest_tv = (TextView) v.findViewById(R.id.total_interest_tv);

        total_payment_tv = (TextView) v.findViewById(R.id.total_payment_tv);

        calcultor_recycleView = (RecyclerView) v.findViewById(R.id.calcultor_recyclerView);

        if(Edit_Mode != null && loanAmount_tv.getText() != "0.0"){

            get_Value();
            result_installment = mCalcultor.getMonthlyInstallment(getLoanAmount, getLoanTrems, getLoanRate);

            show_recyclerView();
        }
    }

    //=============================================================================================
    // [2] 點擊事件
    private View.OnClickListener linearLayout_OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.loanAmount_linear:
                    Dialog_Title = getResources().getString(R.string.calcultor_loanamount_subtitle);
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

            show_alert_dialog(v, Dialog_Title, Sub_Title, myCaseID);
        }
    };


    //=============================================================================================
    // [3] 利用 Alert Dialog 處理用戶輸入資料
    private void show_alert_dialog(View v, String Dialog_Title, String Sub_Title, final int myCaseID){

        final View dialog_view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calcultor_dialog, null);

        final TextInputLayout dialog_Textinput = (TextInputLayout) dialog_view.findViewById(R.id.calcultor_textinput);
        dialog_Textinput.setHint(Sub_Title);
        dialog_Textinput.requestFocus();

        final EditText calcul_EdTExt = (EditText) dialog_Textinput.findViewById(R.id.calcultor_dialog_edittext);
        calcul_EdTExt.requestFocus();

        if(myCaseID == 2){
            //            僅限輸入數字
            calcul_EdTExt.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else {
            //            僅限輸入數字 (含小數點)
            calcul_EdTExt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(v.getContext())
                .setTitle(Dialog_Title)
                .setView(dialog_view)
                .setPositiveButton("Done",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //      核對輸入資料
                        switch (myCaseID){

                            case 1:

                                if(calcul_EdTExt.getText().toString().isEmpty()){
                                    break;
                                }else {
                                    try {
                                        loanAmount_tv.setText(String.format("%.2f", Double.parseDouble(calcul_EdTExt.getText().toString())));
                                    }catch (NumberFormatException ee){ee.printStackTrace();}
                                }

                                break;

                            case 2:

                                if(calcul_EdTExt.getText().toString().isEmpty()){
                                    break;
                                }else if(Integer.parseInt(calcul_EdTExt.getText().toString()) < 3 || Integer.parseInt(calcul_EdTExt.getText().toString()) > 96) {
                                    Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
                                }else {
                                    loanTrems_tv.setText(calcul_EdTExt.getText().toString());
                                }

                                break;

                            case 3:

                                if(calcul_EdTExt.getText().toString().isEmpty()){
                                    break;
                                }else if(Double.parseDouble(calcul_EdTExt.getText().toString()) > 60 || Double.parseDouble(calcul_EdTExt.getText().toString()) < 1 ){
                                    Toast.makeText(getActivity(), "Proo Value", Toast.LENGTH_SHORT).show();
                                }else {
                                    try {
                                        loanRate_tv.setText(String.format("%.2f", Double.parseDouble(calcul_EdTExt.getText().toString())));
                                    }catch (NumberFormatException ee){ee.printStackTrace();}
                                }

                                break;

                        }

                        get_Value();
                        result_installment = mCalcultor.getMonthlyInstallment(getLoanAmount, getLoanTrems, getLoanRate);
                        total_insterest_tv.setText(String.format(dec_point, mCalcultor.getTotalInsterest(getLoanAmount, getLoanTrems, result_installment)));
                        total_payment_tv.setText(String.format(dec_point, mCalcultor.getTotalPayment(result_installment,getLoanTrems)));
                        randomAnim();
                        show_recyclerView();
                    }
                });

        final AlertDialog myDialog = mAlertDialog.show();
        myDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    //=============================================================================================
    // [4] 取出 Loan Amount, Loan Trems, Loan Rate, Installment
    private void get_Value(){
        try {
            getLoanAmount = Double.parseDouble(loanAmount_tv.getText().toString());
            getLoanTrems = Integer.parseInt(loanTrems_tv.getText().toString());
            getLoanRate = Double.parseDouble(loanRate_tv.getText().toString());
            getInstallment = Double.parseDouble(installment_tv.getText().toString());


        } catch (NumberFormatException ee){
            Toast.makeText(getActivity(), ee.toString(), Toast.LENGTH_SHORT);
        }
    }

    //=============================================================================================
    // [5] 計算完畢後動畫
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
                    x += result_installment/getLoanTrems;
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
    // [6] 計算完畢後動畫
    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            if(msg.what == 1) {

                if(x > result_installment){

                    get_Value();
                    installment_tv.setText(String.format(dec_point,result_installment));

                    mCalcultor.getTotalInsterest(getLoanAmount, getLoanTrems, getInstallment);
                    mCalcultor.getTotalPayment(getInstallment, getLoanTrems);

                }else {
                    installment_tv.setText(String.format(dec_point,x));
                }
            }else if(msg.what == 2){
                installment_tv.setText(String.format(dec_point,result_installment));
            }

            super.handleMessage(msg);
        }
    };

    //=============================================================================================
    // [7] 顯示 Recycle View
    public void show_recyclerView(){
        calcultor_recycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        List<Schedule_Data> paymentScheduleData = fill_width_date();
        Schedule_Adapter sc_adapter = new Schedule_Adapter(paymentScheduleData, getActivity());
        calcultor_recycleView.setAdapter(sc_adapter);

    }

    //=============================================================================================
    // [13] 將每期本金, 利息, 結餘 加入 List<Schedule_Data>
    public List<Schedule_Data> fill_width_date(){

        List<Schedule_Data> paymentScheduleData = new ArrayList<Schedule_Data>();

        get_Value();

        double even_balance = getLoanAmount;
        mCalcultor = new Calcultor();

        for (int i = 0; i < getLoanTrems; i++) {

            even_interest = mCalcultor.cal_even_interest(getLoanRate, even_balance);
            even_principle = mCalcultor.cal_even_principle(result_installment, even_interest);
            even_balance = mCalcultor.cal_even_balance(even_balance, result_installment, even_interest);

            paymentScheduleData.add(new Schedule_Data((i + 1) + "", Decimal_Point_changer(even_interest), String.format(dec_point, even_principle), String.format(dec_point, even_balance)));
        }

        return paymentScheduleData;
    }

    //=============================================================================================
    // [13] 小數點轉換
    public String Decimal_Point_changer(double value){

        return String.format(dec_point, value);
    }

}

