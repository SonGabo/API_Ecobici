package xyz.gabrielrohez.apiecobici.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static final String SETTINGS_NAME = "default_settings";
    private static MySharedPreferences sSharedPrefs;
    private SharedPreferences mPref;

    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    private MySharedPreferences(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static MySharedPreferences getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new MySharedPreferences(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static MySharedPreferences getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }

        //Option 1:
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    public void setAccessToken(String accessToken) {
        doEdit();
        mEditor.putString("accessToken", accessToken);
        doCommit();
    }

    public String getAccessToken() {
        return mPref.getString("accessToken", "");
    }

    public void setRefreshToken(String refreshToken) {
        doEdit();
        mEditor.putString("refreshToken", refreshToken);
        doCommit();
    }

    public String getReshToken() {
        return mPref.getString("refreshToken", "");
    }

    public void setLastTime(String lastTime) {
        doEdit();
        mEditor.putString("lastTime", lastTime);
        doCommit();
    }

    public String getLastTime() {
        return mPref.getString("lastTime", "");
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
