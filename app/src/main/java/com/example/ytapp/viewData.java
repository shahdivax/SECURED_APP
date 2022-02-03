package com.example.ytapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class viewData extends AppCompatActivity {
  private static final String TAG = "ListDataActivity";

  Databasehelper mDatabaseHelper;

  private ListView mListview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_data);

    mListview = findViewById(R.id.listview);
    mDatabaseHelper = new Databasehelper(this);
    populateListView();
  }

  private void populateListView() {

    Cursor data = mDatabaseHelper.getData();
    ArrayList<String> listData = new ArrayList<>();

    while (data.moveToNext()) {
      listData.add(
          "Title: "
              + data.getString(1)
              + ",\nUserName: "
              + data.getString(2)
              + ",\nPassword: "
              + data.getString(3)
              + ",\nSecurity Password: "
              + data.getString(4)
              + ",\nTransaction Password: "
              + data.getString(5));
    }
    ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
    mListview.setAdapter(adapter);

    if (listData.isEmpty()) {
      Toast.makeText(viewData.this, "NOTHING FOUND", Toast.LENGTH_SHORT).show();
    }

    mListview.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
            view.startAnimation(buttonClick);
            String[] Tit = adapterView.getItemAtPosition(i).toString().split(" ");
            String[] T = Tit[1].split(",");
            System.out.println(T[0] + " " + T[1]);
            String[] Title = adapterView.getItemAtPosition(i).toString().split(",");
            Cursor data = mDatabaseHelper.getItemID(T[0].trim());
            int itemID = -1;
            while (data.moveToNext()) {
              itemID = data.getInt(0);
            }
            if (itemID > -1) {
              Intent editscreen = new Intent(viewData.this, EditDataActivity.class);
              editscreen.putExtra("ID", itemID);
              editscreen.putExtra("Title", Title[0]);
              editscreen.putExtra("UserName", Title[1]);
              editscreen.putExtra("Pass", Title[2]);
              editscreen.putExtra("SecPass", Title[3]);
              editscreen.putExtra("TeXPass", Title[4]);
              startActivity(editscreen);

            } else {
              Toast.makeText(viewData.this, "NOTHING FOUND", Toast.LENGTH_SHORT).show();
            }
          }
        });
  }

  private void toastMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
