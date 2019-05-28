package com.example.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.weather.modelo.Respuesta;

public class Results_Activity extends AppCompatActivity {

    TextView city, Tmin, Tmax, Note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        city =(TextView)findViewById(R.id.txtCity);
        Tmin =(TextView)findViewById(R.id.txtTempMin);
        Tmax =(TextView)findViewById(R.id.txtTempMax);
        Note =(TextView)findViewById(R.id.txtNote);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            Respuesta r = (Respuesta) getIntent().getSerializableExtra("CITY");


            double Cmin = r.getMain().getTemp_min() - 273;
            double Cmax = r.getMain().getTemp_max() - 273;
            city.setText(r.getName() + ", " + r.getSys().getCountry());
            Tmin.setText(getResources().getString(R.string.Tmin) + r.getMain().getTemp_min().toString() + "K  -  "
                    + Cmin + "C°");
            Tmax.setText(getResources().getString(R.string.TMax) + r.getMain().getTemp_min().toString() + "K  -  "
                + Cmax + "C°");
            Note.setText(getResources().getString(R.string.Hum) + r.getMain().getHumidity().toString());


    }

}
