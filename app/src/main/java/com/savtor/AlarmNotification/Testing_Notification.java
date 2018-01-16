package com.savtor.AlarmNotification;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
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
public class Testing_Notification extends Fragment{

    private Button show_notification_btn, show_custom_notification_btn;
    private EditText notification_title, notification_content;
    private EditText alram_year, alram_months, alram_date, alram_hour, alram_mins;
    private EditText id_btn, repeat_min_btn;
    private Calendar mCalendar;
    private int year, months, date, hour, mins;




    int notification_id = 1;
    int small_icon = R.drawable.falcon_icon_white;
    long when = System.currentTimeMillis();

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

        repeat_min_btn = (EditText) v.findViewById(R.id.repeat_min_btn);
        id_btn = (EditText) v.findViewById(R.id.id_btn);


        notification_title = (EditText) v.findViewById(R.id.notification_title);

        notification_content = (EditText) v.findViewById(R.id.notification_content);

        show_notification_btn = (Button) v.findViewById(R.id.show_notification_btn);
        show_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                myNotification m1 = new myNotification();
//                m1.myNotification(getActivity(), notification_title.getText().toString(), notification_content.getText().toString());

                int id = Integer.parseInt(id_btn.getText().toString());
                int delay_min = Integer.parseInt(repeat_min_btn.getText().toString());

                for (int i = 0; i < delay_min; i++){

                    Intent mIntent = new Intent(getContext(), AlarmBroadCastReceiver.class);
                    PendingIntent mPendingIntent = PendingIntent.getBroadcast(getContext(), id + i, mIntent, 0);

                    mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                    mAlarmManager.cancel(mPendingIntent);

                    Toast.makeText(getContext(), "己取消, 時間為 : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime())  + "id = " + id + i, Toast.LENGTH_SHORT).show();

                }

            }
        });


        show_custom_notification_btn = (Button) v.findViewById(R.id.show_custom_notification_btn);
        show_custom_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(id_btn.getText().toString());
                int delay_min = Integer.parseInt(repeat_min_btn.getText().toString());

				for (int i = 0; i < delay_min; i++){

					mCalendar.set(Calendar.YEAR, Integer.parseInt(alram_year.getText().toString()));
					mCalendar.set(Calendar.MONTH, Integer.parseInt(alram_months.getText().toString()) -1 );
					mCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(alram_date.getText().toString()));
					mCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(alram_hour.getText().toString()));
					mCalendar.set(Calendar.MINUTE, Integer.parseInt(alram_mins.getText().toString()) + i);
					mCalendar.set(Calendar.SECOND, 0);

					Intent mIntent = new Intent(getContext(), AlarmBroadCastReceiver.class);
                    mIntent.putExtra("notification_id", i);
                    mIntent.putExtra("notification_string_id", notification_title.getText().toString());
                    mIntent.putExtra("notification_title", notification_title.getText().toString());
                    mIntent.putExtra("notification_content", notification_content.getText().toString());
					// mIntent.putExtra("notification_tick", "tick");
                    // mIntent.putExtra("notification_subtext", "subtext");

					PendingIntent mPendingIntent = PendingIntent.getBroadcast(getContext(), i, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

					mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

                    /*
                     *  RTC_WAKEUP：在指定時間觸發意圖並喚醒裝置
                     *  RTC：同上但不喚醒裝置
                     *  ELAPSED_REALTIME：在裝置啟動(開機)後開始計算經過的時間，在到達指定的經過時間觸發意圖，但不喚醒裝置。
                     *  ELAPSED_REALTIME_WAKEUP：同上，但會喚醒裝置。
                     */
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

                    Toast.makeText(getContext(), "己設置, 時間為 : " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(mCalendar.getTime())  + "id = " + id + i, Toast.LENGTH_SHORT).show();

				}


            }
        });

        return v;
    }



}
