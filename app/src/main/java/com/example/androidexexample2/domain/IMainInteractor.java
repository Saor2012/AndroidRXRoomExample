package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IMainInteractor {
    ///Single<String> insert(String string);
    Single<Long> insert(String string);
    Single<List<String>> query();
    Single<List<Entity>> queryEntitys();
    Single<Long> getIndex();
    Single<Long> getItemPosition(String string);
    Completable delete(long id);
    Completable deleteTable();
}
