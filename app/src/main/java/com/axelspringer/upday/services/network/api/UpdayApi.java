package com.axelspringer.upday.services.network.api;

import com.axelspringer.upday.model.ArticleDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
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

    @POST("articles")
    Observable<Response<Void>> createArticle(@Body ArticleDTO articleDTO);

    @PUT("articles")
    Observable<Response<Void>> updateArticle();

    @DELETE("articles/{id}")
    Observable<Response<Void>> deleteArticle(@Path("id") Long id);

    @GET("articles/{id}")
    Observable<ArticleDTO> getArticle(@Path("id") Long id);
}
