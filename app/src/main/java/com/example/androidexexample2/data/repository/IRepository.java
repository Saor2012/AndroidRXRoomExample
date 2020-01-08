package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IRepository {
    Flowable<Long> insert(String string);
    Flowable<List<Entry>> query();
    Completable deleteEntry(long id);
    Completable deleteEntryTable();
}
