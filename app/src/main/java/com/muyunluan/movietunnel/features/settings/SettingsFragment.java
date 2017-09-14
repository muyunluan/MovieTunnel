package com.muyunluan.movietunnel.features.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.muyunluan.movietunnel.R;

/**
 * Created by Fei Deng on 8/30/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    private static SettingsFragment mFragment;

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance() {
        if (null == mFragment) {
            mFragment = new SettingsFragment();
        }
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // In Fragment, using getPreferenceScreen()
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences xml file
        addPreferencesFromResource(R.xml.pre_sort);

        // Reading Preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String sortString = sharedPreferences.getString(getString(R.string.pref_sort_key), "");
        Log.i(TAG, "onCreatePreferences: selected sort way - " + sortString);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // For ListPreference or other multiple choice setting, use setSummary() when
        // the setting changes to display the current status
        if (key.equals(getString(R.string.pref_sort_key))) {
            Preference preference = findPreference(key);
            String value = sharedPreferences.getString(preference.getKey(), "");
            Log.i(TAG, "onSharedPreferenceChanged: changed value - " + value);
            // For ListPreference, figure out the label of selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            Log.e(TAG, "onSharedPreferenceChanged: no ListPreference found");
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return true;
    }
}
