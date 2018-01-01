package com.savtor.AlarmNotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.savtor.falconcalcultor.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Falcon_AlramManager
{
	private AlarmManager mAlarmManager;
	
	public void Setup_Alram(Context context, int request_code, int Year, int Month, int Date, int Hour, int Minute, int Trems, int AlertDate, String Name, String Loan_Amount){
		
		String Title = context.getString(R.string.app_name);
		String Content = Name + Loan_Amount ;
		
		
		for(int i = 0; i < Trems; i++){
			
			Calendar mCalendar = Calendar.getInstance();
			mCalendar.set(Calendar.YEAR, Year);
			mCalendar.set(Calendar.MONTH, Month + i - 1);
			mCalendar.set(Calendar.DAY_OF_MONTH, Date - AlertDate);
			mCalendar.set(Calendar.HOUR_OF_DAY, Hour);
			mCalendar.set(Calendar.MINUTE, Minute);
			mCalendar.set(Calendar.SECOND, 0);
			
			Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
			mIntent.putExtra("notification_id", request_code);
			mIntent.putExtra("notification_title", Title);
			mIntent.putExtra("notification_content", Content);
			
			PendingIntent mPendingIntent = PendingIntent.getBroadcast(context, request_code, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

			/*
			 *  RTC_WAKEUP：在指定時間觸發意圖並喚醒裝置
			 *  RTC：同上但不喚醒裝置
			 *  ELAPSED_REALTIME：在裝置啟動(開機)後開始計算經過的時間，在到達指定的經過時間觸發意圖，但不喚醒裝置。
			 *  ELAPSED_REALTIME_WAKEUP：同上，但會喚醒裝置。
			 */
			mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

			Toast.makeText(context, "己設置, 時間為 : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime())  + "id = " + request_code + i, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void Cancel_Alram(Context context, int request_code){
		

		Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
		PendingIntent mPendingIntent = PendingIntent.getBroadcast(context, request_code, mIntent, 0);

		mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		mAlarmManager.cancel(mPendingIntent);
	
	}

	
}
