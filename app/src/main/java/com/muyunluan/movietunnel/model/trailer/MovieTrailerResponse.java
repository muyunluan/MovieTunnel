package com.muyunluan.movietunnel.model.trailer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/29/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieTrailerResponse {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("results")
    private ArrayList<MovieTrailer> mResults;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public ArrayList<MovieTrailer> getmResults() {
        return mResults;
    }

    public void setmResults(ArrayList<MovieTrailer> mResults) {
        this.mResults = mResults;
    }
}
