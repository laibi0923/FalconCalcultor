package com.savtor.Credit_Profile;
import android.widget.*;
import android.content.*;
import android.util.*;

/**
 *	 Created by GhostLeo_DT on 7/2/2018.
 *	 模擬 Hints 效果, 當 Text 為空時將返回指定文字
 */

public class CreditProfile_TextView extends TextView
{
	public CreditProfile_TextView(Context context){
		super(context);
	}
	
	public CreditProfile_TextView(Context context, AttributeSet attr){
		super(context, attr);
	}
	
	public CreditProfile_TextView(Context context, AttributeSet attr, int defStyleAttr){
		super(context, attr, defStyleAttr);
	}
	
	public void setText_Hints(String Value, String Hints){
		if(Value != null && !Value.equals("")){
			setText(Value);
		}else{
			setText(Hints);
		}
	}
}
