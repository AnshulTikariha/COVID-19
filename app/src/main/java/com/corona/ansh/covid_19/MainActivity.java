package com.corona.ansh.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView btn_world_case,btn_india_case,btn_daily_timeline,btn_StayHome,btn_AboutCorona,btn_latestNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btn_india_case = findViewById(R.id.btn_india_case);
        btn_world_case = findViewById(R.id.btn_world_case);
        btn_daily_timeline = findViewById(R.id.btn_Timeline);
        btn_StayHome = findViewById(R.id.btn_StayHome);
        btn_AboutCorona = findViewById(R.id.btn_AboutCorona);
        btn_latestNews = findViewById(R.id.btn_latest_news);

        btn_world_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), World_Tracker.class);
                startActivity(i);
            }
        });
        btn_india_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), India_Tracker.class);
                startActivity(i);
            }
        });
        btn_daily_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Daily_Timeline.class);
                startActivity(i);
            }
        });
        btn_StayHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SteyHome.class);
                startActivity(i);
            }
        });
        btn_AboutCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AboutCorona.class);
                startActivity(i);
            }
        });
        btn_latestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LatestNews.class);
                startActivity(i);
            }
        });

    }
}
