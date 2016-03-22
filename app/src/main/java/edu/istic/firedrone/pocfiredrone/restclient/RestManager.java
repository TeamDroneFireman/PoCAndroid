package edu.istic.firedrone.pocfiredrone.restclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maxime on 17/03/2016.
 */
public class RestManager {

    private static final String API_URL = "http://projetm2gla.istic.univ-rennes1.fr:8081/api/";

    private static final Retrofit REST_ADAPTER = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final RestService REST_SERVICE = REST_ADAPTER.create(RestService.class);

    public static RestService getRestService() {
        return REST_SERVICE;
    }
}