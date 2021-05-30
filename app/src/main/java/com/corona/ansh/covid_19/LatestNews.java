package com.corona.ansh.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LatestNews extends AppCompatActivity {

    ProgressBar progressBar;
    private WebView myWebView;
    private static final String SiteURL = "https://www.mygov.in/covid-19";
    private SwipeRefreshLayout refreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        getSupportActionBar().hide();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        //init variables
        myWebView = findViewById(R.id.webView);
        myWebView.setVisibility(View.GONE);
        refreshLayout = findViewById(R.id.swipeRefreshLayout);

        //load url
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //force links open in webview only
        myWebView.setWebViewClient(new CustomWebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());

        //load url
        myWebView.loadUrl(SiteURL);
        //refresh url
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myWebView.loadUrl(SiteURL);
            }
        });
    }
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            myWebView.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            refreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            myWebView.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()){
            myWebView.goBack();
            finish();
        }
        super.onBackPressed();
    }

}