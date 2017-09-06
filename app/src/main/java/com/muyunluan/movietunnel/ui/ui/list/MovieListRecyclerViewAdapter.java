package com.muyunluan.movietunnel.ui.ui.list;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.utls.network.NetworkBasic;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = MovieListRecyclerViewAdapter.class.getSimpleName();

    private final List<Movie> mValues;
    private final MovieListFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;

    public MovieListRecyclerViewAdapter(List<Movie> items, MovieListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        if (!TextUtils.isEmpty(holder.mItem.getmPosterPath())) {
            String urlStr = NetworkBasic.buildImageUrlStr(holder.mItem.getmPosterPath());
            Picasso.with(mContext).load(urlStr).into(holder.mImageView);
        } else {
            // TODO: load local temperate pictures
        }

        holder.mOverviewTv.setText(holder.mItem.getmOverview());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mCardview;
        public final ImageView mImageView;
        public final TextView mOverviewTv;

        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mCardview = (CardView) view.findViewById(R.id.card_movie);
            mImageView = (ImageView) view.findViewById(R.id.img_movie);
            mOverviewTv = (TextView) view.findViewById(R.id.tv_overview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mOverviewTv.getText() + "'";
        }
    }
}
