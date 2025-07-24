package com.example.mvpdome.ui.seting;

import android.content.Context;

import com.example.mvpdome.mvp.BasePresenter;
import com.example.mvpdome.mvp.BaseView;

/**
 * MVPPlugin
 */

public class SetingContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
