package com.axelspringer.upday.services.network.interceptors;

import android.util.Log;

import com.axelspringer.upday.BuildConfig;
import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.services.network.api.AuthApi;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by damien on 7/4/17.
 */

public class JWTInterceptor implements Interceptor {

    public static final String TAG = JWTInterceptor.class.getSimpleName();
    private String token = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();

        // Rebuild the request with an Authorization header
        if (this.token == null) {
            this.token = updateAccessToken();
        }
        final Request.Builder rb = request.newBuilder().header("Authorization", "Bearer " + this.token);
        //Execute the request
        final Response response = chain.proceed(rb.build());
        Log.v(TAG, response.toString());

        return response;
    }

    private String updateAccessToken() {
        final AuthApi api = UpdayApplication.getClient().getAuthRetrofit().create(AuthApi.class);
        // Parameters
        final Map<String, String> params = new HashMap<>();
        // TODO: Hard-coded only for test purposes
        params.put("username", "admin");
        params.put("password", "admin");
        final String token =  api.authenticate(params).blockingSingle().getToken();


        return token;
    }

}
