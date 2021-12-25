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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth Auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText name,email,pass;
        Button signup,signin;




        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Password);
        signup = findViewById(R.id.Signup);
        signin = findViewById(R.id.signin);

        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("")){
                    Toast.makeText(Signup.this, "Something Found Empty", Toast.LENGTH_SHORT).show();
                }else{

                    Auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        user user = new user(name.getText().toString(),email.getText().toString(),pass.getText().toString());

                                        String id = task.getResult().getUser().getUid();

                                        database.getReference().child("Users").child(id).setValue(user);
                                        Intent intend = new Intent(Signup.this,Signin.class);
                                        intend.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intend);

                                        name.setText("");
                                        email.setText("");
                                        pass.setText("");

                                        Toast.makeText(Signup.this, "USER CREATED PLEASE LOGIN", Toast.LENGTH_SHORT).show();
                                    }else{

                                        Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }}
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Signin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}