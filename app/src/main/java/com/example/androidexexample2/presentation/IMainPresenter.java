package com.example.androidexexample2.presentation;

import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.presentation.base.IBasePresenter;

import java.util.List;

public interface IMainPresenter {
    interface View {
        void initAdapter(List<String> strings);
        void query(List<String> value);
        void queryList(List<Entity> value);
        void insertList(List<Entity> list);
        String getAddValue();
        long getDeleteIndex();
        String getItemValue();
        //void insert();
        void delete(long position);
        void deleteAll();
    }
    interface Presenter extends IBasePresenter<View> {
        void insert(String value);
        long getIndex();
        long getItemPositioin();
        void init();
        void delete(long position);
        void deleteAll();
        void onAddClick();
        void onDeleteClick(Entity item);
        void onDeleteAllClick();
    }
}
