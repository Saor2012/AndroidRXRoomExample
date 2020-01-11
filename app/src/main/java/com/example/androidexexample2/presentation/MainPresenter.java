package com.example.androidexexample2.presentation;

import android.annotation.SuppressLint;

import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.domain.IMainInteractor;
import com.example.androidexexample2.domain.MainInteractor;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;

public class MainPresenter implements IMainPresenter.Presenter {
    private IMainPresenter.View view;
    private IMainInteractor interactor;
    private long indexAddElement;

    public MainPresenter() {
        if (interactor == null)
            interactor = new MainInteractor();
        indexAddElement = -1;
    }

    @Override
    public void startView(IMainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void stopView() {
        if (view != null) view = null;
        if (interactor != null) interactor = null;
    }

    @Override
    public void insert(String value) {
        interactor.insert(value)
            /*.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/
            .subscribe(new DisposableSingleObserver<Long>() {
                @Override
                public void onSuccess(Long aLong) {
                    Timber.e("Successfully save at field id %s", indexAddElement = aLong);
                    interactor.queryEntitys()
                        .subscribe(new DisposableSingleObserver<List<Entity>>() {
                            @Override
                            public void onSuccess(List<Entity> entities) {
                                view.insertList(entities);
                                dispose();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.e("Error at interactor.query(): %s", e.getMessage());
                            }
                        });
                    dispose();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error insert at interactor.insert() throw: %s", e.getMessage());
                }
            });
    }



    @Override
    public long getIndex() {
        return 0;
    }

    @Override
    public long getItemPositioin() {
        return 0;/*interactor.getItemPosition(view.getItemValue())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });*/
    }

    @Override
    public void init() {
        interactor.queryEntitys()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DisposableSingleObserver<List<Entity>>() {
                @Override
                public void onSuccess(List<Entity> entities) {
                    view.queryList(entities);
                    dispose();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error at interactor.query(): %s", e.getMessage());
                }
            });
        //view.queryList(null);
    }

    @Override
    public void delete(long position) {
        interactor.delete(position)
            .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                    view.delete(position);
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error at interactor.delete(): %s", e.getMessage());
                }
            });
    }

    @Override
    public void deleteAll() {
        interactor.deleteTable()
            .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                    view.deleteAll();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error at interactor.deleteTable(): %s", e.getMessage());
                }
            });
        interactor.queryEntitys()
            .subscribe(new DisposableSingleObserver<List<Entity>>() {
                @Override
                public void onSuccess(List<Entity> entities) {
                    if (entities.isEmpty()) Timber.e("onNext: DB isn't empty");
                    else Timber.e("onNext: DB is empty");
                    dispose();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error at interactor.queryEntitys(): %s", e.getMessage());
                }
            });
    }

    @Override
    public void onAddClick() {
        insert(view.getAddValue());
    }

    @Override
    public void onDeleteClick(Entity item) {
        delete(item.getPosition());
    }

    @Override
    public void onDeleteAllClick() {
        deleteAll();
    }
}
