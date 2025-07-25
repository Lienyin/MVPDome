package com.example.mvpdome.ui.user;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpdome.MainActivity;
import com.example.mvpdome.R;
import com.example.mvpdome.bean.UserInfoEntity;
import com.example.mvpdome.bean.WallPaperResponse;
import com.example.mvpdome.mvp.MVPBaseActivity;
import com.example.mvpdome.network.utils.KLog;
import com.example.mvpdome.util.ImageDownloader;


/**
 * MVPPlugin
 */

public class UserActivity extends MVPBaseActivity<UserContract.View, UserPresenter> implements UserContract.View {

    private RecyclerView rv_list_view;
    private LinearLayout ll_url_view;
    private Button btn_url;
    private boolean isQie = true;
    private WebView wv_rul;
    private UserAdapter adapter;
    private String imageUrl;
    private ImageDownloader imageDownloader;
    @Override
    public int setContentLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        rv_list_view = findViewById(R.id.rv_list_view);
        ll_url_view = findViewById(R.id.ll_url_view);
        wv_rul = findViewById(R.id.wv_rul);
        btn_url = findViewById(R.id.btn_url);
        mPresenter.getUserInfo();
        WebSettings settings = wv_rul.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//设定支持 viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);//设定支持缩放

        adapter = new UserAdapter(this);
        rv_list_view.setLayoutManager(new GridLayoutManager(this,2));
        rv_list_view.setAdapter(adapter);
        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String url) {
                if (url != null){
                    isQie = false;
                    wv_rul.setVisibility(View.VISIBLE);
                    rv_list_view.setVisibility(View.GONE);
                    imageUrl = url;
                    wv_rul.loadUrl(url);
                }else {
                    wv_rul.setVisibility(View.GONE);
                    rv_list_view.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isQie){
                    isQie = false;
                    wv_rul.setVisibility(View.VISIBLE);
                    rv_list_view.setVisibility(View.GONE);
                }else {
                    isQie = true;
                    wv_rul.setVisibility(View.GONE);
                    rv_list_view.setVisibility(View.VISIBLE);
                }
            }
        });

        //长按保存图片
        imageDownloader = new ImageDownloader(this);
        wv_rul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageDownloader.saveImage(imageUrl);
                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ImageDownloader.REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限已授予，可以重新尝试保存图片
                imageDownloader.saveImage(imageUrl);
            } else {
                Toast.makeText(this, "存储权限被拒绝，无法保存图片", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onDestroyActivity() {

    }

    @Override
    public void userInfoCallBack(WallPaperResponse.ResBean data) {
        adapter.setData(data.getVertical());
//        Toast.makeText(this, "数据=="+userInfo.name, Toast.LENGTH_SHORT).show();
    }
}
