package com.alin.lib.bannerlib.listener;

import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/30 10:07
 * 版    本   ： ${TODO}
 * 描    述   ：  ${TODO}
 * ================================================
 */
public abstract class OnBannerViewClickListener implements View.OnClickListener {
    private long mCurrentTime;
    private long mLastTime;

    @Override
    public void onClick(View v) {
        mCurrentTime = System.currentTimeMillis();
        if (Math.abs(mCurrentTime - mLastTime) > 800) {
            onBannerViewClick();
        }
        mLastTime = System.currentTimeMillis();
    }

    public abstract void onBannerViewClick();
}
