package com.github.rmannibucau.ds.config.example;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Jars;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testng.PropertiesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@RunWith(ApplicationComposer.class)
public class ConfigTest {
    @Inject
    private MyInjectedAppConfig config;

    @Test
    public void checkFilterAndSourceWereUsed() {
        assertEquals("test", config.getValue());
    }

    @Configuration
    public Properties config() {
        return new PropertiesBuilder()
            .p("openejb.extract.configuration", "false")
            .p("openejb.base", "src/test")
            .build();
    }

    @Module
    @Jars(value = "deltaspike-", excludeDefaults = true)
    @Classes(cdi = true, innerClassesAsBean = true)
    public WebApp app() {
        return new WebApp();
    }

    public static class MyInjectedAppConfig {
        @Inject
        @ConfigProperty(name = "my.password")
        private String value;

        public String getValue() {
            return value;
        }
    }
}
