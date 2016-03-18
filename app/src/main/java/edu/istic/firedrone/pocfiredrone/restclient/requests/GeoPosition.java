package edu.istic.firedrone.pocfiredrone.restclient.requests;

/**
 * Created by btessiau on 18/03/16.
 */
public class GeoPosition {
    private double lat;
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
