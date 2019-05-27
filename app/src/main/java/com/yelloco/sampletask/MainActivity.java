package com.yelloco.sampletask;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.yelloco.sampletask.pizza_activity.interfaces.PizzaViewInterface;
import com.yelloco.sampletask.pizza_activity.model.Pizza;
import com.yelloco.sampletask.pizza_activity.presenter.PizzaPresenter;

import java.sql.Timestamp;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PizzaViewInterface {

    ArrayList<Pizza> pizzas;
    RecyclerView recyclerView;
    PizzaAdapter pizzaAdapter;
    ProgressDialog progressDialog;
    ImageButton sync_button;
    TextView time;
    boolean firstLoad = true;
    PizzaPresenter pizzaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AndroidNetworking.initialize(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Data is being fetched ...");

        pizzaPresenter = new PizzaPresenter(this, this);
        sync_button = findViewById(R.id.sync_button);
        time = findViewById(R.id.time);

        pizzas = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.pizza_recycler_view);
        pizzaAdapter = new PizzaAdapter(this, pizzas);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pizzaAdapter);

        if (firstLoad) {
            progressDialog.show();
            getPizzaList(true);
        }
        sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                getPizzaList(false);
            }
        });

    }

    @Override
    public void getPizzaList(boolean firstTime) {

        pizzaPresenter.getPizzaList(firstTime);
    }

    @Override
    public void returnPizzaList(ArrayList<Pizza> pizzas, Timestamp ts) {

        firstLoad = false;

        progressDialog.dismiss();
        if (pizzas.size() != 0) {
            time.setText("Last Update Time" + "\n" + ts);
            this.pizzas.clear();
            this.pizzas.addAll(pizzas);
            pizzaAdapter.notifyDataSetChanged();
        }
    }
}
