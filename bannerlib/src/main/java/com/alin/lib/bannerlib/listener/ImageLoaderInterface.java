package com.alin.lib.bannerlib.listener;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/5 11:45
 * 版    本   ： ${TODO}
 * 描    述   ：  ${对于BannerView 传入的图片是URL类型，需要实现此接口，来自定义加载图片}
 * ================================================
 */
public interface ImageLoaderInterface<T extends Object,V extends View> extends Serializable {
    //创建ImageView
    V createImageView(Context context);

    //展示ImageView
    void  displayImageView(Context context, T image, V imageView);

    //释放ImageView内存
    void releaseImageView(V imageView);
}
