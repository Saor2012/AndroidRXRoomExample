package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.Entry;
import com.example.androidexexample2.data.repository.IRepository;
import com.example.androidexexample2.data.repository.Repository;
import com.example.androidexexample2.domain.base.BaseInteractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import timber.log.Timber;

public class MainInteractor extends BaseInteractor implements IMainInteractor {
    private IRepository repository;

    public MainInteractor() {
        repository = new Repository();
    }

    @Override
    public Flowable<Long> insert(String string) {
        /*return Flowable.defer(() -> Flowable.just(repository.insert(string))) //Long.valueOf(
            .doOnError(throwable -> Timber.e("Exeption: MainInteractor insert() throw: %s",throwable.getMessage()))
            .compose(applyFlowableSchedulers());*/
        /*return Flowable.just(repository.insert(string))
                .doOnError(throwable -> Timber.e("Exeption: MainInteractor insert() throw: %s",throwable.getMessage()));*/
        return repository.insert(string);
    }

    @Override
    public Flowable<List<String>> query() { //Flowable<List<String>>
        /*return Flowable.defer(() -> repository.query())
            .compose(applyFlowableSchedulers());*/
        return repository.query()
            .compose(applyFlowableSchedulers());
    }

    @Override
    public Completable delete(long id) {
        return Completable.fromAction(() -> repository.deleteEntry(id));
    }

    @Override
    public Completable deleteTable() {
        return Completable.fromAction(() -> repository.deleteEntryTable());
    }
}
