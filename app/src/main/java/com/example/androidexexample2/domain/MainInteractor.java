package com.example.androidexexample2.domain;

import com.example.androidexexample2.data.repository.IRepository;
import com.example.androidexexample2.data.repository.Repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainInteractor implements IMainInteractor {
    private IRepository repository;

    public MainInteractor() {
        repository = new Repository();
    }

    @Override
    public Comparable insert(String string) {
        return repository.insert(string);
    }

    @Override
    public Flowable<List<String>> query() {
        return repository.query();
    }
}
