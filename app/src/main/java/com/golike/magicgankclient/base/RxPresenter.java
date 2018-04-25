package com.golike.magicgankclient.base;

import android.app.Activity;

/**
 * Desc:
 * Company: xuehai
 * Copyright: Copyright (c) 2018
 *
 * @author djh
 * @since 2018/04/25 09/04
 */
public class RxPresenter<T extends Activity> implements BasePresenter<T> {

    private T mView;
    @Override
    public void attach(T view) {
        mView=view;
    }

    @Override
    public void detachView() {

    }
}
