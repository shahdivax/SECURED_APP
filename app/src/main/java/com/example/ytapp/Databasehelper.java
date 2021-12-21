package com.example.ytapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    public Databasehelper(Context context) {
        super(context,TABLE_NAME, null, DATABASE_VERSION);
    }

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "VARUN";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "USERNAME";
    private static final String COL4 = "PASSWORD";
    private static final String COL5 = "SECURITY_PASSWORD";
    private static final String COL6 = "TNX_PASSWORD";






    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2 +" TEXT,"+COL3+" TEXT,"+COL4 +" TEXT,"+COL5 +" TEXT,"+COL6 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String[] item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item[0]);
        contentValues.put(COL3, item[1]);
        contentValues.put(COL4, item[2]);
        contentValues.put(COL5, item[3]);
        contentValues.put(COL6, item[4]);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param //oldName
     */
    public void update(String newName,int id,String newuser,String newpass,String newsec,String newtnx){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" ;
        db.execSQL(query);
        String query2 = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newuser + "' WHERE " + COL1 + " = '" + id + "'" ;
        db.execSQL(query2);
        String query3 = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newpass + "' WHERE " + COL1 + " = '" + id + "'" ;
        db.execSQL(query3);
        String query4 = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newsec + "' WHERE " + COL1 + " = '" + id + "'" ;
        db.execSQL(query4);
        String query5 = "UPDATE " + TABLE_NAME + " SET " + COL6 +
                " = '" + newtnx + "' WHERE " + COL1 + " = '" + id + "'" ;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query5);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void Delete(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}
