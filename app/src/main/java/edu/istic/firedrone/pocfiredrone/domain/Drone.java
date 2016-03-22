package edu.istic.firedrone.pocfiredrone.domain;

/**
 * Created by btessiau on 18/03/16.
 *
 * Represents a drone
 */
public class Drone {
    private String id;
    private GeoPoint geoPoint;
    private String status;

    public Drone() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
