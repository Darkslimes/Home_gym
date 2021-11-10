package com.example.home_gym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DBName="login.db";
    public DBhelper(Context context) {
        super(context, "login.db", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, mail TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password, String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username" , username);
        values.put("password", password);
        values.put("mail", mail);

        long result = db.insert("users", null, values);
        if(result == -1) return false;
        else
            return true;

    }
    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
        }
//    public boolean checkmail (String username, String password, String mail){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from users where username=? and password=? and mail=?", new String[] {username,password,mail});
//        if(cursor.getCount()>0)
//            return true;
//        else
//            return false;
//    }
}
