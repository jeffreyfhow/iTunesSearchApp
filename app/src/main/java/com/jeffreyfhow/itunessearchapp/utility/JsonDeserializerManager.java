package com.jeffreyfhow.itunessearchapp.utility;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jeffreyfhow.itunessearchapp.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonDeserializerManager {

    private static JsonDeserializer<ArrayList<Movie>> movieSearchDeserializer;
    private static Gson movieSearchGson;

    public static JsonDeserializer<ArrayList<Movie>> getMovieSearchDeserializer(){
        if(movieSearchDeserializer == null){
            movieSearchDeserializer = new JsonDeserializer<ArrayList<Movie>>() {
                @Override
                public ArrayList<Movie> deserialize(JsonElement json, Type typeOfT,
                        JsonDeserializationContext context) throws JsonParseException {
                    ArrayList<Movie> movies = new ArrayList<>();

                    JsonArray results = json.getAsJsonObject().get("results").getAsJsonArray();

                    for(int i = 0; i < results.size(); i++){
                        JsonObject data = results.get(i).getAsJsonObject();
                        movies.add(new Movie(
                            data.get("trackName").getAsString(),
                            data.get("artistName").getAsString(),
                            DataUtility.getYearFromString(data.get("releaseDate").getAsString()),
                            data.get("longDescription").getAsString(),
                            data.get("artworkUrl100").getAsString()
                        ));
                    }

                    for(int i= 0; i < movies.size(); i++){
                        Log.v("JsonDeserializer", movies.get(i).toString());
                    }
                    return movies;
                }
            };
        }

        return movieSearchDeserializer;
    }

    public static Gson getMovieSearchGson(){
        if(movieSearchGson == null){
            movieSearchGson = new GsonBuilder()
                .registerTypeAdapter(ArrayList.class, getMovieSearchDeserializer())
                .create();
        }
        return movieSearchGson;
    }

}
