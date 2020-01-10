package com.example.androidexexample2.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ExampleLocalDB {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Entry entry);

    @Query("SELECT * FROM entry")
    Flowable<List<Entry>> loadList();

    @Query("SELECT MAX(id) FROM entry")
    Single<Long> getIndex();

    @Query("SELECT id FROM entry WHERE name = :string")
    Single<Long> getItemPosition(String string);

    @Query("DELETE FROM entry WHERE id = :id")
    Completable deleteElement(long id);

    @Query("DELETE FROM entry")
    Completable daleteEntryTable();
}
