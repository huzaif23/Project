package com.delta.project.project;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
//import com.google.firebase.analytics.FirebaseAnalytics;

public class Front extends AppCompatActivity {

    private Button b1,b2;
    private AdView adView;
//    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        b1 = (Button) findViewById(R.id.btn1);
        b2= (Button) findViewById(R.id.btn2);
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("2A084023A710318A26E94F0CC5113AC6")
                .build();
        adView.loadAd(adRequest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent =new Intent(getApplicationContext(),DashboadV1.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),DashboardV2.class);
                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,new String [] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            } ,1);
        }


    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
