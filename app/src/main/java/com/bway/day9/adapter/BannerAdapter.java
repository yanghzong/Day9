package com.bway.day9.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bway.day9.bean.BannerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 择木 on 2018/12/8.
 */

public class BannerAdapter extends PagerAdapter{

    private Context context;
    private List<BannerBean.DataBean> list;

    public BannerAdapter(Context context, List<BannerBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView sdvimg=new SimpleDraweeView(context);
        sdvimg.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
        sdvimg.setImageURI(Uri.parse(list.get(position).getImagePath()));
        container.addView(sdvimg);
        return sdvimg;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
