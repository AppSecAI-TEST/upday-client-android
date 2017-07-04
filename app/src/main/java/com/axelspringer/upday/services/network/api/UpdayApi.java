package com.axelspringer.upday.services.network.api;

import com.axelspringer.upday.model.ArticleDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by damien on 7/4/17.
 */

public interface UpdayApi {

    @GET("articles")
    Observable<List<ArticleDTO>> getAllArticles();

    @POST("api/articles")
    Observable<Void> createArticle();

    @PUT("api/articles")
    Observable<Void> updateArticle();

    @DELETE("api/articles/{id}")
    Observable<Void> deleteArticle(@Path("id") Integer id);

    @GET("api/articles/{id}")
    Observable<ArticleDTO> getArticle(@Path("id") Integer id);
}
