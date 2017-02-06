package com.example.sheldon.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sheldon on 2/6/2017.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "Chats.db";
    static int VERSION_NUM = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "message";
    public static final String TABLE_NAME = "Message";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
     public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        db.execSQL("create table " + TABLE_NAME + " ( " + KEY_ID + " integer primary key autoincrement, " + KEY_MESSAGE + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

}
