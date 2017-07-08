package com.delta.project.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SharingData extends AppCompatActivity {

    private String site;
   private WebView mWebview;
    private RelativeLayout mwebContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_data);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        mwebContainer= (RelativeLayout) findViewById(R.id.content_sharing_data);
        mWebview = new WebView(getApplicationContext());
        mwebContainer.addView(mWebview);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        site = extra.getString("site_name");
//        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
//        mWebview.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                //Make the bar disappear after URL is loaded, and changes string to Loading...
//                setTitle("Loading...");
//                setProgress(progress * 100); //Make the bar disappear after URL is loaded
//
//                // Return the app name after finish loading
//                if (progress == 100)
//                    setTitle(site);
//            }
//
//        });
        mWebview.setWebViewClient(new GoUrl());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setLoadsImagesAutomatically(true);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setSupportZoom(true);
//        mWebview.getSettings().setBuiltInZoomControls(true);
//        mWebview.getSettings().setDisplayZoomControls(true);
        mWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebview.setScrollbarFadingEnabled(true);

        if (site.equals("Play99")) {
            mWebview.loadUrl("http://play99.pk");
        } else if (site.equals("Showtime")) {
            mWebview.loadUrl("http://172.16.75.200:83");
        } else if (site.equals("AmmarCity")) {
            mWebview.loadUrl("http://ammarcity.net/");
        } else if (site.equals("TimeSpan")) {
            mWebview.loadUrl("http://timespan.pk/");
        }


    }

    private class GoUrl extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if (url.contains("videoid")) {
                Toast.makeText(SharingData.this, "Loading Video on Chrome", Toast.LENGTH_SHORT).show();
                Uri web = Uri.parse(view.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, web);
                startActivity(Intent.createChooser(intent, "Complete action using"));

            }
            return true;
        }


    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.refresh) {
            mWebview.reload();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void finish() {
        if ( mWebview != null) {
            mwebContainer.removeAllViews();
            mWebview.clearCache(true);
            mWebview.clearHistory();
            mWebview.destroy();
        }
        super.finish();
    }

}








