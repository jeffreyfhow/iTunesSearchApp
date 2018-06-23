package com.jeffreyfhow.itunessearchapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieListModel {

    interface MovieListObserver {
        void onMovieListUpdate(ArrayList<Movie> movies, HashMap<String, Boolean> favoriteMap);
    }

    ArrayList<Movie> movies;
    HashMap<String, Boolean> favoriteMap;
    ArrayList<MovieListObserver> observers;

    public MovieListModel(){
        movies = new ArrayList<>();
        favoriteMap = new HashMap<>();
        observers = new ArrayList<MovieListObserver>();
    }

    public void setMovies(ArrayList<Movie> movies){
//        Log.v("MovieListModel", "setMovies() - " + movies.size());

        this.movies.clear();
        this.movies.addAll(movies);

        notifyObservers();
    }

    public void setFavorite(String movieTitle, Boolean isFavorite){
        Boolean wasFavorite = favoriteMap.put(movieTitle, isFavorite);
        boolean isDirty = false;
        if(wasFavorite == null){
            isDirty = isFavorite;
        } else {
            isDirty = wasFavorite != isFavorite;
        }

        if(isDirty){
            notifyObservers();
        }
    }

    public void registerObserver(MovieListObserver observer){
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void unregisterObserver(MovieListObserver observer){
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    public void notifyObservers(){
//        Log.v("MovieListModel", "notifyingObservers()");
        for(int i = 0; i < observers.size(); i++){
            observers.get(i).onMovieListUpdate(movies, favoriteMap);
        }
    }

    public Movie getMovieAt(int index){
        return movies.get(index);
    }
}
