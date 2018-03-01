package com.alin.lib.bannerview.view;

import android.content.Context;
import android.view.View;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;
import com.alin.lib.bannerlib.util.AppUtil;
import com.alin.lib.bannerlib.view.BannerImageView;
import com.bumptech.glide.Glide;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/2/1 17:46
 * 版    本   ： ${TODO}
 * 描    述   ：  ${显示URL图片}
 * ================================================
 */
public class DisplayUrlImageView implements ImageLoaderInterface<String,BannerImageView> {

    @Override
    public BannerImageView createImageView(Context context) {
        return new BannerImageView(context);
    }

    @Override
    public void displayImageView(Context context, String path, BannerImageView imageView) {
        AppUtil.checkNotNull(path,"displayImageView path is null error!");
        Glide.with(context).load(path).into(imageView);
    }

    @Override
    public void releaseImageView(BannerImageView imageView) {
        if (imageView != null)
            Glide.clear(imageView);
    }
}
