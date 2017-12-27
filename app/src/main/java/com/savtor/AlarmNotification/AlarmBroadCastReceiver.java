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

    @Override
    public void onReceive(Context context, Intent intent) {

        myNotification xmyNotification = new myNotification();

        xmyNotification.myNotification(context);

    }

}
