package com.example.mvpdome.mvp;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView{
    public T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);

        initData(savedInstanceState);
        onDestroyActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    //下面这5个方法是子类必须实现的，分别是layout布局、toolbar、mvp的persenter初始化、
    //onCreate内的initData、以及页面销毁的onDestroyActivity（可以根据自己的需要添加）
    public abstract int setContentLayout();

    public abstract void initData(Bundle savedInstanceState);

    public abstract void onDestroyActivity();
}
