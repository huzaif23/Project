package com.delta.project.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DashboardV2 extends AppCompatActivity implements View.OnClickListener {

    private Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_v2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        b1= (Button) findViewById(R.id.btns1);
        b2= (Button) findViewById(R.id.btns2);
        b3= (Button) findViewById(R.id.btns3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btns1 :
                intent=new Intent(this,RechargeCard.class);
                Bundle bundle = new Bundle();
                bundle.putString("p","panel");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btns2 :
                intent=new Intent(this,ConfigureRouter.class);
                startActivity(intent);
                break;
            case R.id.btns3 :
                intent = new Intent(this,Pings.class);
                startActivity(intent);
                break;
        }
    }
}
