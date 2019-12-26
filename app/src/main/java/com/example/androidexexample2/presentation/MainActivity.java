package com.example.androidexexample2.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexexample2.R;
import com.example.androidexexample2.presentation.adapter.ExampleAdapter;

import java.util.List;

import javax.annotation.CheckReturnValue;

public class MainActivity extends AppCompatActivity implements IMainPresenter.View {
    private IMainPresenter.Presenter presenter;
    private EditText editText;
    private TextView textView;
    private Button button;
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;

    @SuppressLint("WrongConstant")
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
    }

    protected void onStart() {
        super.onStart();
        presenter.startView(this);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.stopView();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void query(List<String> value) {
        adapter.addNewItem(value);
    }
}
