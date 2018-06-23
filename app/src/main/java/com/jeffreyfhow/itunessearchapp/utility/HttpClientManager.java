package com.jeffreyfhow.itunessearchapp.utility;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClientManager {

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHttpClient(){

        if(okHttpClient == null){

            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            );

            Interceptor queryIntercepter = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("media", "movie")
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };

            okHttpBuilder.addInterceptor(loggingInterceptor);
            okHttpBuilder.addInterceptor(queryIntercepter);

            okHttpClient = okHttpBuilder.build();
        }

        return okHttpClient;
    }
}
