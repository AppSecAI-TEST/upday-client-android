package com.axelspringer.upday;

import android.app.Application;

import com.axelspringer.upday.infrastructure.di.components.DaggerAppComponent;
import com.axelspringer.upday.infrastructure.di.modules.ApplicationModule;
import com.axelspringer.upday.infrastructure.di.modules.NetworkModule;
import com.axelspringer.upday.services.network.ApiClient;

import javax.inject.Inject;

/**
 * Created by damien on 7/4/17.
 */

public class UpdayApplication extends Application {

    private static UpdayApplication mApplication;

    @Inject
    protected ApiClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build()
                .inject(this);
    }

    public static ApiClient getClient() {
        return mApplication.client;
    }

}
