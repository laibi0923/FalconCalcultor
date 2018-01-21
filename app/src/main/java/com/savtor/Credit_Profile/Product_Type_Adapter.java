package com.savtor.Credit_Profile;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.savtor.falconcalcultor.*;

import java.util.List;

/**
 * Created by GhostLeo_DT on 18/1/2018.
 */
public class Product_Type_Adapter extends PagerAdapter{

    private List<View> page_list;

    public Product_Type_Adapter(List<View> page_list){
        this.page_list = page_list;
    }

    @Override
    public int getCount() {
        return this.page_list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(page_list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(page_list.get(position));
        return page_list.get(position);
    }
}
