package com.example.webview;

import android.content.Context;
import android.os.CountDownTimer;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by Swapnil on 03/03/2020.
 */


public class WebViewJsInterface {

    private WebView webView;
    private Context context;
    private long counter = 0;

    WebViewJsInterface(Context context, WebView webView) {
        this.webView = webView;
        this.context = context;
    }

    @JavascriptInterface
    public void setWebViewTextCallback() {

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                counter = l / 1000;

                String script = WebViewUtils.formatScript("setText", "This is a text from Android which is set " +
                        "in the html page." + counter);

                WebViewUtils.callJavaScriptFunction(webView, script);

            }

            @Override
            public void onFinish() {

            }
        }.start();


    }
}
