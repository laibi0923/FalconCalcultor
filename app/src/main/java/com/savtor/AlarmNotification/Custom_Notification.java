package com.savtor.AlarmNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 21/12/2017.
 */
public class Custom_Notification {

    int icon = R.drawable.falcon_icon_white;
    long when = System.currentTimeMillis();
    int i = 1;

    public void Custom_Notification(Context context, String Title, String Content){


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_view);
        remoteViews.setTextViewText(R.id.notification_title, Title);
        remoteViews.setTextViewText(R.id.notification_content, Content);
        remoteViews.setImageViewResource(R.id.notification_img, icon);


        Intent mIntent = new Intent(context, Splash_Activity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder mBuilder = new Notification.Builder(context.getApplicationContext());

        //設定自定界面
        //if (Build.VERSION.SDK_INT >= 16){
           // mBuilder.setCustomBigContentView(remoteViews);
       // }
        //mBuilder.setCustomContentView(remoteViews);

		mBuilder.setContent(remoteViews);
		
        //設定用戶點撃 Notification 後打開 Apps
        mBuilder.setContentIntent(mPendingIntent);
		
        //設定小圖示
        mBuilder.setSmallIcon(icon);
		
		mBuilder.setNumber(3);

        //設定是否常駐,true為常駐
        mBuilder.setOngoing(false);

        //設定提示
        mBuilder.setTicker("Trick Testing");

        //設定優先順序
        mBuilder.setPriority(Notification.PRIORITY_HIGH);

        //設定展示時間
        mBuilder.setWhen(when);

        //設置點擊通知跳轉頁面後，通知消失
        mBuilder.setAutoCancel(true);

        //使用所有默認值，比如聲音，震動，閃屏等等
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
		
		//mBuilder.setFullScreenIntent(mPendingIntent, true);

        Notification mNotification = mBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(i++, mNotification);


    }



}
