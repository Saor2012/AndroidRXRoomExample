package com.example.androidexexample2.domain;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IMainInteractor {
    ///Single<String> insert(String string);
    Comparable insert(String string);
    Flowable<List<String>> query();
}
