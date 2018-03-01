package com.alin.lib.bannerlib.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;
import com.alin.lib.bannerlib.listener.OnBannerClickListener;
import com.alin.lib.bannerlib.listener.OnBannerViewClickListener;
import com.alin.lib.bannerlib.listener.OnIndicatorVisibilityListener;
import com.alin.lib.bannerlib.listener.OnSelectedItemCallback;
import com.alin.lib.bannerlib.pagetransformer.TransformPageImpl;
import com.alin.lib.bannerlib.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2017/12/28 15:19
 * 版    本   ： ${TODO}
 * 描    述   ： ${
 *
 *     注意：
 *     1、onPageSelected方法调用时，onPageScrolled中的参数positionOffset此时不一定是1。也就是ViewPager滑动还未全部结束时，onPageSelected方法就被调用了。
 *     2、onPageScrollStateChanged会被调用两次，分别是刚准备滑动ViewPager时，即state=1时被调用一次，另外在结束滑动，即收离开屏幕时，会被再调用一次。
 *     3、transformPage方法说明：
 *       1）何时被调用：transformPage会在ViewPager被滑动整个过程中都会一直被调用，即伴随着onPageScrolled、onPageSelected被调用。
 *       2）参数position：ViewPager刚进入被初始化时，会当前页、前一页、后一页都会被初始化，对应的position值分别为:
 *          当前页position=0，前一页position=-1，后一页都会被初始化position=1.
 *          当向左滑动一页时，position的变化分别：
 *          当前页 position=0 ——> position=-1
 *          前一页 position=-1 ——> position=-2
 *          后一页 position=1 ——> position=-0
 *          向右滑动则相反。
 *
 * }
 * ================================================
 */
public class BannerPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener{

    private       Context                    mContext;
    private       List<T>                    mDataList;
    private       int                        mCurrentItem;
    private       int                           mCount;
    private       BannerViewPager               mViewPager;
    private OnBannerClickListener mBannerClickListener;
    private OnSelectedItemCallback mCallback;
    private       boolean                       mIsInfinite;        //true 无限轮播
    private final ArrayList<BannerImageView>    mImageViews;
    private TransformPageImpl mTransformPageImpl;
    private       View                          mEnterView;
    private       View                          mSkipView;
    private OnIndicatorVisibilityListener mOnIndicatorVisibilityListener;
    private ImageLoaderInterface mImageLoader;
    private int mImageScaleType;
    private boolean mLastIndicatorIsVisible;    //true最后一页显示indicator

    public BannerPagerAdapter(Context context, List<T> dataList, BannerViewPager viewPager) {
        mContext = context;
        mDataList = dataList;
        mViewPager = viewPager;
        mImageViews = new ArrayList<>();
        if (mDataList != null && !mDataList.isEmpty()) {
            mCount = mDataList.size();
            for (int i = 0; i < mCount; i++) {
                BannerImageView image = new BannerImageView(mContext);
                BannerImageView imageView = getImageView(i,image);
                imageView.setTag(imageView.toString() + i);
                mImageViews.add(imageView);
            }
        }
    }

    @Override
    public int getCount() {
        return mIsInfinite ? (mCount == 0 || mCount == 1 ? mCount : mCount + 2) : mCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BannerImageView imageView = null;
        if (mImageViews != null && mImageViews.size() > position) {
            imageView = mImageViews.get(position);
        }
        if (imageView == null) {
            throw new IllegalArgumentException("BannerPagerAdapter instantiateItem 'imageView' is null!");
        }
        ViewParent parent = imageView.getParent();
        //测试时，若多地方引用，会导致imageView报错，imageView已经有ViewParent，不能添加进container
        if (parent == null) {
            container.addView(imageView);
        }

        final BannerImageView tempImageView = imageView;
        imageView.setOnClickListener(new OnBannerViewClickListener() {
            @Override
            public void onBannerViewClick() {
                if (mBannerClickListener != null) {
                    //注意：这里如果传入position，则当前item=2时，点击所获取的position=2，再往前切一个时item=1，即第一个，点击所获取的position=2，
                    //没有变，因此点击获取的位置都是第二个item的位置的bug。所以这里传入mCurrentItem，即每次拿被当前选中的item获取实际的位置。
                    int realPosition = getRealPosition(mCurrentItem);
                    if (realPosition > mCount || realPosition < 0){
                        throw new IllegalArgumentException("BannerPagerAdapter param 'mCount' is error!");
                    }
                    mBannerClickListener.onBannerClickListener(tempImageView,mDataList.get(realPosition),realPosition);
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mDataList == null || mDataList.isEmpty()) {
            return;
        }
        //由于Glide、Fresco等网络框架有缓存，所以只有当图片加载的是通过图片的url时，才去清除图片缓存
        if (object instanceof BannerImageView && mDataList.get(0) instanceof String) {
            mImageLoader.releaseImageView((BannerImageView) object);
        }
        container.removeView((View)object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mIsInfinite || mOnIndicatorVisibilityListener == null) {
            return;
        }
        if (position == getCount() - 2 && positionOffset > 0){
            setSkipViewVisibility(View.GONE);
            setEnterViewVisibility(View.GONE);
            if (positionOffset > 0.5) {          //向左滑动，当前倒数第二个item,而且最后一个item还未被选中时
                setEnterViewVisibility(View.VISIBLE);
                if (mEnterView != null) {
                    ViewCompat.setAlpha(mEnterView,positionOffset);
                }
                mOnIndicatorVisibilityListener.onIndicatorVisibilityListener(false || mLastIndicatorIsVisible);
            }else {          //向右滑动,当前最后一个item
                setSkipViewVisibility(View.VISIBLE);
                if (mEnterView != null) {
                    ViewCompat.setAlpha(mEnterView,1 - positionOffset);
                }
                if (positionOffset < 0.13){
                    //为了解决：向右滑动,当前最后一个item，滑动到倒数第二个item时，Indicator提前显示出来了，能看到小圆点从最后一个切换到倒数第二个的过程
                    mOnIndicatorVisibilityListener.onIndicatorVisibilityListener(true || mLastIndicatorIsVisible);
                }
            }
        }else if (position == getCount() - 1){      //倒数第二个item向左滑动，而且最后一个item已经被选中了
            if (positionOffset > 0.5) {
                setSkipViewVisibility(View.GONE);
                setEnterViewVisibility(View.VISIBLE);
            }
            if (mEnterView != null) {
                ViewCompat.setAlpha(mEnterView,1);
            }

            mOnIndicatorVisibilityListener.onIndicatorVisibilityListener(false || mLastIndicatorIsVisible);
        }else{
            setSkipViewVisibility(View.VISIBLE);
            setEnterViewVisibility(View.GONE);
            mOnIndicatorVisibilityListener.onIndicatorVisibilityListener(true);
        }
    }

    private void setSkipViewVisibility(int visibility) {
        if (mSkipView != null)
            mSkipView.setVisibility(visibility);
    }

    private void setEnterViewVisibility(int visibility) {
        if (mEnterView != null)
            mEnterView.setVisibility(visibility);
    }

    @Override
    public void onPageSelected(int position) {
        mCallback.onSelectedCallback(position);
        mCurrentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                if (mIsInfinite) {      //无限轮播
                    scrollToPager();
                }
                break;
        }
    }

//    刷新数据
    public void refreshToData(List<T> dataList,boolean isInfinite){
        AppUtil.checkNotNull(dataList,"refreshToData params 'dataList' is not null!");
        mDataList = dataList;
        mIsInfinite = isInfinite;
        if (mDataList != null && !mDataList.isEmpty()) {
            mCount = mDataList.size();

            if (mImageViews != null && mImageViews.size() > 0) {
                mImageViews.clear();
            }
            if (mIsInfinite) {
                for (int i = 0; i < mCount + 2; i++) {
                    initImageView(i);
                }
            }else {
                for (int i = 0; i < mCount; i++) {
                    initImageView(i);
                }
            }
        }
        notifyDataSetChanged();
    }


    private BannerImageView getImageView(int position,BannerImageView imageView) {
        int realPos;
        if (mIsInfinite) {      //无限轮播
            if (position == 0) {
                realPos = mCount -1;
            }else if (position == mCount + 1){
                realPos = 0;
            }else {
                realPos = position -1;
            }
            if (realPos > mCount || realPos < 0){
                throw new IllegalArgumentException("BannerPagerAdapter instantiateItem 'position' is error!");
            }
        }else {
            realPos = position;
        }

        T data = mDataList.get(realPos);
        if (data == null) {
            throw new IllegalArgumentException("BannerPagerAdapter params 'mDataList' is null!");
        }
        return  imageView.transformImageView(data,mImageScaleType);
    }

    //    轮播图展示的真正的位置
    private int getRealPosition(int position) {
        int resPos;
        if (mIsInfinite) {      //无限轮播
            resPos = (position - 1) % mCount;
            if (resPos < 0){
                resPos = resPos + mCount;
            }
        }else {
            resPos = position;
        }
        return resPos;
    }

    private void scrollToPager() {
        if (mCurrentItem == 0) {        //第一页
            skipToCurrentPage(mCount);
        }else if (mCurrentItem == mCount + 1){  //最后一页
            skipToCurrentPage(1);
        }
    }

    /**
     * 说明：当viewPager切换到最后一个item的时候，显示的是postion=1的图片，然后会自动将ViewPager切换到postion=1的条目，
     * 即mViewPager.setCurrentItem(1,false)，这个时候这个时候还是显示的是postion=1的图片。但是，此时又调用了一次动画切换。
     * 为了防止viewPager切换到最后一个item的时候调用动画卡顿、闪烁，这里做了处理。
     *
     */
    private void skipToCurrentPage(int currentItem) {
        if (mTransformPageImpl == null) {
            throw new IllegalArgumentException("mTransformPageImpl == null,BannerPagerAdapter first setTransformPage()!");
        }
        mTransformPageImpl.setHasTransformPageAnim(false);
        mViewPager.setCurrentItem(currentItem,false);
        mTransformPageImpl.setHasTransformPageAnim(true);
    }

    private void initImageView(int index) {
        BannerImageView image = new BannerImageView(mContext);
        if (mImageLoader != null) {
            image.setImageLoader(mImageLoader);
        }
        BannerImageView imageView = getImageView(index,image);
        mImageViews.add(imageView);
    }

    public void setSkipView(View skipView){
        mSkipView = skipView;
    }

    public void setEnterView(View enterView){
        mEnterView = enterView;
    }

    public void setBannerClickListener(OnBannerClickListener bannerClickListener) {
        mBannerClickListener = bannerClickListener;
    }

    public void setInfinite(boolean infinite) {
        mIsInfinite = infinite;
    }

    public void setOnSelectedItemCallback(OnSelectedItemCallback callback){
        mCallback = callback;
    }

    public void setTransformPage(TransformPageImpl transformPageImpl){
        mTransformPageImpl = transformPageImpl;
    }

    public void setOnIndicatorVisibilityListener(OnIndicatorVisibilityListener listener){
        this.mOnIndicatorVisibilityListener = listener;
    }

    public void setImageLoader(ImageLoaderInterface imageLoader) {
        this.mImageLoader = imageLoader;
    }

    public void setScaleType(int imageScaleType) {
        this. mImageScaleType = imageScaleType;
    }

    public void setLastIndicatorIsVisible(boolean lastIndicatorIsVisible) {
        mLastIndicatorIsVisible = lastIndicatorIsVisible;
    }
}
