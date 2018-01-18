package com.savtor.Credit_Profile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 18/1/2018.
 */
public class Product_Type_ViewHolder extends RecyclerView.ViewHolder {

    public ImageView Type_Img;
    public TextView Type_Name;

    public Product_Type_ViewHolder(View itemView) {
        super(itemView);

        Type_Img = (ImageView) itemView.findViewById(R.id.Type_Icon);
        Type_Name = (TextView) itemView.findViewById(R.id.Type_Name);
    }
}
