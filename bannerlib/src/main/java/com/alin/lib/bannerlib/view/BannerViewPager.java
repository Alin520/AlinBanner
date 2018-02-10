package com.alin.lib.bannerlib.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2017/12/27 17:39
 * 版    本   ： ${TODO}
 * 描    述   ：  ${通过反射方式实现支持低版本上切换动画}
 * ================================================
 */
public class BannerViewPager extends ViewPager {
    private boolean mAllowTouchScrollable;
    private boolean mAllowUserScrollable = true;
    private Field mScroller;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isAllowTouchScrollable() {
        return mAllowTouchScrollable;
    }

    public void setAllowTouchScrollable(boolean allowTouchScrollable) {
        mAllowTouchScrollable = allowTouchScrollable;
    }

    /**
     继承ViewPager，重写setPageTransformer方法，移除版本限制，通过反射设置参数和方法

     getDeclaredMethod*()获取的是【类自身】声明的所有方法，包含public、protected和private方法。
     getMethod*()获取的是类的所有共有方法，这就包括自身的所有【public方法】，和从基类继承的、从接口实现的所有【public方法】。

     getDeclaredField获取的是【类自身】声明的所有字段，包含public、protected和private字段。
     getField获取的是类的所有共有字段，这就包括自身的所有【public字段】，和从基类继承的、从接口实现的所有【public字段】。

     属性动画是在API 3.0之后才有的，所以setScaleX这些方法在11版本以下api是没有的。
     */

    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        Class<ViewPager> viewPagerClass = ViewPager.class;
        try {
            Field pageTransformerField = viewPagerClass.getDeclaredField("mPageTransformer");
            pageTransformerField.setAccessible(true);
            final boolean hasTransformer = transformer != null;
            pageTransformerField.set(this,transformer);
            final boolean needsPopulate = hasTransformer != (transformer != null);

            Method setChildrenDrawingOrderEnabled = viewPagerClass.getDeclaredMethod("setChildrenDrawingOrderEnabled", boolean.class);
            setChildrenDrawingOrderEnabled.setAccessible(true);
            setChildrenDrawingOrderEnabled.invoke(this,hasTransformer);

            Field mDrawingOrder = viewPagerClass.getDeclaredField("mDrawingOrder");
            mDrawingOrder.setAccessible(true);
            Field mPageTransformerLayerType = viewPagerClass.getDeclaredField("mPageTransformerLayerType");
            mPageTransformerLayerType.setAccessible(true);
            if (hasTransformer) {
                mDrawingOrder.set(this,reverseDrawingOrder ? 2 : 1);
                mPageTransformerLayerType.set(this, View.LAYER_TYPE_HARDWARE);
            }else {
                mDrawingOrder.set(this,0);
            }

            if (needsPopulate){
                Method populate = viewPagerClass.getDeclaredMethod("populate");
                populate.setAccessible(true);
                populate.invoke(this);
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mAllowTouchScrollable && super.onTouchEvent(ev);
    }

    //    设置动画时间
    public void setPageChangeDuration(int duration) {
        try {
            Class<ViewPager> viewPagerClass = ViewPager.class;
            mScroller = viewPagerClass.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(this,new BannerScroller(getContext(),duration));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    /**
     * 设置是否允许用户手指滑动
     *
     * @param allowUserScrollable true表示允许跟随用户触摸滑动，false反之
     */
    public void setAllowUserScrollable(boolean allowUserScrollable) {
        mAllowUserScrollable = allowUserScrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mAllowUserScrollable && super.onInterceptTouchEvent(ev);
    }


}
