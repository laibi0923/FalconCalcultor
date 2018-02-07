package com.savtor.Credit_Profile;
import android.widget.*;
import android.content.*;
import android.util.*;

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
