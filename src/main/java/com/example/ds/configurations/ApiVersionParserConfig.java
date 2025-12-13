package com.example.ds.configurations;

/**
 * Used to convert a given API version into the expected version.
 * If the version starts with a 'v' or 'V', it'll remove it as we don't expect alphabetic values
 * If the version is missing a "minor" version, we append it
 *  Examples:
 *      /api/2/animal -> /api/2.0/animal
 *      /api/2.0/animal -> /api/2.0/animal
 *      /api/v2/animal -> /api/2.0/animal
 *      /api/V2/animal -> /api/2.0/animal
 */
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
