package edu.istic.firedrone.pocfiredrone.restclient.responses;

import com.google.gson.annotations.SerializedName;

import edu.istic.firedrone.pocfiredrone.domain.GeoPoint;

/**
 * Created by btessiau on 18/03/16.
 */
public class DroneGetResponse {
    @SerializedName("id")
    protected Long id;
    @SerializedName("geopoint")
    GeoPoint geoPoint;
    @SerializedName("status")
    String status;

    public DroneGetResponse() {
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