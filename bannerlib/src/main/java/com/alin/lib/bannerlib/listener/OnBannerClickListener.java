package com.alin.lib.bannerlib.listener;


import com.alin.lib.bannerlib.view.BannerImageView;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/5 14:04
 * 版    本   ： ${TODO}
 * 描    述   ：  ${点击bannerview item 回调}
 * ================================================
 */
public interface OnBannerClickListener<T> {
    void onBannerClickListener(BannerImageView imageView, T model, int position);
}
