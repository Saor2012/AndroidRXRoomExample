package com.example.androidexexample2.presentation;

import android.annotation.SuppressLint;

import com.example.androidexexample2.domain.IMainInteractor;
import com.example.androidexexample2.domain.MainInteractor;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;

public class MainPresenter implements IMainPresenter.Presenter {
    private IMainPresenter.View view;
    private IMainInteractor interactor;
    //private Disposable disposable;

    public MainPresenter() {
        if (interactor == null)
            interactor = new MainInteractor();
        //disposable = new CompositeDisposable();
    }

    @Override
    public void startView(IMainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void stopView() {
        if (view != null) view = null;
        if (interactor != null) interactor = null;
        /*if (!disposable.isDisposed()) disposable.dispose();
        disposable = null;*/
    }

    @Override
    public void insert(String value) {
        interactor.insert(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DisposableSubscriber<Long>() {
                @Override
                public void onNext(Long aLong) {
                    /*interactor.getEntry(id)
                        .subscribe(new DisposableSubscriber<Entity>() {
                            @Override
                            public void onNext(Entity entity) {
                                view.handleAddItem(entity);
                                dispose();
                            }

                            @Override
                            public void onError(Throwable t) {
                                Timber.e("Exception: getItem at Entity %s", t.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });*/
                    Timber.e("Successfully save at field id %s", aLong);
                    dispose();
                }
                @Override
                public void onError(Throwable t) {
                    Timber.e("Error insert at interactor throw: %s", t.getMessage());
                }
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                }
            });
    }

    @Override
    public void init() {
        /*disposable = interactor.query()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> view.query(v),
                    throwable -> Timber.e("Error %s", throwable.getMessage()),
                    () -> Timber.e("onComplete"));*/
        insert("Initual element");
        interactor.query()
            /*.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/
            /*.subscribe(v -> view.query(v),
                    throwable -> Timber.e("Error %s", throwable.getMessage()),
                    () -> Timber.e("onComplete"));*/
            .subscribe(new DisposableSubscriber<List<String>>() {
                @Override
                public void onNext(List<String> strings) {
                    view.query(strings);
                    dispose();
                }
                @Override
                public void onError(Throwable t) {
                    Timber.e("Error at interactor.query(): %s", t.getMessage());
                }
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                }
            });
    }

    @Override
    public void delete(long position) {
        interactor.delete(position)
            .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e("Error at interactor.delete(): %s", e.getMessage());
                }
            });
    }
}
