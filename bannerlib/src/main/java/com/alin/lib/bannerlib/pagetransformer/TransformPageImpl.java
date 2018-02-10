package com.alin.lib.bannerlib.pagetransformer;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/17 11:15
 * 版    本   ： ${TODO}
 * 描    述   ：  ${BannerView 动画}
 * ================================================
 */
public abstract class TransformPageImpl implements ViewPager.PageTransformer {
    public float mMinScale;
    public float mMinAlpha;
    public float mMinRotation;
    public boolean mHasTransformPageAnim = true;

    @Override
    public void transformPage(View view, float position) {
        onPreTransform(view, position);
        if (position < -1.0f) {
            // (-Infinity,-1)
            dispatchInvisiblePage(view, position);
        } else if (position <= 0.0f) {
            // [-1,0]
            dispatchHandleLeftPage(view, position);
        } else if (position <= 1.0f) {
            // (0,1]
            dispatchHandleRightPage(view, position);
        } else {
            // (1,+Infinity)
            dispatchInvisiblePage(view, position);
        }
    }

    private void onPreTransform(View view, float position) {
        resetTransformPageAnim(view);
    }

    private void dispatchInvisiblePage(View view, float position) {
        if (mHasTransformPageAnim) {
            handleInvisiblePage(view,position);
        }else {
            resetTransformPageAnim(view);
        }
    }

    /**
     * 说明：当viewPager切换到最后一个item的时候，显示的是postion=1的图片，然后会自动将ViewPager切换到postion=1的条目，
     * 即mViewPager.setCurrentItem(1,false)，这个时候这个时候还是显示的是postion=1的图片。但是，此时又调用了一次动画切换。
     * 为了防止viewPager切换到最后一个item的时候调用动画卡顿、闪烁，这里做了处理。
     *
     */
    private void dispatchHandleLeftPage(View view, float position) {
        if (mHasTransformPageAnim) {
            handleLeftPage(view,position);
        }else {
            resetTransformPageAnim(view);
        }
    }

    private void dispatchHandleRightPage(View view, float position) {
        if (mHasTransformPageAnim) {
            handleRightPage(view,position);
        }else {
            resetTransformPageAnim(view);
        }
    }


    //左边页面
    public abstract void handleLeftPage(View view, float position);

    //右边页面
    public abstract void handleRightPage(View view, float position);

    //不可见页面
    public  void handleInvisiblePage(View view, float position){}

//    重置view动画===>z初始化状态
    private void resetTransformPageAnim(View view) {
        ViewCompat.setTranslationX(view,0);
        ViewCompat.setTranslationY(view,0);
        ViewCompat.setAlpha(view,1);
        ViewCompat.setScaleX(view,1);
        ViewCompat.setScaleY(view,1);
        ViewCompat.setRotationY(view,0);
        ViewCompat.setRotationX(view,0);
        ViewCompat.setRotation(view,0);
    }

    public void setHasTransformPageAnim(boolean hasTransformPageAnim){
        mHasTransformPageAnim = hasTransformPageAnim;
    }

    public float getMinScale() {
        return mMinScale;
    }

    public void setMinScale(float minScale) {
        mMinScale = minScale >= 0 && minScale <= 1 ? minScale : mMinScale;
    }

    public float getMinAlpha() {
        return mMinAlpha;
    }

    public void setMinAlpha(float minAlpha) {
        mMinAlpha = minAlpha >= 0 && minAlpha <= 1 ? minAlpha : mMinAlpha;
    }

    public float getMinRotation() {
        return mMinRotation;
    }

    public void setMinRotation(float minRotation) {
        this.mMinRotation = mMinRotation;
    }
}
