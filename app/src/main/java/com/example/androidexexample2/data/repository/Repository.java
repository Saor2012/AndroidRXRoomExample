package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.app.App;
import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import timber.log.Timber;

public class Repository implements IRepository {

    public Repository() {
    }

    @Override
    public Flowable<Long> insert(String string) {
        return Flowable.defer(() -> Flowable.just(App.localDB.insert(new Entry(string))))
                .doOnError(throwable -> Timber.e("Exception: insert at insert dao throw error: %s", throwable.getMessage()));
    }

    @Override
    public Flowable<List<String>> query() { //Flowable<List<String>>
        //return new ArrayList<String>(App.localDB.loadList());
        //return Flowable.just(App.localDB.loadList()).flatMap(entry -> entry);
        return App.localDB.loadList()
            .doOnError(throwable -> Timber.e("Exception: loadList() dao throw error - %s", throwable.getMessage()));
    }

    @Override
    public Completable deleteEntry(long id) {
        return App.localDB.deleteElement(id)
                .doOnError(throwable -> Timber.e("Exception: deleteEntry() dao throw error - %s", throwable.getMessage()));
    }


    @Override
    public Completable deleteEntryTable() {
        return App.localDB.daleteEntryTable()
            .doOnError(throwable -> Timber.e("Exception: deleteEntryTable() dao throw error - %s", throwable.getMessage()));
    }
}
