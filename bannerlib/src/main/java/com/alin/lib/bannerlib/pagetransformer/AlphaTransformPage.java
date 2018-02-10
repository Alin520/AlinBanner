package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/16 11:58
 * 版    本   ： ${TODO}
 * 描    述   ：  ${透明动画}
 * ================================================
 */
public class AlphaTransformPage extends TransformPageImpl {

    @Override
    public void handleLeftPage(View view, float position) {
        float alpha = mMinAlpha + (1 + position) * (1 - mMinAlpha);
        ViewCompat.setAlpha(view,alpha);
    }

    @Override
    public void handleRightPage(View view, float position) {
        float alpha = mMinAlpha + (1 - position) * (1 - mMinAlpha);
        ViewCompat.setAlpha(view,alpha);
    }


    @Override
    public void handleInvisiblePage(View view, float position) {

    }
}
