package com.muyunluan.movietunnel.model.review;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/24/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieReviewResponse {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("page")
    private Integer mPage;
    @SerializedName("results")
    private ArrayList<MovieReview> mResults;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmPage() {
        return mPage;
    }

    public void setmPage(Integer mPage) {
        this.mPage = mPage;
    }

    public ArrayList<MovieReview> getmResults() {
        return mResults;
    }

    public void setmResults(ArrayList<MovieReview> mResults) {
        this.mResults = mResults;
    }

    public int getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(int mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public int getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
    }
}
