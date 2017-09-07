package com.muyunluan.movietunnel.utls.sqlite;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.muyunluan.movietunnel.utls.data.Constants.CONTENT_AUTHORITY;
import static com.muyunluan.movietunnel.utls.data.Constants.CONTENT_PATH_FAVORITES;

/**
 * Created by Fei Deng on 9/5/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

// A contract class is a container for constants that define names for URIs, tables, and columns.
public class MovieFavoriteContract {

    public static final String AUTHORITY = "com.muyunluan.movietunnel";


    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MovieFavoriteContract() {

    }

    // By implementing the BaseColumns interface, your inner class can inherit a primary key field
    // called _ID that some Android classes such as cursor adaptors will expect it to have.
    // It's not required, but this can help your database work harmoniously with the Android framework.
    public static class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI_BASE = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final Uri CONTENT_URI_FAVORITES = CONTENT_URI_BASE.buildUpon().appendPath(CONTENT_PATH_FAVORITES).build();

        public static final String CONTENT_TYPE_TABLE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + CONTENT_PATH_FAVORITES;
        public static final String CONTENT_TYPE_TABLE_ITEM = "vnd.android.cursor.item" + CONTENT_AUTHORITY + "/" + CONTENT_PATH_FAVORITES;

        public static final String TABLE_NAME_FAVORITE = "favorite";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_RELEASE_DATE = "release_date";

    }
}
