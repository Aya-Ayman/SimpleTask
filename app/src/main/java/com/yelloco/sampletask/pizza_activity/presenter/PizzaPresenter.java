package com.yelloco.sampletask.pizza_activity.presenter;

import android.content.Context;
import android.util.Log;

import com.yelloco.sampletask.pizza_activity.controller.XmlParser;
import com.yelloco.sampletask.pizza_activity.model.Pizza;
import com.yelloco.sampletask.pizza_activity.controller.PizzaController;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaControllerInterface;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaPresenterInterface;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaViewInterface;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Aya on 5/27/2019.
 */

public class PizzaPresenter implements PizzaPresenterInterface {

    private PizzaControllerInterface controller;
    private PizzaViewInterface view;
    private Context context;
    private XmlParser xmlParser;


    public PizzaPresenter(PizzaViewInterface view, Context context) {
        this.controller = new PizzaController(this, context);
        this.view = view;
        this.context = context;
        xmlParser = new XmlParser();
    }


    public void getPizzaList(boolean firstTime) {
        controller.getPizzaList(firstTime);
    }

    public void returnPizzaList(String response, Timestamp ts) {
        ArrayList<Pizza> pizzas = xmlParser.parseXml(response);
        ArrayList<Pizza> sortedPizzas = sort(pizzas);
        view.returnPizzaList(sortedPizzas, ts);
    }

    public ArrayList<Pizza> sort(ArrayList<Pizza> pizzas) {
        Collections.sort(pizzas, new Comparator<Pizza>() {
            public int compare(Pizza v1, Pizza v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        return pizzas;
    }
}
