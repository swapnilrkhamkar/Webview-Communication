package com.example.webview;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Swapnil on 03/03/2020.
 */

public class WebViewUtils {

    static void callJavaScriptFunction(final WebView webView, final String script) {
        if (webView == null) {
            return;
        }
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(script, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {

                        }
                    });
                } else {
                    //  String script = displayAlert('Hello World' , true);
                    webView.loadUrl("javascript:" + script);
                }
            }
        });
    }

    @NonNull
    static String formatScript(@NonNull final String function,
                               @Nullable final Object... params) {

        final StringBuilder builder = new StringBuilder(function).append('(');
        final int length = params.length;
        for (int i = 0; i < params.length; ++i) {
            if (params[i] instanceof String) {
                builder.append("\'");
            }
            builder.append(params[i]);
            if (params[i] instanceof String) {
                builder.append("\'");
            }
            if (i != length - 1) {
                builder.append(",");
            }
        }

        builder.append(')');
        return builder.toString();
    }
}
