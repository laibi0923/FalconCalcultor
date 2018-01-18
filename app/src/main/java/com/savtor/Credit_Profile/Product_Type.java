package com.savtor.Credit_Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savtor.falconcalcultor.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GhostLeo_DT on 18/1/2018.
 */
public class Product_Type extends Fragment {

    private int[] icon = {R.drawable.ic_person_black_24dp,
                          R.drawable.ic_domain_black_24dp,
                          R.drawable.ic_cached_black_24dp,
                          R.drawable.ic_directions_car_black_24dp,
                          R.drawable.ic_credit_card_black_48dp};

    private String[] name = {"1", "2", "3", "4", "5"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.credittype_main, container,false);
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.product_type_recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        List<Product_Type_Data> Product_Type_List = new ArrayList<Product_Type_Data>();

        for (int i = 0; i < icon.length; i ++){
            Product_Type_List.add(new Product_Type_Data(icon[i], name[i]));
        }

        Product_Type_Adapter adapter = new Product_Type_Adapter(Product_Type_List, getActivity());
        mRecyclerView.setAdapter(adapter);
        return v;
    }


}
