package edu.istic.firedrone.pocfiredrone.restclient;


import android.content.Context;

import com.orhanobut.wasp.Wasp;

import java.net.CookiePolicy;

/**
 * Created by maxime on 15/03/2016.
 */
public class RestServiceBuilder {

    public static RestService build(Context context){
        RestService service = new Wasp.Builder(context)
                .setEndpoint("http://lamereabaptiste.com")
                .trustCertificates()                         // Optional
                .enableCookies(CookiePolicy.ACCEPT_ALL)      // Optional
                .build()
                .create(RestService.class);

        return service;
    }

}
