package com.savtor.Debit_Info;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import com.savtor.falconcalcultor.*;
import java.util.*;
import java.util.zip.*;
import android.support.v4.view.*;
import android.widget.*;

public class DebitInfo_Main extends Fragment
{

	private View Page_Personal, Page_Mortgage, Page_Revolving, Page_Car, Page_Card;
	private TextView Personal_Title, Mortgage_Title, Revolving_Title, Car_Title, Card_Title;
	private ImageView Personal_Icon, Mortgage_Icon, Revolving_Icon, Car_Icon, Card_Icon;
	private List<View> Page_List;
	private ViewPager mViewPager;
	private DebitInfo_Adapter mAdapter;
	
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

		
		Page_Mortgage = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Mortgage_Title = (TextView) Page_Mortgage.findViewById(R.id.product_type);
		Mortgage_Title.setText(getString(R.string.title_product_mortgage));
		Mortgage_Icon = (ImageView) Page_Mortgage.findViewById(R.id.product_type_icon);
		Mortgage_Icon.setImageResource(R.drawable.ic_location_city_black_48dp);


		Page_Revolving = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Revolving_Title = (TextView) Page_Revolving.findViewById(R.id.product_type);
		Revolving_Title.setText(getString(R.string.title_product_revolving));
		Revolving_Icon = (ImageView) Page_Revolving.findViewById(R.id.product_type_icon);
		Revolving_Icon.setImageResource(R.drawable.ic_loop_black_48dp);


		Page_Car = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Car_Title = (TextView) Page_Car.findViewById(R.id.product_type);
		Car_Title.setText(getString(R.string.title_product_car));
		Car_Icon = (ImageView) Page_Car.findViewById(R.id.product_type_icon);
		Car_Icon.setImageResource(R.drawable.ic_airport_shuttle_black_48dp);


		Page_Card = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Card_Title = (TextView) Page_Card.findViewById(R.id.product_type);
		Card_Title.setText(getString(R.string.title_product_card));
		Card_Icon = (ImageView) Page_Card.findViewById(R.id.product_type_icon);
		Card_Icon.setImageResource(R.drawable.ic_credit_card_black_48dp);


		Page_List = new ArrayList<View>();
		Page_List.add(Page_Personal);
		Page_List.add(Page_Mortgage);
		Page_List.add(Page_Revolving);
		Page_List.add(Page_Car);
		Page_List.add(Page_Card);

		
		
		mViewPager = (ViewPager) v.findViewById(R.id.debit_viewpager);
		mAdapter = new DebitInfo_Adapter(Page_List);
		mViewPager.setAdapter(mAdapter);
	}
	
}

