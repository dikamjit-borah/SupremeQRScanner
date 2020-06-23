package com.dikamjitborah.hobarb.superqrscanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Result extends AppCompatActivity {
WebView webView;
TextView textView;
public ArrayList<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String url, url_default;
        Intent intent = getIntent();
        url_default = "https://www.google.co.in/search?q=";

        url = (String) intent.getExtras().getString("URL", url_default);

        urls = new ArrayList<>();

        readPreferences();
        if(urls == null)
            urls = new ArrayList<>();
        urls.add(url);
        writepreferences();

        textView = findViewById(R.id.text_view_result);
        textView.setText(url);
        webView = findViewById(R.id.webView_result);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url_default  + url);
    }

    private void writepreferences() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(urls);

        SharedPreferences sharedPreferences = getSharedPreferences("HISTORY", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("HISTORY_LIST", jsonString);
        editor.apply();

    }

    private void readPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HISTORY", MODE_PRIVATE);
        String theUrls = sharedPreferences.getString("HISTORY_LIST","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        urls = gson.fromJson(theUrls, type);

    }
}