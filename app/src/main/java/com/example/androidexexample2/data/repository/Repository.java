package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.app.App;
import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import timber.log.Timber;

public class Repository implements IRepository {

    public Repository() {
    }

    @Override
    public Single<Long> insert(String string) {
        return Single.defer(() -> Single.just(App.localDB.insert(new Entry(string))))
                .doOnError(throwable -> Timber.e("Exception: insert at insert dao throw error: %s", throwable.getMessage()));
    }

    @Override
    public Single<List<Entry>> query() {
        return App.localDB.loadList()
                .doOnError(throwable -> Timber.e("Exception: loadList() dao throw error - %s", throwable.getMessage()));
    }

    @Override
    public Single<Long> getIndex() {
        return App.localDB.getIndex()
                .doOnError(throwable -> Timber.e("Exception: getIndex() dao throw error - %s", throwable.getMessage()));
    }

    @Override
    public Single<Long> getItemPosition(String string) {
        return App.localDB.getItemPosition(string);
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
