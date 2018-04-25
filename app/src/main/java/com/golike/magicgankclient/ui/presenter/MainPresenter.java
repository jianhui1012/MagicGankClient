package com.golike.magicgankclient.ui.presenter;

import com.golike.magicgankclient.base.RxPresenter;
import com.golike.magicgankclient.ui.view.IMainView;

public class MainPresenter extends RxPresenter<IMainView> {


    public void loginExecute() {
        if (mView != null)
            mView.login("", "");
    }

}
