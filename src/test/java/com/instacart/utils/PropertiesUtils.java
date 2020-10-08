package com.instacart.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesUtils {
    private static final String DEFAULT_TEST_PROFILE = "profile1";
    private static final Properties prop = new Properties();

    public static void initPropertiesFromResources(String filename) {
        filename = (null == filename) ? DEFAULT_TEST_PROFILE : filename;
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream(filename + ".properties")) {

            //load a properties file from  path, inside static method
            prop.load(input);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(final String property) {

        PropertiesUtils.initPropertiesFromResources(System.getProperty("profile"));
        return StringUtils.defaultString(prop.getProperty(property));
    }

    public static List<String> getProperties(List<String> keys) {
        List<String> values = new ArrayList<>();
        keys.toArray(new String[keys.size()]);

        for (String key : keys) {
            values.add(getProperty(key));
        }

        return values;
    }

    public static void setProperty(final String property, final String value) {
        prop.setProperty(property, value);
    }

    public static boolean getBoolean(final String property) {
        return Boolean.valueOf(StringUtils.defaultString(prop.getProperty(property)));
    }
}
