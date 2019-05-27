package com.yelloco.sampletask.pizza_activity.controller;

import android.content.Context;
import android.util.Log;

import com.yelloco.sampletask.pizza_activity.model.Pizza;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaControllerInterface;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaPresenterInterface;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Aya on 5/27/2019.
 */

public class PizzaController implements PizzaControllerInterface{

    private PizzaPresenterInterface presenter;
    private PizzaApiManager apiManager;
    private Context context;

    public PizzaController(PizzaPresenterInterface presenter, Context context) {
        this.presenter = presenter;
        this.apiManager = new PizzaApiManager(context, this);
        this.context=context;
    }

    public void getPizzaList(boolean firstTime)
    {

        if(!firstTime)
        apiManager.getPizzaList();
        else
        apiManager.getPizzaListFirstTime();
    }

    public void returnPizzaList(String response, Timestamp ts)
    {
        presenter.returnPizzaList(response,ts);
    }
}
