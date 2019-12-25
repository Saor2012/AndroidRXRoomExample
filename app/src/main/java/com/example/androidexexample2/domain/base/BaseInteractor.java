package com.example.androidexexample2.domain.base;

import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseInteractor {
    private Scheduler workThread;
    private Scheduler observeThread;

    public BaseInteractor() {
        workThread = Schedulers.io();
        observeThread = AndroidSchedulers.mainThread();
    }

    protected <T> SingleTransformer<T, T> applySingleSchedulers() { //Переходить з головного потоку в io() - побічний потік
        return single -> single.subscribeOn(workThread)
                .observeOn(observeThread);
    }
}
