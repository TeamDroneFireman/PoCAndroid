package edu.istic.firedrone.pocfiredrone.restclient.requests;

/**
 * Created by maxime on 15/03/2016.
 */
public class SendDroneBody {
    protected double longitude;
    protected double latitude;

    public SendDroneBody(double longitude, double latitude) {
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
