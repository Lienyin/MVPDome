package com.example.mvpdome.ui.user;

import android.content.Context;

import com.example.mvpdome.bean.UserInfoEntity;
import com.example.mvpdome.mvp.BasePresenter;
import com.example.mvpdome.mvp.BaseView;

/**
 * MVPPlugin
 */

public class UserContract {
    interface View extends BaseView {
        void userInfoCallBack(UserInfoEntity userInfo);
    }

    interface  Presenter extends BasePresenter<View> {
        void requestNetwork();
        void getUserInfo();
    }
}
