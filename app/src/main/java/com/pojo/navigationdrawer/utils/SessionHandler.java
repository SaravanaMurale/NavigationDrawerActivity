package com.pojo.navigationdrawer.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionHandler {

    public static final String SHARED_PREF_NAME = "pojo";
    public static final String NO_VALUE = "null";

    public static void setValueString(Context context, String key, String value) {

        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static String getValueString(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return preferences.getString(key, NO_VALUE);

    }


    public static void setValueSInt(Context context, String key, int value) {

        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();

    }

    public static int getValueInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return preferences.getInt(key, 0);

    }

    public static void remove(Context contextRemoveRewardID, String key) {

        SharedPreferences removeRewardID = contextRemoveRewardID.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = removeRewardID.edit();
        editor.remove(key);
        editor.commit();


    }

    public static void clear(Context contextRemoveRewardID) {

        SharedPreferences removeRewardID = contextRemoveRewardID.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = removeRewardID.edit();
        editor.clear();
        editor.commit();


    }

}
