package edu.istic.firedrone.pocfiredrone.restclient.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maxime on 15/03/2016.
 */
public class SendDroneBody {
    private final String commandkey = "DISCOVER";

    @SerializedName("key")
    protected String key;

    @SerializedName("geoPoint")
    GeoPosition geoPosition;

    public SendDroneBody(double longitude, double latitude) {
        this.key = commandkey;
        this.geoPosition = new GeoPosition();
        this.geoPosition.setLng(longitude);
        this.geoPosition.setLat(latitude);
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public String getCommandkey() {
        return commandkey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
