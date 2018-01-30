package com.savtor.Debit_Info;
import android.support.v4.view.*;
import android.view.*;
import java.util.*;

public class DebitInfo_Adapter extends PagerAdapter
{
	public List<View> page_list;
	
	public DebitInfo_Adapter(List<View> page_list){
		this.page_list = page_list;
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return page_list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		// TODO: Implement this method
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		// TODO: Implement this method
		container.removeView(page_list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		// TODO: Implement this method
		container.addView(page_list.get(position));
		return page_list.get(position);
	}

	
	
	
}
