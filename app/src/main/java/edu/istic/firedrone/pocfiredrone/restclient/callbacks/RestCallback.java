package edu.istic.firedrone.pocfiredrone.restclient.callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxime on 22/03/2016.
 */
public abstract class RestCallback<T> implements Callback<T> {

    protected Call<T> call = null;
    protected Throwable throwable = null;
    protected Response<T> response = null;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.call = call;
        this.response = response;

        if(response.isSuccessful()) {
            onSuccess();
        } else {
            onError();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        this.call = call;
        this.throwable = t;

        onError();
    }

    public abstract void onError();
    public abstract void onSuccess();
}
