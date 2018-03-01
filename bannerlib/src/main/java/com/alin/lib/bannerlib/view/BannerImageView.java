package com.alin.lib.bannerlib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;


/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/10 17:40
 * 版    本   ： ${TODO}
 * 描    述   ：  ${TODO}
 * ================================================
 */
public class BannerImageView<T> extends android.support.v7.widget.AppCompatImageView{

    private T                    mImagePath;
    private int                  mScaleType;
    private ImageLoaderInterface mImageLoader;
    private Context              mContext;

    public BannerImageView(Context context) {
        super(context);
        mContext = context;
    }

    public BannerImageView transformImageView(T imagePath, int scaleType) {
        mImagePath = imagePath;
        mScaleType = scaleType;
        if (mImageLoader != null) {
            mImageLoader.createImageView(mContext);
        }

        if (mImagePath instanceof String){
            String imageUrl = (String) mImagePath;
            displayImage(imageUrl);
        }else if (mImagePath instanceof Integer){
            int imageId = (Integer) mImagePath;
            this.setImageResource(imageId);
        }else if (mImagePath instanceof Drawable){
            Drawable imageDrawable = (Drawable) mImagePath;
            this.setImageDrawable(imageDrawable);
            
        }

        switch (mScaleType){
            case 0:
                this.setScaleType(ScaleType.CENTER);
                break;
            case 1:
                this.setScaleType(ScaleType.CENTER_CROP);
                break;
            case 2:
                this.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case 3:
                this.setScaleType(ScaleType.FIT_CENTER);
                break;
            case 4:
                this.setScaleType(ScaleType.FIT_END);
                break;
            case 5:
                this.setScaleType(ScaleType.FIT_START);
                break;
            case 6:
                this.setScaleType(ScaleType.FIT_XY);
                break;
            case 7:
                this.setScaleType(ScaleType.MATRIX);
                break;
        }
        return this;
    }

    private <T> void displayImage(T imagePath) {
        if (mImageLoader != null) {
            mImageLoader.displayImageView(mContext,imagePath,this);
        }
    }

    public void setImageLoader(ImageLoaderInterface imageLoader) {
        this.mImageLoader = imageLoader;
    }
}
