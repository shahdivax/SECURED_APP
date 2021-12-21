package com.example.ytapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button SAVE,DELETE;
    private EditText EditNAME,editUSERNAME,editPASS,editSecPASS,editTnxPASS;

    Databasehelper mDatabaseHelper;

    private  String selectedName[],selectedusername[],selectedpass[],selectedsecpass[],selectedtnxpass[];
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        SAVE = findViewById(R.id.SAVE);
        DELETE = findViewById(R.id.DELETE);
        EditNAME = findViewById(R.id.EditNAME);
        editUSERNAME=findViewById(R.id.editUSERNAME);
        editPASS=findViewById(R.id.editPASS);
        editSecPASS=findViewById(R.id.editSecPASS);
        editTnxPASS=findViewById(R.id.editTnxPASS);


        mDatabaseHelper = new Databasehelper(this);

        Intent receiveIntent = getIntent();

        selectedID = receiveIntent.getIntExtra("ID",-1);
        selectedName = receiveIntent.getStringExtra("Title").split(" ");
        selectedusername=receiveIntent.getStringExtra("UserName").split(" ");
        selectedpass=receiveIntent.getStringExtra("Pass").split(" ");
        selectedsecpass=receiveIntent.getStringExtra("SecPass").split(" ");
        selectedtnxpass=receiveIntent.getStringExtra("TeXPass").split(" ");

        EditNAME.setText(selectedName[1]);
        editUSERNAME.setText(selectedusername[1]);
        editPASS.setText(selectedpass[1]);
        editSecPASS.setText(selectedsecpass[2]);
        editTnxPASS.setText(selectedtnxpass[2]);


        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                view.startAnimation(buttonClick);
                String title = EditNAME.getText().toString().replace(" ","_");
                String user = editUSERNAME.getText().toString().replace(" ","_");
                String pass = editPASS.getText().toString().replace(" ","_");
                String sec = editSecPASS.getText().toString().replace(" ","_");
                String Tnx = editTnxPASS.getText().toString().replace(" ","_");

                if (title.equals("") || user.equals("") || pass.equals("") || sec.equals("") || Tnx.equals("")){
                    Toast.makeText(EditDataActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();

                }else {
                    mDatabaseHelper.update(title,selectedID,user,pass,sec,Tnx);
                    Toast.makeText(EditDataActivity.this, title+" Updated Successful", Toast.LENGTH_SHORT).show();
                    Intent editscreen = new Intent(EditDataActivity.this, viewData.class);
                    editscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(editscreen);
                }
            }
        });

        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                view.startAnimation(buttonClick);
                mDatabaseHelper.Delete(selectedID,selectedName[1]);
                EditNAME.setText("");
                Toast.makeText(EditDataActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent editscreen = new Intent(EditDataActivity.this, viewData.class);
                editscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(editscreen);
            }
        });

    }
}