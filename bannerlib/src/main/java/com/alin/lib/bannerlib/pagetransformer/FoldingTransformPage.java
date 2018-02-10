package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/17 17:46
 * 版    本   ： ${TODO}
 * 描    述   ：  ${折叠效果}
 * ================================================
 */
class FoldingTransformPage extends TransformPageImpl {
    @Override
    public void handleLeftPage(View view, float position) {
//        ViewCompat.setScaleX(view,1 + position);
        ViewCompat.setScaleX(view, 1.0f + position);
        ViewCompat.setPivotX(view, view.getWidth());

    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setPivotX(view, 0);
        ViewCompat.setScaleX(view, 1.0f - position);
        ViewCompat.setAlpha(view, 1);
    }
}
