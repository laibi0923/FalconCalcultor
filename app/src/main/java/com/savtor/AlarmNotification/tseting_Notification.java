package com.savtor.AlarmNotification;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.savtor.falconcalcultor.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by GhostLeo_DT on 21/12/2017.
 */
public class tseting_Notification extends Fragment{

    private Button show_notification_btn, show_custom_notification_btn;
    private EditText notification_title, notification_content;
    private EditText alram_year, alram_months, alram_date, alram_hour, alram_mins;
    private Calendar mCalendar;
    private int year, months, date, hour, mins;

    private AlarmManager mAlarmManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.testing_notification_fragment, container, false);


        mCalendar = Calendar.getInstance();

        year = mCalendar.get(Calendar.YEAR);
        months = mCalendar.get(Calendar.MONTH) + 1 ;
        date = mCalendar.get(Calendar.DAY_OF_MONTH);
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mins = mCalendar.get(Calendar.MINUTE);


        alram_year = (EditText) v.findViewById(R.id.alram_year); alram_year.setText(String.valueOf(year));
        alram_months = (EditText) v.findViewById(R.id.alram_months); alram_months.setText(String.valueOf(months));
        alram_date = (EditText) v.findViewById(R.id.alram_date); alram_date.setText(String.valueOf(date));
        alram_hour = (EditText) v.findViewById(R.id.alram_hour); alram_hour.setText(String.valueOf(hour));
        alram_mins = (EditText) v.findViewById(R.id.alram_mins); alram_mins.setText(String.valueOf(mins));


        notification_title = (EditText) v.findViewById(R.id.notification_title);

        notification_content = (EditText) v.findViewById(R.id.notification_content);

        show_notification_btn = (Button) v.findViewById(R.id.show_notification_btn);
        show_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myNotification m1 = new myNotification();
                m1.myNotification(getActivity(), notification_title.getText().toString(), notification_content.getText().toString());
            }
        });


        show_custom_notification_btn = (Button) v.findViewById(R.id.show_custom_notification_btn);
        show_custom_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Custom_Notification mN = new Custom_Notification();
//                mN.Custom_Notification(getActivity(), notification_title.getText().toString(), notification_content.getText().toString());


				for (int i =0; i < 2; i++){
					
					mCalendar.set(Calendar.YEAR, Integer.parseInt(alram_year.getText().toString()));
					mCalendar.set(Calendar.MONTH, Integer.parseInt(alram_months.getText().toString()) -1 );
					mCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(alram_date.getText().toString()));
					mCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(alram_hour.getText().toString()));
					mCalendar.set(Calendar.MINUTE, Integer.parseInt(alram_mins.getText().toString()) + i);
					mCalendar.set(Calendar.SECOND, 0);

					Intent mIntent = new Intent(getContext(), AlarmBroadCastReceiver.class);
					PendingIntent mPendingIntent = PendingIntent.getBroadcast(getContext(), i, mIntent, 0);

					mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
					mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);
					
					
				}
				

                Toast.makeText(getContext(), "己設置, 時間為 : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }



}
