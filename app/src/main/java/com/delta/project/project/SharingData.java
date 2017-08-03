package com.delta.project.project;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

public class SharingData extends AppCompatActivity {

    private String site;
   private Webs mWebview;
    private FrameLayout mwebContainer;
    private Toolbar toolbar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.activity_sharing_data);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
        mWebview = (Webs) findViewById(R.id.web);
        Webs webs= new Webs(context);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        site = extra.getString("site_name");
        mWebview.setWebViewClient(new GoUrl());
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 100)
                    setTitle(site);
            }

        });
        mWebview.setDownloadListener(new DownloadListener() {

            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                Toast.makeText(context,"Starting Download",Toast.LENGTH_SHORT).show();
                DownloadManager.Request request = new DownloadManager.Request( Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url,contentDisposition,mimetype));
                DownloadManager dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        });



        if (site.equals("Play99")) {
            mWebview.loadUrl("http://play99.pk");
        } else if (site.equals("Showtime")) {
            mWebview.loadUrl("http://172.16.75.200:83");
        } else if (site.equals("AmmarCity")) {
            mWebview.loadUrl("http://ammarcity.net/");
        } else if (site.equals("TimeSpan")) {
            mWebview.loadUrl("http://192.168.9.1");
        }
        else if(site.equals("Mobile TV")) {
            mWebview.loadUrl("http://172.16.75.200/mobitv");
        }
        webs.Download(context);

    }

    private class GoUrl extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if (url.contains("videoid")) {
                Toast.makeText(getApplicationContext(), "Loading Video on Chrome", Toast.LENGTH_SHORT).show();
                Uri web = Uri.parse(view.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, web);
                startActivity(Intent.createChooser(intent, "Complete action using"));
            }
           else if(url.contains("rtsp")) {
                if(checkInstalled()) {
                Toast.makeText(SharingData.this, "Loading Video on VLC", Toast.LENGTH_SHORT).show();
                Uri web = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("org.videolan.vlc.betav7neon");
                intent.setDataAndTypeAndNormalize(web, "video/*");
                startActivity(intent);
            }
                else if(!checkInstalled())  {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.videolan.vlc.betav7neon&hl=en"));

                    startActivity(intent);
                }
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
        getMenuInflater().inflate(R.menu.menu_dash_board_v1, menu);
        return true;
    }

//    @Override
//    public void finish() {
//        if ( mWebview != null) {
//            mWebview.clearCache(true);
//            mWebview.clearHistory();
//        }
//        super.finish();
//    }

    private boolean checkInstalled() {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("org.videolan.vlc.betav7neon",PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}