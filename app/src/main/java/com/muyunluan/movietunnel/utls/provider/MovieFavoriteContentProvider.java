package com.muyunluan.movietunnel.utls.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.muyunluan.movietunnel.utls.sqlite.MovieDBHelper;

import static com.muyunluan.movietunnel.utls.data.Constants.CONTENT_AUTHORITY;
import static com.muyunluan.movietunnel.utls.data.Constants.CONTENT_PATH_FAVORITES;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.CONTENT_TYPE_TABLE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.CONTENT_TYPE_TABLE_ITEM;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.CONTENT_URI_FAVORITES;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.TABLE_NAME_FAVORITE;

/**
 * Created by Fei Deng on 9/5/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieFavoriteContentProvider extends ContentProvider {

    private static final String TAG = MovieFavoriteContentProvider.class.getSimpleName();

    // 1st: Design Content URIs in Contract class

    // 2nd: Build UriMatcher to handle URIs for a Table or a Row
    private static final UriMatcher mUriMatcher = buildUriMatcher();
    // Define final integer constants for the directory of tasks and a single item.
    // It's convention to use 100, 200, 300, etc for directories,
    // and related ints (101, 102, ..) for items in that directory.
    public static final int CODE_TABLE_FAVORITES = 100;
    public static final int CODE_TABLE_FAVORITES_ITEM = 101;
    private static UriMatcher buildUriMatcher() {
        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // The method addURI() maps an authority and path to an integer value.
        // The method match() returns the integer value for a URI.
        uriMatcher.addURI(CONTENT_AUTHORITY, CONTENT_PATH_FAVORITES, CODE_TABLE_FAVORITES);
        uriMatcher.addURI(CONTENT_AUTHORITY, CONTENT_PATH_FAVORITES + "/#", CODE_TABLE_FAVORITES_ITEM);
        return uriMatcher;
    }

    private MovieDBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        // 3rd: Init SQLite Helper
        mDBHelper = new MovieDBHelper(getContext());
        return true;
    }

    // 4th: Implement CRUD methods
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        int match = mUriMatcher.match(uri);
        Cursor cursor = null;
        switch (match) {
            case CODE_TABLE_FAVORITES:
                break;
            case CODE_TABLE_FAVORITES_ITEM:
                cursor = db.query(TABLE_NAME_FAVORITE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Set a notification URI on the Cursor and return that Cursor
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        int match = mUriMatcher.match(uri);
        Uri returnUri = null;

        switch (match) {
            case CODE_TABLE_FAVORITES:
                long id = db.insert(TABLE_NAME_FAVORITE, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(CONTENT_URI_FAVORITES, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);
        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        int match = mUriMatcher.match(uri);
        int favoritesDeleted = 0;

        switch (match) {
            case CODE_TABLE_FAVORITES_ITEM:
                /*
                * If we pass null as the selection to SQLiteDatabase#delete, our entire table will be
                * deleted. However, if we do pass null and delete all of the rows in the table, we won't
                * know how many rows were deleted. According to the documentation for SQLiteDatabase,
                * passing "1" for the selection will delete all rows and return the number of rows
                * deleted, which is what the caller of this method expects.
                */
                String id = uri.getPathSegments().get(1);
                // Use selections/selectionArgs to filter for this ID
                favoritesDeleted = db.delete(TABLE_NAME_FAVORITE, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Notify the resolver of a change and return the number of items deleted
        if (favoritesDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return favoritesDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    // 5th: Define MIME type in Contract class and implement here
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = mUriMatcher.match(uri);
        switch (match) {
            case CODE_TABLE_FAVORITES:
                return CONTENT_TYPE_TABLE;
            case CODE_TABLE_FAVORITES_ITEM:
                return CONTENT_TYPE_TABLE_ITEM;
            default:
                break;
        }
        return null;
    }

}
