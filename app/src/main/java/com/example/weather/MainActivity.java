package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


import com.example.weather.modelo.Cities;
import com.example.weather.modelo.Respuesta;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lst;
    private ArrayList<City> cities;
    private CityAdapter adapter;
    private LinearLayoutManager llm;
    private ArrayList<Cities> cit;
    private Button bta;
    private AutoCompleteTextView aut;
    private Cities ciudadElegida;
    private FloatingActionButton fab;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        aut = (AutoCompleteTextView)findViewById(R.id.TxtUser);
        aut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cities select = (Cities) parent.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), "Ciudad: " + select.getName() + " " + select.getCountry(), Toast.LENGTH_LONG).show();
                ciudadElegida = select;
            }
        });

        lst = (RecyclerView)findViewById(R.id.lstCities);
        cities = new ArrayList<>();
        bta = (Button)findViewById(R.id.Bt_actualizate);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        calendarView = (CalendarView)findViewById(R.id.calendarView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ciudadElegida != null){
                    long ab = 86400000 + calendarView.getDate();
                    GetData(ciudadElegida.getId());
                }
            }
        });

        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loadCities();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        cities.add(new City("Bangkok, Thailand",getResources().getString(R.string.ActualT) + "30°", getResources().getString(R.string.Visiting) + "20.05 Millions"));
        cities.add(new City("London, UK",getResources().getString(R.string.ActualT) + "14°", getResources().getString(R.string.Visiting) + "19.83 Millions"));
        cities.add(new City("Paris, France",getResources().getString(R.string.ActualT) + "16°", getResources().getString(R.string.Visiting) + "17.44 Millions"));
        cities.add(new City("Dubai, Arab Emirates",getResources().getString(R.string.ActualT) + "34°", getResources().getString(R.string.Visiting) + "15.79 Millions"));
        cities.add(new City("Singapur, Singapur",getResources().getString(R.string.ActualT) + "29°", getResources().getString(R.string.Visiting) + "13.91 Millions"));
        cities.add(new City("Nueva York, USA",getResources().getString(R.string.ActualT) + "24°", getResources().getString(R.string.Visiting) + "13.13 Millions"));
        cities.add(new City("Kuala Lumpur, Malasia",getResources().getString(R.string.ActualT) + "28°", getResources().getString(R.string.Visiting) + "12.58 Millions"));
        cities.add(new City("Tokyo, Japan",getResources().getString(R.string.ActualT) + "24°", getResources().getString(R.string.Visiting) + "11.93 Millions"));
        cities.add(new City("Estambul, Turkey",getResources().getString(R.string.ActualT) + "19°", getResources().getString(R.string.Visiting) + "10.70 Millions"));
        cities.add(new City("Seul, South Korea",getResources().getString(R.string.ActualT) + "35°", getResources().getString(R.string.Visiting) + "9.54 Millions"));

        adapter = new CityAdapter(MainActivity.this, cities);
        llm = new LinearLayoutManager(MainActivity.this);
        lst.setLayoutManager(llm);
        lst.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("cities");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren() ){

                    String city = ds.child("city").getValue().toString();
                    String temperature = ds.child("temperature").toString();
                    String note = ds.child("note").toString();

                    cities.add(new City(city,temperature,note));

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadCities() throws IOException {

        InputStream is = getResources().openRawResource(R.raw.citylist);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {

        } finally {
            is.close();
        }

        String jsonString = writer.toString();

        final Gson gson = new Gson();
        cit = gson.fromJson(jsonString, new TypeToken<List<Cities>>(){}.getType());
        ArrayAdapter<Cities> a = new ArrayAdapter<Cities>(getApplicationContext(), android.R.layout.simple_list_item_1,cit);
        aut.setAdapter(a);

    }

    private void GetData(int City){
        final Gson g = new Gson();
        String x = "d594165f2026dbcb4604c51cfc32893a";
        Retrofit ret = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build();
        ret.create(Prueba.class).historical(City, x).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    try {
                        String x = response.body().string();
                        Respuesta A = g.fromJson(x, Respuesta.class);
                        A.getBase();

                        Intent intent = new Intent(MainActivity.this, Results_Activity.class);
                        intent.putExtra("CITY", A);
                        startActivity(intent);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prueba() {
        final Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build();
        retrofit.create(Prueba.class).prueba().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    try {
                        String r = response.body().string();
                        Respuesta respuesta = gson.fromJson(r, Respuesta.class);
                        respuesta.getBase();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
