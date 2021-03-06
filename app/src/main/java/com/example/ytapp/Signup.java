package com.example.ytapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText name,email,pass;
        Button signup;
        TextView signin;




        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Password);
        signup = findViewById(R.id.Signup);
        signin = findViewById(R.id.signin);
        pd = new ProgressDialog(Signup.this);
        pd.setMessage("Signing You Up");

        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("")){
                    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                    view.startAnimation(buttonClick);
                    Animation shake = AnimationUtils.loadAnimation(Signup.this, R.anim.shake);
                    view.startAnimation(shake);
                    Toast.makeText(Signup.this, "Something Found Empty", Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();

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
                                        pd.dismiss();
                                    }else{
                                        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                                        view.startAnimation(buttonClick);
                                        Animation shake = AnimationUtils.loadAnimation(Signup.this, R.anim.shake);
                                        view.startAnimation(shake);

                                        Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
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