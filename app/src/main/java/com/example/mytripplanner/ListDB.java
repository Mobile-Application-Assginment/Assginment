//
//  NAME        : ListDB.java
//  Project     : Mobile Application Development - Assignment 2
//  By          : Charng Gwon Lee, Hyungbum Kim, Younchul Cho
//  Date        : Mar. 14, 2020
//  PURPOSE     : This file is connect with the database. It defines the database and table.
//                  Also, it gives inserting and searching database method.
//                  In this program, it has four tables in a database (airport, user, schedule, and trip task)
//

package com.example.mytripplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class ListDB {

    // database constants
    private static final String DB_NAME = "trip_db.db";
    private static final int    DB_VERSION = 1;


    // Airport table constants
    private static final String AIRPORT_TABLE = "airport";
    private static final String AIRPORT_ID = "_id";
    private static final int    AIRPORT_ID_COL = 0;
    private static final String AIRPORT_NAME = "airport_name";
    private static final int    AIRPORT_NAME_COL = 1;


    // Schedule time table constants
    private static final String TIME_TABLE = "time";
    private static final String TIME_ID = "_id";
    private static final int    TIME_ID_COL = 0;
    private static final String TIME_VALUE = "time_value";
    private static final int    TIME_VALUE_COL = 1;


    // Trip type table constants
    private static final String TRIP_TABLE = "trip";
    private static final String TRIP_ID = "_id";
    private static final int    TRIP_ID_COL = 0;
    private static final String TRIP_TYPE = "trip_type";
    private static final int    TRIP_TYPE_COL = 1;


    // User table constants
    private static final String USER_TABLE = "user";
    private static final String USER_ID = "_id";
    private static final int    USER_ID_COL = 0;
    private static final String USER_NAME = "user_name";
    private static final int    USER_NAME_COL = 1;


    // Trip task table constants
    private static final String TASK_TABLE = "task";
    public static final String TASK_ID = "_id";
    private static final int    TASK_ID_COL = 0;
    public static final String TASK_USER_ID = "user_id";
    private static final int    TASK_USER_ID_COL = 1;
    public static final String TASK_DEPARTURE_AIRPORT_ID = "dep_airport_id";
    private static final int    TASK_DEPARTURE_AIRPORT_ID_COL = 2;
    public static final String TASK_DESTINATION_AIRPORT_ID = "dest_airport_id";
    private static final int    TASK_DESTINATION_AIRPORT_ID_COL = 3;
    public static final String TASK_TIME_ID = "time_id";
    private static final int    TASK_TIME_ID_COL = 4;
    public static final String TASK_ADULT_NUM = "adult_num";
    private static final int    TASK_ADULT_NUM_COL = 5;
    public static final String TASK_CHILD_NUM = "child_num";
    private static final int    TASK_CHILD_NUM_COL = 6;
    public static final String TASK_TRIP_ID = "trip_id";
    private static final int    TASK_TRIP_ID_COL = 7;


    // CREATE TABLE statements
    private static final String CREATE_AIRPORT_TABLE =
            "CREATE TABLE " + AIRPORT_TABLE + " (" +
                    AIRPORT_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AIRPORT_NAME + " TEXT    NOT NULL UNIQUE);";

    private static final String CREATE_TIME_TABLE =
            "CREATE TABLE " + TIME_TABLE + " (" +
                    TIME_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TIME_VALUE + " TEXT    NOT NULL UNIQUE);";

    private static final String CREATE_TRIP_TABLE =
            "CREATE TABLE " + TRIP_TABLE + " (" +
                    TRIP_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TRIP_TYPE + " TEXT    NOT NULL UNIQUE);";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + USER_TABLE + " (" +
                    USER_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_NAME + " TEXT    NOT NULL UNIQUE);";

    private static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + TASK_TABLE + " (" +
                    TASK_ID                     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK_USER_ID                + " INTEGER NOT NULL, " +
                    TASK_DEPARTURE_AIRPORT_ID    + " INTEGER NOT NULL, " +
                    TASK_DESTINATION_AIRPORT_ID + " INTEGER NOT NULL, " +
                    TASK_TIME_ID                + " INTEGER NOT NULL, " +
                    TASK_ADULT_NUM              + " INTEGER NOT NULL, " +
                    TASK_CHILD_NUM              + " INTEGER NOT NULL, " +
                    TASK_TRIP_ID                + " INTEGER);";


    // DROP TABLE statements
    private static final String DROP_AIRPORT_TABLE =
            "DROP TABLE IF EXISTS " + AIRPORT_TABLE;

    private static final String DROP_TIME_TABLE =
            "DROP TABLE IF EXISTS " + TIME_TABLE;

    private static final String DROP_TRIP_TABLE =
            "DROP TABLE IF EXISTS " + TRIP_TABLE;

    private static final String DROP_USER_TABLE =
            "DROP TABLE IF EXISTS " + USER_TABLE;

    private static final String DROP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + TASK_TABLE;


    // Initiate the database (create or drop tables)
    // The database is initialized when DBHelper class is created
    // It is common practice to define a private static class with the DB Class
    public static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Create the each tables which is in the database
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create the Table
            db.execSQL(CREATE_AIRPORT_TABLE);
            db.execSQL(CREATE_TIME_TABLE);
            db.execSQL(CREATE_TRIP_TABLE);
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_TASK_TABLE);

            // Print log fo successfully create the database
            Log.d("List DB", "Create Database, the version is " + DB_VERSION);

        }


        // Upgrade the database (Remove/drop the all table and create again)
        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("List DB", "Upgrading db from version " + oldVersion + " to " + newVersion);

            db.execSQL(ListDB.DROP_AIRPORT_TABLE);
            db.execSQL(ListDB.DROP_TIME_TABLE);
            db.execSQL(ListDB.DROP_TRIP_TABLE);
            db.execSQL(ListDB.DROP_USER_TABLE);
            db.execSQL(ListDB.DROP_TASK_TABLE);
            onCreate(db);
        }
    }

    // Database and Database helper objects
    private SQLiteDatabase db;
    private static DBHelper dbHelper;

    public static DBHelper getInstance() {

        return dbHelper;
    }


    // constructor
    public ListDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }
    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    // Close the database, if it is opened
    private void closeDB() {
        if (db != null)
            db.close();
    }

    // Close the cursor, if it is opened
    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    // Insert the new name of airport into the database
    public int insertAirport(String name) {
        ContentValues cv = new ContentValues();
        cv.put(AIRPORT_NAME, name);

        this.openWriteableDB();
        int rowID = (int)db.insert(AIRPORT_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    // Insert the new name of user into the database
    public int insertUser(String name) {
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, name);

        this.openWriteableDB();
        int rowID = (int)db.insert(USER_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }


    // Insert the new schedule time into the database
    public int insertTime(String time) {
        ContentValues cv = new ContentValues();
        cv.put(TIME_VALUE, time);

        this.openWriteableDB();
        int rowID = (int)db.insert(TIME_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }


    // Insert the new schedule time into the database
    public int insertTrip(String type) {
        ContentValues cv = new ContentValues();
        cv.put(TRIP_TYPE, type);

        this.openWriteableDB();
        int rowID = (int)db.insert(TRIP_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }


    // Insert the new trip task into the database
    // Initialize contentvalues and fill it with data
    // Keys of the contentValues dictionary are column names
    public int insertTask(Task task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_DEPARTURE_AIRPORT_ID, task.getDepatureAirportId());
        cv.put(TASK_DESTINATION_AIRPORT_ID, task.getDestinationAirportId());
        cv.put(TASK_USER_ID, task.getUserId());
        cv.put(TASK_TIME_ID, task.getTimeId());
        cv.put(TASK_ADULT_NUM, task.getAdultNum());
        cv.put(TASK_CHILD_NUM, task.getChildNum());
        cv.put(TASK_TRIP_ID, task.getTripId());

        this.openWriteableDB();
        int rowID = (int)db.insert(TASK_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }


    //Get last data for widget
    public Task getLastTask(){

        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null,
                null, null,
                null, null, null);

        cursor.moveToLast();
        Task task = new Task(
                cursor.getInt(TASK_ID_COL),
                cursor.getInt(TASK_DEPARTURE_AIRPORT_ID_COL),
                cursor.getInt(TASK_DESTINATION_AIRPORT_ID_COL),
                cursor.getInt(TASK_USER_ID_COL),
                cursor.getInt(TASK_TIME_ID_COL),
                cursor.getInt(TASK_ADULT_NUM_COL),
                cursor.getInt(TASK_CHILD_NUM_COL),
                cursor.getInt(TASK_TRIP_ID_COL)
        );

        this.closeCursor(cursor);
//        this.closeDB();
        return task;
    }

    // Get the airport id based on the name of airport
    public int getAirportId(String name) {
        String where = AIRPORT_NAME + "= ?";
        String[] whereArgs = { name };
        int id = -1;

        this.openReadableDB();
        Cursor cursor = db.query(AIRPORT_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        Airport airport = getAirportFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(airport != null) { return airport.getAirportId(); }
        else { return id; }
    }

    // Get the airport name based on the airport id
    public String getAirportName(int id) {
        String where = AIRPORT_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(AIRPORT_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        Airport airport = getAirportFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(airport != null) { return airport.getAirportName(); }
        else { return null; }
    }


    // Get the whole airport information which cursor indicate
    private static Airport getAirportFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Airport airport = new Airport(
                        cursor.getInt(AIRPORT_ID_COL),
                        cursor.getString(AIRPORT_NAME_COL));

                return airport;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    // Get the user id based on the name of the user
    public int getUserId(String name) {
        String where = USER_NAME + "= ?";
        String[] whereArgs = { name };
        int id = -1;

        this.openReadableDB();
        Cursor cursor = db.query(USER_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        User user = getUserFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(user != null) {
            return (user.getUserId());
        }
        else {
            return insertUser(name);
        }
    }


    // Get the user name based on the user id
    public String getUserName(int id) {
        String where = USER_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(USER_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        User user = getUserFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(user != null) { return (user.getUserName()); }
        else { return null; }
    }

    // Get the whole user information which cursor indicate
    // reading from SQLite is performed using query method,
    // with returns instance of Cursor
    private static User getUserFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                User user = new User(
                        cursor.getInt(USER_ID_COL),
                        cursor.getString(USER_NAME_COL));

                return user;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    // Get the specific time id based on schedule time
    public int getTimeId(String time) {
        String where = TIME_VALUE + "= ?";
        String[] whereArgs = { time };
        int id = -1;

        this.openReadableDB();
        Cursor cursor = db.query(TIME_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        ScheduleTime scheduleTime = getTimeFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(scheduleTime != null) {
            return (scheduleTime.getTimeId());
        }
        else { return id; }
    }

    // Get the specific time based on time id
    public String getTimeValue(int id) {
        String where = TIME_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(TIME_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        ScheduleTime scheduleTime = getTimeFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(scheduleTime != null) { return (scheduleTime.getTimeValue());  }
        else { return null; }
    }


    // Get the whole schedule time information which cursor indicate
    private static ScheduleTime getTimeFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                ScheduleTime scheduleTime = new ScheduleTime(
                        cursor.getInt(TIME_ID_COL),
                        cursor.getString(TIME_VALUE_COL));

                return scheduleTime;
            }
            catch(Exception e) {
                return null;
            }
        }
    }
    // Get the specific time id based on schedule time
    public int getTripId(String tripType) {
        String where = TRIP_TYPE + "= ?";
        String[] whereArgs = { tripType };
        int id = -1;

        this.openReadableDB();
        Cursor cursor = db.query(TRIP_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        Trip trip = getTripFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(trip != null) {
            return (trip.getTripId());
        }
        else { return id; }
    }

    // Get the specific time based on time id
    public String getTripType(int id) {
        String where = TRIP_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(TRIP_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        Trip trip = getTripFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        if(trip != null) { return (trip.getTripType());  }
        else { return null; }
    }



    // Get the whole schedule time information which cursor indicate
    private static Trip getTripFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Trip trip = new Trip(
                        cursor.getInt(TRIP_ID_COL),
                        cursor.getString(TRIP_TYPE_COL));

                return trip;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    // Get the whole schedule time information which cursor indicate
    public ArrayList<String> getAirportList() {

        this.openReadableDB();
        Cursor cursor = db.query(AIRPORT_TABLE, null,
                null, null,
                null, null, null);

        ArrayList<String> tasks = new ArrayList<String>();
        while (cursor.moveToNext()) {
            Airport airport = getAirportFromCursor(cursor);
            tasks.add(airport.getAirportName());
        }

        this.closeCursor(cursor);
        this.closeDB();

        return tasks;
    }

    // Get the whole Task information which cursor indicate
    public Cursor getTaskCursor(int nameId) {
        String where = TASK_USER_ID + "= ?";
        String[] whereArgs = { Integer.toString(nameId) };

        this.openReadableDB();

        Cursor cursor = db.query(TASK_TABLE, null,
                where, whereArgs,
                null, null, null);

        return cursor;
    }

    // Get the Task list which cursor indicate
    public ArrayList<Task> getTaskList(int nameId) {
        String where = TASK_USER_ID + "= ?";
        String[] whereArgs = { Integer.toString(nameId) };

        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null,
                where, whereArgs,
                null, null, null);

        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getTaskFromCursor(cursor));
        }

        this.closeCursor(cursor);
        this.closeDB();

        return tasks;
    }


    // Get the whole Task information which cursor indicate
    private static Task getTaskFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Task task = new Task(
                        cursor.getInt(TASK_ID_COL),
                        cursor.getInt(TASK_DEPARTURE_AIRPORT_ID_COL),
                        cursor.getInt(TASK_DESTINATION_AIRPORT_ID_COL),
                        cursor.getInt(TASK_USER_ID_COL),
                        cursor.getInt(TASK_TIME_ID_COL),
                        cursor.getInt(TASK_ADULT_NUM_COL),
                        cursor.getInt(TASK_CHILD_NUM_COL),
                        cursor.getInt(TASK_TRIP_ID_COL)
                );

                return task;
            }
            catch(Exception e) {
                return null;
            }
        }
    }
}
