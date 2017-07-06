package com.axelspringer.upday.infrastructure.di.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.axelspringer.upday.UpdayApplication;

/**
 * Created by damien on 7/6/17.
 */

public class UpdayFactory extends ViewModelProvider.NewInstanceFactory {

    private UpdayApplication application;

    public UpdayFactory(UpdayApplication application) {
        this.application = application;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        // TODO: specific view models with custom parameters if needed
        return t;
    }
}
