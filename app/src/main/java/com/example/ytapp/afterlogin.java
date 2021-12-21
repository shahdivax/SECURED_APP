package com.example.ytapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class afterlogin extends AppCompatActivity {
    private Button adddata,viewdata;
    private TextView viewofdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        adddata = findViewById(R.id.adddata);
        viewdata = findViewById(R.id.viewdata);
    }

    public void addData(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        view.startAnimation(buttonClick);

        Intent intend = new Intent(this,middle.class);
        startActivity(intend);

    }

    public void viewData(View view){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        view.startAnimation(buttonClick);

        Intent intend = new Intent(this,viewData.class);
        startActivity(intend);

    }


}