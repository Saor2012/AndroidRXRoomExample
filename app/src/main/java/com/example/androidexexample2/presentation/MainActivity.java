package com.example.androidexexample2.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexexample2.R;
import com.example.androidexexample2.data.Entity;
import com.example.androidexexample2.databinding.ActivityMainBinding;
import com.example.androidexexample2.presentation.adapter.ExampleAdapter;
import com.example.androidexexample2.presentation.base.BaseActivity;
import com.example.androidexexample2.presentation.base.IBasePresenter;

import java.util.List;

//import javax.annotation.CheckReturnValue;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements IMainPresenter.View {
    private IMainPresenter.Presenter presenter;
    private ExampleAdapter adapter;

    /*@SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.appCompatEditText);
        textView = findViewById(R.id.appCompatTextView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter = new ExampleAdapter(presenter));
        button = findViewById(R.id.appCompatButton);
        button.setOnClickListener(v -> {
            String str = String.valueOf(editText.getText());
            textView.setText(str);
            presenter.insert(str);
        });
        //adapter = new ExampleAdapter()
    }*/

    /*protected void onStart() {
        super.onStart();

    }*/

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        presenter = new MainPresenter();
        getBinding().setEvent(presenter);
    }

    /*@Override
    public void onDestroy() {
        if (presenter != null)
            presenter.stopView();
        presenter = null;
        super.onDestroy();
        //super.onDestroy();
    }*/

    @Override
    protected void onStartView() {
        presenter.startView(this);
        presenter.init();
    }

    @Override
    protected void onDestroyView() {
        if (presenter != null) presenter.stopView();
        presenter = null;
        if (adapter != null) adapter = null;

    }

    @Override
    protected IBasePresenter getPresenter() {
        return presenter;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initAdapter(List<String> strings) {
        if (presenter != null) {
            getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
            getBinding().recyclerView.setAdapter(adapter = new ExampleAdapter(presenter, null)); //<-- Need strings list, v1

        }
    }

    @Override
    public void query(List<String> value) {
        /*if (adapter != null && value != null)
            adapter.addNewItem(value);
        else {
            initAdapter(value);
        }*/
        //if (adapter == null) initAdapter(value);
        if (value != null) initAdapter(value);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void queryList(List<Entity> entities) {
        if (entities != null && entities.size() != 0){
            getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
            adapter = new ExampleAdapter(presenter, entities);
            getBinding().recyclerView.setAdapter(adapter);
            //adapter.notifyDataSetChanged(); //View list
        }
    }

    @Override
    public void insertList(List<Entity> list) {
        if (adapter != null) adapter.addNewItem(list);
    }

    @Override
    public String getAddValue() {
        String value = String.valueOf(getBinding().appCompatEditText.getText());
        if (!value.equals("")) {
            getBinding().appCompatTextView.setText(value);
            getBinding().appCompatEditText.setText("");
            return value;
        } else {
            getBinding().appCompatTextView.setText("Hello world");
            toast("Invalid value");
            return null;
        }
    }

    /*@Override
    public long getDeleteIndex() {
        //presenter.
        return 0;
    }

    @Override
    public String getItemValue() {
        return "Str";
    }*/

    /*@Override
    public void insert() {
        adapter.addNewItem();
    }*/

    @Override
    public void delete(long position) {
        adapter.deleteItem((int)position);
    }

    @Override
    public void deleteAll() {
        adapter.deleteAll();
    }
}
