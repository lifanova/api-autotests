package ru.alfabank.props;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesTest extends BaseTest {
    @Test
    public void readProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String url = System.getProperty("url");
    }
}
