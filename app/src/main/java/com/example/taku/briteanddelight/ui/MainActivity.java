package com.example.taku.briteanddelight.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.taku.briteanddelight.MyApplication;
import com.example.taku.briteanddelight.R;
import com.example.taku.briteanddelight.data.User;
import com.squareup.sqlbrite.BriteDatabase;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    BriteDatabase db;
    Subscription userSubscription;

    RecyclerView recyclerView;
    UserAdapter userAdapter;

    EditText firstNameEditText;
    EditText lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        db = MyApplication.getBriteDatabase(this);
        userSubscription = db.createQuery(User.TABLE_NAME, User.SELECT_ALL)
                .mapToList(User.MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userAdapter);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        findViewById(R.id.addButton).setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            db.insert(User.TABLE_NAME, User.FACTORY.marshal()
                    .first_name(firstName)
                    .last_name(lastName)
                    .asContentValues());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userSubscription.unsubscribe();
    }
}
