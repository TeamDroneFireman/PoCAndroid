package edu.istic.firedrone.pocfiredrone.restclient.requests;

/**
 * Created by maxime on 15/03/2016.
 */
public class CommandDiscover extends Command{
    private final String commandkey = "DISCOVER";

    public CommandDiscover(double longitude, double latitude) {
        this.key = commandkey;
        this.geoPosition = new GeoPosition();
        this.geoPosition.setLng(longitude);
        this.geoPosition.setLat(latitude);
    }
}
