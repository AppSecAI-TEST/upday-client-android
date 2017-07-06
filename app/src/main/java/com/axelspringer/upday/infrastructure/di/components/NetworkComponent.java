package com.axelspringer.upday.infrastructure.di.components;

import com.axelspringer.upday.infrastructure.di.modules.NetworkModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by dchomat on 06/07/2017.
 */

@Singleton
@Component(modules = NetworkModule.class )
public interface NetworkComponent {

    @Named("api") Retrofit getApiRetrofit();

    Retrofit getAuthRetrofit();

}
