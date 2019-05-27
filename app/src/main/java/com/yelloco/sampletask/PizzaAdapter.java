package com.yelloco.sampletask;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yelloco.sampletask.pizza_activity.model.Pizza;

import java.util.ArrayList;

/**
 * Created by Aya on 5/25/2019.
 */

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Pizza>pizzas= new ArrayList<>();

    public PizzaAdapter(Context context, ArrayList<Pizza>pizzas) {
        this.context=context;
        this.pizzas=pizzas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description,cost;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_name);
            description = (TextView) view.findViewById(R.id.item_description);
            cost = (TextView) view.findViewById(R.id.item_cost);
        }
    }


    @NonNull
    @Override
    public PizzaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull PizzaAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(pizzas.get(position).getName());
        holder.description.setText(pizzas.get(position).getDescription());
        holder.cost.setText(pizzas.get(position).getCost()+"$");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(context,CaptureActivity.class);
                i.putExtra("item",pizzas.get(position));
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }
}
