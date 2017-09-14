package com.muyunluan.movietunnel.features.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Fei Deng on 9/11/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

// RemoteViewsService is a service that allows a remote adapter to request RemoteViews objects.
public class MovieWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieRemoteViewsFactory(this.getApplicationContext());
    }
}
