package com.examole.android.homeofmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.examole.android.homeofmovies.Model.DataSource;
import com.examole.android.homeofmovies.R;
import com.examole.android.homeofmovies.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 1/24/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private Context context;
    private DataSource[] movieList;
    final private whenMovieIsClicked mListener;

    public interface whenMovieIsClicked{
        void getMovieDetails(int moviePosition);
    }

    public MovieAdapter(Context context, DataSource[] movieList,whenMovieIsClicked listener) {
        this.context = context;
        this.movieList = movieList;
        mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.length;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movie_poster;
        TextView movie_name;
        TextView movie_rate;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movie_name = (TextView) itemView.findViewById(R.id.movie_title);
            movie_rate = (TextView) itemView.findViewById(R.id.movie_rate);
            movie_poster = (ImageView) itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        public void bind(int position){

            movie_name.setText(movieList[position].getMovie_title());
            movie_rate.setText(movieList[position].getMovie_rate());
            Picasso.with(context).load(movieList[position].getMovie_image()).into(movie_poster);
        }

        @Override
        public void onClick(View view) {
            int movieIndex = getAdapterPosition();
            mListener.getMovieDetails(movieIndex);
        }
    }
}
