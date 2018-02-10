package com.alin.lib.bannerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alin.lib.bannerlib.BannerView;
import com.alin.lib.bannerlib.listener.OnBannerClickListener;
import com.alin.lib.bannerlib.view.BannerImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/31 13:28
 * 版    本   ： ${TODO}
 * 描    述   ：  ${播放效果测试}
 * ================================================
 */
public class AutoPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Integer> mList;
    private ArrayList<String> mTitles;
    private BannerView mBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_play);

        mBannerView = (BannerView) findViewById(R.id.auto_play_bn);
        findViewById(R.id.start_play_tv).setOnClickListener(this);
        findViewById(R.id.stop_play_tv).setOnClickListener(this);
        findViewById(R.id.infinite_play_tv).setOnClickListener(this);
        findViewById(R.id.allow_touch_scrollable_tv).setOnClickListener(this);
        init();
        mBannerView.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClickListener(BannerImageView imageView, Object model, int position) {
                Toast.makeText(AutoPlayActivity.this,"当前position=" + position + ",被点击了~",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.banner_1);
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);
        mTitles = new ArrayList<>();
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");
        mBannerView.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_play_tv:
                mBannerView.setImages(mList)
                        .setIndcatorTitles(mTitles)
                        .setAllowTouchScrollable(true)
                        .setInfinite(true)
                        .setAutoPlay(true)
                        .start();
                break;
            case R.id.stop_play_tv:
                mBannerView.setImages(mList)
                        .setIndcatorTitles(mTitles)
                        .setAllowTouchScrollable(true)
                        .setInfinite(true)
                        .setAutoPlay(false)
                        .start();
                break;
            case R.id.infinite_play_tv:
                mBannerView.setImages(mList)
                        .setIndcatorTitles(mTitles)
                        .setAllowTouchScrollable(true)
                        .setInfinite(false)
                        .start();
                break;

            case R.id.allow_touch_scrollable_tv:
                mBannerView.setImages(mList)
                        .setIndcatorTitles(mTitles)
                        .setInfinite(true)
                        .setAutoPlay(true)
                        .setAllowTouchScrollable(false)
                        .start();
                    break;
        }
    }
}