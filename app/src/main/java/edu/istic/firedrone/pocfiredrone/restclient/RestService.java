package edu.istic.firedrone.pocfiredrone.restclient;

import java.util.List;

import edu.istic.firedrone.pocfiredrone.restclient.requests.command.Command;
import edu.istic.firedrone.pocfiredrone.restclient.responses.DroneGetResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by maxime on 15/03/2016.
 */
public interface RestService {
    @POST("commands/")
    Call<Void> sendDrone(@Body Command body);

    @GET("drones/")
    Call<List<DroneGetResponse>> getDrones();
}
