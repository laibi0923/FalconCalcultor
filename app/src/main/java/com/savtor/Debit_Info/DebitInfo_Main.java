package com.savtor.Debit_Info;
import android.content.res.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v4.view.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.savtor.falconcalcultor.*;
import java.util.*;
import android.view.View.*;

public class DebitInfo_Main extends Fragment
{

	private View Page_Personal, Page_Mortgage, Page_Revolving, Page_Car, Page_Card;
	private View Amount_Bar1, Amount_Bar2, Amount_Bar3, Amount_Bar4, Amount_Bar5;
	private View Count_Bar1, Count_Bar2, Count_Bar3, Count_Bar4, Count_Bar5;
	private TextView Personal_Title, Mortgage_Title, Revolving_Title, Car_Title, Card_Title;
	private TextView Personal_Count_Text, Mortgage_Count_Text, Revolving_Count_Text, Car_Count_Text, Card_Count_Text;
	private TextView Personal_TotalAmount_Text, Mortgage_TotalAmount_Text, Revolving_TotalAmount_Text, Car_TotalAmount_Text, Card_TotalAmount_Text;
	private TextView Personal_TotalPay_Text, Mortgage_TotalPay_Text, Revolving_TotalPay_Text, Car_TotalPay_Text, Card_TotalPay_Text;
	private LinearLayout Personal_Review_Btn, Mortgage_Review_Btn, Revolving_Review_Btn, Car_Review_Btn, Card_Review_Btn;
	private ImageView Personal_Icon, Mortgage_Icon, Revolving_Icon, Car_Icon, Card_Icon;
	private List<View> Page_List;
	private ViewPager mViewPager;
	private DebitInfo_Adapter mAdapter;
	private LinearLayout silderDotspanel;
	private ImageView[] dots;
	private int dotscount;
	private int bar_min_height = 5;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.zz_debit_main, container, false);
		
		Find_View(v, inflater);
		
		return v;
	}
	
	private void Find_View(View v, LayoutInflater inflater){
		
		Page_Personal = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Personal_Title = (TextView) Page_Personal.findViewById(R.id.product_type);
		Personal_Title.setText(getString(R.string.title_product_personal));
		Personal_Icon = (ImageView) Page_Personal.findViewById(R.id.product_type_icon);
		Personal_Icon.setImageResource(R.drawable.ic_person_black_48dp);
		Personal_Count_Text = (TextView) Page_Personal.findViewById(R.id.total_count);
		Personal_TotalAmount_Text = (TextView) Page_Personal.findViewById(R.id.total_amount);
		Personal_TotalPay_Text = (TextView) Page_Personal.findViewById(R.id.total_payment);
		Personal_Review_Btn = (LinearLayout) Page_Personal.findViewById(R.id.product_review_btn);
		Personal_Review_Btn.setTag(1);
		Personal_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Mortgage = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Mortgage_Title = (TextView) Page_Mortgage.findViewById(R.id.product_type);
		Mortgage_Title.setText(getString(R.string.title_product_mortgage));
		Mortgage_Icon = (ImageView) Page_Mortgage.findViewById(R.id.product_type_icon);
		Mortgage_Icon.setImageResource(R.drawable.ic_location_city_black_48dp);
		Mortgage_Count_Text = (TextView) Page_Mortgage.findViewById(R.id.total_count);
		Mortgage_TotalAmount_Text = (TextView) Page_Mortgage.findViewById(R.id.total_amount);
		Mortgage_TotalPay_Text = (TextView) Page_Mortgage.findViewById(R.id.total_payment);
		Mortgage_Review_Btn = (LinearLayout) Page_Mortgage.findViewById(R.id.product_review_btn);
		Mortgage_Review_Btn.setTag(2);
		Mortgage_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Revolving = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Revolving_Title = (TextView) Page_Revolving.findViewById(R.id.product_type);
		Revolving_Title.setText(getString(R.string.title_product_revolving));
		Revolving_Icon = (ImageView) Page_Revolving.findViewById(R.id.product_type_icon);
		Revolving_Icon.setImageResource(R.drawable.ic_loop_black_48dp);
		Revolving_Count_Text = (TextView) Page_Revolving.findViewById(R.id.total_count);
		Revolving_TotalAmount_Text = (TextView) Page_Revolving.findViewById(R.id.total_amount);
		Revolving_TotalPay_Text = (TextView) Page_Revolving.findViewById(R.id.total_payment);
		Revolving_Review_Btn = (LinearLayout) Page_Revolving.findViewById(R.id.product_review_btn);
		Revolving_Review_Btn.setTag(3);
		Revolving_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Car = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Car_Title = (TextView) Page_Car.findViewById(R.id.product_type);
		Car_Title.setText(getString(R.string.title_product_car));
		Car_Icon = (ImageView) Page_Car.findViewById(R.id.product_type_icon);
		Car_Icon.setImageResource(R.drawable.ic_airport_shuttle_black_48dp);
		Car_Count_Text = (TextView) Page_Car.findViewById(R.id.total_count);
		Car_TotalAmount_Text = (TextView) Page_Car.findViewById(R.id.total_amount);
		Car_TotalPay_Text = (TextView) Page_Car.findViewById(R.id.total_payment);
		Car_Review_Btn = (LinearLayout) Page_Car.findViewById(R.id.product_review_btn);
		Car_Review_Btn.setTag(4);
		Car_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		Page_Card = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Card_Title = (TextView) Page_Card.findViewById(R.id.product_type);
		Card_Title.setText(getString(R.string.title_product_card));
		Card_Icon = (ImageView) Page_Card.findViewById(R.id.product_type_icon);
		Card_Icon.setImageResource(R.drawable.ic_credit_card_black_48dp);
		Card_Count_Text = (TextView) Page_Card.findViewById(R.id.total_count);
		Card_TotalAmount_Text = (TextView) Page_Card.findViewById(R.id.total_amount);
		Card_TotalPay_Text = (TextView) Page_Card.findViewById(R.id.total_payment);
		Card_Review_Btn = (LinearLayout) Page_Card.findViewById(R.id.product_review_btn);
		Card_Review_Btn.setTag(5);
		Card_Review_Btn.setOnClickListener(Review_Btn_OnclikListener);
		
		
		
		Amount_Bar1 = v.findViewById(R.id.amount_bar_1);
		Amount_Bar1.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Amount_Bar2 = v.findViewById(R.id.amount_bar_2);
		Amount_Bar2.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Amount_Bar3 = v.findViewById(R.id.amount_bar_3);
		Amount_Bar3.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Amount_Bar4 = v.findViewById(R.id.amount_bar_4);
		Amount_Bar4.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Amount_Bar5 = v.findViewById(R.id.amount_bar_5);
		Amount_Bar5.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		

		Count_Bar1 = v.findViewById(R.id.count_bar_1);
		Count_Bar1.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Count_Bar2 = v.findViewById(R.id.count_bar_2);
		Count_Bar2.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Count_Bar3 = v.findViewById(R.id.count_bar_3);
		Count_Bar3.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Count_Bar4 = v.findViewById(R.id.count_bar_4);
		Count_Bar4.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		Count_Bar5 = v.findViewById(R.id.count_bar_5);
		Count_Bar5.getLayoutParams().height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP, bar_min_height);
		
		clear_bar_color();
		Amount_Bar1.setBackgroundResource(R.color.blue_grey_800);
		Count_Bar1.setBackgroundResource(R.color.blue_grey_800);
		
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
	
	private View.OnClickListener Review_Btn_OnclikListener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			// TODO: Implement this method
			int btn_tag = view.getTag();
			switch( (int) btn_tag){
				
				case 1:
					break;
					
				case 2:
					break;
					
				case 3:
					break;
					
				case 4:
					break;
					
				case 5:
					break;
			}
		}
		
	};
	
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
	}
	
	private void set_bar_color(int position){
		
		switch(position){
			
			case 0:
				Amount_Bar1.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar1.setBackgroundResource(R.color.blue_grey_800);
				break;
				
			case 1:
				Amount_Bar2.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar2.setBackgroundResource(R.color.blue_grey_800);
				break;
				
			case 2:
				Amount_Bar3.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar3.setBackgroundResource(R.color.blue_grey_800);
				break;
				
			case 3:
				Amount_Bar4.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar4.setBackgroundResource(R.color.blue_grey_800);
				break;
				
			case 4:
				Amount_Bar5.setBackgroundResource(R.color.blue_grey_800);
				Count_Bar5.setBackgroundResource(R.color.blue_grey_800);
				break;
		}
		
	}
	
	public float getRawSize(int unit, float value) { 
        Resources res = this.getResources(); 
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics()); 
    } 
}

