package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboadV1 extends AppCompatActivity implements View.OnClickListener{

    private Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad_v1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
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
                intent = new Intent(this,SharingData.class);
                bundle = new Bundle();
                bundle.putString("site_name","Mobile TV");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.btn3:
                intent=new Intent(this,RechargeCard.class);
                bundle = new Bundle();
                bundle.putString("r","recharge");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent= new Intent(this,ConfigureRouter.class);
                startActivity(intent);
                break;


            default:  Toast.makeText(DashboadV1.this, "Please Choose from above", Toast.LENGTH_SHORT).show();

        }

    }

}
