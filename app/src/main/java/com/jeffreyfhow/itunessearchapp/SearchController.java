package com.jeffreyfhow.itunessearchapp;

import android.util.Log;

import com.jeffreyfhow.itunessearchapp.network.NetworkApi;
import com.jeffreyfhow.itunessearchapp.network.NetworkService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchController {

    private MovieListModel model;
    private NetworkService networkService;
    private NetworkApi networkApi;

    public SearchController(MovieListModel model){
        networkService = new NetworkService();
        networkApi = networkService.getApi();
        this.model = model;
    }

    public void requestMovies(String title, int limit){

        Observable<ArrayList<Movie>> movieObservable =
            networkApi.fetchMovieResults(title, limit);

        Disposable subscription = movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                v -> { retrieveMovieResults(v); },
                e -> { retrieveMovieResultsError(e); }
            );

    }

    private void retrieveMovieResults(ArrayList<Movie> movies){

        Log.v("SearchController", "retrieveMovieResults() success");
        for(int i = 0; i < movies.size(); i++){
            Log.v("SearchController", movies.get(i).toString());
        }
        model.setMovies(movies);
    }

    private void retrieveMovieResultsError(Throwable t){
        Log.v("SearchController", "JSON ERROR: " + t.getMessage());
    }
}
