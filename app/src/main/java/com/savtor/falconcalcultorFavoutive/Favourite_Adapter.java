package com.savtor.falconcalcultorFavoutive;

import android.content.Context;
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
import com.savtor.falconcalcultorCalcultor.Calcultor;
import com.savtor.falconcalcultorCalcultor.Fragment_Calcultor;
import com.savtor.falconcalaultorDatabase.Favouite_Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.v4.app.*;


/**
 * Created by GhostLeo_DT on 2/12/2017.
 */

//RecyclerView.Adapter<Favourite_ViewHolder>1

public class Favourite_Adapter extends RecyclerSwipeAdapter<Favourite_ViewHolder> {

    List<Favouite_Item> list = Collections.emptyList();
    Context context;
    FragmentManager mFragmentManager;

    public Favourite_Adapter(List<Favouite_Item> list, Context context, FragmentManager mFragmentManager) {
        this.list = list;
        this.context = context;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public Favourite_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_recycleview_item, parent, false);

        Favourite_ViewHolder holder = new Favourite_ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Favourite_ViewHolder holder, final int position) {

        holder.create_date_tv.setText(list.get(position).getCreate_date());

        if(list.get(position).getName().isEmpty()){
            holder.name_tv.setText("-");
        }else {
            holder.name_tv.setText(list.get(position).getName());
        }
		
		holder.loan_amount_tv.setText(String.valueOf(list.get(position).getLoan_Amount()));

        holder.loan_trems_tv.setText(String.valueOf( list.get(position).getTrems() ));

		holder.loan_rate_tv.setText(String.valueOf( list.get(position).getLoan_Rate()));
        
		holder.apply_status_tv.setText(list.get(position).getApply_status());
       

        if(list.get(position).getLoan_Type() == "0"){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_person_black_24dp);
        }else if(list.get(position).getLoan_Type() == "1" || list.get(position).getLoan_Type().equals("1")){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_domain_black_24dp);
        }else if(list.get(position).getLoan_Type() == "2"){
            holder.loan_type_icon.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);
        }

        if (list.get(position).getAlert_date() == ""){
            holder.alert_icon_im.setVisibility(View.GONE);
        }else {
            holder.alert_icon_im.setVisibility(View.VISIBLE);
        }

        holder.Del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Del on database
                Favourite_DataBasic favourite_dataBasic = new Favourite_DataBasic(v.getContext(), "Favourite_Adapter");
                favourite_dataBasic.delete(list.get(position).getid());

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
				
				mBundle.putInt("DB_ID", list.get(position).getid());

				mFragment.setArguments(mBundle);
				
				mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.mFrameLayout, mFragment).commit();
            }
        });


        Calcultor mCalcultor = new Calcultor();
        double result = mCalcultor.getMonthlyInstallment(list.get(position).getLoan_Amount(), list.get(position).getTrems(), list.get(position).getLoan_Rate());
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


    public void setFilter(List<Favouite_Item> countryModels) {
        list = new ArrayList<>();
        list.addAll(countryModels);
        notifyDataSetChanged();
    }

    @Override
    public void openItem(int position) {
        super.openItem(position);
    }
}


