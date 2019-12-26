package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IMainInteractor {
    ///Single<String> insert(String string);
    Flowable<Long> insert(String string);
    Flowable<List<String>> query();
    Completable delete(long id);
    Completable deleteTable();
}
