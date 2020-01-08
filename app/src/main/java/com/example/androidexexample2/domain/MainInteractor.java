package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.Entry;
import com.example.androidexexample2.data.repository.IRepository;
import com.example.androidexexample2.data.repository.Repository;
import com.example.androidexexample2.domain.base.BaseInteractor;

import java.util.ArrayList;
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
        //return repository.insert(string);
        if (string != null)
            return repository.insert(string);
        else {
            throw new NullPointerException("Null entry string value");
            //return null;
        }
    }

    @Override
    public Flowable<List<String>> query() { //Flowable<List<String>>
        /*return Flowable.defer(() -> repository.query())
            .compose(applyFlowableSchedulers());*/
        /*return repository.query()
            .compose(applyFlowableSchedulers());*/
        return Flowable.defer(() -> repository.query())
                .flatMap(entry -> {
                    if (entry == null) return Flowable.error(new Throwable("The list of Entrys dao is null"));
                    return Flowable.just(entry);
                })
                .doOnError(throwable -> Timber.e("Exception: Interactor query on flatMap throw error - %s", throwable.getMessage()))
                .map(entries -> {
                    //return new List<String>(){};
                    //new List<String>(Integer.parseInt(entries.toString()));
                    List<String> newList = new ArrayList<String>();
                    entries.forEach(entry -> newList.add(entry.getName()));
                    return newList; //Flowable.just(newList);
                })
                .doOnError(throwable -> Timber.e("Exception: Interactor query on map throw error -  %s", throwable.getMessage()))
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
