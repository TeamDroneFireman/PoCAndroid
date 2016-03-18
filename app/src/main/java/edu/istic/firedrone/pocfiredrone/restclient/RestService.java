package edu.istic.firedrone.pocfiredrone.restclient;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.http.Body;
import com.orhanobut.wasp.http.GET;
import com.orhanobut.wasp.http.POST;

import java.util.List;

import edu.istic.firedrone.pocfiredrone.domain.Drone;
import edu.istic.firedrone.pocfiredrone.restclient.requests.SendDroneBody;

/**
 * Created by maxime on 15/03/2016.
 */
public interface RestService {
    @POST("/senddrone")
    void sendDrone(@Body SendDroneBody body, Callback callback);

    @GET("/getdrones")
    List<Drone> getDrones(Callback callback);
}
