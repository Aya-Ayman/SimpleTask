package com.yelloco.sampletask.pizza_activity.interfaces;

import com.yelloco.sampletask.pizza_activity.model.Pizza;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Aya on 5/27/2019.
 */

public interface PizzaPresenterInterface {
    public void getPizzaList(boolean firstTime);
    public void returnPizzaList(String response,Timestamp ts);
}
