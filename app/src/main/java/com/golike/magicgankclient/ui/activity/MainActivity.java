package com.golike.magicgankclient.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseActivity;
import com.golike.magicgankclient.ui.adapter.ViewPagerFragmentAdapter;
import com.golike.magicgankclient.ui.fragment.CommonListFragment;
import com.golike.magicgankclient.ui.fragment.RecommendByDayFragment;
import com.golike.magicgankclient.ui.fragment.WelfAreFragment;
import com.golike.magicgankclient.ui.activity.presenter.MainPresenter;
import com.golike.magicgankclient.ui.activity.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/***
 *
 */
public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.mainTabLayout)
    TabLayout mTabLayout;

    List<Fragment> mFragments;
    FragmentManager mFragmentManager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    final String[] titles = {"每日推荐", "福利", "干货定制", "Android"};

    @Override
    public void login(String name, String pwd) {
        basePresenter.loginExecute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragments();
        initViewPager();
    }

    private void initViewPager() {
        for (String title : titles) {
            TabLayout.Tab tab = mTabLayout.newTab();
            //tab.setText(title);
            mTabLayout.addTab(tab);
        }
        mFragmentManager = this.getSupportFragmentManager();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, mFragments);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //设置文本需要在setupWithViewPager之后不然无效
        mTabLayout.getTabAt(0).setText(titles[0]);
        mTabLayout.getTabAt(1).setText(titles[1]);
        mTabLayout.getTabAt(2).setText(titles[2]);
        mTabLayout.getTabAt(3).setText(titles[3]);
    }

    private void initFragments() {
        mFragments = new ArrayList<Fragment>() {
        };
        mFragments.add(new RecommendByDayFragment());
        mFragments.add(new WelfAreFragment());

        mFragments.add(setArguments(new CommonListFragment(),"休息视频"));
        mFragments.add(setArguments(new CommonListFragment(),"Android"));
    }

    public Fragment setArguments(Fragment fragment,String type){
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
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
