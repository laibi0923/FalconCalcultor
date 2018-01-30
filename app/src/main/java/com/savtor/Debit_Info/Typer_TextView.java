package com.savtor.Debit_Info;
import android.content.*;
import android.util.*;
import android.widget.*;
import java.util.*;

public class Typer_TextView extends TextView
{
	private static final String TAG = "PrinterTextView";
	
	private final String DEFAULT_INTERVAL_CHAR = "";
	
	private final int DEFAULT_TIME_DELAY = 120;
	
	private Timer mTimer;
	
	private String mPrintStr;
	
	private int intervalTime = DEFAULT_TIME_DELAY;
	
	private String intervalChar = DEFAULT_INTERVAL_CHAR;
	
	private int printProgress = 0;
	
	public Typer_TextView(Context context){
		super(context);
	}
	
	public Typer_TextView(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	public Typer_TextView(Context context, AttributeSet attrs, int defStyleAttr){
		super(context, attrs, defStyleAttr);
	}
	
	public void setPrintText(String str){
		setPrintText(str, DEFAULT_TIME_DELAY);
	}
	
	public void setPrintText(String str, int time){
		setPrintText(str, time, DEFAULT_INTERVAL_CHAR);
	}
	
	public void setPrintText(String str, int time, String intervalChar){
		
		if(strIsEmpty(str) || 0 == time || strIsEmpty(intervalChar)){
			return;
		}
		
		this.mPrintStr = str;
		this.intervalTime = time;
		this.intervalChar = intervalChar;
	}
	
	public void startPrint(){
		if(strIsEmpty(mPrintStr)){
			if(!strIsEmpty(getText().toString())){
				this.mPrintStr = getText().toString();
			}else{
				return;
			}
		}
		
		
		setText("");
		stopPrint();
		mTimer = new Timer();
		mTimer.schedule(new PrinterTimeTask(), intervalTime, intervalTime);
	}
	
	public void stopPrint(){
		if(null != mTimer){
			mTimer.cancel();
			mTimer = null;
		}
	}
	
	private boolean strIsEmpty(String str){
		if(null != str && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public class PrinterTimeTask extends TimerTask
	{

		@Override
		public void run()
		{
			// TODO: Implement this method
			post(new Runnable(){

					@Override
					public void run()
					{
						// TODO: Implement this method
						if(printProgress < mPrintStr.length()){
							printProgress++;
							setText(mPrintStr.substring(0, printProgress) + ((printProgress & 1) == 1 ? intervalChar : ""));
						}else{
							setText(mPrintStr);
							stopPrint();
						}
					}
			});
			
		}
		
	}
	
	
}
