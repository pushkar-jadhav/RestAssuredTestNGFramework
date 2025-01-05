package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilders.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;
    public synchronized static String getToken(){

        try{
            if(access_token==null||Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing Token...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiresInDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiresInDurationInSeconds-300);
            }
            else {
                System.out.println("Token is still valid..");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to renew access token");
        }
        return access_token;
    }

    private static Response renewToken(){

        HashMap<String, String> formParameters = new HashMap<>();
        formParameters.put("grant_type", ConfigLoader.getConfigLoader().getGrantType());
        formParameters.put("refresh_token", ConfigLoader.getConfigLoader().getRefreshToken());
        formParameters.put("client_id",ConfigLoader.getConfigLoader().getClientId());
        formParameters.put("client_secret",ConfigLoader.getConfigLoader().getClientSecret());
        Response response = RestResource.postAccessToken(formParameters);
        if(response.statusCode()!=200)
        {
            throw new RuntimeException("Failed to renew access token");
        }
        return response;
    }
}
