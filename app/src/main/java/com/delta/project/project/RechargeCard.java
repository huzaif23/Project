package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class RechargeCard extends AppCompatActivity {

    private WebView webView;
    private RelativeLayout container;
    private String url;
    private String s;
   private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_card);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
       container = (RelativeLayout) findViewById(R.id.content_recharge_card);
        webView = new WebView(getApplicationContext());
        webView.setMinimumHeight(700);
        container.addView(webView);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        s=extra.getString("r","p");
        Webs webs = new Webs(webView);
        webs.screenSupport();

            if(s.equals("recharge")) {
            url = "http://103.255.148.14/user/login.php";
            }
        else  {
                getSupportActionBar().setTitle("Panel");
            url = "http://103.255.148.14/dealer/login.php";
            }
        webView.loadUrl(url);
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
            webView.reload();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board_v1, menu);
        return true;
    }

//    private class GoUrl extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//    }

}
