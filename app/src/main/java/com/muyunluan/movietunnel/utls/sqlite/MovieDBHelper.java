package com.muyunluan.movietunnel.utls.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.TABLE_NAME_FAVORITE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry._ID;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_ID;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_POSTER_PATH;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_OVERVIEW;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_RELEASE_DATE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_TITLE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_VOTE_AVERAGE;


/**
 * Created by Fei Deng on 9/1/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movieDb.db";
    private static final int VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_FAVORITE_TABLE = "CREATE TABLE "
                +
                TABLE_NAME_FAVORITE + " ("
                +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +
                COLUMN_ID + " TEXT UNIQUE NOT NULL, "
                +
                COLUMN_TITLE + " TEXT NOT NULL, "
                +
                COLUMN_POSTER_PATH + " TEXT NOT NULL, "
                +
                COLUMN_OVERVIEW + " TEXT NOT NULL, "
                +
                COLUMN_VOTE_AVERAGE + " TEXT NOT NULL, "
                +
                COLUMN_RELEASE_DATE + " TEXT NOT NULL);";
        db.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAVORITE);
        onCreate(db);
    }
}
