package com.example.androidexexample2.presentation;

import android.annotation.SuppressLint;

import com.example.androidexexample2.domain.IMainInteractor;
import com.example.androidexexample2.domain.MainInteractor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter implements IMainPresenter.Presenter {
    private IMainPresenter.View view;
    private IMainInteractor interactor;
    private Disposable disposable;

    public MainPresenter() {
        if (interactor == null)
            interactor = new MainInteractor();
        disposable = new CompositeDisposable();
    }

    @Override
    public void startView(IMainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void stopView() {
        if (view != null) view = null;
        if (interactor != null) interactor = null;
        if (!disposable.isDisposed()) disposable.dispose();
        disposable = null;
    }

    @Override
    public void insert(String value) {
        interactor.insert(value)
            .subscribe();

    }

    @SuppressLint("CheckResult")
    @Override
    public void init() {
        disposable = interactor.query()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> view.query(v),
                    throwable -> Timber.e("Error %s", throwable.getMessage()),
                    () -> Timber.e("onComplete"));
    }
}
