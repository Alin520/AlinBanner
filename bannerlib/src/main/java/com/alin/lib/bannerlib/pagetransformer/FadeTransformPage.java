package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/29 18:02
 * 版    本   ： ${TODO}
 * 描    述   ：  ${淡出动画}
 * ================================================
 */
public class FadeTransformPage extends TransformPageImpl{
    @Override
    public void handleLeftPage(View view, float position) {
        ViewCompat.setTranslationX(view,-view.getWidth() * position);
        ViewCompat.setAlpha(view,1 + position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setTranslationX(view,-view.getWidth() * position);
        ViewCompat.setAlpha(view,1 - position);
    }
}
