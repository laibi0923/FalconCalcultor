package com.savtor.Wish_List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.savtor.Credit_Database.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;
import com.savtor.Credit_Database.Favouite_Item;
import com.savtor.Credit_Profile.Credit_Profile_Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.v4.app.*;


/**
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class WishList_Adapter extends RecyclerSwipeAdapter<WishList_ViewHolder> {

    private List<Favouite_Item> list = Collections.emptyList();
    private Context context;
    private FragmentManager mFragmentManager;

    private int get_setting_password, get_setting_language, get_setting_decimal;
    private SharedPreferences mSharedPreferences;
    private String dec_point = "%1$.2f";


    public WishList_Adapter(List<Favouite_Item> list, Context context, FragmentManager mFragmentManager) {
        this.list = list;
        this.context = context;
        this.mFragmentManager = mFragmentManager;

        mSharedPreferences = context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        get_sharedpreferences();
    }

    @Override
    public WishList_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aa_wish_item, parent, false);

        WishList_ViewHolder holder = new WishList_ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final WishList_ViewHolder holder, final int position ) {
        
        holder.Last_Modify.setText(list.get(position).getCreate_Date());

        if (list.get(position).getProduct_Type().equals("Personal")){
            holder.Product_Icon.setImageResource(R.drawable.ic_person_black_48dp);
        }else if (list.get(position).getProduct_Type().equals("Mortgage")){
            holder.Product_Icon.setImageResource(R.drawable.ic_location_city_black_48dp);
        }else if (list.get(position).getProduct_Type().equals("Revolving")){
            holder.Product_Icon.setImageResource(R.drawable.ic_loop_black_48dp);
        }else if (list.get(position).getProduct_Type().equals("Car")){
            holder.Product_Icon.setImageResource(R.drawable.ic_airport_shuttle_black_48dp);
        }else if (list.get(position).getProduct_Type().equals("Card")){
            holder.Product_Icon.setImageResource(R.drawable.ic_credit_card_black_48dp);
        }

        holder.Product_Name.setText(list.get(position).getProduct_Name());

        if (list.get(position).getLoan_No() == null || list.get(position).getLoan_No().isEmpty()){
            holder.Loan_Number.setVisibility(View.GONE);
        }else {
            holder.Loan_Number.setText(list.get(position).getLoan_No());
        }

		holder.Loan_Amount.setText("借貸金額 : $ " + String.format(dec_point, list.get(position).getLoan_Amount()));

        holder.Loan_Trems.setText("償還期限 : " + String.valueOf(list.get(position).getLoan_Trems() ));

		holder.Loan_Rate.setText("借貸年利率 : " + String.format(dec_point, list.get(position).getLoan_Rate()));

        holder.Loan_installment.setText("$ " + String.format(dec_point, list.get(position).getLoan_Installment()));

        if (list.get(position).getProduct_Status().equals("NotApply")){
            holder.Approval_Status.setText(context.getString(R.string.product_status_notapply));
        }else if (list.get(position).getProduct_Status().equals("Pending")){
            holder.Approval_Status.setText(context.getString(R.string.product_status_pending));
        }else if (list.get(position).getProduct_Status().equals("Approval")){
            holder.Approval_Status.setText(context.getString(R.string.product_status_approval));
        }else if (list.get(position).getProduct_Status().equals("Reject")){
            holder.Approval_Status.setText(context.getString(R.string.product_status_reject));
        }else if (list.get(position).getProduct_Status().equals("Cancel")){
            holder.Approval_Status.setText(context.getString(R.string.product_status_cancel));
        }

        if (list.get(position).getAlarm_Time().isEmpty()){
            holder.Alarm_Icon.setVisibility(View.GONE);
        }else {
            holder.Alarm_Icon.setVisibility(View.VISIBLE);
        }

        holder.Delete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Del on database
                Favourite_DataBasic favourite_dataBasic = new Favourite_DataBasic(v.getContext(), "WishList_Adapter");
                favourite_dataBasic.delete(list.get(position).getID());

                // Del on list
                list.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount()); //加埋呢句先唔會 IndexOutOfIndexException

            }
        });

        holder.Edit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment mFragment = new Credit_Profile_Main();

                Bundle mBundle = new Bundle();
				
				mBundle.putString("From", "Favoutive");
                mBundle.putDouble("Amount", list.get(position).getLoan_Amount());
                mBundle.putInt("Trems", list.get(position).getLoan_Trems());
                mBundle.putDouble("Rate", list.get(position).getLoan_Rate());
                mBundle.putDouble("Installment", list.get(position).getLoan_Installment());
				mBundle.putInt("DB_ID", list.get(position).getID());

				mFragment.setArguments(mBundle);

				mFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.mFrameLayout, mFragment)
                        .commit();
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

    //=============================================================================================
    // []
    public void get_sharedpreferences(){
        get_setting_password = mSharedPreferences.getInt("Setting_password", 1);
        get_setting_language = mSharedPreferences.getInt("Setting_language", 1);
        get_setting_decimal = mSharedPreferences.getInt("Setting_decimal" , 1);

        if (get_setting_decimal == 1){
            dec_point = "%1$.0f";
        }else if(get_setting_decimal == 2){
            dec_point = "%1$.2f";
        }
    }



}


