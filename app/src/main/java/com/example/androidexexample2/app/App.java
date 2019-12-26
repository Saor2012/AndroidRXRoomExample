package com.example.androidexexample2.app;

import android.app.Application;

import androidx.room.Room;

import com.example.androidexexample2.data.LocalDataBase;
import com.example.androidexexample2.data.ExampleLocalDB;
import com.example.androidexexample2.presentation.Constants;

import timber.log.BuildConfig;
import timber.log.Timber;

public class App extends Application {
    public static ExampleLocalDB localDB;
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        localDB = provideEntryDataBase();
    }
    private ExampleLocalDB provideEntryDataBase() {
        /*RoomDatabase.Builder<T> tBuilder = Room.databaseBuilder(this, LocalDataBase.class, Constants.NAME_DAO);
        tBuilder.allowMainThreadQueries();
        LocalDataBase base = tBuilder
                .build();
        return base.getNewDAO();*/
        LocalDataBase database = Room.databaseBuilder(this, LocalDataBase.class, Constants.NAME_DAO)
                .allowMainThreadQueries()
                .build();
        return database.getNewDAO();
    }
}
