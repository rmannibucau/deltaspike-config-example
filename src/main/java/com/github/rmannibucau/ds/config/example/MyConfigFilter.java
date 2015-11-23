package com.github.rmannibucau.ds.config.example;

import org.apache.deltaspike.core.spi.config.ConfigFilter;

public class MyConfigFilter implements ConfigFilter {
    @Override
    public String filterValue(final String key, final String value) {
        return isEncrypted(key) ? decrypt(value) : value;
    }

    @Override // filter passwords and secrets in logs
    public String filterValueForLog(final String key, final String value) {
        return isEncrypted(key) ? "xxxxxx" : value;
    }

    // for the sample just "reverse" the string but in real life use some encryption
    private String decrypt(final String value) {
        return new StringBuilder(value).reverse().toString();
    }

    private boolean isEncrypted(final String key) {
        return key.contains("password") || key.contains("secret");
    }
}
