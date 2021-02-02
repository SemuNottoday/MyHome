package com.example.myrent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    public static String DB_NAME="MyHome";
    public static int DB_VERSION=1;

    public static String st = "create table "+ UserLocalData.UserInfoEntry.USER_PROFILE+
            "( "+ UserLocalData.UserInfoEntry.USER_ID+" text,"+
            UserLocalData.UserInfoEntry.USER_NAME+" text,"+
            UserLocalData.UserInfoEntry.USER_PASS+" text,"+
            UserLocalData.UserInfoEntry.USER_EMAIL+" text,"+
            UserLocalData.UserInfoEntry.USER_PHONE+" int)";
    public static String st2 = "create table "+ UserLocalData.UserInfoEntry.FAVORITE_LIST+
            "( "+ UserLocalData.UserInfoEntry.USERS_ID+" text,"+
            UserLocalData.UserInfoEntry.HOUSE_ID+" text)";


    public static String dro="drop table " + UserLocalData.UserInfoEntry.USER_PROFILE;


    public DbHandler(Context contex){
        super(contex ,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(st);
        db.execSQL(st2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dro);
        onCreate(db);
    }
    public void registerUserInfo(String id,String name,String pass,String email){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(UserLocalData.UserInfoEntry.USER_ID,id);
        value.put(UserLocalData.UserInfoEntry.USER_NAME,name);
        value.put(UserLocalData.UserInfoEntry.USER_PASS,pass);
        value.put(UserLocalData.UserInfoEntry.USER_EMAIL,email);
        database.insert(UserLocalData.UserInfoEntry.USER_PROFILE,null,value);
    }
    public void registerFavList(String Uid,String Hid){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(UserLocalData.UserInfoEntry.USERS_ID,Uid);
        value.put(UserLocalData.UserInfoEntry.HOUSE_ID,Hid);
        database.insert(UserLocalData.UserInfoEntry.FAVORITE_LIST,null,value);
    }

    public Cursor readUserProfile(SQLiteDatabase database)
    {
        String[] projections={
                UserLocalData.UserInfoEntry.USER_ID,
                UserLocalData.UserInfoEntry.USER_NAME,
                UserLocalData.UserInfoEntry.USER_PASS,
                UserLocalData.UserInfoEntry.USER_EMAIL,
                UserLocalData.UserInfoEntry.USER_PHONE};

        Cursor cursor=database.query(UserLocalData.UserInfoEntry.USER_PROFILE,projections,null,null,null,null,null);
        return cursor;
    }
}
