package edu.istic.firedrone.pocfiredrone.restclient;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.http.Body;
import com.orhanobut.wasp.http.POST;
import com.orhanobut.wasp.http.Path;

import edu.istic.firedrone.pocfiredrone.restclient.requests.SendDroneBody;

/**
 * Created by maxime on 15/03/2016.
 */
public interface RestService {
    @POST("/senddrone")
    void sendDrone(@Body SendDroneBody body, Callback callback);
}
