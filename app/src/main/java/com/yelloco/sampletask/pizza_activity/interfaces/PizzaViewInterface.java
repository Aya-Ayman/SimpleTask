package com.yelloco.sampletask.pizza_activity.interfaces;

import com.yelloco.sampletask.pizza_activity.model.Pizza;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Aya on 5/27/2019.
 */

public interface PizzaViewInterface {

    public void getPizzaList(boolean firstTime);
    public void returnPizzaList(ArrayList<Pizza> pizzas,Timestamp ts);
}
