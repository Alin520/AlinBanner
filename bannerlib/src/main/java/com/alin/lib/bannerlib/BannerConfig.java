package com.alin.lib.bannerlib;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;
import com.alin.lib.bannerlib.pagetransformer.TransformerSlidEffect;
import com.alin.lib.bannerlib.view.BannerImageView;
import com.alin.lib.bannerlib.view.DefaultDisplayUrlImageView;


/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2017/12/28 10:12
 * 版    本   ： ${TODO}
 * 描    述   ：  ${BannerView 默认参数配置}
 * ================================================
 */
public class BannerConfig {
    /**
     * indicator style
     *  0:：无指示器
     *  1：圆形指示器
     *  2：数字指示器
     */
    public static final int STYLE_NOT_INDICATOR = 0;
    public static final int STYLE_NUM_INDICATOR = 1;
    public static final int STYLE_CIRCLE_INDICATOR = 2;

    public static final int  RM =  RelativeLayout.LayoutParams.MATCH_PARENT;
    public static final int  RW =  RelativeLayout.LayoutParams.WRAP_CONTENT;
    public static final int  LM =  LinearLayout.LayoutParams.MATCH_PARENT;
    public static final int  LW =  LinearLayout.LayoutParams.WRAP_CONTENT;

//    BannerView 动画最小缩放
    public static final float  DEFAULT_MIN_SCALE = 0.8f;
    //    BannerView 动画最小透明度
    public static final float  DEFAULT_MIN_ALPHA = 0.3f;
    //    BannerView 动画最小旋转角度
    public static final int  DEFAULT_MIN_ROTATION = 90;

    /**
     * indicator设置
     */
//    指示器每个小圆点的高度
    public static final int DEFAULT_POINTER_HEIGHT = 6;

    //    指示器每个小圆点的宽度
    public static final int DEFAULT_POINTER_WIDTH = 6;

    //    indicator指示器每个点的间距
    public static final int DEFAULT_POINTER_MARGIN = 4;

    //    indicator布局指示器位置
    public static final int DEFAULT_INDICATOR_GRAVITY = Gravity.BOTTOM | Gravity.CENTER_VERTICAL;

    //    indicator被选中的指示器
    public static final int DEFAULT_POINTER_SELECTED = R.drawable.bg_red_circle;

    //    indicator未被选中的指示器
    public static final int DEFAULT_POINTER_UNSELECTED = R.drawable.bg_black_circle;

    //    indicator数字指示器 padding
    public static final int DEFAULT_NUMBER_POINTER_PADDING = 4;

    //    indicator数字指示器 背景
    public static final int DEFAULT_NUMBER_POINTER_BACKGROUND = R.drawable.bg_number_pointer;

    //    indicator数字指示器 字体大小
    public static final int DEFAULT_NUMBER_POINTER_TEXT_SIZE = 12;

    //    indicator数字指示器 字体颜色
    public static final int DEFAULT_NUMBER_POINTER_TEXT_COLOR = R.color.white;

    //    indicator背景高度
    public static final int DEFAULT_INDICATOR_BACKGROUND_HEIGHT = 40;

//    indicator背景颜色
    public static final int DEFAULT_INDICATOR_BACKGROUND_COLOR = R.color.transparent_gray;

//    indicator title字体大小
    public static final int DEFAULT_INDICATOR_TEXT_SIZE = 12;

//    indicator title字体颜色
    public static final int DEFAULT_INDICATOR_TEXT_COLOR = R.color.black;

    //    indicator 指示器类型
    public static final int DEFAULT_POINTER_TYPE = STYLE_CIRCLE_INDICATOR;

//    indicator内部左右padding
    public static final int DEFAULT_INDICATOR_LEFT_AND_RIGHT_PADDING = 15;
//    indicator内部上下padding
    public static final int DEFAULT_INDICATOR_TOP_AND_BOTTOM_PADDING = 0;
    //    indicator上下margin
    public static final int DEFAULT_INDICATOR_TOP_AND_BOTTOM_MARGIN = 0;
    //    indicator左右margin
    public static final int DEFAULT_INDICATOR_LEFT_AND_RIGHT_MARGIN = 0;

    /**
     * bannerView 设置
     */
    //    banner 默认高度
    public static final int DEFAULT_BANNER_HEIGHT = 0;

    //    banner 图片间滑动的时间
    public static final int DEFAULT_PAGE_CHANGE_DURATION = 700;

//    banner的指示器（圆点、数字）的位置
    public static final int DEFAULT_BANNER_POINTER_GRAVITY = RelativeLayout.CENTER_HORIZONTAL;

    //    banner的Title的位置
    public static final int DEFAULT_BANNER_TITLE_GRAVITY = -1;

    //    banner的Title的长度
    public static final int DEFAULT_BANNER_TITLE_WIDTH = 110;

    //    是否自动循环播放
    public static final boolean DEFAULT_IS_AUTO_PLAY = true;

    //    bannerView默认图片
    public static final int DEFAULT_BANNER_IMAGEVIEW_ID = R.mipmap.banner_2;

    //    bannerView  轮播时间间隔
    public static final int DEFAULT_INTERVAL_TIME = 1000;

    //    bannerView 图片scacle
    public static final int DEFAULT_BANNER_IMAGEVIEW_SCACLE_TYPE = 1;

    //    bannerView 滑动动画效果
    public static final int DEFAULT_BANNER_SLID_EFFECT = TransformerSlidEffect.Default.ordinal();

//    是否允许用户手指滑动
    public static final boolean DEFAULT_ALLOW_TOUCH_SCROLLABLE = true;

//    无限轮播
    public static final boolean DEFAULT_IS_INFINITE = true;

//    默认图片加载方式 Gilde
    public static final ImageLoaderInterface DEFAULT_IMAGE_LOADER = new DefaultDisplayUrlImageView();

    //    是否在最后一页显示indicator
    public static final boolean DEFAULT_LAST_INDICATOR_IS_VISIBLE = false;
}
