package com.example.taku.briteanddelight;

import android.app.Application;
import android.content.Context;

import com.example.taku.briteanddelight.data.MyOpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

public class MyApplication extends Application {

    BriteDatabase briteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        SqlBrite sqlBrite = SqlBrite.create();
        briteDatabase = sqlBrite.wrapDatabaseHelper(MyOpenHelper.getInstance(this), Schedulers.io());
    }

    public static BriteDatabase getBriteDatabase(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.briteDatabase;
    }

}
