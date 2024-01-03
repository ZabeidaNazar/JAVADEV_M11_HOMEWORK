package com.homework.java;

import com.homework.java.props.PropertyReader;
import org.flywaydb.core.Flyway;

import java.util.Properties;

public class FlywayUtil {
    public static void flywayMigration() {
        Properties properties = PropertyReader.getProperties("hibernate.properties");
        Flyway flyway = Flyway.configure()
                .dataSource(properties.getProperty("hibernate.connection.url"),
                        properties.getProperty("hibernate.connection.username"),
                        properties.getProperty("hibernate.connection.password"))
                .load();
        flyway.migrate();
    }
}
