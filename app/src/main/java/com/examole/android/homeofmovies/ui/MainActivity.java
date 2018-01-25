package com.examole.android.homeofmovies.ui;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.examole.android.homeofmovies.Adapter.MovieAdapter;
import com.examole.android.homeofmovies.Constants.Constant;
import com.examole.android.homeofmovies.Model.DataSource;
import com.examole.android.homeofmovies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.whenMovieIsClicked {



    DataSource []moviesList;
    RecyclerView movieRecyclerView;
    MovieAdapter movieAdapter;
    Button prevBtn;
    Button nextBtn;
    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    public static final String MOVIE_RATE = "MOVIE_RATE";
    public static final String MOVIE_POSTER = "MOVIE_POSTER";
    public static final String MOVIE_OVERVIEW = "MOVIE_OVERVIEW";
    public static final String MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        prevBtn = (Button) findViewById(R.id.prev_button);
        nextBtn = (Button) findViewById(R.id.next_button);
        prevBtn.setVisibility(View.INVISIBLE);

        getMovieApi(Constant.PAGE_NUMBER,Constant.SORT_BY_POP,Constant.API_KEY);
    }


    public void getMovieApi(int pageNum, String sortBy, String apiKey){
        String movieApi = "https://api.themoviedb.org/3/discover/movie?page="+pageNum
                +"&sort_by="+sortBy+"&api_key="+apiKey;
        if(pageNum != 1)prevBtn.setVisibility(View.VISIBLE);

        if(isNetworkAvailable()){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(movieApi)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // when something is error
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                   try {
                       String apiAsString = response.body().string();
                       if(response.isSuccessful()){
                           moviesList = getMovies(apiAsString);
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   sendDataToAdapter(moviesList);
                               }

                           });
                       }else{
                           // something wrong

                       }

                   }catch (JSONException e){

                   }

                    }

            });
        }else{
            // no connection
        }
    }


    public DataSource[] getMovies(String apiAsString) throws JSONException{
        JSONObject allJSON = new JSONObject(apiAsString);
        JSONArray results = allJSON.getJSONArray("results");
        DataSource[] resultsArray = new DataSource[results.length()];
        for(int i = 0; i < resultsArray.length; i++){
            JSONObject movie = results.getJSONObject(i);
            DataSource ds = new DataSource();
            ds.setMovie_title(movie.getString("title"));
            ds.setMovie_image(Constant.BASE_URL_IMAGE+"w185"+movie.getString("poster_path"));
            ds.setMovie_rate(movie.getString("vote_average"));
            ds.setMovie_overView(movie.getString("overview"));
            ds.setMovie_releaseDate(movie.getString("release_date"));


            resultsArray[i] = ds;
        }
        return resultsArray;
    }


    public void sendDataToAdapter(DataSource[] moviesList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.setHasFixedSize(true);
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(),moviesList,this);
        movieRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void getMovieDetails(int moviePosition) {


        Intent intent = new Intent(MainActivity.this,DetailActivity.class);

        intent.putExtra(MOVIE_TITLE,moviesList[moviePosition].getMovie_title());
        intent.putExtra(MOVIE_RATE,moviesList[moviePosition].getMovie_rate());
        intent.putExtra(MOVIE_POSTER,moviesList[moviePosition].getMovie_image());
        intent.putExtra(MOVIE_OVERVIEW,moviesList[moviePosition].getMovie_overView());
        intent.putExtra(MOVIE_RELEASE_DATE,moviesList[moviePosition].getMovie_releaseDate());

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }


    // check internet is available or not
    public boolean isNetworkAvailable(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.show_popular) {

            getMovieApi(Constant.PAGE_NUMBER,Constant.SORT_BY_POP,Constant.API_KEY);
            return true;
        }
        else if(id == R.id.show_high_rate){

            getMovieApi(Constant.PAGE_NUMBER,Constant.SORT_BY_HR,Constant.API_KEY);
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }
}
