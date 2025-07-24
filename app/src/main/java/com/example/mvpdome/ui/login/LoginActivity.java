package com.example.mvpdome.ui.login;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.mvpdome.R;
import com.example.mvpdome.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void loginSuccess(Object obj) {

    }

    @Override
    public void loginFailed(String message) {

    }

}
