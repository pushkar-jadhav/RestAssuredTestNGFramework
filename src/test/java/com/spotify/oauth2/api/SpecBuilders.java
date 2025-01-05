package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {


    public static RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
//                .setBaseUri("https://api.spotify.com")
                .setBaseUri(ConfigLoader.getConfigLoader().getBaseURI())
                .setBasePath(Routes.BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccessTokenRequestSpecification(){
        return new RequestSpecBuilder()
//                .setBaseUri("https://accounts.spotify.com")
                .setBaseUri(ConfigLoader.getConfigLoader().getAccessTokenBaseURI())
                .setBasePath(Routes.API)
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecification(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
