package com.app.gymbuzz.helpers;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.app.gymbuzz.R;
import com.app.gymbuzz.global.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created on 5/8/2017.
 */

public class NotificationHelper {
    static final String CHANNEL = "112112";
    private static String TAG = NotificationHelper.class.getSimpleName();
    private Context mContext;

    public static NotificationHelper getInstance() {
        return new NotificationHelper();
    }

    public void showNotification(Context mContext, int icon, String title, String message, String timeStamp,
                                 Intent intent) {
        // Check for empty push message
       /* if (TextUtils.isEmpty(message))
            return;*/
        message = message + "";
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        mContext,
                        AppConstants.INTENT_ID,
                        intent,
                        PendingIntent.FLAG_NO_CREATE
                );
        AppConstants.INTENT_ID++;

        //notification sound here
        final Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(message);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL, mContext.getResources().getString(R.string.app_name), importance);
            mChannel.setDescription(message);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null)
                manager.createNotificationChannel(mChannel);


            Notification notification = new NotificationCompat.Builder(mContext, CHANNEL)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setTicker(message)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .setChannelId(CHANNEL)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(resultPendingIntent)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
            notificationManager.cancel(AppConstants.NOTIFICATION_ID);
            notificationManager.notify(AppConstants.NOTIFICATION_ID, notification);

        } else {
            Notification notification;
            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, CHANNEL);
            notification = mBuilder
                    .setSmallIcon(icon)
                    .setTicker(message).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentIntent(resultPendingIntent)
                    .setSound(alarmSound)
                    .setStyle(bigTextStyle)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setWhen(getTimeMilliSec(timeStamp))
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                    .setContentText(message)
                    .setOnlyAlertOnce(true).setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build();
            playNotificationSound(mContext);
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(AppConstants.NOTIFICATION_ID);
            notificationManager.notify(AppConstants.NOTIFICATION_ID, notification);
        }
        //AppConstants.NOTIFICATION_ID++;

    }

    public void playNotificationSound(Context mContext) {
        try {
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
            r.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getTimeMilliSec(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(timeStamp);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }
}
