package com.axelspringer.upday.services.network.api;

import com.axelspringer.upday.model.JWTResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by damien on 7/4/17.
 */

public interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("authenticate")
    Observable<JWTResponse> authenticate(@Body Map<String, String> body);
}
