package com.examole.android.homeofmovies.Constants;

/**
 * Created by dell on 1/24/2018.
 */

public class Constant {

    public static final String API_KEY = "";
    public static final String SORT_BY_POP = "popularity.desc";
    public static final String SORT_BY_HR = "vote_average.desc";
    public static final int PAGE_NUMBER = 1;

    public static final String  MOVIE_API = "https://api.themoviedb.org/3/discover/movie?page="+PAGE_NUMBER
            +"&sort_by="+ SORT_BY_POP +"&api_key="+API_KEY;
    public static final String BASE_URL_IMAGE = "http://image.tmdb.org/t/p/";
}
