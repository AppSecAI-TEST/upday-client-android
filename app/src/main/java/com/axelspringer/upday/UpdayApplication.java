package com.axelspringer.upday;

import android.app.Application;

import com.axelspringer.upday.services.network.ApiClient;

import retrofit2.Retrofit;

/**
 * Created by damien on 7/4/17.
 */

public class UpdayApplication extends Application {

    private static UpdayApplication mApplication;
    private ApiClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.client = new ApiClient(BuildConfig.BASE_URL);
    }

    public static ApiClient getClient() {
        return mApplication.client;
    }
}
