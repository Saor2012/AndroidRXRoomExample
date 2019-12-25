package com.example.androidexexample2.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ExampleLocalDB {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Entry entry);

    @Query("SELECT * FROM entry")
    Flowable<List<Entry>> loadList();

    @Query("DELETE FROM entry")
    Single daleteEntryTable();
}
