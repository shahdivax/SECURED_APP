package com.example.ytapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class middle extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);//view.startAnimation(buttonClick);

    private EditText username,Password,SecurityPass,TnxPass,Title;
    private Button Adddata;

    Databasehelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);

        Title = findViewById(R.id.Title);
        username = findViewById(R.id.username);
        Password = findViewById(R.id.Password);
        SecurityPass = findViewById(R.id.SecurityPass);
        TnxPass = findViewById(R.id.TnxPass);
        Adddata = findViewById(R.id.Adddata);
        mDatabaseHelper = new Databasehelper(this);

        Adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                String newEntry = Title.getText().toString().toUpperCase().replace(" ","_");
                String newEntry1 = username.getText().toString().replace(" ","_");
                String newEntry2 = Password.getText().toString().replace(" ","_");
                String newEntry3 = SecurityPass.getText().toString().replace(" ","_");
                String newEntry4 = TnxPass.getText().toString().replace(" ","_");

                String[] title = {newEntry,newEntry1,newEntry2,newEntry3,newEntry4};


                if(title[0].equals("") || title[1].equals("") || title[2].equals("") || title[3].equals("") || title[4].equals(""))
                {

                    Toast.makeText(middle.this, "SOMETHING FOUND EMPTY", Toast.LENGTH_SHORT).show();
                    view.startAnimation(buttonClick);
                    Animation shake = AnimationUtils.loadAnimation(middle.this, R.anim.shake);

                }
                else {
                    addData(title);

                    Toast.makeText(middle.this, "DATA ADDED", Toast.LENGTH_SHORT).show();
                    Title.setText("");
                    username.setText("");
                    Password.setText("");
                    SecurityPass.setText("");
                    TnxPass.setText("");

                }

            }
        });




    }


    public void addData (String[] newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
         }



}