package com.delta.project.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Info extends AppCompatActivity implements View.OnClickListener {
    private TextView t1;
    private ImageView img1, img2, img3,img4;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        t1 = (TextView) findViewById(R.id.txt1);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()) {
            case R.id.img1:
                intent.setData(Uri.parse("https://www.facebook.com/huzaifa.ahmed.925"));
                startActivity(intent);
                break;
            case R.id.img2:
                intent.setData(Uri.parse("https://twitter.com/huzaif23"));
                startActivity(intent);
            break;
            case R.id.img3:
                intent.setData(Uri.parse("https://www.linkedin.com/in/huzaifa-ahmed-a6917bab/"));
                startActivity(intent);
                break;
            case R.id.img4:
                intent.setData(Uri.parse("https://github.com/huzaif23"));
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