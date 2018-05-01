package com.golike.magicgankclient.ui.fragment.adapter.viewholder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.golike.magicgankclient.R;
import com.golike.magicgankclient.model.SearchData;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class CommonListHolder extends BaseViewHolder<SearchData.GANK> {
    private TextView gank_desc;
    private ImageView gank_icon;
    private TextView gank_name;
    private TextView gank_date;

    public CommonListHolder(ViewGroup parent) {
        super(parent, R.layout.item_commonlist);
        gank_desc = $(R.id.gank_desc);
        gank_icon = $(R.id.gank_icon);
        gank_name = $(R.id.gank_name);
        gank_date = $(R.id.gank_date);
    }

    @Override
    public void setData(final SearchData.GANK gank) {
        Log.i("ViewHolder", "position" + getDataPosition());
        if (gank == null)
            return;

        gank_desc.setText(gank.desc);
        gank_name.setText(gank.getWho());
        gank_date.setText(gank.getPublishedAt());
        if (gank.getImages().size() > 0) {
            Glide.with(getContext())
                    .load(gank.getImages().get(0))
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(gank_icon);
        } else {
            gank_icon.setVisibility(View.GONE);
        }
    }
}