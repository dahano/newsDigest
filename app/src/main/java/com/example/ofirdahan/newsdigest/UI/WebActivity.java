package com.example.ofirdahan.newsdigest.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ofirdahan.newsdigest.R;

/**
 * Created by ofirdahan on 3/21/17.
 */

public class WebActivity extends Activity{
    private WebView mWebView;
    public Bundle getBundle = null;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.web_view_activity);
        getBundle = this.getIntent().getExtras();
        String url = getBundle.getString("url");

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient());
        if (getIntent().getExtras().getString("url") != null) {
            mWebView.loadUrl(url);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
