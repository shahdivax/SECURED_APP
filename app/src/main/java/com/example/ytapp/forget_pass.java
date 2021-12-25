package com.example.ytapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_pass extends AppCompatActivity {

    FirebaseAuth auth;

    EditText email;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);


        email=findViewById(R.id.resetEmail);
        reset = findViewById(R.id.RESETbutton);
        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().equals("")){
                    Toast.makeText(forget_pass.this, "Something Found Empty", Toast.LENGTH_SHORT).show();
                }else{

                    auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intend = new Intent(forget_pass.this,Signin.class);
                                intend.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intend);
                                email.setText("");
                                Toast.makeText(forget_pass.this, "Email send", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(forget_pass.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }}
        });

    }
}