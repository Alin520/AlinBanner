package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.util.Log;
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
public class ZoomTransformPage extends TransformPageImpl {

    @Override
    public void handleLeftPage(View view, float position) {
        float scale = Math.max(mMinScale, 1 + position);
        float vertMargin = view.getHeight() * (1 - scale) / 2;
        float horzMargin = view.getWidth() * (1 - scale) / 2;
        ViewCompat.setTranslationX(view, horzMargin - vertMargin / 2);
        ViewCompat.setScaleX(view, scale);
        ViewCompat.setScaleY(view, scale);
        ViewCompat.setAlpha(view, mMinAlpha + (scale - mMinScale) / (1 - mMinScale) * (1 - mMinAlpha));
    }

    @Override
    public void handleRightPage(View view, float position) {
        float scale = Math.max(mMinScale, 1 - position);
        float vertMargin = view.getHeight() * (1 - scale) / 2;
        float horzMargin = view.getWidth() * (1 - scale) / 2;
        ViewCompat.setTranslationX(view, -horzMargin + vertMargin / 2);
        ViewCompat.setScaleX(view, scale);
        ViewCompat.setScaleY(view, scale);
        ViewCompat.setAlpha(view, mMinAlpha + (scale - mMinScale) / (1 - mMinScale) * (1 - mMinAlpha));
        float pso = mMinAlpha + (scale - mMinScale) / (1 - mMinScale) * (1 - mMinAlpha);
        Log.e("handleRightPage"," handleRightPage==" + pso);
    }
}
