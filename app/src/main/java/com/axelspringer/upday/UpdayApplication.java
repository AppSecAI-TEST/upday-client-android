package com.axelspringer.upday;

import android.app.Application;

import com.axelspringer.upday.infrastructure.di.components.DaggerNetworkComponent;
import com.axelspringer.upday.infrastructure.di.components.NetworkComponent;
import com.axelspringer.upday.infrastructure.di.modules.NetworkModule;

/**
 * Created by damien on 7/4/17.
 */

public class UpdayApplication extends Application {

    private static UpdayApplication mApplication;

    protected NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();
    }

    public static NetworkComponent getClient() {
        return mApplication.networkComponent;
    }

}