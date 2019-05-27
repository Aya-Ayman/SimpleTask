package com.yelloco.sampletask.pizza_activity.model;

import java.io.Serializable;

/**
 * Created by Aya on 5/25/2019.
 */

public class Pizza implements Serializable {

    private int id;
    private String name;
    private String description;
    private String  cost;

    public Pizza() {

    }
    public Pizza(String name, String description, String cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Pizza(int id, String name, String description, String cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
