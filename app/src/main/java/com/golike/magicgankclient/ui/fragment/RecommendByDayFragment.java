package com.golike.magicgankclient.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseFragment;
import com.golike.magicgankclient.model.SearchData;
import com.golike.magicgankclient.ui.fragment.adapter.viewholder.CommonListHolder;
import com.golike.magicgankclient.ui.fragment.presenter.RecommendByDayPresenter;
import com.golike.magicgankclient.ui.fragment.view.IRecommendByDayView;
import com.golike.magicgankclient.utils.Util;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;

import butterknife.BindView;


/***
 *
 */
public class RecommendByDayFragment extends BaseFragment<IRecommendByDayView, RecommendByDayPresenter> implements IRecommendByDayView {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<SearchData.GANK> adapter;

    final String[] titles = {"干货闲读", "每日干货推荐", "新电影热映榜"};
    final int[] icons = { R.mipmap.home_icon_fm,R.mipmap.home_icon_day,R.mipmap.home_icon_rank};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommendbyday;
    }

    @Override
    public void getBundleData() {

    }

    @Override
    public void initView() {
        if (adapter == null) {
            recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<SearchData.GANK>(getActivity()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new CommonListHolder(parent);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(getActivity(), 0.5f), Util.dip2px(getActivity(), 8), 0);
            itemDecoration.setDrawLastItem(true);
            itemDecoration.setDrawHeaderFooter(true);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setRefreshListener(()->{
                recyclerView.postDelayed(()->{
                    adapter.clear();
                    adapter.addAll(new ArrayList<>());
                }, 1500);});
            adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recommendbyday_listview_header, parent, false);
                    RollPagerView rollpagerview = root.findViewById(R.id.rollpagerview);
                    TabLayout recommendTabLayout = root.findViewById(R.id.recommendTabLayout);
                    rollpagerview.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.GRAY));
                    rollpagerview.setPlayDelay(2000);
                    rollpagerview.setAdapter(new BannerAdapter(getActivity()));
                    for(int i=0;i<titles.length;i++){
                        TabLayout.Tab tab = recommendTabLayout.newTab();
                        tab.setText(titles[i]);
                        tab.setIcon(icons[i]);
                        recommendTabLayout.addTab(tab);
                    }
                    return root;
                }

                @Override
                public void onBindView(View headerView) {
                }
            });
            adapter.addAll(new ArrayList<>());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }

    @Override
    public RecommendByDayPresenter initPresenter() {
        return new RecommendByDayPresenter();
    }

    @Override
    public void showPage(SearchData searchData) {

    }
}
