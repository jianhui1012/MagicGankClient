package com.golike.magicgankclient.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IBaseView, T extends RxPresenter<V>> extends Fragment implements IBaseView {

    protected Context mContext;
    protected T basePresenter;
    private Unbinder unbinder;

    protected View mRootView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        basePresenter = initPresenter();
        basePresenter.attach((V) this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundleData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        mRootView = view;
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        if (basePresenter != null) {
            basePresenter.detachView();
        }
        super.onDetach();
    }

    public abstract int getLayoutId();

    public abstract void getBundleData();

    public abstract void initView();

    public abstract T initPresenter();

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
