package com.corona.ansh.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.corona.ansh.covid_19.Adapter.CountriesStatAdapter;
import com.corona.ansh.covid_19.Model.countries_stat;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class World_Tracker extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout result_layout;
    ProgressBar progressBar;
    TextView txt_active,txt_confirm,txt_recovered,txt_deaths,txt_update_time;
    com.corona.ansh.covid_19.Adapter.CountriesStatAdapter CountriesStatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world__tracker);

        getSupportActionBar().hide();
        progressBar = findViewById(R.id.progressbar);
        result_layout = findViewById(R.id.result_layout);
        txt_active = findViewById(R.id.txt_active);
        txt_confirm = findViewById(R.id.txt_confirmed);
        txt_recovered = findViewById(R.id.txt_recovered);
        txt_deaths = findViewById(R.id.txt_deaths);
        txt_update_time = findViewById(R.id.txt_last_update);

        progressBar.setVisibility(View.VISIBLE);
        result_layout.setVisibility(View.GONE);

        recyclerView=findViewById(R.id.countries_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CheckConnection();
    }

    public void CheckConnection()
    {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (null == networkInfo)
        {
            progressBar.setVisibility(View.GONE);
            result_layout.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        else {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://corona-virus-world-and-india-data.p.rapidapi.com/api")
                    .get()
                    .addHeader("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "5ec3d74366msh163bb8360268d41p13ae97jsn753b3deb8745")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    result_layout.setVisibility(View.GONE);
                }

                @Override
                public void onResponse(@NotNull final Call call, @NotNull Response response) throws IOException {

                    if (response.isSuccessful()) {
                        final String myResponse = response.body().string();

                        World_Tracker.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  responce_txt.setText(myResponse);
                                Log.d("DEBUG",myResponse);
                                progressBar.setVisibility(View.GONE);
                                result_layout.setVisibility(View.VISIBLE);

                                String active,confirmed,deaths,recovered,lastupdatedtime;
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(myResponse);
                                    String data = jsonObject.getString("countries_stat");
                                    Log.d("DEBUG2",data);
                                    String world_total = jsonObject.getString("world_total");
                                    jsonObject = new JSONObject(world_total);
                                    active = jsonObject.getString("active_cases");
                                    confirmed = jsonObject.getString("total_cases");
                                    deaths = jsonObject.getString("total_deaths");
                                    recovered = jsonObject.getString("total_recovered");
                                    lastupdatedtime = jsonObject.getString("statistic_taken_at");
                                    Log.d("DEBUG3",world_total);
                                    Log.d("DEBUG3",active);
                                    txt_active.setText(active);
                                    txt_confirm.setText(confirmed);
                                    txt_deaths.setText(deaths);
                                    txt_recovered.setText(recovered);
                                    txt_update_time.setText(lastupdatedtime);

                                    Gson gson = new Gson();
                                    countries_stat[] countriesStats = gson.fromJson(data, countries_stat[].class);
                                    recyclerView.setAdapter(new CountriesStatAdapter(getApplicationContext(), countriesStats));
                                    CountriesStatAdapter = new CountriesStatAdapter(World_Tracker.this, countriesStats);
                                    recyclerView.setAdapter(CountriesStatAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }
            });
        }

    }

    public void backbutton(View view) {
        super.onBackPressed();
    }
}
