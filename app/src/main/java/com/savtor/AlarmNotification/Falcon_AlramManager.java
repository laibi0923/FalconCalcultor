package com.savtor.AlarmNotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.savtor.falconcalcultor.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Falcon_AlramManager
{
	private AlarmManager mAlarmManager;
	
	public void Setup_Alram(Context context, int request_code, Calendar mCalendar, int Trems, int AlertDate, String Name, double Loan_Amount){
		
		String Title = context.getString(R.string.app_name);
		
		
			
			
			String Content = "繳款提醒 : " + Name  + " 於 " + new SimpleDateFormat("yyyy/MM/dd").format(mCalendar.getTime()) + ", " + AlertDate + "天後需繳付金額為 $" + Loan_Amount;
			
			//mCalendar.add(Calendar.DAY_OF_MONTH, - AlertDate);
			
			Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
			mIntent.putExtra("notification_id", request_code);
			mIntent.putExtra("notification_title", Title);
			mIntent.putExtra("notification_content", Content);
			
			PendingIntent mPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), request_code, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

			/*
			 *  RTC_WAKEUP：在指定時間觸發意圖並喚醒裝置
			 *  RTC：同上但不喚醒裝置
			 *  ELAPSED_REALTIME：在裝置啟動(開機)後開始計算經過的時間，在到達指定的經過時間觸發意圖，但不喚醒裝置。
			 *  ELAPSED_REALTIME_WAKEUP：同上，但會喚醒裝置。
			 */
			mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

//			Toast.makeText(context, "己設置, 時間為 : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime())  + "id = " + request_code + i, Toast.LENGTH_SHORT).show();
			Log.e("己設置時間為", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime())  + ", id = " + request_code);
		
		
	}
	
	public void Cancel_Alram(Context context, int request_code, int Trems){
		
		for(int i = 0; i < Trems; i++){
			
			Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
			PendingIntent mPendingIntent = PendingIntent.getBroadcast(context, request_code + i, mIntent, 0);

			mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			mAlarmManager.cancel(mPendingIntent);
			
		}
	
	}

	
}
