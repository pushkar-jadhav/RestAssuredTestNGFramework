package com.spotify.oauth2.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath){
        Properties properties = new Properties();
        FileInputStream fis;
        try{
            fis = new FileInputStream(filePath);
            try{
                properties.load(fis);
            }
            catch (IOException ex){
                ex.printStackTrace();
                throw new RuntimeException("Failed to load properties file at "+filePath);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw  new RuntimeException("Properties file not found at "+filePath);
        }
        return properties;
    }
}
