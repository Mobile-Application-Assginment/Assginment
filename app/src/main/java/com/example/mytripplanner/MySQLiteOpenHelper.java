/*
 *   NAME    : SQLiteOpenHelper.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The SQLiteOpenHelper class has been created to provide a method for creating
 *             and changing a table and schema.
 */

package com.example.mytripplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// To create table or change schema
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    // After installing app and when SQLiteOpenHelper is firstly called, this method is called
    // to create table.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table schedule" +
                "(_id integer primary key autoincrement," +
                "name text, departure text, destination text," +
                "adultNumber text, childNumber text);";
        db.execSQL(sql);
    }

    // This method called every time, when Database version is changed.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists schedule;";
        db.execSQL(sql);
        onCreate(db);
    }
}
