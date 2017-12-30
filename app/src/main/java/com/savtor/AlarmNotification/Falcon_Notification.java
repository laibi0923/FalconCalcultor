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
public class Falcon_Notification {

    int small_icon = R.drawable.falcon_icon_white;
    long when = System.currentTimeMillis();

    public void Falcon_Notification(Context context, String string_id, int notification_id, String Ticker, String Title, String ContenText, String SubText){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

		// 小圖示
        mBuilder.setSmallIcon(small_icon);

//                Drawable mDrawable = ContextCompat.getDrawable(v.getContext(), R.drawable.falcon_icon_black);
//                Bitmap large_icon = ((BitmapDrawable) mDrawable).getBitmap();
//
//                mBuilder.setLargeIcon(large_icon);

		// Status bar 上顯示文字
        mBuilder.setTicker(Ticker);

		// 標題
        mBuilder.setContentTitle(Title);

		// 內容
        mBuilder.setContentText(ContenText);
		
		// 顯示樣式 (多行文字)
		NotificationCompat.BigTextStyle mBigTextStyle = new NotificationCompat.BigTextStyle();
		
		mBigTextStyle.setBigContentTitle(Title);
		mBigTextStyle.bigText(ContenText);
		
		mBuilder.setStyle(mBigTextStyle);

//        mBuilder.setSubText(SubText);

//        mBuilder.setContentInfo("Info");

//        mBuilder.setNumber(2);

		// 點擊後自動消失
        mBuilder.setAutoCancel(true);

		// 優先等級
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

		// 通知時間
        mBuilder.setWhen(when);

		// 常駐設定
        mBuilder.setOngoing(false);

		// 設置燈光， 震動方式 為預設模式
        mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);

		// 跳轉動作
        Intent mIntent = new Intent(context, Splash_Activity.class);

		// 準備跳轉動作
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);

        mBuilder.setContentIntent(mPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(notification_id++, mBuilder.build());

    }


}
