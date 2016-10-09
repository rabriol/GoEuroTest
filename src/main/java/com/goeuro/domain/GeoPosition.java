package com.goeuro.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by brito on 10/8/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition {
    private String latitude;
    private String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GeoPosition{");
        sb.append("latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
