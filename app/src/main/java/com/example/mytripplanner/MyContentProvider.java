package com.example.mytripplanner;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import java.util.HashMap;



public class MyContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.mytripplanner.myContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/users";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "_id";
    static final String name = "airport_name";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;
    static final String TABLE_NAME = "airport";

    public ListDB.DBHelper dbHelper = null;

//    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "users/*", uriCode);
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/users";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = ListDB.getInstance();

        if (dbHelper != null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder == "") {
            sortOrder = id;
        }

        if (dbHelper == null) {
            dbHelper = ListDB.getInstance();
        }

        Cursor c = dbHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = dbHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = dbHelper.getReadableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = dbHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}