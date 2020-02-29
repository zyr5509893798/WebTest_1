package com.example.webtest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String errorHtml = "";
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorHtml = "<html><body><h1>Page not find!</h1></body></html>";
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("https://home.firefoxchina.cn/");
        Log.i(TAG, "–onCreate–");

        //设置web视图的客户端
        mWebView.setWebViewClient(new MyWebViewClient());

    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.i(TAG, "–onResume()–");
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "-MyWebViewClient->shouldOverrideUrlLoading()–");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG, "-MyWebViewClient->onPageStarted()–");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.i(TAG, "-MyWebViewClient->onPageFinished()–");
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Log.i(TAG, "-MyWebViewClient->onReceivedError()–\n errorCode="+errorCode+" \ndescription="+description+" \nfailingUrl="+failingUrl);
            //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
            view.loadData(errorHtml, "text/html", "UTF-8");

        }
    }

    }
