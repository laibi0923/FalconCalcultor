package com.savtor.falconcalcultorSchedule;

import com.savtor.falconcalcultor.*;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GhostLeo_DT on 26/11/2017.
 */
public class Schedule_ViewHolder extends RecyclerView.ViewHolder {

    TextView scheldule_loan_trems, scheldule_loan_interest, scheldule_loan_principle, scheldule_loan_balance, schedule_cumulative_Interest;

    Schedule_ViewHolder(View view){
        super (view);

        scheldule_loan_balance = (TextView) view.findViewById(R.id.recycle_balance_tv);
        scheldule_loan_interest = (TextView) view.findViewById(R.id.recycle_interest_tv);
        scheldule_loan_trems = (TextView) view.findViewById(R.id.recycle_trems_tv);
        scheldule_loan_principle = (TextView) view.findViewById(R.id.recycle_principle_tv);
        schedule_cumulative_Interest = (TextView) view.findViewById(R.id.recycle_cumulative_Interest_tv);

    }
}
