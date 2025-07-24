package com.example.mvpdome.ui.login;

import android.content.Context;

import com.example.mvpdome.api.ApiService;
import com.example.mvpdome.bean.UserInfoEntity;
import com.example.mvpdome.bean.WallPaperResponse;
import com.example.mvpdome.mvp.BasePresenterImpl;
import com.example.mvpdome.network.NetworkApi;
import com.example.mvpdome.network.observer.BaseObserver;
import com.example.mvpdome.network.utils.KLog;

import java.util.List;

/**
 * MVPPlugin
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(String username, String password) {
        NetworkApi.createService(ApiService.class)
                .getWallPaper()
                .compose(NetworkApi.applySchedulers(new BaseObserver<WallPaperResponse>() {
                    @Override
                    public void onSuccess(WallPaperResponse wallPaperResponse) {
                        List<WallPaperResponse.ResBean.VerticalBean> vertical = wallPaperResponse.getRes().getVertical();
                        if (vertical != null && vertical.size() > 0) {
                            String imgUrl = vertical.get(0).getImg();
                            //Glide.with(MainActivity.this).load(imgUrl).into(imageView);
                            mView.loginSuccess(new UserInfoEntity("张三", "18"));
                        } else {
                            //Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        KLog.e("MainActivity", e.toString());
                        mView.loginFailed("登录失败");
                        //Toast.makeText(this, "访问失败", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
