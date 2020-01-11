package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IRepository {
    Single<Long> insert(String string);
    Single<List<Entry>> query();
    Single<Long> getIndex();
    Single<Long> getItemPosition(String string);
    Completable deleteEntry(long id);
    Completable deleteEntryTable();
}
