package com.axelspringer.upday.infrastructure.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.ui.list.ArticleListViewModel;

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
