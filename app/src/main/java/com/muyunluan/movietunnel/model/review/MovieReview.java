package com.muyunluan.movietunnel.model.review;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fei Deng on 8/15/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieReview {
    @SerializedName("id")
    private String mId;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("content")
    private String mContent;
    @SerializedName("url")
    private String mUrl;

    public MovieReview(String mId, String mAuthor, String mContent, String mUrl) {
        this.mId = mId;
        this.mAuthor = mAuthor;
        this.mContent = mContent;
        this.mUrl = mUrl;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "mId='" + mId + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
