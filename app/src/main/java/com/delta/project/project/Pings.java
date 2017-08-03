package com.delta.project.project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pings extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    Button b1;
    Button b2;
    Toolbar toolbar ;
    int c = 0;
    PingProcess pingProcess=new PingProcess();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "206541231", true);
       StartAppAd.disableSplash();
        setContentView(R.layout.activity_ping);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        Toast.makeText(getApplicationContext(),"Tap refresh to clear the window",Toast.LENGTH_SHORT).show();
        textView = (TextView) findViewById(R.id.txt);
        editText = (EditText) findViewById(R.id.et);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                String a="";
                if(c>1) {
                    pingProcess.cancel(true);
                    pingProcess.onCancelled();
                }
                if(editText != null) {
                a =  editText.getText().toString();
                    pingProcess = new PingProcess();
                    pingProcess.execute(a);
                }


            }
            });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Stopping",Toast.LENGTH_SHORT).show();
                        if(pingProcess !=null ) {
                        pingProcess.cancel(true);
                        }
                    }
                });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board_v1, menu);
        return true;
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
        if(id == R.id.refresh) {
            textView.setText("");
        }


        return super.onOptionsItemSelected(item);
    }


    public class PingProcess extends AsyncTask<String,String,String> {
        Process process;
        Runtime runtime = Runtime.getRuntime();

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].isEmpty()) {
                textView.setText("request timed out");
            }
            textView.append(values[0] + "\n");

      }


        @Override
        protected String doInBackground(String... params) {
        String url = params[0];
        String s;



while(!isCancelled()) {

    try {


        process = runtime.exec("/system/bin/ping -c 20 " + url + "");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = bufferedReader.readLine()) != null) {
            if (isCancelled()){
                break;
            }
            publishProgress(s);

        }




    } catch (IOException e) {
        e.printStackTrace();
    }

}
            return "";
        }

        @Override
        protected void onCancelled() {
           super.onCancelled();
    }

}


}

