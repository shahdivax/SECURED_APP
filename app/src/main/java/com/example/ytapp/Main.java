package com.example.ytapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

  private EditText pass;
  private Button btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    pass = findViewById(R.id.Password);
    btn = findViewById(R.id.button);
  }

  public void onbClick(View view) {

    if (pass.getText().toString().equals("1234")) {
      AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
      view.startAnimation(buttonClick);
      Toast.makeText(Main.this, "Login Successful", Toast.LENGTH_SHORT).show();

      Intent intend = new Intent(this, afterlogin.class);
      startActivity(intend);
      pass.setText("");
    } else {

      Toast.makeText(Main.this, "Wrong Password", Toast.LENGTH_SHORT).show();
      AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
      view.startAnimation(buttonClick);
      Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
      view.startAnimation(shake);
    }
  }
}
