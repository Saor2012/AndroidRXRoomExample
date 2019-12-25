package com.example.androidexexample2.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entry.class}, version = 1)
public abstract class LocalDataBase extends RoomDatabase {
    public abstract ExampleLocalDB getNewDAO();
}
