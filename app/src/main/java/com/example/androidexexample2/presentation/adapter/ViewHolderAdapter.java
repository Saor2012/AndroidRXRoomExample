package com.example.androidexexample2.presentation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexexample2.data.Entry;
import com.example.androidexexample2.databinding.RvItemBinding;
import com.example.androidexexample2.presentation.IMainPresenter;

public class ViewHolderAdapter extends RecyclerView.ViewHolder {
    private IMainPresenter.Presenter presenter;
    private RvItemBinding binding;
    //private TextView listTextView;

    public ViewHolderAdapter(@NonNull View itemView, IMainPresenter.Presenter presenter) {
        super(itemView);
        this.presenter = presenter;
        //listTextView = null;
        binding = DataBindingUtil.bind(itemView);
        if (binding != null && presenter != null)
            binding.setEvent(presenter);
    }

    public void bind(String str) { //Entry entry
        if (str != null) {
            binding.rvAddedName.setText(str);
        }
    }
}
