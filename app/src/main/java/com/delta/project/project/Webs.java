package com.delta.project.project;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webs extends WebView  {

    public Webs(Context context) {
        super(context);
        this.setWebViewClient(new GoUrl());
        this.setWebChromeClient(new WebChromeClient());
        this.getSettings().setJavaScriptEnabled(true);
        screenSupport();
    }
    public Webs(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        this.setWebViewClient(new GoUrl());
        this.setWebChromeClient(new WebChromeClient());
        this.getSettings().setJavaScriptEnabled(true);
        screenSupport();

    }

    public void screenSupport() {
        this.getSettings().setLoadWithOverviewMode(true);
        this.getSettings().setUseWideViewPort(true);
        this.getSettings().setSupportZoom(true);
        this.getSettings().setBuiltInZoomControls(true);
        this.getSettings().setDisplayZoomControls(true);
        this.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    }
    private class GoUrl extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }




}
