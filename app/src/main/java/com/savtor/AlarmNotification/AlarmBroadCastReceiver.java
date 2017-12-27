package com.savtor.AlarmNotification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.savtor.falconcalcultor.*;

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

        myNotification xmyNotification = new myNotification();

        xmyNotification.myNotification(context, notification_string_id, notification_id, notification_tick,notification_title, notification_content, notification_subtext);

    }

}
