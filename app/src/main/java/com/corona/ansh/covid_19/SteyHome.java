package com.corona.ansh.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SteyHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stey_home);
        getSupportActionBar().hide();
    }

    public void backbutton(View view) {
        super.onBackPressed();
    }
}
