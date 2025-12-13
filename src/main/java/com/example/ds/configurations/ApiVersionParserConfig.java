package com.example.ds.configurations;

public class ApiVersionParserConfig implements org.springframework.web.accept.ApiVersionParser {

    @Override
    public Comparable parseVersion(String version) {

        // Removes the "v" prefix changing v1 to just 1
        if(version.startsWith("v") || version.startsWith("V")) {
            version = version.substring(1);
        }

        // Appends minor version if missing; 1 becomes 1.0
        if (!version.contains(".")) {
            version = version + ".0";
        }

        return version;
    }

}
