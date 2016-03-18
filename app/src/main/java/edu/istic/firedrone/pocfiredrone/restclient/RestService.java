package edu.istic.firedrone.pocfiredrone.restclient;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.http.Body;
import com.orhanobut.wasp.http.GET;
import com.orhanobut.wasp.http.POST;

import java.util.List;

import edu.istic.firedrone.pocfiredrone.restclient.requests.CommandDiscover;
import edu.istic.firedrone.pocfiredrone.restclient.responses.GetDronesResponse;

/**
 * Created by maxime on 15/03/2016.
 */
public interface RestService {
    @POST("/command")
    void sendDrone(@Body CommandDiscover body, Callback callback);

    @GET("/drone")
    List<GetDronesResponse> getDrones(Callback callback);
}
