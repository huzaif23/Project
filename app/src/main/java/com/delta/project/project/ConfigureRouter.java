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
import android.view.Window;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class ConfigureRouter extends AppCompatActivity  {

    private Webs webView;
   Context context = this;
    private String ip;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.activity_configure_router);
         toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        webView = (Webs) findViewById(R.id.web);
        final WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        DhcpInfo dhcpInfo = wm.getDhcpInfo();
        ip = Formatter.formatIpAddress(dhcpInfo.gateway);
        webView.setWebViewClient(new GoUrl());
        webView.loadUrl("http://"+ip);
    }

    private class GoUrl extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(ConfigureRouter.this,"Works",Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
            return true;
        }


        @Override
        public void onReceivedHttpAuthRequest(final WebView view, final HttpAuthHandler handler, String host, String realm) {
            final LayoutInflater factory = LayoutInflater.from(context);
            final View v = factory.inflate(R.layout.jsdialogue, null);
            ((TextView)v.findViewById(R.id.prompt_message_text)).setText("Enter your username & password");

            new AlertDialog.Builder(context)
                    .setTitle(R.string.title_activity_configure_router)
                    .setView(v)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String user = ((EditText)v.findViewById(R.id.prompt_input_field)).getText().toString();
                                    String pass = ((EditText) v.findViewById(R.id.prompt_input_field_pass)).getText().toString();
                                  webView.loadUrl("http://"+user+":"+pass+"@"+ip);
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


    public void authenticate() {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin","admin1".toCharArray());
            }
        });
        try {
            HttpURLConnection auth = (HttpURLConnection) new URL("http://192.168.1.1").openConnection();
            auth.setUseCaches(false);
            auth.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}




