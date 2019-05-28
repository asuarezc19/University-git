package com.example.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private ArrayList<City> cities;

    public CityAdapter(Context context, ArrayList<City> contacts){
        this.cities = contacts;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city,parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position){
        final City c = cities.get(position);
        holder.city.setText(c.getName());
        holder.temperture.setText(c.getTemperture());
        holder.note.setText(c.getNote());
    }

    @Override
    public int getItemCount() {return cities.size();}

    public static class CityViewHolder extends RecyclerView.ViewHolder{

        private TextView city;
        private TextView temperture;
        private TextView note;

        public CityViewHolder(View item){
            super(item);
            city = (TextView)item.findViewById(R.id.txtCity);
            temperture = (TextView)item.findViewById(R.id.txtTemperture);
            note = (TextView)item.findViewById(R.id.txtNote);
        }
    }



}

