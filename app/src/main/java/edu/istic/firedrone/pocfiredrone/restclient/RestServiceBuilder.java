package edu.istic.firedrone.pocfiredrone.restclient;


import android.content.Context;

import com.orhanobut.wasp.Wasp;

import java.net.CookiePolicy;

/**
 * Created by maxime on 15/03/2016.
 *
 * Builds a service
 */
public class RestServiceBuilder {

    /**
     * Builds a rest service with the library
     *
     * @param context App context
     * @return The rest service
     */
    public static RestService build(Context context){
        RestService service = new Wasp.Builder(context)
                .setEndpoint("http://46.101.30.252")
                .trustCertificates()                         // Optional
                .enableCookies(CookiePolicy.ACCEPT_ALL)      // Optional
                .build()
                .create(RestService.class);

        return service;
    }

}
