package com.homework.java.props;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties(String configPath) {
        Properties appProps = new Properties();
        try {
            appProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return appProps;
    }

    public static String getProperty(String configPath, String name) {
        Properties appProps = getProperties(configPath);
        return appProps.getProperty(name);
    }
}
