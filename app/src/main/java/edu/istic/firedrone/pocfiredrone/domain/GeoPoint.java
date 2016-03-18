package edu.istic.firedrone.pocfiredrone.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by btessiau on 18/03/16.
 */
public class GeoPoint {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public GeoPoint() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
