package com.example.androidexexample2.presentation.base;

public interface IBasePresenter<V> {
    void startView(V view);
    void stopView();
}
