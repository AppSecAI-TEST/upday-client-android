package com.axelspringer.upday.infrastructure.di.components;

import android.app.Application;
import android.content.Context;

import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.infrastructure.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dchomat on 06/07/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface AppComponent {

    void inject(UpdayApplication updayApplication);

    Context getContext();

    Application getApplication();

}
