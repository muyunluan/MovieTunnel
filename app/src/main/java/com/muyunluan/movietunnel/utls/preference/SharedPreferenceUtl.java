package com.muyunluan.movietunnel.utls.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Fei Deng on 9/11/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class SharedPreferenceUtl {

    private static final String PREFERENCE_NAME_FAVORITE = "favorite_movie";

    private static final String PREFERENCE_KEY_FAVORITE = "key_favorite";

    private static SharedPreferenceUtl sharedPreferenceUtl;
    private SharedPreferences sharedPreferences;

    private SharedPreferenceUtl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME_FAVORITE, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtl getInstance(Context context) {
        if (null == sharedPreferenceUtl) {
            sharedPreferenceUtl = new SharedPreferenceUtl(context);
        }
        return sharedPreferenceUtl;
    }

    public Map<String, ?> getAllItems() {
        return sharedPreferences.getAll();
    }

    public void setFavoriteMovieWithImage(int id, String posterPath) {
        sharedPreferences.edit().putString(String.valueOf(id), posterPath).apply();
    }

    public boolean getMovieIsFavorite(int id) {
        return sharedPreferences.getString(String.valueOf(id), null) != null;
    }

    public void removeFavoriteMovie(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(id));
        editor.apply();
    }
}
