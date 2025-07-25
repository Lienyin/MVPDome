package com.example.mvpdome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvpdome.api.ApiService;
import com.example.mvpdome.base.BaseActivity;
import com.example.mvpdome.bean.WallPaperResponse;
import com.example.mvpdome.network.NetworkApi;
import com.example.mvpdome.network.observer.BaseObserver;
import com.example.mvpdome.network.utils.KLog;
import com.example.mvpdome.ui.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<String> mData = new ArrayList<>();
    private ImageView iv_next;

    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        iv_next = findViewById(R.id.iv_next);
        iv_next.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        });
    }

}