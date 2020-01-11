package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.data.Entry;
import com.example.androidexexample2.data.repository.IRepository;
import com.example.androidexexample2.data.repository.Repository;
import com.example.androidexexample2.domain.base.BaseInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import timber.log.Timber;

public class MainInteractor extends BaseInteractor implements IMainInteractor {
    private IRepository repository;

    public MainInteractor() {
        repository = new Repository();
    }

    @Override
    public Single<Long> insert(String string) {
        if (string != null) return repository.insert(string);
        else {
            throw new NullPointerException("Null entry string value");
        }
    }

    @Override
    public Single<List<String>> query() {
        return Single.defer(() -> repository.query())
            .flatMap(entry -> {
                if (entry == null) return Single.error(new Throwable("The list of Entrys dao is null"));
                return Single.just(entry);
            })
            .doOnError(throwable -> Timber.e("Exception: Interactor query on flatMap throw error - %s", throwable.getMessage()))
            .map(entries -> {
                List<String> newList = new ArrayList<String>();
                entries.forEach(entry -> newList.add(entry.getName()));
                return newList;
            })
            .doOnError(throwable -> Timber.e("Exception: Interactor query on map throw error - %s", throwable.getMessage()))
            .compose(applySingleSchedulers());
    }

    @Override
    public Single<List<Entity>> queryEntitys() {
        return Single.defer(() -> repository.query())
            .flatMap(list -> {
                if (list != null && list.size() != 0){
                    return Single.just(list);
                }else {
                    return Single.error(new Throwable("Exception: no records"));
                }
            }).doOnError(throwable -> Timber.e("Exception: Interactor loadListEntity  %s", throwable.getMessage()))
            .flatMap(list -> {
                List<Entity> entityList = new ArrayList<>();
                list.forEach(v -> entityList.add(new Entity(v.getId(),v.getName())));
                return Single.just(entityList);
            });
    }

    @Override
    public Single<Long> getIndex() {
        return Single.defer(() -> repository.getIndex())
            .flatMap(index -> {
                if (index == null) return Single.error(new Throwable("The last index at list of Entrys dao throw null"));
                return Single.just(index);
            }).doOnError(throwable -> Timber.e("Exception: Interactor getIndex on flatMap throw error - %s", throwable.getMessage()))
            .compose(applySingleSchedulers());
    }

    @Override
    public Single<Long> getItemPosition(String string) {
        return Single.defer(() -> repository.getItemPosition(string))
                .flatMap(index -> {
                    if (index == null) return Single.error(new Throwable("The item position at list of Entrys dao throw null"));
                    return Single.just(index);
                }).doOnError(throwable -> Timber.e("Exception: Interactor getItemPosition on flatMap throw error - %s", throwable.getMessage()))
                .compose(applySingleSchedulers());
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
