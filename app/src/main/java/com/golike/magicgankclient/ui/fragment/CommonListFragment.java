package com.golike.magicgankclient.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseFragment;
import com.golike.magicgankclient.model.SearchData;
import com.golike.magicgankclient.ui.fragment.adapter.viewholder.CommonListHolder;
import com.golike.magicgankclient.ui.fragment.presenter.CommonListPresenter;
import com.golike.magicgankclient.ui.fragment.view.ICommonListView;
import com.golike.magicgankclient.utils.Util;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;

import butterknife.BindView;

public class CommonListFragment extends BaseFragment<ICommonListView, CommonListPresenter> implements ICommonListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<SearchData.GANK> adapter;
    private int index = 1;
    private String type;
    private LinearLayoutManager layoutManager;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_commonlist;
    }

    @Override
    public void getBundleData() {
        type = getArguments().getString("type", "Android");
    }

    @Override
    public void initView() {
        onRefresh();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }

    @Override
    public CommonListPresenter initPresenter() {
        return new CommonListPresenter();
    }


    @Override
    public void showPage(SearchData searchData) {
        if (adapter == null) {
            adapter = new RecyclerArrayAdapter<SearchData.GANK>(getActivity()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new CommonListHolder(parent);
                }
            };
            layoutManager = new LinearLayoutManager(mContext);
            recyclerView.setLayoutManager(layoutManager);
            DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(getActivity(),0.5f), Util.dip2px(getActivity(),8),0);
            itemDecoration.setDrawLastItem(true);
            itemDecoration.setDrawHeaderFooter(true);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setAdapterWithProgress(adapter);
            adapter.setMore(R.layout.view_more, this);
            adapter.setNoMore(R.layout.view_nomore);
            adapter.setOnItemLongClickListener((position)->{
                adapter.remove(position);
                return true;
                });
            adapter.setOnItemClickListener(position -> {
                adapter.getItem(position);
                CommonListFragment.this.getActivity().
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(adapter.getItem(position).getUrl())));
            });
            recyclerView.setRefreshListener(this::onRefresh);
            adapter.clear();
            adapter.addAll(searchData.getResults());
            index++;
        } else {
            adapter.addAll(searchData.getResults());
            index++;
        }
    }

    @Override
    public void onLoadMore() {
        if (basePresenter != null)
            basePresenter.loadData(type, index, 10);
    }

    @Override
    public void onRefresh() {
        index = 1;
        basePresenter.loadData(type, 1, 10);
    }
}
