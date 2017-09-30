package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class DashboadV1 extends AppCompatActivity implements View.OnClickListener{

    private Button b1,b2,b3,b4;
    private InterstitialAd ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad_v1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        ad = new InterstitialAd(this);
        ad.setAdUnitId(getString(R.string.interstitial_full_screen));
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()) {
            case R.id.btn1:
                intent= new Intent(this,SharingSites.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                ad.loadAd(new AdRequest.Builder().build());
                ad.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Log.v("admob","ad closed");
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        Log.v("admob","ad failed to load"+i);
                        super.onAdFailedToLoad(i);
                    }

                    @Override
                    public void onAdLeftApplication() {
                        Log.v("admob","ad closed");
                        super.onAdLeftApplication();
                    }

                    @Override
                    public void onAdOpened() {
                        Log.v("admob","ad closed");
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        if (ad.isLoaded()) {
                            Toast.makeText(DashboadV1.this,"Ad is loading",Toast.LENGTH_SHORT).show();
                            ad.show();
                        }

                    }
                });
                intent = new Intent(this,SharingData.class);
                bundle = new Bundle();
                bundle.putString("site_name","Mobile TV");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.btn3:
                    intent = new Intent(this, RechargeCard.class);
                    bundle = new Bundle();
                    bundle.putString("r", "Recharge Card");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                    case R.id.btn4:
                        intent = new Intent(this, ConfigureRouter.class);
                        bundle = new Bundle();
                        bundle.putString("c", "change");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;

                    default:
                        Toast.makeText(DashboadV1.this, "Please Choose from above", Toast.LENGTH_SHORT).show();
                }
        }

  }


