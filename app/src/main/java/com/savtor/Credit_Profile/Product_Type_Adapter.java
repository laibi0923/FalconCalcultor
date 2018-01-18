package com.savtor.Credit_Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 18/1/2018.
 */
public class Product_Type_Adapter extends RecyclerView.Adapter<Product_Type_ViewHolder> {

    List<Product_Type_Data> list = Collections.emptyList();
    Context context;

    public Product_Type_Adapter(List<Product_Type_Data> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public Product_Type_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.credittype_recycleview_item, parent, false);

        Product_Type_ViewHolder holder = new Product_Type_ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(Product_Type_ViewHolder holder, int position) {
        holder.Type_Img.setImageResource(list.get(position).Type_Icon);
        holder.Type_Name.setText(list.get(position).Type_Name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
