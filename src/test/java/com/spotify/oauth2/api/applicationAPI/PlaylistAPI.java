package com.spotify.oauth2.api.applicationAPI;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilders.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilders.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class PlaylistAPI {
    @Step
    public static Response post(Playlist playlistPayload)
    {
        return RestResource.post(TokenManager.getToken(), Routes.USERS+"/"+ ConfigLoader.getConfigLoader().getUserId()
                +Routes.PLAYLISTS, playlistPayload);
    }

    @Step
    public static Response post(Playlist playlistPayload, String token)
    {
        return RestResource.post(token, Routes.USERS+"/"+ConfigLoader.getConfigLoader().getUserId()
                +Routes.PLAYLISTS, playlistPayload);
    }

    @Step
    public static Response get(String playlistId){
        return RestResource.get(Routes.PLAYLISTS+"/"+playlistId, TokenManager.getToken());
    }

    @Step
    public static Response put(Playlist playlist, String playlistId){
        return RestResource.put(playlist, Routes.PLAYLISTS+"/"+playlistId, TokenManager.getToken());
    }
}
