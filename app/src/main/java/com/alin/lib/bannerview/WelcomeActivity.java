package com.alin.lib.bannerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alin.lib.bannerlib.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/2/1 10:28
 * 版    本   ： ${TODO}
 * 描    述   ：  ${欢迎页}
 * ================================================
 */
public class WelcomeActivity extends AppCompatActivity {
    private List<Integer> mList;
    private BannerView mBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mBannerView = findViewById(R.id.welcome_bn);
        TextView skipView = findViewById(R.id.skip_tv);
        TextView enterView = findViewById(R.id.enter_tv);
        init();
        mBannerView.setSkipOrEnterListener(null, enterView, new BannerView.OnSkipOrEnterCallback() {
            @Override
            public void onSkipOrEnterCallback() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.splash_bg_1);
        mList.add(R.mipmap.splash_bg_2);
        mList.add(R.mipmap.splash_bg_3);
        mBannerView.setImages(mList)
                .setInfinite(false)
                .start();
    }
}
