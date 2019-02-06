package xyz.gabrielrohez.apiecobici.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public final static String PREFS_NAME = "api_ecobici_prefs";

    public static void setAccessToken(String accessToken){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ACCESS_TOKEN", accessToken);
        editor.apply();
    }

    public static String getAccessToken() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString("ACCESS_TOKEN", "");
    }

    public static void setRefreshToken(String refreshToken){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("REFRESH_TOKEN", refreshToken);
        editor.apply();
    }

    public static String getReshToken(){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString("REFRESH_TOKEN", "");
    }
}
