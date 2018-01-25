package com.examole.android.homeofmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 1/24/2018.
 */

public class DataSource implements Parcelable  {
    String movie_image;
    String movie_title;
    String movie_rate;
    String movie_overView;
    String movie_releaseDate;

    public DataSource(String movie_image, String movie_name, String movie_rate,String movie_overView,
                      String movi_releaseDate) {
        this.movie_image = movie_image;
        this.movie_title = movie_name;
        this.movie_rate = movie_rate;
        this.movie_overView = movie_overView;
        this.movie_releaseDate = movi_releaseDate;
    }
    public DataSource() {
    }

    public String getMovie_overView() {
        return movie_overView;
    }

    public void setMovie_overView(String movie_overView) {
        this.movie_overView = movie_overView;
    }

    public String getMovie_releaseDate() {
        return movie_releaseDate;
    }

    public void setMovie_releaseDate(String movie_releaseDate) {
        this.movie_releaseDate = movie_releaseDate;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_rate() {
        return movie_rate;
    }

    public void setMovie_rate(String movie_rate) {
        this.movie_rate = movie_rate;
    }

    public DataSource(Parcel parcel) {
        movie_image = parcel.readString();
        movie_title = parcel.readString();
        movie_rate = parcel.readString();
        movie_overView = parcel.readString();
        movie_releaseDate = parcel.readString();

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movie_title);
        parcel.writeString(movie_image);
        parcel.writeString(movie_overView);
        parcel.writeString(movie_rate);
        parcel.writeString(movie_releaseDate);

    }

    public static final Creator<DataSource> CREATOR = new Creator<DataSource>() {
        @Override
        public DataSource createFromParcel(Parcel parcel) {
            return new DataSource(parcel);
        }

        @Override
        public DataSource[] newArray(int i) {
            return new DataSource[i];
        }
    };
}
