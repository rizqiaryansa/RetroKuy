package com.aryansa.rizqi.magangdotkuy.connectivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RizqiAryansa on 3/16/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient clientOkHttp = new
                OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientOkHttp)
                .build();

        return retrofit;
    }
}
