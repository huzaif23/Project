package com.delta.project.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import static android.support.design.widget.Snackbar.make;

public class ConfigureRouter extends AppCompatActivity  {

    private Webs webView;
   Context context = this;
    private String ip;
    private Toolbar toolbar;
    private String msg;
    private Boolean check=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.activity_configure_router);
         toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        webView = (Webs) findViewById(R.id.web);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        msg =bundle.getString("c");
        final WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        DhcpInfo dhcpInfo = wm.getDhcpInfo();
        ip = Formatter.formatIpAddress(dhcpInfo.gateway);

        webView.setWebViewClient(new GoUrl());
        webView.loadUrl("http://"+ip);
    }

    private class GoUrl extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (msg.equals("change")) {
                if (check == false) {
              Snackbar snackbar=  Snackbar.make(webView,"To change your Password just go to bottom of the page and click on Security Key  enter new Password and press Ok ***Dont change anything else***"
                        ,Snackbar.LENGTH_LONG);
                    View snack = snackbar.getView();
                    TextView text = (TextView) snack.findViewById(android.support.design.R.id.snackbar_text);
                    text.setMaxLines(6);
                    snackbar.show();
            }
            }
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
                                  handler.proceed(user,pass);
                                    if (msg.equals("change")) {
                                   Snackbar snackbar =  make(webView,"To change your Password just  go Wireless -> Wireless Security go to the bottom of the page and click on change Psk Password  enter new password and click save  ***Dont change anything else***"
                                            ,Snackbar.LENGTH_LONG);
                                    View snack = snackbar.getView();
                                        TextView text = (TextView) snack.findViewById(android.support.design.R.id.snackbar_text);
                                        text.setMaxLines(6);
                                        snackbar.show();
                                    }
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

    @Override
    protected void onResume() {
        webView.reload();
        super.onResume();
    }


}




