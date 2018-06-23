package com.jeffreyfhow.itunessearchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieListScreen implements MovieListModel.MovieListObserver{

    private final RecyclerView recyclerView;

    private final MovieResultAdapter adapter;

    private AppCompatActivity activity;

    public MovieListScreen(AppCompatActivity activity, View parent) {
        this.activity = activity;
        recyclerView = parent.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(
            parent.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MovieResultAdapter(parent.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMovieListUpdate(ArrayList<Movie> movies, HashMap<String, Boolean> favoriteMap) {
        Log.v("MovieListScreen", "onMovieListUpdate()");
        adapter.updateData(movies, favoriteMap);
    }

    public void setRecyclerClickListener(MovieResultAdapter.Listener listener){
        adapter.setListener(listener);
    }
}
