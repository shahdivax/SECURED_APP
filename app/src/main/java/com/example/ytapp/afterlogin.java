package com.example.ytapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;


public class afterlogin extends AppCompatActivity {

    public String Tab_Name;

    public String getTab_Name() {
        return Tab_Name;
    }

    public void setTab_Name(String tab_Name) {
        Tab_Name = tab_Name;
    }

    private Button adddata,viewdata;
    private TextView viewofdata;
    FirebaseDatabase Database;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        adddata = findViewById(R.id.adddata);
        viewdata = findViewById(R.id.viewdata);
        Database = FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();

        String id = Auth.getCurrentUser().getUid();
        Database.getReference().child("Users").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                    String name = task.getResult().child("username").getValue().toString();

                    Toast.makeText(afterlogin.this, "Welcome "+name, Toast.LENGTH_SHORT).show();



                }else {
                    Toast.makeText(afterlogin.this, "Something not found", Toast.LENGTH_SHORT).show();

                }

            }
        });
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