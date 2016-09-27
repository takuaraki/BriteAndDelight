package com.example.taku.briteanddelight.data;

import android.database.Cursor;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import rx.functions.Func1;

@AutoValue
public abstract class User implements UserModel, Parcelable {

    public static final Factory<User> FACTORY = new Factory<>(AutoValue_User::new);

    public static final Func1<Cursor, User> MAPPER = cursor -> new Mapper<>(FACTORY).map(cursor);

}
