package com.golike.magicgankclient.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseActivity;
import com.golike.magicgankclient.ui.presenter.MainPresenter;
import com.golike.magicgankclient.ui.view.IMainView;

import butterknife.BindView;
import butterknife.OnClick;

/***
 *
 */
public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.viewPager)
    private ViewPager viewPager;

    @Override
    public void login(String name, String pwd) {
        basePresenter.loginExecute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        mCommonToolbar.setTitle(R.string.app_name);
        //mCommonToolbar.setTitleTextColor(getResources().getColor(Theme.);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra("data");
    }

    @Override
    public void initView() {
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({})
    public void onClick(@NonNull View view) {
    }


}
