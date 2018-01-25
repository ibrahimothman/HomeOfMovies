package com.examole.android.homeofmovies.ui;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.examole.android.homeofmovies.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    String movie_title;
    String movie_rate;
    String movie_poster;
    String movie_overview;
    String movie_release_date;
    TextView movie_title_text;
    TextView movie_rate_text;
    TextView movie_overview_text;
    TextView movie_release_date_text;
    ImageView movie_poster_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        movie_title = intent.getExtras().getString(MainActivity.MOVIE_TITLE);
        movie_overview = intent.getExtras().getString(MainActivity.MOVIE_OVERVIEW);
        movie_poster = intent.getExtras().getString(MainActivity.MOVIE_POSTER);
        movie_rate = intent.getExtras().getString(MainActivity.MOVIE_RATE);
        movie_release_date = intent.getExtras().getString(MainActivity.MOVIE_RELEASE_DATE);

        movie_overview_text = (TextView) findViewById(R.id.movie_overview);
        movie_rate_text = (TextView) findViewById(R.id.movie_rate);
        movie_title_text = (TextView) findViewById(R.id.movie_title);
        movie_release_date_text = (TextView) findViewById(R.id.movie_release_date);
        movie_poster_image = (ImageView) findViewById(R.id.movie_poster);

        movie_overview_text.setText(movie_overview);
        movie_title_text.setText(movie_title);
        movie_rate_text.setText("Rate : "+movie_rate);
        movie_release_date_text.setText("Published on : "+movie_release_date);
        Picasso.with(this).load(movie_poster)
                .into(movie_poster_image);

            setTitle(movie_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}

