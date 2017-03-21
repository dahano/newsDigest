package com.example.ofirdahan.newsdigest.Models;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ofirdahan.newsdigest.R;


public class WebViewActivity extends Activity{

    private static final String TAG = WebViewActivity.class.getSimpleName();

    private WebView mWebView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);
        mWebView = (WebView) findViewById(R.id.web_view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(mWebView.canGoBack()){
                        mWebView.goBack();
                    }else{
                        Log.e(TAG, "User was unable to go back ");
                        super.onKeyDown(keyCode, event);
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
