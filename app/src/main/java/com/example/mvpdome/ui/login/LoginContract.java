package com.example.mvpdome.ui.login;

import android.content.Context;

import com.example.mvpdome.mvp.BasePresenter;
import com.example.mvpdome.mvp.BaseView;

/**
 * MVPPlugin
 */

public class LoginContract {
    interface View extends BaseView {
        //在view层回调
        void loginSuccess(Object obj);

        void loginFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        //在view层调用
        void login(String username,String password);
    }
}
