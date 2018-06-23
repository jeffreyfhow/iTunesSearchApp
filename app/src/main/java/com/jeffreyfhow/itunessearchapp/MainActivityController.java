package com.jeffreyfhow.itunessearchapp;

import android.util.Log;

import com.jeffreyfhow.itunessearchapp.network.NetworkApi;
import com.jeffreyfhow.itunessearchapp.network.NetworkService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityController {

    public MainActivityController(){}

    public void start(){
        NetworkService networkService = new NetworkService();
        NetworkApi networkApi = networkService.getApi();

        Observable<ArrayList<Movie>> movieObservable =
                networkApi.fetchMovieResults("avengers", 10);

        Disposable subscription = movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                v -> {
                    ArrayList<Movie> m = v;
                    for(int i = 0; i < m.size(); i++){
                        Log.v("MainActivityController", m.get(i).toString());
                    }
                    int i = 0;
                },
                e -> {
                    Log.v("MainActivityController", "JSON ERROR: " + e.getMessage());
                },
                () -> {

                }
            );

    }
}
