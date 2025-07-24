package com.example.mvpdome.ui.seting;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mvpdome.R;
import com.example.mvpdome.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 */

public class SetingActivity extends MVPBaseActivity<SetingContract.View, SetingPresenter> implements SetingContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_seting);
    }
}
