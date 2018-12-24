package com.bway.day9.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.day9.R;
import com.bway.day9.bean.ShowBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.net.URI;
import java.util.List;

/**
 * Created by 择木 on 2018/12/7.
 */

public class MyXRecycleViewAdapter  extends XRecyclerView.Adapter<MyXRecycleViewAdapter.ViewHolder>{
    private Context context;
    private List<ShowBean.DataBean.DatasBean> list;
    private GenericDraweeHierarchy build;

    public MyXRecycleViewAdapter(Context context, List<ShowBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.item_xrecycleview,null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenericDraweeHierarchyBuilder hierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
        RoundingParams circle = RoundingParams.asCircle();
        build = hierarchyBuilder.setRoundingParams(circle).build();
        holder.sdvShow.setHierarchy(build);
        holder.sdvShow.setImageURI(Uri.parse(list.get(position).getEnvelopePic()));
        holder.tvShow.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvShow;
        private final SimpleDraweeView sdvShow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvShow = itemView.findViewById(R.id.tv_show);
            sdvShow = itemView.findViewById(R.id.sdv_show);
        }
    }
}
