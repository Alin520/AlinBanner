package com.alin.lib.bannerlib.view;

import android.content.Context;
import android.view.View;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;
import com.alin.lib.bannerlib.util.AppUtil;
import com.bumptech.glide.Glide;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/2/2 10:14
 * 版    本   ： ${TODO}
 * 描    述   ：  ${默认的显示URL图片}
 * ================================================
 */
public class DefaultDisplayUrlImageView implements ImageLoaderInterface<String,BannerImageView> {
    @Override
    public BannerImageView createImageView(Context context) {
        AppUtil.checkNotNull(context,"DefaultDisplayUrlImageView params context is null error!");
        return new BannerImageView(context);
    }

    @Override
    public void displayImageView(Context context, String imageUrl, BannerImageView imageView) {
        AppUtil.checkNotNull(context,"DefaultDisplayUrlImageView params context is null error!");
        AppUtil.checkNotNull(imageUrl,"displayImageView params imageUrl is null error!");
        Glide.with(context).load(imageUrl).into(imageView);
    }
}
