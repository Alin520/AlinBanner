package com.alin.lib.bannerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alin.lib.bannerlib.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/31 13:26
 * 版    本   ： ${TODO}
 * 描    述   ：  ${indicator位置效果展示}
 * ================================================
 */
public class IndicatorResultActivity extends AppCompatActivity{

    private BannerView leftToRightBv;
    private BannerView centerToRightBv;
    private BannerView rightToleftBv;
    private BannerView default_bv1;
    private BannerView default_bv2;
    private BannerView default_bv3;
    private List<Integer> mList;
    private ArrayList<String> mTitles;
    private BannerView mNoIndicatorBgBv;
    private BannerView mCircleNoBgBv;
    private BannerView mCircleBgBv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_result);
        mNoIndicatorBgBv = (BannerView) findViewById(R.id.noIndicatorBgBv);
        mCircleNoBgBv = (BannerView) findViewById(R.id.circleNoBgBv);
        mCircleBgBv = (BannerView) findViewById(R.id.circleBgBv);
        leftToRightBv = (BannerView) findViewById(R.id.leftToRightBv);
        centerToRightBv = (BannerView) findViewById(R.id.centerToRightBv);
        rightToleftBv = (BannerView) findViewById(R.id.rightToleftBv);
        default_bv1 = (BannerView) findViewById(R.id.default_bv1);
        default_bv2 = (BannerView) findViewById(R.id.default_bv2);
        default_bv3 = (BannerView) findViewById(R.id.default_bv3);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.banner_1);
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);

        mTitles = new ArrayList<>();
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");
    }

    private void initView() {
//        无Indicator
        mNoIndicatorBgBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

//        数字指示器
        mCircleNoBgBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

//        数字指示器,设置指示器参数
        mCircleBgBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

//        圆形指示器
        leftToRightBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

        centerToRightBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

        rightToleftBv.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

        default_bv1.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

        default_bv2.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

        default_bv3.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

    }

}
