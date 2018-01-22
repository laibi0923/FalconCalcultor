package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GhostLeo_DT on 18/1/2018.
 */
public class Product_Type extends Fragment {

    private View Page_Personal;
    private ImageView Personal_icon, Personal_befor_icon;
    private TextView Personal_title;
    private LinearLayout Personal_LinearLayout;

    private View Page_Mortgage;
    private ImageView Mortgage_icon;
    private TextView Mortgage_title;
    private LinearLayout Mortgage_LinearLayout;

    private View Page_Revolving;
    private ImageView Revolving_icon;
    private TextView Revolving_title;
    private LinearLayout Revolving_LinearLayout;

    private View Page_Car;
    private ImageView Car_icon;
    private TextView Car_title;
    private LinearLayout Car_LinearLayout;

    private View Page_Card;
    private ImageView Card_icon;
    private TextView Card_title;
    private LinearLayout Card_LinearLayout;

    private List<View> Page_List;
    private List<String> Page_Title;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Product_Type_Adapter mAdapter;

    public static int lastPosition = 0;
    private Bundle mBundle, getBundle;

    private String From_Where;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBundle = new Bundle();
        getBundle = getArguments();

        if (getBundle != null) {
            From_Where = getBundle.getString("From", "");
            mBundle.putDouble("Amount", getBundle.getDouble("Amount", 0.00));
            mBundle.putDouble("Rate", getBundle.getDouble("Rate", 0.00));
            mBundle.putDouble("Installment", getBundle.getDouble("Installment", 0.00));
            mBundle.putInt("Trems", getBundle.getInt("Trems", 12));

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.credittype_main, container,false);

        Find_View(v, inflater);

        return v;
    }

/*================================================================================================
 *                                      Find View
================================================================================================ */
    private void Find_View(View v, LayoutInflater inflater){

        Page_Personal = inflater.inflate(R.layout.credittype_viewpager, null);
        Personal_icon = (ImageView) Page_Personal.findViewById(R.id.product_icon);
        Personal_icon.setImageResource(R.drawable.ic_person_black_48dp);


        Personal_title = (TextView) Page_Personal.findViewById(R.id.product_title);
        Personal_title.setText(R.string.title_product_personal);


        Page_Mortgage = inflater.inflate(R.layout.credittype_viewpager, null);
        Mortgage_icon = (ImageView) Page_Mortgage.findViewById(R.id.product_icon);
        Mortgage_icon.setImageResource(R.drawable.ic_location_city_black_48dp);

        Mortgage_title = (TextView) Page_Mortgage.findViewById(R.id.product_title);
        Mortgage_title.setText(R.string.title_product_mortgage);



        Page_Revolving = inflater.inflate(R.layout.credittype_viewpager, null);
        Revolving_icon = (ImageView) Page_Revolving.findViewById(R.id.product_icon);
        Revolving_icon.setImageResource(R.drawable.ic_loop_black_48dp);

        Revolving_title = (TextView) Page_Revolving.findViewById(R.id.product_title);
        Revolving_title.setText(R.string.title_product_revolving);


        Page_Car = inflater.inflate(R.layout.credittype_viewpager, null);
        Car_icon = (ImageView) Page_Car.findViewById(R.id.product_icon);
        Car_icon.setImageResource(R.drawable.ic_airport_shuttle_black_48dp);

        Car_title = (TextView) Page_Car.findViewById(R.id.product_title);
        Car_title.setText(R.string.title_product_car);


        Page_Card = inflater.inflate(R.layout.credittype_viewpager, null);
        Card_icon = (ImageView) Page_Card.findViewById(R.id.product_icon);
        Card_icon.setImageResource(R.drawable.ic_credit_card_black_48dp);

        Card_title = (TextView) Page_Card.findViewById(R.id.product_title);
        Card_title.setText(R.string.title_product_card);


        Page_List = new ArrayList<View>();
        Page_Title = new ArrayList<String>();

        Page_List.add(Page_Personal);
        Page_List.add(Page_Mortgage);
        Page_List.add(Page_Revolving);
        Page_List.add(Page_Car);

        if (From_Where == null || From_Where == ""){
            Page_List.add(Page_Card);
        }

        mViewPager = (ViewPager) v.findViewById(R.id.mviewPager);
        mAdapter = new Product_Type_Adapter(Page_List);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        mTabLayout = (TabLayout) v.findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < Page_List.size(); i++){
            mTabLayout.getTabAt(i).setText("1");
            mTabLayout.getTabTextColors();
        }

    }

/*================================================================================================
 *                              Transaction to Product Type
================================================================================================ */
    private void Fragment_Transaction(Fragment mfragment){

        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.setCustomAnimations(R.anim.fade_in, 0);
        mfragment.setArguments(mBundle);
        mFragmentTransaction.replace(R.id.mFrameLayout, mfragment);
        mFragmentTransaction.commit();

    }

/*================================================================================================
 *
================================================================================================ */
    private View.OnClickListener Product_OnSelect_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int get_tag = (int) v.getTag();

            if (get_tag == 1){ mBundle.putString("Product_Type", "Personal"); }
            else if(get_tag == 2){ mBundle.putString("Product_Type", "Mortgage"); }
            else if (get_tag == 3){ mBundle.putString("Product_Type", "Revolving"); }
            else if (get_tag == 4){ mBundle.putString("Product_Type", "Car"); }
            else if (get_tag == 5){ mBundle.putString("Product_Type", "Card"); }

            mBundle.putString("From", "Product_Type");
            Fragment_Transaction(new Credit_Profile_Main());
        }
    };

}
