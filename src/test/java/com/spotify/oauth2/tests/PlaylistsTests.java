package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.api.applicationAPI.PlaylistAPI;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Epic("Spotify OAUTH")
@Feature("Playlist API")
public class PlaylistsTests extends BaseTest {

    @Story("DSP-123: Create Playlist")
    @TmsLink("12345")
    @Issue("DSP-xxxxx")
    @Description("Create playlist using valid data")
    @Test(description = "Create Playlist With valid data")
    public void validatePlaylistCreated(){

        Playlist playlist = playlistBuilder(FakerUtils.generateName(),
        FakerUtils.generateDescription(),false);
        Response response = PlaylistAPI.post(playlist);
        assertStatusCode(response.statusCode(), StatusCodes.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), playlist);
    }

    @Story("DSP-123: Create Playlist")
    @Description("create playlist without name")
    @Test(description = "Create Playlist without name")
    public void validatePlaylistNotCreatedWithoutName(){

        Playlist playlist = playlistBuilder("",
                FakerUtils.generateDescription(),false);
        Response response = PlaylistAPI.post(playlist);
        assertStatusCode(response.statusCode(), StatusCodes.CODE_400);
        assertErrorEqual(response.as(Error.class), StatusCodes.CODE_400);
    }

    @Story("DSP-123: Create Playlist")
    @Description("create playlist with invalid token")
    @Test(description = "Create Playlist with invalid token")
    public void validatePlaylistNotCreatedWithInvalidToken(){
        String invalidAccessToken = "1234567890qwerty";
        Playlist playlist = playlistBuilder(FakerUtils.generateName(),
                FakerUtils.generateDescription(),false);
        Response response = PlaylistAPI.post(playlist,invalidAccessToken);
        assertStatusCode(response.statusCode(), StatusCodes.CODE_401);
        assertErrorEqual(response.as(Error.class), StatusCodes.CODE_401);
    }

    @Story("DSP-122: Get Playlist")
    @Description("get playlist by playlist id")
    @Test(description = "Get Playlist using playlist id")
    public void validateGetPlaylist(){

        Playlist playlist = playlistBuilder("Playlist 2025",
                "Sample Playlist 1",false);
        Response response = PlaylistAPI.get(DataLoader.getDataLoader().getPlaylistId());
        assertStatusCode(response.statusCode(), StatusCodes.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), playlist);
    }

    @Story("DSP-121: Update Playlist")
    @Description("update playlist by playlist id")
    @Test(description = "Update Playlist using playlist id")
    public void validateUpdatePlaylist(){

        Playlist playlist = playlistBuilder(FakerUtils.generateName(),
                FakerUtils.generateDescription(),false);
        Response response = PlaylistAPI.get(DataLoader.getDataLoader().getUpdatePlaylistId());
        assertStatusCode(response.statusCode(), StatusCodes.CODE_200);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().name(name)
                .description(description)._public(_public).build();
//        Playlist playlist = new Playlist();
//        playlist.setName(name);
//        playlist.setDescription(description);
//        playlist.set_public(_public);
//        return playlist;
    }
    @Step
    public void assertPlaylistEqual(Playlist playlistDeserializedResponse, Playlist playlistRequest){
        assertThat(playlistDeserializedResponse.getDescription(),is(equalTo(playlistRequest.getDescription())));
        assertThat(playlistDeserializedResponse.getName(),is(equalTo(playlistRequest.getName())));
    }
    @Step
    public void assertStatusCode(int actualStatusCode, StatusCodes expectedStatusCode){
        assertThat(actualStatusCode,is(equalTo(expectedStatusCode.code)));
    }

    @Step
    public void assertErrorEqual(Error errorDeserializedResponse, StatusCodes statusCodes){
        assertThat(errorDeserializedResponse.getError().getMessage(),is(equalTo(statusCodes.message)));
        assertThat(errorDeserializedResponse.getError().getStatus(),is(equalTo(statusCodes.code)));
    }
}

