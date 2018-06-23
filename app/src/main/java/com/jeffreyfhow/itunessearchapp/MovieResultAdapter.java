package com.jeffreyfhow.itunessearchapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeffreyfhow.itunessearchapp.utility.URLImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieResultAdapter
        extends RecyclerView.Adapter<MovieResultAdapter.ViewHolder>
{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView titleTextView;
        private TextView directorTextView;
        private TextView yearTextView;
        private TextView descriptionTextView;
        private ImageView posterImageView;

        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
            titleTextView = cardView.findViewById(R.id.titleText);
            directorTextView = cardView.findViewById(R.id.directorText);
            yearTextView = cardView.findViewById(R.id.yearText);
            descriptionTextView = cardView.findViewById(R.id.descText);
            posterImageView = cardView.findViewById(R.id.posterImage);
        }
    }

    private final ArrayList<Movie> movies;
    private final HashMap<String, Boolean> favoriteMap;
    private Context context;

    public MovieResultAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
        favoriteMap = new HashMap<String, Boolean>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_card, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie currMovie = movies.get(position);
        holder.titleTextView.setText(currMovie.getTitle());
        holder.directorTextView.setText(currMovie.getDirector());
        holder.yearTextView.setText(currMovie.getYear().toString());
        holder.descriptionTextView.setText(currMovie.getDescription());
        URLImageLoader.getInstance().loadImage(
                context, currMovie.getImageUrl(), holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateData(ArrayList<Movie> movies, HashMap<String, Boolean> favoriteMap) {
        Log.v("MovieResultAdapter", "updateData() - ");
        this.movies.clear();
        this.movies.addAll(movies);

        this.favoriteMap.clear();
        this.favoriteMap.putAll(favoriteMap);

//        for(int i = 0; i < this.movies.size(); i++){
//            Log.v("MainActivityController", this.movies.get(i).toString());
//        }

        notifyDataSetChanged();
    }
}
