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

	private View Page_Personal, Page_Mortgage;
	private Typer_TextView Personal_TextView, Mort_TextView;
	private ImageView Personal_Icon;
	private List<View> Page_List;
	private ViewPager mViewPager;
	private DebitInfo_Adapter mAdapter;
	private String Total_Count_Text = "已批出貸款共 ";
	private String Total_Amount = "總貸款銀碼$ ";
	private String Toal_Insterest = "預計總利息支出$ ";
	private String Month_Pay = "每月共支出$ ";
	private String Month_Insterest = "每月利息支出$ ";
	
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
		Personal_Icon = (ImageView) Page_Personal.findViewById(R.id.product_type_icon);
		Personal_Icon.setImageResource(R.drawable.ic_person_black_48dp);
		Personal_TextView = (Typer_TextView) Page_Personal.findViewById(R.id.typer_textview);
		Personal_TextView.setPrintText(Total_Count_Text + "個\n" +
		Total_Amount + "18,000\n" +
		Toal_Insterest + "5,000\n" +
		Month_Pay + "60,000\n" +
		Month_Insterest + "30,000"
		, 50, "_");
		Personal_TextView.startPrint();
		
		Page_Mortgage = inflater.inflate(R.layout.zz_tseting_debit_viewpapger, null);
		Mort_TextView = (Typer_TextView) Page_Mortgage.findViewById(R.id.typer_textview);
		Mort_TextView.setPrintText("12445678890065433678", 100, "|");
		Mort_TextView.startPrint();
		
		Page_List = new ArrayList<View>();
		Page_List.add(Page_Personal);
		Page_List.add(Page_Mortgage);
		
		
		mViewPager = (ViewPager) v.findViewById(R.id.mviewPager);
		mAdapter = new DebitInfo_Adapter(Page_List);
		mViewPager.setAdapter(mAdapter);
	}
	
}

