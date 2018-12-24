package com.bway.day9;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bway.day9.adapter.BannerAdapter;
import com.bway.day9.adapter.MyXRecycleViewAdapter;
import com.bway.day9.bean.BannerBean;
import com.bway.day9.bean.ShowBean;
import com.bway.day9.inter.ICallBack;
import com.bway.day9.inter.MynetInterface;
import com.bway.day9.utils.HttpUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private XRecyclerView xrvShow;
    private MyXRecycleViewAdapter myXrecycleViewAdapter;
    private ViewPager vpBanner;
    private MynetInterface mynetInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        xrvShow.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
        xrvShow.setRefreshProgressStyle(ProgressStyle.BallZigZag);

    }

    private void initData() {
        HttpUtils.getInstance().doget(new ICallBack() {

            @Override
            public void success(Object obj) {
                myXrecycleViewAdapter = new MyXRecycleViewAdapter(MainActivity.this, (List<ShowBean.DataBean.DatasBean>) obj);
                xrvShow.setAdapter(myXrecycleViewAdapter);
                xrvShow.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void failed(Exception e) {

            }
        });
        HttpUtils.getInstance().dogetbanner(new ICallBack() {
            @Override
            public void success(Object obj) {
                BannerAdapter   bannerAdapter = new BannerAdapter(MainActivity.this, (List<BannerBean.DataBean>) obj);
                vpBanner.setAdapter(bannerAdapter);
            }

            @Override
            public void failed(Exception e) {

            }
        });


    }

    private void initView() {
        xrvShow = findViewById(R.id.xrv_show);
        vpBanner = findViewById(R.id.vp_banner);
        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeBackGround(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        xrvShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myXrecycleViewAdapter.notifyDataSetChanged();
                xrvShow.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        xrvShow.loadMoreComplete();
                    }
                });

            }
        });
    }

    private void changeBackGround(int position) {
        switch (position){
            case 0:

                break;
        }
    }
}
