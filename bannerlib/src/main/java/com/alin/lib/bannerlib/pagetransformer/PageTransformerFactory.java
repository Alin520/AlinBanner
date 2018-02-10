package com.alin.lib.bannerlib.pagetransformer;


import android.util.Log;

import java.util.WeakHashMap;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/16 11:50
 * 版    本   ： ${TODO}
 * 描    述   ：  ${获取 BannerView 动画类型}
 * ================================================
 */
public class PageTransformerFactory{
    private static WeakHashMap<String, TransformPageImpl> mTransformerMap = new WeakHashMap<>();

    public static TransformPageImpl createTransformer(TransformerSlidEffect  slidEffect){
        TransformPageImpl transformer;
        transformer = mTransformerMap.get(slidEffect.name());
        if (transformer != null) {
            Log.d("PageTransformerFactory","mTransformerMap==" + slidEffect.name());
            return transformer;
        }

        switch (slidEffect){
            case Default:
                transformer = new DefaultTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Alpha:
                transformer = new AlphaTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Rotate:
                transformer = new RotateTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Zoom:
                transformer = new ZoomTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Translate:
                transformer = new TranslateTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Folding:
                transformer = new FoldingTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Flip:
                transformer = new FlipTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Cube:
                transformer = new CubeTransformerPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Fade:
                transformer = new FadeTransformPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
            case Flashing:
                transformer = new FlashingTransformerPage();
                mTransformerMap.put(slidEffect.name(),transformer);
                break;
        }
        return transformer;
    }
}
