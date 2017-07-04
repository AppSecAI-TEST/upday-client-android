package com.axelspringer.upday.services.network;

import com.axelspringer.upday.services.network.interceptors.JWTInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by damien on 7/4/17.
 */

public class ApiClient {

    private OkHttpClient okHttpClient;
    private Retrofit apiRetrofit;
    private Retrofit authRetrofit;

    public ApiClient(final String baseUrl) {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        this.okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new JWTInterceptor())
                .addInterceptor(logger)
                .build();

        this.authRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.apiRetrofit = this.authRetrofit.newBuilder()
                .client(this.okHttpClient)
                .build();
    }

    public Retrofit getRetrofit() {
        return apiRetrofit;
    }

    public Retrofit getAuthRetrofit() {
        return authRetrofit;
    }
}
