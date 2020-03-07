package com.atixiansoftwares.librarybooks.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {
    private static PreferenceHelper mInstance;
    private Context mContext;

    private SharedPreferences mMyPreference;

    private PreferenceHelper() {
    }

    public static PreferenceHelper getInstance() {
        if (mInstance == null) mInstance = new PreferenceHelper();
        return mInstance;
    }

    public void Initialize(Context ctxt) {
        mContext = ctxt;
        mMyPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void writeBoolean(String key, Boolean value) {
        SharedPreferences.Editor e = mMyPreference.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    public void clearSP() {
        SharedPreferences.Editor e = mMyPreference.edit();
        e.clear();
        e.commit();
    }
    public Boolean readBoolean(String key) {

        return  mMyPreference.getBoolean(key, false);
    }

    public void writeString(String key, String value) {
        SharedPreferences.Editor e = mMyPreference.edit();
        e.putString(key, value);
        e.apply();
    }

    public String readString(String key) {

        return mMyPreference.getString(key, "DNF");
    }

    public void writeInt(String key, int value) {
        SharedPreferences.Editor e = mMyPreference.edit();
        e.putInt(key, value);
        e.apply();
    }

    public int readInt(String key) {

        return mMyPreference.getInt(key, 1001);
    }



}