package com.example.mvpdome.ui.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvpdome.R;
import com.example.mvpdome.bean.UserInfoEntity;
import com.example.mvpdome.mvp.MVPBaseActivity;
import com.example.mvpdome.network.utils.KLog;


/**
 * MVPPlugin
 */

public class UserActivity extends MVPBaseActivity<UserContract.View, UserPresenter> implements UserContract.View {

    private TextView tv_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tv_name = findViewById(R.id.tv_name);
        KLog.e("TAG", "=====111111");
        mPresenter.getUserInfo();
    }

    @Override
    public void userInfoCallBack(UserInfoEntity userInfo) {
        KLog.e("TAG", "====2222");
        tv_name.setText(userInfo.name);
        Toast.makeText(this, "数据=="+userInfo.name, Toast.LENGTH_SHORT).show();
    }
}
