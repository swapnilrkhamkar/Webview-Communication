package com.example.webview;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Swapnil on 03/03/2020.
 */

public class MainActivity extends AppCompatActivity {

    private Button button;
    private WebView webView;
    private WebViewJsInterface webViewJsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        initWebView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewJsInterface.setWebViewTextCallback();
            }
        });
    }

    private void initWebView() {
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setUseWideViewPort(false);
        webViewJsInterface = new WebViewJsInterface(this, webView);
        webView.addJavascriptInterface(webViewJsInterface, "handler");
        webView.loadUrl("file:///android_asset/main.html");
    }
}
