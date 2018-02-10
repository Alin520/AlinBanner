package com.alin.lib.bannerlib.pagetransformer;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/16 11:52
 * 版    本   ： ${TODO}
 * 描    述   ：  ${ViewPager 滑动效果}
 * ================================================
 */


public enum TransformerSlidEffect {
    Default,Alpha,Rotate,Zoom,Translate,Folding,Flip,Cube,
    Fade,Flashing;    //折叠

    public static TransformerSlidEffect valueOf(int index){
        if (index < 0 || index > values().length)
            throw new IllegalArgumentException("TransformerSlidEffect valueOf input params 'index' is error!");
        return values()[index];
    }
}
