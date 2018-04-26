package com.golike.magicgankclient.ui.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.golike.magicgankclient.R;
import com.golike.magicgankclient.base.BaseActivity;
import com.golike.magicgankclient.http.HttpUtils;
import com.golike.magicgankclient.model.SearchData;
import com.golike.magicgankclient.ui.presenter.MainPresenter;
import com.golike.magicgankclient.ui.view.IMainView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity2 extends BaseActivity<IMainView, MainPresenter>  {

    private Subscriber<SearchData> subscriber;


    enum Sort {
        Pop, Choose
    }

    @BindView(R.id.getData)
    public Button getData;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    private RecyclerView.Adapter adapter = new NewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int[] arrary = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        getData.setText(SortHelper.getInstance().sort(arrary, Sort.Choose));

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initData() {

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

    @OnClick({R.id.getData, R.id.recyclerView})
    public void onClick(@NonNull View view) {
        if (view.getId() == R.id.getData) {
            subscriber = new Subscriber<SearchData>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity2.this, "请求完成", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(SearchData searchData) {
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new NewAdapter(MainActivity2.this, searchData));
                    //adapter.notifyDataSetChanged();
                }
            };
            HttpUtils.getInstance().getHomeData(subscriber, "福利", 10, 1);
        } else if (view.getId() == R.id.recyclerView) {

        }
    }


}
