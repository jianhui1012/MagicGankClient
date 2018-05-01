package com.golike.magicgankclient.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.bugtags.library.Bugtags;
import com.golike.magicgankclient.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/24.
 */

public abstract class BaseActivity<V extends IBaseView, T extends RxPresenter<V>> extends AppCompatActivity implements IBaseView {

    protected Context mContext;
    protected T basePresenter;
    private Unbinder unbinder;

    protected Toolbar mCommonToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        transparent19and20();
        basePresenter = initPresenter();
        basePresenter.attach((V) this);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null) {
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        initData();
        initView();
        //沉浸式状态栏设置
        statusBarColor();
    }

    public abstract void initToolBar();

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initView();

    public abstract T initPresenter();

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    int statusBarColor;

    protected void statusBarColor() {
        final int tmpStatusBarColor = getTheme().obtainStyledAttributes(new int[]{R.attr.baseStatusBarColor}).getColor(0, Color.TRANSPARENT);
        getWindow().getDecorView().post(() -> setStatusBarColor(tmpStatusBarColor));
    }

    public void setStatusBarColor(int statusBarColor) {
        if(this.statusBarColor == statusBarColor){
            return;
        }
        this.statusBarColor = statusBarColor;
        initSystemBar();
    }

    SystemBarTintManager tintManager;

    protected void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= 21) {
                fixMStatusBar();
            }
        }
        // 创建状态栏的管理实例
        tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        // 设置一个颜色给系统栏
        tintManager.setTintColor(statusBarColor);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void fixMStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(statusBarColor);// SDK21
    }


    @Override
    protected void onDestroy() {
        if (basePresenter != null) {
            basePresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
