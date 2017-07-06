package com.axelspringer.upday.ui.list;

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


    // We could improve this view model by storing local data. A new repository class could be injected
    // here by an interface called in the ViewModelProvider.NewInstanceFactory.
    // Source: https://riggaroo.co.za/android-architecture-components-looking-viewmodels-part-2/

    /**
     * In-memory articles (returned by the WS)
     * @return articles
     */
    public MutableLiveData<List<ArticleDTO>> getArticles() {
        return articles;
    }

    /**
     * Query for all articles from the backend
     */
    public void queryAllArticles() {
        ApiService.getArticles().subscribe(
                articles -> {
                    Log.v(MainActivity.TAG, articles.toString());
                    this.articles.setValue(articles);
                },
                throwable -> Log.e(MainActivity.TAG, throwable.toString()));
    }

}
