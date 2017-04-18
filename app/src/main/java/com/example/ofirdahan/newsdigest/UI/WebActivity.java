package com.example.ofirdahan.newsdigest.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ofirdahan.newsdigest.R;

/**
 * Created by ofirdahan on 3/21/17.
 */

public class WebActivity extends Activity{
    private static final String URL = "url";
    private WebView mWebView;
    public Bundle getBundle = null;

    public static Intent getWebActivityIntent(Context context, String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        final Intent intent = new Intent(context, WebActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.web_view_activity);
        getBundle = this.getIntent().getExtras();
        String url = getBundle.getString(URL);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (getIntent().getExtras().getString("url") != null) {
            mWebView.loadUrl(url);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    super.onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
