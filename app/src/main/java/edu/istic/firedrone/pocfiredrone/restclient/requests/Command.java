package edu.istic.firedrone.pocfiredrone.restclient.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maxime on 15/03/2016.
 */
public abstract class Command {
    @SerializedName("key")
    protected String key;

    @SerializedName("geoPoint")
    GeoPosition geoPosition;

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
