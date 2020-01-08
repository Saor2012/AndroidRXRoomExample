package com.example.androidexexample2.presentation;

import com.example.androidexexample2.presentation.base.IBasePresenter;

import java.util.List;

public interface IMainPresenter {
    interface View {
        void initAdapter(List<String> strings);
        void query(List<String> value);
        String getAddValue();
        long getdeleteIndex();
    }
    interface Presenter extends IBasePresenter<View> {
        void insert(String value);
        void init();
        void delete(long position);
        void onAddClick();
        void onDeleteClick();
    }
}
