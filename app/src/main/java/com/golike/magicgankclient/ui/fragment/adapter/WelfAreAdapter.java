package com.golike.magicgankclient.ui.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.golike.magicgankclient.R;
import com.golike.magicgankclient.model.SearchData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author admin
 * @date 2018/2/7
 */

public class WelfAreAdapter extends RecyclerView.Adapter<WelfAreAdapter.ViewHolder> {

    private Context context = null;
    List<SearchData.GANK> dataList = new ArrayList<>();

    public WelfAreAdapter(Context context, List<SearchData.GANK> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setDataList(List<SearchData.GANK> dataList) {
        this.dataList = dataList;
    }

    public void addAll(List<SearchData.GANK> dataList) {
        if (dataList == null)
            return;
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }

    public void clear(){
        if (dataList == null)
            return;
        this.dataList.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchData.GANK gank = this.dataList.get(position);
        if (gank != null) {
            holder.title.setText(gank.desc);
            Glide.with(context)
                    .load(gank.getUrl() + "?imageView2/0/w/720")
                    .into(holder.face_src);
        }
    }

    @Override
    public int getItemCount() {
        return this.dataList == null ? 0 : this.dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.face_src)
        ImageView face_src;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
