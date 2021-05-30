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
import android.widget.Toast;

import com.corona.ansh.covid_19.Adapter.DailyTimelineAdapter;
import com.corona.ansh.covid_19.Model.DailyTimelineModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;

public class Daily_Timeline extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout result_layout;
    com.corona.ansh.covid_19.Adapter.DailyTimelineAdapter DailyTimelineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__timeline);
        getSupportActionBar().hide();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        result_layout = findViewById(R.id.result_layout);
        result_layout.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.list_recycler);
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
                    .url("https://corona-virus-world-and-india-data.p.rapidapi.com/api_india_timeline")
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
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String myResponse = response.body().string();

                        Daily_Timeline.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  responce_txt.setText(myResponse);
                                Log.d("DEBUG", myResponse);
                                progressBar.setVisibility(View.GONE);
                                result_layout.setVisibility(View.VISIBLE);

                                Gson gson = new Gson();
                                DailyTimelineModel[] models = gson.fromJson(myResponse, DailyTimelineModel[].class);
                                recyclerView.setAdapter(new DailyTimelineAdapter(getApplicationContext(), models));
                                DailyTimelineAdapter = new DailyTimelineAdapter(Daily_Timeline.this, models);
                                recyclerView.setAdapter(DailyTimelineAdapter);



                            }
                        });

                    }
//                else {
//                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
//                }
                }
            });
        }

    }

    public void backbutton(View view) {
        super.onBackPressed();
    }
}
