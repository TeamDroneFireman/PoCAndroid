package edu.istic.firedrone.pocfiredrone.restclient.responses;

import java.util.ArrayList;
import java.util.List;

import edu.istic.firedrone.pocfiredrone.domain.Drone;

/**
 * Created by btessiau on 18/03/16.
 */
public class GetDronesResponse {
    protected List<Drone> drones;

    public GetDronesResponse(double longitude, double latitude) {
        this.drones = new ArrayList<>();
    }

    public List<Drone> getDrones() {
        return this.drones;
    }
}
