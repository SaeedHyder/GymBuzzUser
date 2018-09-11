package com.app.gymbuzz.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.app.gymbuzz.R;
import com.app.gymbuzz.activities.MainActivity;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.helpers.BasePreferenceHelper;
import com.app.gymbuzz.helpers.NotificationHelper;
import com.app.gymbuzz.retrofit.WebService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private WebService webservice;
    private BasePreferenceHelper preferenceHelper;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        preferenceHelper = new BasePreferenceHelper(getApplicationContext());
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            buildNotification(remoteMessage);


        }
    }

    private void buildNotification(RemoteMessage messageBody) {
        //getNotificaitonCount();
        String title = getString(R.string.app_name);
        String message = messageBody.getData().get("message");
        Log.e(TAG, "message: " + message);
        Intent resultIntent = new Intent(MyFirebaseMessagingService.this, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent.putExtra("message", message);
        resultIntent.putExtra("tapped", true);

        Intent pushNotification = new Intent(AppConstants.PUSH_NOTIFICATION);
        pushNotification.putExtra("message", message);

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(pushNotification);
        showNotificationMessage(MyFirebaseMessagingService.this, title, message, "", resultIntent);
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        NotificationHelper.getInstance().showNotification(context,
                R.mipmap.ic_launcher,
                title,
                message,
                timeStamp,
                intent);
    }


}
