package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.app.App;
import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Flowable;

public class Repository implements IRepository {

    public Repository() {
    }

    @Override
    public Comparable insert(String string) {
        return App.localDB.insert(new Entry(string));
    }

    @Override
    public Flowable<List<Entry>> query() {
        //return new ArrayList<String>(App.localDB.loadList());
        return App.localDB.loadList();
    }
}
