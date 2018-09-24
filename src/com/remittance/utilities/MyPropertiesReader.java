package com.remittance.utilities;

import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesReader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = MyPropertiesReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String input){
        return properties.getProperty(input);
    }

}
