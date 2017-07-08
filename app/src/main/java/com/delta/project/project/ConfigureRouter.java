package com.delta.project.project;

import android.content.Context;
import android.content.DialogInterface;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConfigureRouter extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private WebView webView;
    final Context context = this;
    private String ip;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_router);
         toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.content_configure_router);
        webView = new WebView(getApplicationContext());
        relativeLayout.addView(webView);
        final WifiManager wm = (WifiManager) super.getApplicationContext().getSystemService(WIFI_SERVICE);
        DhcpInfo dhcpInfo = wm.getDhcpInfo();
        ip = Formatter.formatIpAddress(dhcpInfo.gateway);
        Webs webs = new Webs(webView);
        webs.screenSupport();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new GoUrl());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("http://"+ip);
    }

    private class GoUrl extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view, final HttpAuthHandler handler, String host, String realm) {
            final LayoutInflater factory = LayoutInflater.from(context);
            final View v = factory.inflate(R.layout.jsdialogue, null);
            ((TextView)v.findViewById(R.id.prompt_message_text)).setText("Enter your username & password");
            ((EditText)v.findViewById(R.id.prompt_input_field)).setText("");
            ((EditText)v.findViewById(R.id.prompt_input_field_pass)).setText("");

            new AlertDialog.Builder(context)
                    .setTitle(R.string.title_activity_configure_router)
                    .setView(v)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String user = ((EditText)v.findViewById(R.id.prompt_input_field)).getText().toString();
                                    String pass = ((EditText) v.findViewById(R.id.prompt_input_field_pass)).getText().toString();
                                    handler.proceed(user,pass);


                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                 onBackPressed();
                                }
                            })
                    .setOnCancelListener(
                            new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialog) {
                                    onBackPressed();
                                }
                            })
                    .show();
        }

    }
    @Override
    public void finish() {
        if(webView != null){
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
        }
        super.finish();
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

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board_v1, menu);
        return true;
    }
}




