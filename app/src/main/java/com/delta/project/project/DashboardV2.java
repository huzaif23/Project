package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DashboardV2 extends AppCompatActivity implements View.OnClickListener {

    private Button b1,b2,b3;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_v2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        b1= (Button) findViewById(R.id.btns1);
        b2= (Button) findViewById(R.id.btns2);
        b3= (Button) findViewById(R.id.btns3);
        adView = (AdView) findViewById(R.id.adView);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()) {
            case R.id.btns1 :
                intent=new Intent(this,RechargeCard.class);
                 bundle = new Bundle();
                bundle.putString("p","Panel");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btns2 :
                intent=new Intent(this,ConfigureRouter.class);
                bundle = new Bundle();
                bundle.putString("c","configure");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btns3 :
                intent = new Intent(this,Pings.class);
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
