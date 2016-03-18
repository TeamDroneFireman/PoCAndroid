package edu.istic.firedrone.pocfiredrone.restclient.responses;

import java.util.ArrayList;
import java.util.Date;

import edu.istic.firedrone.pocfiredrone.restclient.requests.GeoPosition;

/**
 * Created by btessiau on 18/03/16.
 */
public class GetDronesResponse {
    ArrayList<String> listPictures;
    GeoPosition geoPosition;
    protected Date lastUpdate;
    protected Long id;

    public GetDronesResponse(double longitude, double latitude) {
        this.listPictures = new ArrayList<>();
        this.lastUpdate = new Date();
        this.id = null;
        this.id = null;
        this.geoPosition = new GeoPosition();
        this.geoPosition.setLng(longitude);
        this.geoPosition.setLat(latitude);
    }

    public ArrayList<String> getListPictures() {
        return listPictures;
    }

    public void setListPictures(ArrayList<String> listPictures) {
        this.listPictures = listPictures;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }
}