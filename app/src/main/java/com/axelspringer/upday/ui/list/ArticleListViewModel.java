package com.axelspringer.upday.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.services.ApiService;

import java.util.List;

/**
 * Created by damien on 7/6/17.
 */

public class ArticleListViewModel extends ViewModel {


    private MutableLiveData<List<ArticleDTO>> articles = new MutableLiveData<>();

    public MutableLiveData<List<ArticleDTO>> getArticles() {
        return articles;
    }

    public void queryAllArticles() {
        ApiService.getArticles().subscribe(
                articles -> {
                    Log.v(MainActivity.TAG, articles.toString());
                    this.articles.setValue(articles);
                },
                throwable -> Log.e(MainActivity.TAG, throwable.toString()));
    }

}
