package com.muyunluan.movietunnel.ui.ui.trailer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.trailer.MovieTrailer;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/22/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerViewHolder> {
    private static final String TAG = MovieTrailerAdapter.class.getSimpleName();

    private ArrayList<MovieTrailer> mMovieTrailers = new ArrayList<>();

    final private MovieTrailerFragment.OnTrailerListItemClick mOnTrailerListItemClick;
    private String mTrailerKey;

    public MovieTrailerAdapter(ArrayList<MovieTrailer> movieTrailers,
                               MovieTrailerFragment.OnTrailerListItemClick onTrailerListItemClick) {
        this.mMovieTrailers = movieTrailers;
        mOnTrailerListItemClick = onTrailerListItemClick;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie_trailer_item, parent, false);
        TrailerViewHolder trailerViewHolder = new TrailerViewHolder(rootView);
        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        MovieTrailer movieTrailer = mMovieTrailers.get(position);
        holder.titleTv.setText(movieTrailer.getmName());

        mTrailerKey = movieTrailer.getmKey();
    }

    @Override
    public int getItemCount() {
        return mMovieTrailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView playImg;
        private TextView titleTv;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            playImg = (ImageView) itemView.findViewById(R.id.img_play);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnTrailerListItemClick.onTailerListItemClick(mTrailerKey);
        }
    }
}
