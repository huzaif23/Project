package com.delta.project.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView t1;
    private ImageView img1, img2, img3,img4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        t1 = (TextView) findViewById(R.id.txt1);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
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
}