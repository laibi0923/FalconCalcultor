package com.savtor.AlarmNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.savtor.falconcalcultor.*;
/**
 * Created by GhostLeo_DT on 21/12/2017.
 * 網路資源 :  https://xnfood.com.tw/android-notificationmanager/
 */
public class myNotification {

    int small_icon = R.drawable.falcon_icon_white;
    long when = System.currentTimeMillis();

    public void myNotification(Context context, String string_id, int notification_id, String Ticker, String Title, String ContenText, String SubText){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setSmallIcon(small_icon);

//                Drawable mDrawable = ContextCompat.getDrawable(v.getContext(), R.drawable.falcon_icon_black);
//                Bitmap large_icon = ((BitmapDrawable) mDrawable).getBitmap();
//
//                mBuilder.setLargeIcon(large_icon);

        mBuilder.setTicker(Ticker);

        mBuilder.setContentTitle(Title);

        mBuilder.setContentText(ContenText);

//        mBuilder.setSubText(SubText);

//        mBuilder.setContentInfo("Info");

//        mBuilder.setNumber(2);

        mBuilder.setAutoCancel(true);

        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        mBuilder.setWhen(when);

        mBuilder.setOngoing(false);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);

        Intent mIntent = new Intent(context, Splash_Activity.class);

        PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);

        mBuilder.setContentIntent(mPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(string_id, notification_id++, mBuilder.build());

    }


}
