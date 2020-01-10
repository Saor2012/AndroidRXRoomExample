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
    //private Disposable disposable;

    public MainPresenter() {
        if (interactor == null)
            interactor = new MainInteractor();
        //disposable = new CompositeDisposable();
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
                    Timber.e("Successfully save at field id %s", indexAddElement = aLong);
                    /*interactor.query()
                        .subscribe(new DisposableSubscriber<List<String>>() {
                            @Override
                            public void onNext(List<String> strings) {
                                view.query(strings);
                                Timber.e("Successfully insert list");
                                dispose();
                            }

                            @Override
                            public void onError(Throwable t) {
                                Timber.e("Error insert at interactor.query() throw: %s", t.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Timber.e("onComplete");
                            }
                        });*/
                    interactor.queryEntitys()
                        .subscribe(new DisposableSubscriber<List<Entity>>() {
                            @Override
                            public void onNext(List<Entity> strings) {
                                view.insertList(strings);
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
                    dispose();
                }
                @Override
                public void onError(Throwable t) {
                    Timber.e("Error insert at interactor.insert() throw: %s", t.getMessage());
                }
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
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
        /*disposable = interactor.query()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> view.query(v),
                    throwable -> Timber.e("Error %s", throwable.getMessage()),
                    () -> Timber.e("onComplete"));*/
        //insert("Initual element");
        //interactor.query()
        interactor.queryEntitys()
            /*.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/
            /*.subscribe(v -> view.query(v),
                    throwable -> Timber.e("Error %s", throwable.getMessage()),
                    () -> Timber.e("onComplete"));*/
            .subscribe(new DisposableSubscriber<List<Entity>>() {
                @Override
                public void onNext(List<Entity> strings) {
                    view.queryList(strings);
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
            .subscribe(new DisposableSubscriber<List<Entity>>() {
                @Override
                public void onNext(List<Entity> strings) {
                    if (strings.isEmpty()) Timber.e("onNext: DB isn't empty");
                    else Timber.e("onNext: DB is empty");
                    dispose();
                }

                @Override
                public void onError(Throwable t) {
                    Timber.e("Error at interactor.queryEntitys(): %s", t.getMessage());
                }
                @Override
                public void onComplete() {
                    Timber.e("onComplete");
                }
            });
    }

    @Override
    public void onAddClick() {
        insert(view.getAddValue());
    }

    @Override
    public void onDeleteClick(Entity item) {
//        delete(view.getDeleteIndex());
        delete(item.getPosition());
    }

    @Override
    public void onDeleteAllClick() {
        deleteAll();
    }
}
