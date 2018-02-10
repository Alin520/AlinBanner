package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/26 16:26
 * 版    本   ： ${TODO}
 * 描    述   ：  ${闪烁效果}
 * ================================================
 */
public class FlashingTransformerPage extends TransformPageImpl {
    @Override
    public void handleLeftPage(View view, float position) {
        ViewCompat.setAlpha(view, 1);
        ViewCompat.setTranslationX(view, 0);
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setAlpha(view, 1 - position);
        ViewCompat.setTranslationX(view, -view.getWidth() * position);
        float scale = mMinScale + (1 - mMinScale) * (1 - position);
        ViewCompat.setScaleX(view, scale);
        ViewCompat.setScaleY(view, scale);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setAlpha(view, 0);
    }
}
