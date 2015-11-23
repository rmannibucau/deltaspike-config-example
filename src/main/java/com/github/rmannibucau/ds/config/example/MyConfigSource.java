package com.github.rmannibucau.ds.config.example;

import org.apache.deltaspike.core.impl.config.PropertiesConfigSource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// uses ${base}/conf/my-app.properties as source
public class MyConfigSource extends PropertiesConfigSource {
    public MyConfigSource() {
        super(loadProperties());
    }

    public String getConfigName() {
        return "MyAppConfig";
    }

    private static Properties loadProperties() {
        return new Properties() {{
            final File config = new File(
                System.getProperty("catalina.base", System.getProperty("openejb.base", "")),
                "conf/my-app.properties");
            if (config.isFile()) {
                try (final InputStream is = new BufferedInputStream(new FileInputStream(config))) {
                    load(is);
                } catch (final IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }};
    }
}
