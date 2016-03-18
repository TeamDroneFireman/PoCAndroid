package edu.istic.firedrone.pocfiredrone.domain;

/**
 * Created by btessiau on 18/03/16.
 */
public class Drone {
    protected double longitude;
    protected double latitude;

    public Drone(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
