package com.spotify.oauth2.utils;

import java.io.File;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    String configFilePath = System.getProperty("user.dir")+ File.separator+"src"+ File.separator+"test"
            + File.separator+"resources"+ File.separator+"config.properties";

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader(configFilePath);
    }

    public static ConfigLoader getConfigLoader(){
        if(configLoader==null)
        {
            configLoader = new ConfigLoader();
        }
        return  configLoader;
    }

    public String getClientId(){
        String clientId = properties.getProperty("client_id");
        if(clientId!=null){
            return  clientId;
        }
        else
            throw new RuntimeException("Client ID value is not specified");
    }

    public String getClientSecret(){
        String clientSecret = properties.getProperty("client_secret");
        if(clientSecret!=null){
            return  clientSecret;
        }
        else
            throw new RuntimeException("Client Secret value is not specified");
    }
    public String getGrantType(){
        String grantType = properties.getProperty("grant_type");
        if(grantType!=null){
            return  grantType;
        }
        else
            throw new RuntimeException("Grant type value is not specified");
    }

    public String getRefreshToken(){
        String refreshToken = properties.getProperty("refresh_token");
        if(refreshToken!=null){
            return  refreshToken;
        }
        else
            throw new RuntimeException("Refresh Token value is not specified");
    }
    public String getUserId(){
        String userId = properties.getProperty("user_id");
        if(userId!=null){
            return  userId;
        }
        else
            throw new RuntimeException("User ID value is not specified");
    }

    public String getBaseURI(){
        String environment = System.getProperty("environment");
        String baseUri=null;
        if(environment.equalsIgnoreCase("QA")){
            baseUri = properties.getProperty("QA_BASE_URI");
        } else if (environment.equalsIgnoreCase("UAT")) {
            baseUri = properties.getProperty("UAT_BASE_URI");
        }
        if(baseUri!=null){
            return  baseUri;
        }
        else
            throw new RuntimeException("Base Uri value is not specified");
    }
    public String getAccessTokenBaseURI(){
        String environment = System.getProperty("environment");
        String accessTokenBaseUri=null;
        if(environment.equalsIgnoreCase("QA")){
            accessTokenBaseUri = properties.getProperty("QA_ACCESS_TOKEN_BASE_URI");
        } else if (environment.equalsIgnoreCase("UAT")) {
            accessTokenBaseUri = properties.getProperty("UAT_ACCESS_TOKEN_BASE_URI");
        }
        if(accessTokenBaseUri!=null){
            return  accessTokenBaseUri;
        }
        else
            throw new RuntimeException("Access Token Base URI value is not specified");
    }

}
