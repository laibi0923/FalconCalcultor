package com.savtor.Debit_Info;
import com.savtor.WishList_Database.WishList_DataBasic;
import com.savtor.falconcalcultor.*;

import android.content.res.*;
import android.os.*;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v4.view.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.support.v4.widget.*;

public class DebitInfo_Main extends Fragment
{

	private View Page_Personal, Page_Mortgage, Page_Revolving, Page_Car, Page_Card;

	private View Amount_Bar1, Amount_Bar2, Amount_Bar3, Amount_Bar4, Amount_Bar5;
	private View Count_Bar1, Count_Bar2, Count_Bar3, Count_Bar4, Count_Bar5;
	private View Payment_Bar1, Payment_Bar2, Payment_Bar3, Payment_Bar4, Payment_Bar5;

	private TextView Personal_Title, Mortgage_Title, Revolving_Title, Car_Title, Card_Title;
	private TextView Personal_Count_TextView, Mortgage_Count_TextView, Revolving_Count_TextView, Car_Count_TextView, Card_Count_TextView;
	private TextView Personal_TotalAmount_TextView, Mortgage_TotalAmount_TextView, Revolving_TotalAmount_TextView, Car_TotalAmount_TextView, Card_TotalAmount_TextView;
	private TextView Personal_TotalPay_TextView, Mortgage_TotalPay_TextView, Revolving_TotalPay_TextView, Car_TotalPay_TextView, Card_TotalPay_TextView;
	private TextView Amount_Percentage, Product_Percentage, Payment_Percentage;

	private String Personal_Count, Mortgage_Count, Revolving_Count, Car_Count, Card_Count;
	private String Personal_TotalAmount, Mortgage_TotalAmount, Revolving_TotalAmount, Car_TotalAmount, Card_TotalAmount;
	private String Personal_TotalpayAmount, Mortgage_TotalpayAmount, Revolving_TotalpayAmount, Car_TotalpayAmount, Card_TotalpayAmount;

	private float total_count, total_approval_amount, total_payment_amount;
	
	private LinearLayout Personal_Review_Btn, Mortgage_Review_Btn, Revolving_Review_Btn, Car_Review_Btn, Card_Review_Btn;

	private ImageView Personal_Icon, Mortgage_Icon, Revolving_Icon, Car_Icon, Card_Icon;
	
	private LinearLayout Empty_View;
	
	private NestedScrollView NCV;

	private List<View> Page_List;

	private ViewPager mViewPager;

	private DebitInfo_Adapter mAdapter;

	private LinearLayout silderDotspanel;

	private ImageView[] dots;

	private int dotscount;

	private int bar_min_height = 5;

	private WishList_DataBasic mDataBasic;
	
	private String toggle_view;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDataBasic = new WishList_DataBasic(getActivity(), "DebitInfo_Main");

		Personal_Count = String.valueOf(mDataBasic.get_count_value("Personal", "Approval"));
		Mortgage_Count = String.valueOf(mDataBasic.get_count_value("Mortgage", "Approval"));
		Revolving_Count = String.valueOf(mDataBasic.get_count_value("Revolving", "Approval"));
		Car_Count = String.valueOf(mDataBasic.get_count_value("Car", "Approval"));
		Card_Count = String.valueOf(mDataBasic.get_count_value("Card", "Approval"));

		total_count = mDataBasic.get_count_value("Approval");

		Personal_TotalAmount = String.format("%.2f", mDataBasic.get_sumAmount_value("Personal", "Approval"));
		Mortgage_TotalAmount = String.format("%.2f", mDataBasic.get_sumAmount_value("Mortgage", "Approval"));
		Revolving_TotalAmount = String.format("%.2f", mDataBasic.get_sumAmount_value("Revolving", "Approval"));
		Car_TotalAmount = String.format("%.2f", mDataBasic.get_sumAmount_value("Car", "Approval"));
		Card_TotalAmount = String.format("%.2f", mDataBasic.get_sumAmount_value("Card", "Approval"));

		total_approval_amount = mDataBasic.get_sumAmount_value("Approval");

		Personal_TotalpayAmount = String.format("%.2f", mDataBasic.get_sumPayment_value("Personal", "Approval"));
		Mortgage_TotalpayAmount = String.format("%.2f", mDataBasic.get_sumPayment_value("Mortgage", "Approval"));
		Revolving_TotalpayAmount = String.format("%.2f", mDataBasic.get_sumPayment_value("Revolving", "Approval"));
		Car_TotalpayAmount = String.format("%.2f", mDataBasic.get_sumPayment_value("Car", "Approval"));
		Card_TotalpayAmount = String.format("%.2f", mDataBasic.get_sumPayment_value("Card", "Approval"));

		total_payment_amount = mDataBasic.get_sumPayment_value("Approval");
		
		mDataBasic.close();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.debitinfo_main, container, false);
		
		Find_View(v, inflater);
		
		return v;
	}
	
	private void Find_View(View v, LayoutInflater inflater){

		Page_Personal = inflater.inflate(R.layout.debitinfo_viewpapger, null);
		Personal_Title = (TextView) Page_Personal.findViewById(R.id.product_type);
		Personal_Title.setText(getString(R.string.title_product_personal));
		Personal_Icon = (ImageView) Page_Personal.findViewById(R.id.product_type_icon);
		Personal_Icon.setImageResource(R.drawable.ic_person_black_48dp);
		Personal_Count_TextView = (TextView) Page_Personal.findViewById(R.id.total_count);
		Personal_Count_TextView.setText(Personal_Count);
		Personal_TotalAmount_TextView = (TextView) Page_Personal.findViewById(R.id.total_amount);
		Personal_TotalAmount_TextView.setText("$ " + Personal_TotalAmount);
		Personal_TotalPay_TextView = (TextView) Page_Personal.findViewById(R.id.total_payment);
		Personal_TotalPay_TextView.setText("$ " + Personal_TotalpayAmount);
		Personal_Review_Btn = (LinearLayout) Page_Personal.findViewById(R.id.product_review_btn);
		Personal_Review_Btn.setTag(1);
		Personal_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Mortgage = inflater.inflate(R.layout.debitinfo_viewpapger, null);
		Mortgage_Title = (TextView) Page_Mortgage.findViewById(R.id.product_type);
		Mortgage_Title.setText(getString(R.string.title_product_mortgage));
		Mortgage_Icon = (ImageView) Page_Mortgage.findViewById(R.id.product_type_icon);
		Mortgage_Icon.setImageResource(R.drawable.ic_location_city_black_48dp);
		Mortgage_Count_TextView = (TextView) Page_Mortgage.findViewById(R.id.total_count);
		Mortgage_Count_TextView.setText(Mortgage_Count);
		Mortgage_TotalAmount_TextView = (TextView) Page_Mortgage.findViewById(R.id.total_amount);
		Mortgage_TotalAmount_TextView.setText("$ " + Mortgage_TotalAmount);
		Mortgage_TotalPay_TextView = (TextView) Page_Mortgage.findViewById(R.id.total_payment);
		Mortgage_TotalPay_TextView.setText("$ " + Mortgage_TotalpayAmount);
		Mortgage_Review_Btn = (LinearLayout) Page_Mortgage.findViewById(R.id.product_review_btn);
		Mortgage_Review_Btn.setTag(2);
		Mortgage_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Revolving = inflater.inflate(R.layout.debitinfo_viewpapger, null);
		Revolving_Title = (TextView) Page_Revolving.findViewById(R.id.product_type);
		Revolving_Title.setText(getString(R.string.title_product_revolving));
		Revolving_Icon = (ImageView) Page_Revolving.findViewById(R.id.product_type_icon);
		Revolving_Icon.setImageResource(R.drawable.ic_loop_black_48dp);
		Revolving_Count_TextView = (TextView) Page_Revolving.findViewById(R.id.total_count);
		Revolving_Count_TextView.setText(String.valueOf(Revolving_Count));
		Revolving_TotalAmount_TextView = (TextView) Page_Revolving.findViewById(R.id.total_amount);
		Revolving_TotalAmount_TextView.setText("$ " + Revolving_TotalAmount);
		Revolving_TotalPay_TextView = (TextView) Page_Revolving.findViewById(R.id.total_payment);
		Revolving_TotalPay_TextView.setText("$ " + Revolving_TotalpayAmount);
		Revolving_Review_Btn = (LinearLayout) Page_Revolving.findViewById(R.id.product_review_btn);
		Revolving_Review_Btn.setTag(3);
		Revolving_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Car = inflater.inflate(R.layout.debitinfo_viewpapger, null);
		Car_Title = (TextView) Page_Car.findViewById(R.id.product_type);
		Car_Title.setText(getString(R.string.title_product_car));
		Car_Icon = (ImageView) Page_Car.findViewById(R.id.product_type_icon);
		Car_Icon.setImageResource(R.drawable.ic_airport_shuttle_black_48dp);
		Car_Count_TextView = (TextView) Page_Car.findViewById(R.id.total_count);
		Car_Count_TextView.setText(String.valueOf(Car_Count));
		Car_TotalAmount_TextView = (TextView) Page_Car.findViewById(R.id.total_amount);
		Car_TotalAmount_TextView.setText("$ " + Car_TotalAmount);
		Car_TotalPay_TextView = (TextView) Page_Car.findViewById(R.id.total_payment);
		Car_TotalPay_TextView.setText("$ " + Car_TotalpayAmount);
		Car_Review_Btn = (LinearLayout) Page_Car.findViewById(R.id.product_review_btn);
		Car_Review_Btn.setTag(4);
		Car_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Card = inflater.inflate(R.layout.debitinfo_viewpapger, null);
		Card_Title = (TextView) Page_Card.findViewById(R.id.product_type);
		Card_Title.setText(getString(R.string.title_product_card));
		Card_Icon = (ImageView) Page_Card.findViewById(R.id.product_type_icon);
		Card_Icon.setImageResource(R.drawable.ic_credit_card_black_48dp);
		Card_Count_TextView = (TextView) Page_Card.findViewById(R.id.total_count);
		Card_Count_TextView.setText(String.valueOf(Card_Count));
		Card_TotalAmount_TextView = (TextView) Page_Card.findViewById(R.id.total_amount);
		Card_TotalAmount_TextView.setText("$ " + Card_TotalAmount);
		Card_TotalPay_TextView = (TextView) Page_Card.findViewById(R.id.total_payment);
		Card_TotalPay_TextView.setText("$ " + Card_TotalpayAmount);
		Card_Review_Btn = (LinearLayout) Page_Card.findViewById(R.id.product_review_btn);
		Card_Review_Btn.setTag(5);
		Card_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);



		Amount_Percentage = (TextView) v.findViewById(R.id.amount_percentage);
		Amount_Bar1 = v.findViewById(R.id.amount_bar_1);
		Amount_Bar1.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Personal_TotalAmount), total_approval_amount) + bar_min_height);
		Amount_Bar2 = v.findViewById(R.id.amount_bar_2);
		Amount_Bar2.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Mortgage_TotalAmount), total_approval_amount) + bar_min_height);
		Amount_Bar3 = v.findViewById(R.id.amount_bar_3);
		Amount_Bar3.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Revolving_TotalAmount), total_approval_amount) + bar_min_height);
		Amount_Bar4 = v.findViewById(R.id.amount_bar_4);
		Amount_Bar4.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Car_TotalAmount), total_approval_amount) + bar_min_height);
		Amount_Bar5 = v.findViewById(R.id.amount_bar_5);
		Amount_Bar5.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Card_TotalAmount), total_approval_amount) + bar_min_height);


		Product_Percentage = (TextView) v.findViewById(R.id.product_percentage);
		Count_Bar1 = v.findViewById(R.id.count_bar_1);
		Count_Bar1.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Personal_Count), total_count) + bar_min_height);
		Count_Bar2 = v.findViewById(R.id.count_bar_2);
		Count_Bar2.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Mortgage_Count), total_count) + bar_min_height);
		Count_Bar3 = v.findViewById(R.id.count_bar_3);
		Count_Bar3.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Revolving_Count), total_count) + bar_min_height);
		Count_Bar4 = v.findViewById(R.id.count_bar_4);
		Count_Bar4.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Car_Count), total_count) + bar_min_height);
		Count_Bar5 = v.findViewById(R.id.count_bar_5);
		Count_Bar5.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Card_Count), total_count) + bar_min_height);


		Payment_Percentage = (TextView) v.findViewById(R.id.payment_percentage);
		Payment_Bar1 = v.findViewById(R.id.payment_bar_1);
		Payment_Bar1.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Personal_TotalpayAmount), total_payment_amount) + bar_min_height);
		Payment_Bar2 = v.findViewById(R.id.payment_bar_2);
		Payment_Bar2.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Mortgage_TotalpayAmount), total_payment_amount) + bar_min_height);
		Payment_Bar3 = v.findViewById(R.id.payment_bar_3);
		Payment_Bar3.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Revolving_TotalpayAmount), total_payment_amount) + bar_min_height);
		Payment_Bar4 = v.findViewById(R.id.payment_bar_4);
		Payment_Bar4.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Car_TotalpayAmount), total_payment_amount) + bar_min_height);
		Payment_Bar5 = v.findViewById(R.id.payment_bar_5);
		Payment_Bar5.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, cal_percentage(Float.parseFloat(Card_TotalpayAmount), total_payment_amount) +bar_min_height);
		
		Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_TotalAmount), total_approval_amount)) + " %");
		Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_Count), total_count)) + " %");
		Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_TotalpayAmount), total_payment_amount)) + " %");
		
		clear_bar_color();
		Amount_Bar1.setBackgroundResource(R.color.blue_grey_800);
		Count_Bar1.setBackgroundResource(R.color.blue_grey_800);
		Payment_Bar1.setBackgroundResource(R.color.blue_grey_800);
		
		Page_List = new ArrayList<View>();
		Page_List.add(Page_Personal);
		Page_List.add(Page_Mortgage);
		Page_List.add(Page_Revolving);
		Page_List.add(Page_Car);
		Page_List.add(Page_Card);

		
		mViewPager = (ViewPager) v.findViewById(R.id.debit_viewpager);
		mAdapter = new DebitInfo_Adapter(Page_List);
		mViewPager.setAdapter(mAdapter);
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

				@Override
				public void onPageSelected(int position) {
					
					for(int i=0; i < dotscount; i++){
						dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_non_active));
						dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_active));
					}
					
					clear_bar_color();
					set_bar_color(position);
				}

				@Override
				public void onPageScrollStateChanged(int state) {}
			});
		
		silderDotspanel = (LinearLayout) v.findViewById(R.id.sliding_dots);
		dotscount = mAdapter.getCount();
		dots = new ImageView[dotscount];

		for(int i = 0; i < dotscount; i++){
			dots[i] = new ImageView(this.getActivity());
			dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_non_active));

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(8, 0, 8, 0);
			silderDotspanel.addView(dots[i], params);
		}

		dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_active));
	}

/*================================================================================================
 *
================================================================================================ */
	private View.OnClickListener Review_Btn_OnclikListener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			// TODO: Implement this method
			int btn_tag = (int) view.getTag();
			switch(btn_tag){
				
				case 1:
					Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
					break;
					
				case 2:
					Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
					break;
					
				case 3:
					Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
					break;
					
				case 4:
					Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
					break;
					
				case 5:
					Toast.makeText(getContext(), "5", Toast.LENGTH_SHORT).show();
					break;
			}
		}
		
	};

/*================================================================================================
 *                                  預設所有 Bar 顏色
================================================================================================ */
	private void clear_bar_color(){
		Amount_Bar1.setBackgroundResource(R.color.blue_grey_900);
		Amount_Bar2.setBackgroundResource(R.color.blue_grey_900);
		Amount_Bar3.setBackgroundResource(R.color.blue_grey_900);
		Amount_Bar4.setBackgroundResource(R.color.blue_grey_900);
		Amount_Bar5.setBackgroundResource(R.color.blue_grey_900);
		Count_Bar1.setBackgroundResource(R.color.blue_grey_900);
		Count_Bar2.setBackgroundResource(R.color.blue_grey_900);
		Count_Bar3.setBackgroundResource(R.color.blue_grey_900);
		Count_Bar4.setBackgroundResource(R.color.blue_grey_900);
		Count_Bar5.setBackgroundResource(R.color.blue_grey_900);
		Payment_Bar1.setBackgroundResource(R.color.blue_grey_900);
		Payment_Bar2.setBackgroundResource(R.color.blue_grey_900);
		Payment_Bar3.setBackgroundResource(R.color.blue_grey_900);
		Payment_Bar4.setBackgroundResource(R.color.blue_grey_900);
		Payment_Bar5.setBackgroundResource(R.color.blue_grey_900);
	}

/*================================================================================================
 *                                 高亮選中 Bar 顏色
================================================================================================ */
	private void set_bar_color(int position){
		
		switch(position){
			
			case 0:
				Amount_Bar1.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar1.setBackgroundResource(R.color.blue_grey_800);
				Payment_Bar1.setBackgroundResource(R.color.blue_grey_800);
				Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_TotalAmount), total_approval_amount)) + " %");
				Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_Count), total_count)) + " %");
				Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Personal_TotalpayAmount), total_payment_amount)) + " %");
				break;
				
			case 1:
				Amount_Bar2.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar2.setBackgroundResource(R.color.blue_grey_800);
				Payment_Bar2.setBackgroundResource(R.color.blue_grey_800);
				Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Mortgage_TotalpayAmount), total_approval_amount)) + " %");
				Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Mortgage_Count), total_count)) + " %");
				Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Mortgage_TotalpayAmount), total_payment_amount)) + " %");
				break;
				
			case 2:
				Amount_Bar3.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar3.setBackgroundResource(R.color.blue_grey_800);
				Payment_Bar3.setBackgroundResource(R.color.blue_grey_800);
				Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Revolving_TotalAmount), total_approval_amount)) + " %");
				Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Revolving_Count), total_count)) + " %");
				Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Revolving_TotalpayAmount), total_payment_amount)) + " %");
				break;
				
			case 3:
				Amount_Bar4.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar4.setBackgroundResource(R.color.blue_grey_800);
				Payment_Bar4.setBackgroundResource(R.color.blue_grey_800);
				Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Car_TotalAmount), total_approval_amount)) + " %");
				Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Car_Count), total_count)) + " %");
				Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Car_TotalpayAmount), total_payment_amount)) + " %");
				break;
				
			case 4:
				Amount_Bar5.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar5.setBackgroundResource(R.color.blue_grey_800);
				Payment_Bar5.setBackgroundResource(R.color.blue_grey_800);
				Amount_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Card_TotalAmount), total_approval_amount)) + " %");
				Product_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Card_Count), total_count)) + " %");
				Payment_Percentage.setText(String.format("%.2f", cal_percentage(Float.parseFloat(Card_TotalpayAmount), total_payment_amount)) + " %");
				break;
		}
		
	}

/*================================================================================================
 *                                     轉換為 DP
================================================================================================ */
	public float getRawSize(int unit, float value) { 
        Resources res = this.getResources(); 
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics()); 
    }


/*================================================================================================
 *                                     設置統計項目條高度
================================================================================================ */
	private float cal_percentage(float value, float total){
		
		float result = value / total * 100;
		if (Float.isNaN(result)){ result = 0; }
		return result;
	}

}

