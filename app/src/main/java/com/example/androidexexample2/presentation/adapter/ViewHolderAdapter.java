package com.example.androidexexample2.presentation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.data.Entry;
import com.example.androidexexample2.databinding.RvItem2Binding;
//import com.example.androidexexample2.databinding.RvItemBinding;
import com.example.androidexexample2.presentation.IMainPresenter;

public class ViewHolderAdapter extends RecyclerView.ViewHolder {
    private IMainPresenter.Presenter presenter;
    private RvItem2Binding binding;

    public ViewHolderAdapter(@NonNull View itemView, IMainPresenter.Presenter presenter) {
        super(itemView);
        this.presenter = presenter;
        binding = DataBindingUtil.bind(itemView);
        if (binding != null && presenter != null)
            binding.setEvent(presenter);
    }

    public void bind(Entity item, int position) { //Entry entry
        if (item != null) {
            if (position != 0 || position != -1) item.setPosition(position);
            else item.setPosition((int) item.getId());
            binding.setEntity(item);
            binding.rvAddedName.setText(item.getName());

        }
    }
}
