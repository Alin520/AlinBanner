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
 * 描    述   ：  ${TODO}
 * ================================================
 */
public class CubeTransformerPage extends TransformPageImpl {
    private float mMaxRotation = 90.0f;

    @Override
    public void handleLeftPage(View view, float position) {
        ViewCompat.setPivotX(view, 0);
        ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewCompat.setRotationY(view, mMaxRotation * position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setPivotX(view, 0);
        ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewCompat.setRotationY(view, mMaxRotation * position);
    }

}
