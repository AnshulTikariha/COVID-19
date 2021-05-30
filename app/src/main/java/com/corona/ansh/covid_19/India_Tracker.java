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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class India_Tracker extends AppCompatActivity {

    TextView txt_confirmed, txt_recovered, txt_active, txt_deaths, txt_lastUpdate;
    ProgressBar progressBar;
    LinearLayout result_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india__tracker);
        getSupportActionBar().hide();

        txt_confirmed = findViewById(R.id.txt_confirmed);
        txt_recovered = findViewById(R.id.txt_recovered);
        txt_active = findViewById(R.id.txt_active);
        txt_deaths = findViewById(R.id.txt_deaths);
        txt_lastUpdate = findViewById(R.id.txt_last_update);


        progressBar = findViewById(R.id.progressbar);
        result_layout = findViewById(R.id.result_layout);

        progressBar.setVisibility(View.VISIBLE);
        result_layout.setVisibility(View.GONE);

        CheckConnection();
    }

    public void CheckConnection() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (null == networkInfo) {
            progressBar.setVisibility(View.GONE);
            result_layout.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://corona-virus-world-and-india-data.p.rapidapi.com/api_india")
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

                        India_Tracker.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  responce_txt.setText(myResponse);
                                //     Log.d("DEBUG",myResponse);
                                progressBar.setVisibility(View.GONE);
                                result_layout.setVisibility(View.VISIBLE);


                                String active, confirmed, deaths, recovered, lastupdatedtime;
                                String CG_active, CG_confirmed, CG_deaths, CG_recovered, CG_lastupdatedtime;
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(myResponse);
                                    String data = jsonObject.getString("total_values");
                                    String state_wise = jsonObject.getString("state_wise");

                                    jsonObject = new JSONObject(data);
                                    active = jsonObject.getString("active");
                                    confirmed = jsonObject.getString("confirmed");
                                    deaths = jsonObject.getString("deaths");
                                    recovered = jsonObject.getString("recovered");
                                    lastupdatedtime = jsonObject.getString("lastupdatedtime");

                                    txt_active.setText(active);
                                    txt_confirmed.setText(confirmed);
                                    txt_deaths.setText(deaths);
                                    txt_recovered.setText(recovered);
                                    txt_lastUpdate.setText(lastupdatedtime);

                                    //for state data

                                    //extracting data array from json string
                                    JSONArray jsonArray = new JSONArray("[" + state_wise + "]");
                                    int length = jsonArray.length();
                                    for (int i = 0; i < length; i++) {
                                        JSONObject json_array = jsonArray.optJSONObject(0);
                                        Iterator<?> keys = json_array.keys();
                                        while (keys.hasNext()) {
                                            String key = (String) keys.next();
                                            System.out.println("Key: " + key);
                                            System.out.println("Value: " + json_array.get(key));
                                        }
                                    }
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
