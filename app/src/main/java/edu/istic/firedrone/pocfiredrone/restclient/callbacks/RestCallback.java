package edu.istic.firedrone.pocfiredrone.restclient.callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxime on 22/03/2016.
 */
public abstract class RestCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onError(call);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(call);
    }

    public abstract void onError(Call<T> call);
    public abstract void onSuccess(Call<T> call, Response<T> response);
}
