package com.example.androidexexample2.data.repository;

import com.example.androidexexample2.data.Entry;

import java.util.List;

import io.reactivex.Flowable;

public interface IRepository {
    Comparable insert(String string);
    Flowable<List<Entry>> query();
}
