package com.example.tickolo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_TICKETS = "tickets";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_SECTION = "section";
  public static final String COLUMN_ROW = "row";
  public static final String COLUMN_QUANTITY = "quantity";
  public static final String COLUMN_PRICE = "price";
  public static final String COLUMN_FACE_VALUE = "facevavalue";

  private static final String DATABASE_NAME = "tickets.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_TICKETS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_SECTION
      + " text, " + COLUMN_NAME 
      + " text, " + COLUMN_ROW 
      + " text, " + COLUMN_QUANTITY
      + " integer, " + COLUMN_PRICE
      + " integer, " + COLUMN_FACE_VALUE
      + " integer);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKETS);
    onCreate(db);
  }
} 
