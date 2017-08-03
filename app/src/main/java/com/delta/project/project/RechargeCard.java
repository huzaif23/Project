package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

public class RechargeCard extends AppCompatActivity {

     Webs webView;
    private FrameLayout container;
    private String url;
    private String s;
   private Toolbar toolbar;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.activity_recharge_card);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        webView = (Webs) findViewById(R.id.web);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        s=extra.getString("r","p");
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

    @Override
    public void onBackPressed() {
        count++;
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else if(count <= 1) {
            Toast.makeText(this,"Please press again to go back session will be logged out",Toast.LENGTH_SHORT).show();
         }
         else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        if (webView!=null){
        webView.clearHistory();
        webView.clearCache(true);
        webView.pauseTimers();
    }
    super.onDestroy();
}

    @Override
    protected void onResume() {
        webView.reload();
        super.onResume();
    }
}
