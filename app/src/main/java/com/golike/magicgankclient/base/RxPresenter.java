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
public class RxPresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;
    @Override
    public void attach(V view) {
        mView=view;
    }

    @Override
    public void detachView() {

    }
}
