package com.axelspringer.upday.infrastructure.di.modules;

import com.axelspringer.upday.BuildConfig;
import com.axelspringer.upday.services.network.ApiClient;
import com.axelspringer.upday.services.network.interceptors.JWTInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dchomat on 06/07/2017.
 */

@Module
public class NetworkModule {

    String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public ApiClient provideApiClient(Retrofit authRetrofit, @Named("api") Retrofit apiRetrofit) {
        return new ApiClient(authRetrofit, apiRetrofit);
    }

    @Provides
    @Singleton
    public Retrofit provideAuthRetrofit(OkHttpClient httpClient) {
        return getDefaultRetrofitBuilder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("api")
    public Retrofit provideApiRetrofit(@Named("api") OkHttpClient httpClient) {
        return getDefaultRetrofitBuilder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient() {
        return getDefaultOkHttpBuilder().build();
    }

    @Provides
    @Singleton
    @Named("api")
    public OkHttpClient provideApiOkhttpClient() {
        return getDefaultOkHttpBuilder().addInterceptor(new JWTInterceptor()).build();
    }

    private Retrofit.Builder getDefaultRetrofitBuilder() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    private OkHttpClient.Builder getDefaultOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder;
    }

}
