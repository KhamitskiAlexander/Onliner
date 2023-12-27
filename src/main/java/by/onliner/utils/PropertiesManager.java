package by.onliner.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private String propertyFilePath;
    private Properties prop;

    public PropertiesManager() {
        propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        loadData();
    }

    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName) {
        return prop.getProperty(propertyName);
    }
}
