package com.dikamjitborah.hobarb.superqrscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Result extends AppCompatActivity {
WebView webView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String url, url_default;
        Intent intent = getIntent();
        url_default = "https://www.google.co.in/search?q=";

        url = (String) intent.getExtras().getString("URL", url_default);

        textView = findViewById(R.id.text_view_result);
        textView.setText(url);
        webView = findViewById(R.id.webView_result);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url_default  + url);
    }
}