package com.savtor.falconcalcultorFavoutive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorCalcultor.Fragment_Calcultor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.v4.app.*;


/**
 * Created by GhostLeo_DT on 2/12/2017.
 */

//RecyclerView.Adapter<Favourite_ViewHolder>

public class Favourite_Adapter extends RecyclerSwipeAdapter<Favourite_ViewHolder> {

    List<Favourite_Data> list = Collections.emptyList();
    Context context;
    FragmentManager mFragmentManager;

    public Favourite_Adapter(List<Favourite_Data> list, Context context, FragmentManager mFragmentManager) {
        this.list = list;
        this.context = context;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public Favourite_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item_view, parent, false);

        Favourite_ViewHolder holder = new Favourite_ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Favourite_ViewHolder holder, final int position) {

        holder.create_date_tv.setText(list.get(position).create_date);

        if(list.get(position).name.isEmpty()){
            holder.name_tv.setText("-");
        }else {
            holder.name_tv.setText(list.get(position).name);
        }

        if(list.get(position).loan_amount.isEmpty()){
            holder.loan_amount_tv.setText("-");
        }else {
            holder.loan_amount_tv.setText(list.get(position).loan_amount);
        }

        if(list.get(position).loan_trems.isEmpty()){
            holder.loan_trems_tv.setText("-");
        }else {
            holder.loan_trems_tv.setText(list.get(position).loan_trems);
        }

        if(list.get(position).loan_rate.isEmpty()){
            holder.loan_rate_tv.setText("-");
        }else {
            holder.loan_rate_tv.setText(list.get(position).loan_rate);
        }

        if (list.get(position).apply_status == "0"){
            holder.apply_status_tv.setText("");
        }else if(list.get(position).apply_status == "1"){
            holder.apply_status_tv.setText("Pending");
        }else  if(list.get(position).apply_status == "2"){
            holder.apply_status_tv.setText("Approval");
        }else  if(list.get(position).apply_status == "3"){
            holder.apply_status_tv.setText("Reject");
        }


        if(list.get(position).loan_type == "0"){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_person_black_24dp);
        }else if(list.get(position).loan_type == "1" || list.get(position).loan_type.equals("1")){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_domain_black_24dp);
        }else if(list.get(position).loan_type == "2"){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);
        }

        if (list.get(position).alert_date == "0"){
            holder.alert_icon_im.setVisibility(View.GONE);
        }else {
            holder.alert_icon_im.setVisibility(View.VISIBLE);
        }

        holder.Del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Del on database
                Favourite_DataBasic favourite_dataBasic = new Favourite_DataBasic(v.getContext());
                favourite_dataBasic.delete(Integer.parseInt(list.get(position).getid()));

                Log.e("Del Action", "Positino = " + position );
                // Del on list
                list.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount()); //加埋呢句先唔會 IndexOutOfIndexException

            }
        });
//
        holder.Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment mFragment = new Fragment_Calcultor();

                Bundle mBundle = new Bundle();
				
				mBundle.putString("EDIT_MODE", "true");
				
				mBundle.putInt("DB_ID",Integer.parseInt(list.get(position).getid()));
				
				mFragment.setArguments(mBundle);
				
				mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.mFrameLayout, mFragment).commit();
            }
        });


        Fragment_Calcultor fc = new Fragment_Calcultor();
        double result = fc.getMonthlyInstallment(Float.parseFloat(list.get(position).loan_amount), Float.parseFloat(list.get(position).loan_rate),Integer.parseInt(list.get(position).loan_trems));
        holder.installment_tv.setText(String.format("%.2f",result));

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);


        holder.swipeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                ====================================================
//                設置5秒後自動關閉
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run()
                    {
                        // TODO: Implement this method
                        holder.swipeLayout.close();
                    }
                },2000);

//                ====================================================
                return false;
            }
        });

        holder.top_warp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.swipeLayout.open();

//                ====================================================
//                設置5秒後自動關閉
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run()
                    {
                        // TODO: Implement this method
                        holder.swipeLayout.close();
                    }
                },2000);

//                ====================================================

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }


    public void setFilter(List<Favourite_Data> countryModels) {
        list = new ArrayList<>();
        list.addAll(countryModels);
        notifyDataSetChanged();
    }

    @Override
    public void openItem(int position) {
        super.openItem(position);
    }
}


