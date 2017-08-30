package com.muyunluan.movietunnel.ui.ui.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.muyunluan.movietunnel.R;

/**
 * Created by Fei Deng on 8/30/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = this.getSupportActionBar();
        // Set the action bar back button to look like an up button
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }


}
