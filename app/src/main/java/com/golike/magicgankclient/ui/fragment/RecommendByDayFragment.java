package com.golike.magicgankclient.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
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

import butterknife.BindView;


/***
 *
 */
public class RecommendByDayFragment extends BaseFragment<IRecommendByDayView, RecommendByDayPresenter> implements IRecommendByDayView {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<SearchData.GANK> adapter;
    private int index = 1;
    private String type;
    private LinearLayoutManager layoutManager;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommendbyday;
    }

    @Override
    public void getBundleData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public RecommendByDayPresenter initPresenter() {
        return new RecommendByDayPresenter();
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
            DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(getActivity(), 0.5f), Util.dip2px(getActivity(), 8), 0);
            itemDecoration.setDrawLastItem(true);
            itemDecoration.setDrawHeaderFooter(true);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setAdapterWithProgress(adapter);
            adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    RollPagerView header = new RollPagerView(getActivity());
                    header.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.GRAY));
                    //header.setHintPadding(0, 0, 0, (int) Utils.convertDpToPixel(8, getActivity()));
                    header.setPlayDelay(2000);
                    //header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Util.convertDpToPixel(200, getActivity())));
                    header.setAdapter(null);
                    return header;
                }

                @Override
                public void onBindView(View headerView) {

                }
            });
            adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    RecyclerView recyclerView = new RecyclerView(parent.getContext()) {
                        //为了不打扰横向RecyclerView的滑动操作，可以这样处理
                        @Override
                        public boolean onTouchEvent(MotionEvent event) {
                            super.onTouchEvent(event);
                            return true;
                        }
                    };
//                    recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) Utils.convertDpToPixel(300, HeaderFooterActivity.this)));
//                    final NarrowImageAdapter adapter;
//                    recyclerView.setAdapter(adapter = new NarrowImageAdapter(parent.getContext()));
//                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false));
//                    recyclerView.addItemDecoration(new SpaceDecoration((int) Utils.convertDpToPixel(8,parent.getContext())));
//
                    return recyclerView;
                }

                @Override
                public void onBindView(View headerView) {
                    //这里的处理别忘了
                    ((ViewGroup) headerView).requestDisallowInterceptTouchEvent(true);
                }
            });
        }
    }
}
