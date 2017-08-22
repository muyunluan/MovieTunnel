package com.muyunluan.movietunnel.utls;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static com.muyunluan.movietunnel.utls.data.Constants.DISCOVER_BASE_URL;
import static com.muyunluan.movietunnel.utls.data.Constants.IMAGE_BASE_URL;
import static com.muyunluan.movietunnel.utls.data.Constants.IMAGE_SIZE;
import static com.muyunluan.movietunnel.utls.data.Constants.MOVIE_BASE_URL;
import static com.muyunluan.movietunnel.utls.data.Constants.PARAM_API_KEY;
import static com.muyunluan.movietunnel.utls.data.Constants.PARAM_LANGUAGE;
import static com.muyunluan.movietunnel.utls.data.Constants.PARAM_SORT;
import static com.muyunluan.movietunnel.utls.data.Constants.PATH_REVIEWS;
import static com.muyunluan.movietunnel.utls.data.Constants.PATH_TRAILERS;
import static com.muyunluan.movietunnel.utls.data.Constants.SORT_BY_POPULAR;
import static com.muyunluan.movietunnel.utls.data.Constants.SORT_BY_RATING;

/**
 * Created by Fei Deng on 8/18/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class NetworkApiService {
    private final static String TAG = NetworkUtils.class.getSimpleName();

    public static URL buildBaseUrl(String searchQuery, boolean isRating) {
        Uri builtUri = Uri.parse(DISCOVER_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, searchQuery)
                .appendQueryParameter(PARAM_SORT, isRating ? SORT_BY_RATING : SORT_BY_POPULAR)
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .build();

        return buildUrl(builtUri);
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String buildImageUrlStr(String posterPath) {
        if (null != posterPath || !posterPath.isEmpty()) {
            StringBuffer imageStr = new StringBuffer(IMAGE_BASE_URL);
            imageStr.append(IMAGE_SIZE);
            imageStr.append(posterPath);
            return imageStr.toString();
        } else {
            Log.e(TAG, "buildImageUrlStr: Empty input for image poster path");
            return null;
        }
    }

    public static URL buildTrailerUrl(String searchQuery, int id) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(String.valueOf(id))
                .appendPath(PATH_TRAILERS)
                .appendQueryParameter(PARAM_API_KEY, searchQuery)
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .build();

        return buildUrl(builtUri);
    }

    public static URL buildReviewsUrl(String searchQuery, int id) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(String.valueOf(id))
                .appendPath(PATH_REVIEWS)
                .appendQueryParameter(PARAM_API_KEY, searchQuery)
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .build();

        return buildUrl(builtUri);
    }

    private static URL buildUrl(Uri builtUri) {
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
