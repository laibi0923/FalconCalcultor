package com.savtor.Alarm_Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.savtor.falconcalcultor.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Falcon_AlramManager
{
	private AlarmManager mAlarmManager;
	
	public void Setup_Alram(Context context, int request_code, Calendar Date_Calendar, Calendar Time_Calendar, String EOM, int Trems, int AlertDate, String Name, double Loan_Amount){
		
		String Title = context.getString(R.string.app_name);
		
		for(int i = 0; i < Trems; i++){
			
			Calendar xCalendar = Calendar.getInstance();
			xCalendar.set(Calendar.YEAR, Date_Calendar.get(Calendar.YEAR));
			xCalendar.set(Calendar.MONTH, Date_Calendar.get(Calendar.MONTH));
			if(EOM == "true"){
				xCalendar.set(Calendar.DAY_OF_MONTH, Date_Calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			}else if(EOM == "false"){
				xCalendar.set(Calendar.DAY_OF_MONTH, Date_Calendar.get(Calendar.DAY_OF_MONTH));
			}
			xCalendar.set(Calendar.HOUR_OF_DAY, Time_Calendar.get(Calendar.HOUR_OF_DAY));
			xCalendar.set(Calendar.MINUTE, Time_Calendar.get(Calendar.MINUTE));
			xCalendar.set(Calendar.SECOND, 0);
			
			xCalendar.add(Calendar.MONTH, i);
			xCalendar.add(Calendar.DAY_OF_MONTH, -AlertDate);
			
			String Content = "繳款提醒 : " + Name  + " 於 " + new SimpleDateFormat("yyyy/MM/dd").format(xCalendar.getTime()) + ", " + AlertDate + "天後需繳付金額為 $" + Loan_Amount;

			Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
			mIntent.putExtra("notification_id", request_code);
			mIntent.putExtra("notification_title", Title);
			mIntent.putExtra("notification_content", Content);

			PendingIntent mPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), request_code + i, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

			/*
			 *  RTC_WAKEUP：在指定時間觸發意圖並喚醒裝置
			 *  RTC：同上但不喚醒裝置
			 *  ELAPSED_REALTIME：在裝置啟動(開機)後開始計算經過的時間，在到達指定的經過時間觸發意圖，但不喚醒裝置。
			 *  ELAPSED_REALTIME_WAKEUP：同上，但會喚醒裝置。
			 */
			mAlarmManager.set(AlarmManager.RTC_WAKEUP, xCalendar.getTimeInMillis(), mPendingIntent);
			Log.e("SETUP ALARM", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(xCalendar.getTime())  + ", Request Code = " + request_code + i);
			
		}
	}
	
	public void Cancel_Alram(Context context, int request_code, int Trems){
		
		for(int i = 0; i < Trems; i++){
			
			Intent mIntent = new Intent(context, AlarmBroadCastReceiver.class);
			PendingIntent mPendingIntent = PendingIntent.getBroadcast(context, request_code + i, mIntent, 0);

			mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			mAlarmManager.cancel(mPendingIntent);
			Log.e("CANCEL ALARM", "Request Code = " + request_code + i);
		}
	
	}

	
}
