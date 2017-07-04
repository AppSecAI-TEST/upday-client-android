package com.axelspringer.upday.services;

import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.services.network.api.UpdayApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by damien on 7/4/17.
 */

public class ApiService {

    public static Observable<List<ArticleDTO>> getArticles() {
        final UpdayApi api = UpdayApplication.getClient().getRetrofit().create(UpdayApi.class);
        return api.getAllArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
