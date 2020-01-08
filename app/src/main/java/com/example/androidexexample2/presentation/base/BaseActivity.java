package com.example.androidexexample2.presentation.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity {
    private Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStartView();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected Binding getBinding(){
        return binding;
    }

    protected abstract void initView();


    @Override
    public void onDestroy() {
        if (getPresenter() != null) {
            onDestroyView();
            getPresenter().stopView();
        }
        super.onDestroy();
    }

    protected abstract void onStartView();

    protected abstract void onDestroyView();

    protected abstract IBasePresenter getPresenter();

    protected void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
