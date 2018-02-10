package com.alin.lib.bannerlib.view;

import android.content.Context;
import android.widget.Scroller;

import com.alin.lib.bannerlib.BannerConfig;


/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/30 17:42
 * 版    本   ： ${TODO}
 * 描    述   ：  ${TODO}
 * ================================================
 */
public class BannerScroller extends Scroller{

    private int mDuration = BannerConfig.DEFAULT_PAGE_CHANGE_DURATION;

    public BannerScroller(Context context, int duration) {
        super(context);
        mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy,mDuration);
    }
}
