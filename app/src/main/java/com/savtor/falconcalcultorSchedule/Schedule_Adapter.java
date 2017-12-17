package com.savtor.falconcalcultorSchedule;
import com.savtor.falconcalcultor.*;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

/**
 * Created by GhostLeo_DT on 26/11/2017.
 */
public class Schedule_Adapter extends RecyclerView.Adapter<Schedule_ViewHolder>{
    List<Schedule_Data> list = Collections.emptyList();
    Context context;

    public Schedule_Adapter(List<Schedule_Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Schedule_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_recycleview_item, parent, false);
        Schedule_ViewHolder holder = new Schedule_ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Schedule_ViewHolder holder, int position) {
        holder.scheldule_loan_trems.setText(list.get(position).sc_data_trems);
        holder.scheldule_loan_interest.setText(list.get(position).sc_data_insterest);
        holder.scheldule_loan_principle.setText(list.get(position).sc_data_principle);
        holder.scheldule_loan_balance.setText(list.get(position).sc_data_balance);
        holder.schedule_cumulative_Interest.setText(list.get(position).cumulative_Interest);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
