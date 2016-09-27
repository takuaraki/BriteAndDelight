package com.example.taku.briteanddelight.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.taku.briteanddelight.MyApplication;
import com.example.taku.briteanddelight.R;
import com.example.taku.briteanddelight.data.User;
import com.squareup.sqlbrite.BriteDatabase;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Subscription userSubscription;

    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        BriteDatabase db = MyApplication.getBriteDatabase(this);
        userSubscription = db.createQuery(User.TABLE_NAME, User.SELECT_ALL)
                .mapToList(User.MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userSubscription.unsubscribe();
    }
}
