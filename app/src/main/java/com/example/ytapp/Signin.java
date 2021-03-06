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

public class Signin extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog pd;

    EditText email,pass,user;
    Button signin;
    TextView forget,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        pd = new ProgressDialog(Signin.this);
        pd.setMessage("Signing You In");


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        email = findViewById(R.id.inEMAIl);
        pass = findViewById(R.id.Inpass);
        signin =findViewById(R.id.SignIn);
        signup = findViewById(R.id.TextSignup);
        forget = findViewById(R.id.textView);
        user = findViewById(R.id.Name);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().equals("") || pass.getText().toString().equals("")){
                    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                    view.startAnimation(buttonClick);
                    Animation shake = AnimationUtils.loadAnimation(Signin.this, R.anim.shake);
                    view.startAnimation(shake);
                    Toast.makeText(Signin.this, "Something Found Empty", Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();

                    auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        Intent intend = new Intent(Signin.this,afterlogin.class);
                                        startActivity(intend);
                                        email.setText("");
                                        pass.setText("");

                                        Toast.makeText(Signin.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();

                                    }else{
                                        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                                        view.startAnimation(buttonClick);
                                        Animation shake = AnimationUtils.loadAnimation(Signin.this, R.anim.shake);
                                        view.startAnimation(shake);
                                        Toast.makeText(Signin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    }
                                }
                            });

                }}

        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this,Signup.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this,forget_pass.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}