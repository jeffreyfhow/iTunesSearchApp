package com.jeffreyfhow.itunessearchapp.network;

import com.jeffreyfhow.itunessearchapp.Movie;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("search")
    Observable<ArrayList<Movie>> fetchMovieResults(
        @Query("term") String title,
        @Query("limit") Integer limit
    );

}
