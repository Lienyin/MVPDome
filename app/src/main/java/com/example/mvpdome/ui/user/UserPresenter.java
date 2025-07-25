package com.example.mvpdome.ui.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.example.mvpdome.MainActivity;
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

public class UserPresenter extends BasePresenterImpl<UserContract.View> implements UserContract.Presenter{

    /**
     * 访问网络
     */
    public void requestNetwork() {
        NetworkApi.createService(ApiService.class)
                .getWallPaper()
                .compose(NetworkApi.applySchedulers(new BaseObserver<WallPaperResponse>() {
                    @Override
                    public void onSuccess(WallPaperResponse wallPaperResponse) {
                        List<WallPaperResponse.ResBean.VerticalBean> vertical = wallPaperResponse.getRes().getVertical();
                        if (vertical != null && vertical.size() > 0) {
                            String imgUrl = vertical.get(0).getImg();
                            //Glide.with(MainActivity.this).load(imgUrl).into(imageView);
                        } else {
                            //Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        KLog.e("MainActivity", e.toString());
                        //Toast.makeText(this, "访问失败", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public void getUserInfo() {
        NetworkApi.createService(ApiService.class)
                .getWallPaper()
                .compose(NetworkApi.applySchedulers(new BaseObserver<WallPaperResponse>() {
                    @Override
                    public void onSuccess(WallPaperResponse wallPaperResponse) {
                        List<WallPaperResponse.ResBean.VerticalBean> vertical = wallPaperResponse.getRes().getVertical();
                        if (vertical != null && vertical.size() > 0) {
                            String imgUrl = vertical.get(0).getImg();
                            //Glide.with(MainActivity.this).load(imgUrl).into(imageView);
                            mView.userInfoCallBack(wallPaperResponse.getRes());
                        } else {
                            KLog.e("TAG", "数据为空");
                            //Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        KLog.e("TAG===访问失败", e.toString());
                        //Toast.makeText(this, "访问失败", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
