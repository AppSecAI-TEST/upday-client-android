package com.axelspringer.upday.services.network;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;

/**
 * Created by damien on 7/4/17.
 */

public class ApiClient {

    @Inject
    protected Retrofit authRetrofit;
    @Inject
    @Named("api")
    protected Retrofit apiRetrofit;

    public ApiClient(Retrofit authRetrofit, Retrofit apiRetrofit) {
        this.authRetrofit = authRetrofit;
        this.apiRetrofit = apiRetrofit;
    }

    public Retrofit getApiRetrofit() {
        return apiRetrofit;
    }

    public Retrofit getAuthRetrofit() {
        return authRetrofit;
    }
}
