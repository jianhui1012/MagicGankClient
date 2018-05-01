package com.golike.magicgankclient.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseFragment;
import com.golike.magicgankclient.model.SearchData;
import com.golike.magicgankclient.ui.fragment.adapter.WelfAreAdapter;
import com.golike.magicgankclient.ui.fragment.presenter.WelfArePresenter;
import com.golike.magicgankclient.ui.fragment.view.IWelfAreView;


import butterknife.BindView;

public class WelfAreFragment extends BaseFragment<IWelfAreView, WelfArePresenter> implements IWelfAreView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private WelfAreAdapter adapter;

    private int index = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    public void getBundleData() {

    }

    @Override
    public void initView() {
        if (basePresenter != null)
            basePresenter.loadData(index, 10);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }

    @Override
    public WelfArePresenter initPresenter() {
        return new WelfArePresenter();
    }

    @Override
    public void updateData(SearchData searchData) {
        if (adapter == null) {
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            adapter = new WelfAreAdapter(getActivity(), searchData.getResults());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            //adapter.clear();
            adapter.addAll(searchData.getResults());
        }
    }
}
