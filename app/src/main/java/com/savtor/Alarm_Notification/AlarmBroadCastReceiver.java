package com.savtor.Alarm_Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by GhostLeo_DT on 22/12/2017.
 */
public class AlarmBroadCastReceiver extends BroadcastReceiver {

    int notification_id;
    String notification_string_id;
    String notification_tick;
    String notification_title;
    String notification_content;
    String notification_subtext;
 
    @Override
    public void onReceive(Context context, Intent intent) {

        notification_id = intent.getIntExtra("notification_id", 0);
        notification_string_id = intent.getStringExtra("notification_string_id");
        notification_tick = intent.getStringExtra("notification_tick");
        notification_title = intent.getStringExtra("notification_title");
        notification_content = intent.getStringExtra("notification_content");
        notification_subtext = intent.getStringExtra("notification_subtext");

        Falcon_Notification mFalcon_Notification = new Falcon_Notification();

        mFalcon_Notification.Falcon_Notification(context, notification_string_id, notification_id, notification_tick,notification_title, notification_content, notification_subtext);

    }

}
