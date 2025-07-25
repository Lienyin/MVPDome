package com.example.mvpdome.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        initData(savedInstanceState);
    }

    public abstract int setContentLayout();
    public abstract void initData(Bundle savedInstanceState);
}
