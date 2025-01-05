package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilders.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String token, String path, Object payload)
    {
        return given(getRequestSpecification())
                .body(payload)
                .auth().oauth2(token)
        .when()
                .post(path)
        .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response postAccessToken(HashMap<String,String> formParameters){
        return given(getAccessTokenRequestSpecification())
                .formParams(formParameters)
        .when()
                .post(Routes.TOKEN)
        .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response get(String path, String token){

        return given(getRequestSpecification())
                .auth().oauth2(token)
        .when()
                .get(path)
        .then()
                .spec(getResponseSpecification())
                .extract().response();
    }

    public static Response put(Object payload, String path, String token){

        return given(getRequestSpecification())
                .body(payload)
                .auth().oauth2(token)
        .when()
                .put(path)
         .then()
                .spec(getResponseSpecification())
                .extract().response();
    }
}
