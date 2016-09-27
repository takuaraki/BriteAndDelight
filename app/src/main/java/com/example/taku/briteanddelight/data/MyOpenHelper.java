package com.example.taku.briteanddelight.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static MyOpenHelper instance;

    public static MyOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MyOpenHelper(context);
        }
        return instance;
    }

    private MyOpenHelper(Context context) {
        super(context, "brite_and_delight.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(User.CREATE_TABLE);

        sqLiteDatabase.insert(User.TABLE_NAME, null, User.FACTORY.marshal()
                ._id(1)
                .first_name("taku")
                .last_name("araki")
                .asContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
