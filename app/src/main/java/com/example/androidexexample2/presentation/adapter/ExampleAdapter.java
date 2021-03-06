package com.example.androidexexample2.presentation.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexexample2.R;
import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.presentation.IMainPresenter;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ExampleAdapter extends RecyclerView.Adapter<ViewHolderAdapter> {
    private List<Entity> list;
    private IMainPresenter.Presenter presenter;

    public ExampleAdapter(IMainPresenter.Presenter presenter, List<Entity> strings) {
        this.list = strings;
        Timber.e("Current list size %s", getItemCount());
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item2, parent, false), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {
        if (list.size() != 0)
            holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addNewItem(List<Entity> list) {
        this.list = list;
        notifyItemChanged(getItemCount() - 1);
    }

    public void deleteItem(int position) {
        if (position != -1) {
            if (list.remove(position) == null) {
                Timber.e("Error at adapter.deleteItem() with position %s, list size %s", position, getItemCount());
            } else {
                Timber.e("Sucsessful delete item with position %s, list size %s", position, getItemCount());
            }
            notifyItemChanged(position);
        }
    }

    public void deleteAll() {
        list.clear();
        if (list.isEmpty() && list.size() == 0) Timber.e("List size: %s", getItemCount());
        else Timber.e("List size: %s", getItemCount());
        notifyDataSetChanged();
    }

}
