package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/17 11:58
 * 版    本   ： ${TODO}
 * 描    述   ：  ${平移动画}
 * ================================================
 */
class TranslateTransformPage extends TransformPageImpl {
    @Override
    public void handleRightPage(View view, float position) {
//        1==>0
        if (position >= 0.5){
            float scacle =  mMinScale + (1 - mMinScale) * (2 - 2 * position);
            ViewCompat.setScaleX(view,scacle);
            ViewCompat.setScaleY(view,scacle);
        }

    }

    @Override
    public void handleLeftPage(View view, float position) {
        if (position >= -0.5){
            float scacle = 1 + 2 * (1 - mMinScale) * position;
            ViewCompat.setScaleX(view,scacle);
            ViewCompat.setScaleY(view,scacle);
        }

    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setAlpha(view,0);
    }
}
