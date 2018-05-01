package com.golike.magicgankclient.ui.fragment.presenter;

import com.golike.magicgankclient.base.RxPresenter;
import com.golike.magicgankclient.http.HttpUtils;
import com.golike.magicgankclient.model.SearchData;
import com.golike.magicgankclient.ui.fragment.view.ICommonListView;

import rx.Subscriber;

public class CommonListPresenter extends RxPresenter<ICommonListView> {

    public void loadData(String type, int index, int count) {
        HttpUtils.getInstance().getHomeData(new Subscriber<SearchData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SearchData searchData) {
                if (mView != null)
                    mView.showPage(searchData);
            }
        }, type, count, index);
    }


}
