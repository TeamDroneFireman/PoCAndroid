package edu.istic.firedrone.pocfiredrone.domain;

/**
 * Created by btessiau on 18/03/16.
 *
 * Represents a drone
 */
public class Drone {
    private Long id;
    private GeoPoint geoPoint;
    private String status;

    public Drone(Long id,GeoPoint geoPoint,String status) {
        this.id = id;
        this.geoPoint = geoPoint;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
