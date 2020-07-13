package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangshuai.gitlearingdemo.R;

public class WebPageActivity extends AppCompatActivity {

    protected WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);
        mWebView = findViewById(R.id.webView);
        initWebView();
    }

    protected void initWebView() {
    }
}
