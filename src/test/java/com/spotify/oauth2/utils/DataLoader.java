package com.spotify.oauth2.utils;

import java.io.File;
import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    String dataFilePath = System.getProperty("user.dir")+ File.separator+"src"+ File.separator+"test"
            + File.separator+"resources"+ File.separator+"data.properties";

    private DataLoader(){
        properties = PropertyUtils.propertyLoader(dataFilePath);
    }

    public static DataLoader getDataLoader(){
        if(dataLoader==null)
        {
            dataLoader = new DataLoader();
        }
        return  dataLoader;
    }

    public String getPlaylistId(){
        String playlistId = properties.getProperty("get_playlist_id");
        if(playlistId!=null){
            return  playlistId;
        }
        else
            throw new RuntimeException("Playlist ID value is not specified");
    }
    public String getUpdatePlaylistId(){
        String playlistId = properties.getProperty("update_playlist_id");
        if(playlistId!=null){
            return  playlistId;
        }
        else
            throw new RuntimeException("Playlist ID value is not specified");
    }

}
