package com.example.androidexexample2.presentation;

import com.example.androidexexample2.presentation.base.IBasePresenter;

import java.util.List;

public interface IMainPresenter {
    interface View {
        void query(List<String> value);
    }
    interface Presenter extends IBasePresenter<View> {
        void insert(String value);
        void init();
    }
}
