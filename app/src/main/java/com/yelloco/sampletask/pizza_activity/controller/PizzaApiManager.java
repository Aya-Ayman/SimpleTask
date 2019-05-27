package com.yelloco.sampletask.pizza_activity.controller;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaApiManagerInterface;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaControllerInterface;

import java.sql.Timestamp;

/**
 * Created by Aya on 5/27/2019.
 */

public class PizzaApiManager implements PizzaApiManagerInterface {

    private Context context;
    private PizzaControllerInterface controller;

    public PizzaApiManager(Context context, PizzaControllerInterface controller) {
        this.context = context;
        this.controller = controller;
    }

    public void getPizzaListFirstTime() {

        AndroidNetworking.get("https://api.androidhive.info/pizza/?format=xml")
                .getResponseOnlyFromNetwork()
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Timestamp ts = new Timestamp(System.currentTimeMillis());
                        controller.returnPizzaList(response, ts);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("response", "error" + anError.getResponse());

                    }
                });


    }

    public void getPizzaList() {

        Log.i("response", "from api go");
        AndroidNetworking.get("https://api.androidhive.info/pizza/?format=xml")
                .getResponseOnlyIfCached()
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Timestamp ts = new Timestamp(System.currentTimeMillis());
                        controller.returnPizzaList(response, ts);


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("response", "error" + anError.getResponse());

                    }
                });


    }
}
