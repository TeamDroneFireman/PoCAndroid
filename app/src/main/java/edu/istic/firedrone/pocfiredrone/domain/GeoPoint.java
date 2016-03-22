package edu.istic.firedrone.pocfiredrone.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by btessiau on 18/03/16.
 */
public class GeoPoint {

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lng")
    private double longitude;

    public GeoPoint() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
