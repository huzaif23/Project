package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SharingSites extends AppCompatActivity implements View.OnClickListener {

    ImageView img,img2,img3,img4;
    private String sites ;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_sites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img= (ImageView) findViewById(R.id.imageView1);
        img2= (ImageView) findViewById(R.id.imageView2);
        img3= (ImageView) findViewById(R.id.imageView3);
        img4= (ImageView) findViewById(R.id.imageView4);
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest =new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
        img.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        Intent intent=new Intent(getApplicationContext(),SharingData.class);
        switch (v.getId()) {
            case R.id.imageView1 :
                sites="Play99";
                bundle.putString("site_name", sites);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageView2 :
                sites="Showtime";
                bundle.putString("site_name", sites);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageView3 :
                sites="AmmarCity";
                bundle.putString("site_name", sites);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageView4 :
                sites="Timespan";
                bundle.putString("site_name", sites);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

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
