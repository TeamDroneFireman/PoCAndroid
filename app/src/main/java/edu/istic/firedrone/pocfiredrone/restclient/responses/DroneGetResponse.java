package edu.istic.firedrone.pocfiredrone.restclient.responses;

import com.google.gson.annotations.SerializedName;

import edu.istic.firedrone.pocfiredrone.domain.Drone;
import edu.istic.firedrone.pocfiredrone.domain.GeoPoint;

/**
 * Created by btessiau on 18/03/16.
 */
public class DroneGetResponse {

    @SerializedName("stringConnect")
    protected String droneId;

    @SerializedName("geoPosition")
    GeoPoint geoPoint;

    @SerializedName("status")
    String status;

    public DroneGetResponse() {
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
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

    public Drone toDrone() {
        Drone drone = new Drone();
        drone.setId(getDroneId());
        drone.setGeoPoint(getGeoPoint());
        drone.setStatus(getStatus());

        return drone;
    }
}