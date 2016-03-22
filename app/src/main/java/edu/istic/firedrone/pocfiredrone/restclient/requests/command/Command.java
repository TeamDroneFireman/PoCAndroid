package edu.istic.firedrone.pocfiredrone.restclient.requests.command;

import com.google.gson.annotations.SerializedName;

import edu.istic.firedrone.pocfiredrone.domain.GeoPoint;

/**
 * Created by maxime on 15/03/2016.
 */
public abstract class Command {
    @SerializedName("key")
    protected String key;

    @SerializedName("geopoint")
    GeoPoint geoPoint;

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
