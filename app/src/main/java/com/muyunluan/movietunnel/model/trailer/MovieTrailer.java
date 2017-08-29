package com.muyunluan.movietunnel.model.trailer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fei Deng on 8/15/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieTrailer {
    @SerializedName("id")
    private String mId;
    @SerializedName("iso_639_1")
    private String mIso639_1;
    @SerializedName("iso_3166_1")
    private String mIso3166_1;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private Integer mSize;
    @SerializedName("type")
    private String mType;

    public MovieTrailer(String mId, String mIso639_1, String mIso3166_1, String mKey, String mName, String mSite, Integer mSize, String mType) {
        this.mId = mId;
        this.mIso639_1 = mIso639_1;
        this.mIso3166_1 = mIso3166_1;
        this.mKey = mKey;
        this.mName = mName;
        this.mSite = mSite;
        this.mSize = mSize;
        this.mType = mType;
    }

    @Override
    public String toString() {
        return "MovieTrailer{" +
                "mId='" + mId + '\'' +
                ", mIso639_1='" + mIso639_1 + '\'' +
                ", mIso3166_1='" + mIso3166_1 + '\'' +
                ", mKey='" + mKey + '\'' +
                ", mName='" + mName + '\'' +
                ", mSite='" + mSite + '\'' +
                ", mSize=" + mSize +
                ", mType='" + mType + '\'' +
                '}';
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmIso639_1() {
        return mIso639_1;
    }

    public void setmIso639_1(String mIso639_1) {
        this.mIso639_1 = mIso639_1;
    }

    public String getmIso3166_1() {
        return mIso3166_1;
    }

    public void setmIso3166_1(String mIso3166_1) {
        this.mIso3166_1 = mIso3166_1;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSite() {
        return mSite;
    }

    public void setmSite(String mSite) {
        this.mSite = mSite;
    }

    public Integer getmSize() {
        return mSize;
    }

    public void setmSize(Integer mSize) {
        this.mSize = mSize;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
