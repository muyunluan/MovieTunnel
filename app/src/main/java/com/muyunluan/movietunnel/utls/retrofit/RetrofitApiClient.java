package com.muyunluan.movietunnel.utls.retrofit;

import com.muyunluan.movietunnel.utls.data.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fei Deng on 8/16/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class RetrofitApiClient {

    private static final String TAG = RetrofitApiClient.class.getSimpleName();

    private static Retrofit retrofit = null;

    // To send network requests to an API, we need to use the Retrofit Builder
    // class and specify the base URL for the service.
    public static Retrofit getBaseClient() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
