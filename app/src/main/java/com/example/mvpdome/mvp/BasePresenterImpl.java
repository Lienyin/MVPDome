package com.example.mvpdome.mvp;

/**
 * MVPPlugin
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
    protected V mView;
    @Override
    public void attachView(V view) {
        mView=view;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
