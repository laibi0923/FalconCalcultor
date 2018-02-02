package com.savtor.Payment_Schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.savtor.falconcalcultor.*;
import com.savtor.Falcon_Calcultor.Calcultor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by GhostLeo_DT on 11/12/2017.
 */
public class Schedule_Fragment extends Fragment {

    private RecyclerView calcultor_recycleView;
    private LinearLayout schedule_btn;

    private Calcultor mCalcultor;
    private double even_interest, even_principle, loaninstallment;
    private double laonamount, loanrate;
    private int loantrems;

    private double Cumulative_Interest = 0;

    private SharedPreferences mSharedPreferences;

    private int get_setting_password, get_setting_language, get_setting_decimal;
    private String dec_point;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        get_sharedpreferences();

        Bundle mBundle = getArguments();

        if (mBundle != null){
            laonamount = mBundle.getDouble("loan_amount");
            loantrems = mBundle.getInt("loan_trems");
            loanrate = mBundle.getDouble("loan_rate");
            loaninstallment = mBundle.getDouble("loan_installment");
        }



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.schedule_fragment, container, false);

        Find_View(v);
        show_recyclerView();

        return v;
    }

    //=============================================================================================
    // [?]
    public void Find_View(View v){

        schedule_btn = (LinearLayout) v.findViewById(R.id.schedule_btn);
        schedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        calcultor_recycleView = (RecyclerView) v.findViewById(R.id.calcultor_recyclerView);
    }



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

        double even_balance = laonamount;
        mCalcultor = new Calcultor();

        for (int i = 0; i < loantrems; i++) {

            even_interest = mCalcultor.cal_even_interest(loanrate, even_balance);
            even_principle = mCalcultor.cal_even_principle(loaninstallment, even_interest);
            even_balance = mCalcultor.cal_even_balance(even_balance, loaninstallment, even_interest);

            if (Cumulative_Interest == 0){
                Cumulative_Interest = even_interest;
            }else {
                Cumulative_Interest = Cumulative_Interest + even_interest;
            }

            paymentScheduleData.add(new Schedule_Data((i + 1) + "", Decimal_Point_changer(even_interest), String.format(dec_point, even_principle), String.format(dec_point, even_balance), String.format(dec_point, Cumulative_Interest)));
        }

        return paymentScheduleData;
    }


    //=============================================================================================
    // []
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
    // []
    public String Decimal_Point_changer(double value){

        return String.format(dec_point, value);
    }



}
