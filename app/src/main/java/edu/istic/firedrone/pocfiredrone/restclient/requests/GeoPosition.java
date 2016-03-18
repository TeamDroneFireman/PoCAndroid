package edu.istic.firedrone.pocfiredrone.restclient.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by btessiau on 18/03/16.
 */
public class GeoPosition {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public GeoPosition() {
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
