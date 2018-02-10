package com.alin.lib.bannerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alin.lib.bannerlib.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/2/1 11:58
 * 版    本   ： ${TODO}
 * 描    述   ：  ${欢迎页面--重叠效果}
 * ================================================
 */
public class WelcomeOverlayActivity extends AppCompatActivity{
    private List<Integer> mList;
    private ArrayList<String> mTitles;
    private ArrayList mList2;
    private BannerView mBackgroudBv;
    private BannerView mForegroundBv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_overlay);

        mBackgroudBv = (BannerView) findViewById(R.id.background_bn);
        mForegroundBv = (BannerView) findViewById(R.id.foreground_bn);
        TextView skipView = (TextView) findViewById(R.id.skip_tv);
        TextView enterView = (TextView) findViewById(R.id.enter_tv);
        init();
        mForegroundBv.setSkipOrEnterListener(skipView, enterView, new BannerView.OnSkipOrEnterCallback() {
            @Override
            public void onSkipOrEnterCallback() {
                startActivity(new Intent(WelcomeOverlayActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.splash_bg_1);
        mList.add(R.mipmap.splash_bg_2);
        mList.add(R.mipmap.splash_bg_3);

        mList2 = new ArrayList<>();
        mList2.add(R.mipmap.img_welcome_1);
        mList2.add(R.mipmap.img_welcome_2);
        mList2.add(R.mipmap.img_welcome_1);

        mBackgroudBv.setImages(mList)
                .start();
        mForegroundBv.setImages(mList2)
                .start();
    }
}
