package com.axelspringer.upday.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by damien on 7/4/17.
 */

public class JWTResponse {

    @SerializedName("id_token")
    private String token;

    public String getToken() {
        return token;
    }
}
