package com.app.gymbuzz.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.gymbuzz.entities.UserModel;
import com.app.gymbuzz.retrofit.GsonFactory;


public class BasePreferenceHelper extends PreferenceHelper {

    private Context context;

    protected static final String KEY_LOGIN_STATUS = "islogin";

    private static final String FILENAME = "preferences";

    protected static final String Firebase_TOKEN = "Firebasetoken";

    protected static final String NotificationCount = "NotificationCount";

    protected static final String KEY_IS_UNIT_KG = "isUnitKg";

    protected static final String KEY_USER = "key_user";
    private static final String KEY_USERID = "USERID";
    public BasePreferenceHelper(Context c) {
        this.context = c;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
    }

    public void setLoginStatus( boolean isLogin ) {
        putBooleanPreference( context, FILENAME, KEY_LOGIN_STATUS, isLogin );
    }

    public boolean isLogin() {
        return getBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS);
    }


    public String getFirebase_TOKEN() {
        return getStringPreference(context, FILENAME, Firebase_TOKEN);
    }

    public void setFirebase_TOKEN(String _token) {
        putStringPreference(context, FILENAME, Firebase_TOKEN, _token);
    }
    public int getNotificationCount() {
        return getIntegerPreference(context, FILENAME, NotificationCount);
    }

    public void setNotificationCount(int count) {
        putIntegerPreference(context, FILENAME, NotificationCount, count);
    }


    public void setUnitKg( boolean isUnitKg ) {
        putBooleanPreference( context, FILENAME, KEY_IS_UNIT_KG, isUnitKg );
    }
    public String getUserToken() {
        return getStringPreference(context, FILENAME, KEY_USERID);
    }

    public void setUserToken(String userID) {
        putStringPreference(context, FILENAME, KEY_USERID, userID);
    }

    public UserModel getUser() {
        return GsonFactory.getConfiguredGson().fromJson(
                getStringPreference(context, FILENAME, KEY_USER), UserModel.class);
    }

    public void putUser(UserModel user) {
        putStringPreference(context, FILENAME, KEY_USER, GsonFactory.getConfiguredGson().toJson(user));
    }
    public boolean isUnitKg() {
        return getBooleanPreference(context, FILENAME, KEY_IS_UNIT_KG);
    }

}
