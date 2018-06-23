package com.jeffreyfhow.itunessearchapp.network;

import com.jeffreyfhow.itunessearchapp.utility.HttpClientManager;
import com.jeffreyfhow.itunessearchapp.utility.JsonDeserializerManager;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Assemble Retrofit for network service calls to iTunes API
 */
public class NetworkService {

    private Retrofit retrofit = null;
    private NetworkApi api = null;

    private Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Builder()
                .baseUrl("https://itunes.apple.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(JsonDeserializerManager.getMovieSearchGson())
                )
                .client(HttpClientManager.getOkHttpClient())
                .build();
        }
        return retrofit;
    }

    public NetworkApi getApi(){
        if(api == null){
            api = getRetrofit().create(NetworkApi.class);
        }
        return api;
    }
}
