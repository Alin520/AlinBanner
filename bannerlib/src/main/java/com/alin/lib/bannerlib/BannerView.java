package com.alin.lib.bannerlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alin.lib.bannerlib.listener.ImageLoaderInterface;
import com.alin.lib.bannerlib.listener.OnBannerClickListener;
import com.alin.lib.bannerlib.listener.OnBannerViewClickListener;
import com.alin.lib.bannerlib.listener.OnIndicatorVisibilityListener;
import com.alin.lib.bannerlib.listener.OnSelectedItemCallback;
import com.alin.lib.bannerlib.pagetransformer.PageTransformerFactory;
import com.alin.lib.bannerlib.pagetransformer.TransformPageImpl;
import com.alin.lib.bannerlib.pagetransformer.TransformerSlidEffect;
import com.alin.lib.bannerlib.util.AppUtil;
import com.alin.lib.bannerlib.util.CommonUtil;
import com.alin.lib.bannerlib.util.WeakHandler;
import com.alin.lib.bannerlib.view.BannerImageView;
import com.alin.lib.bannerlib.view.BannerPagerAdapter;
import com.alin.lib.bannerlib.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2017/12/27 17:17
 * 版    本   ： ${TODO}
 * 描    述   ：  ${TODO}
 * ================================================
 */
public class BannerView <T> extends RelativeLayout {
    private Context              mContext;
    private List<String>    mIndcatorTitles;              //banner的title
    private LayoutInflater    mInflater;
    private int               mBannerBackgroundImageId;
    private int               mCount;
    private ImageView         mBannerDefaultImage;
    private BannerViewPager mViewPager;
    private TextView          mBannerTitle;                           //轮播页数，小圆点
    private LinearLayout      mCircleIndicatorContainer;         //轮播页数，小圆点
    private TextView          mNumIndicatorContainer;
    private RelativeLayout    mIndicatorContainerLlyt;
    private int               mIndicatorBackgroundHeight;
    private int               mTitleTextColor;
    private int               mIndicatorBackgroundColor;
    private int               mImageScaleType;
    private int               mIndicatorGravity;
    private int mPointerHeight;
    private int mPointerWidth;
    private int mPointerSelected;
    private int mPointerUnselected;
    private int                   mPageChangeDuration;
    private int                   mIntervalTime;
    private boolean               mIsAutoPlay;
    private float                 mTitleTextSize;
    private ArrayList<ImageView>  mIndicatorImages;
    private int mPointerMargin;
    private BannerPagerAdapter mPagerAdpter;
    private boolean               mAllowTouchScrollable;      //true 可以手动滑动
    private OnBannerClickListener mBannerClickListener;
    private int                   mCurrentItem;
    private Runnable              mTask;
    private WeakHandler mHandler = new WeakHandler();
    private int mPointerType;
    private List<T>                     mImagesList;
    private boolean                     mIsInfinite;        //true 无限轮播
    private int mNumberPointerTextColor;
    private int mNumberPointerTextSize;
    private int                         mBannerPointerGravity;        //banner的title位置
    private LayoutParams mIndicatorParams;
    private int                         mLeftAndRightMargin;
    private int                   mTopAndBottomMargin;
    private int                   mLeftAndRightPadding;
    private int                   mTopAndBottomPadding;
    private int                   mTitleGravity;
    private int                   mTitleWidth;
    private int mNumberPointerPadding;
    private int mNumberPointerBackground;
    private LayoutParams          mPointParams;
    private LayoutParams          mTitleParams;
    private int                   mBannerHeight;
    private TransformerSlidEffect mSlidEffect;
    private int                   mSlidEffectIndex;
    private TransformPageImpl mPageTransformer;
    private long mCurrentTime;
    private long mLastTime;
    private float mAnimMinAlpha;
    private float mAnimMinScale;
    private int mAnimMinRotation;
    private int mPointerSelectHeight;
    private int mPointerSelectWidth;
    private LinearLayout.LayoutParams mParams;
    private ImageLoaderInterface  mImageLoader;
    private boolean mLastIndicatorIsVisible;

    public BannerView(@NonNull Context context) {
        this(context,null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        mContext = getContext();
        initAttrs(attrs);
        initData();
        initView();
        initTask();
    }

    @SuppressLint("ResourceAsColor")
    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.banner_view);
            try {
                if (ta != null) {
                    mBannerHeight = (int) ta.getDimension(R.styleable.banner_view_banner_height, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_BANNER_HEIGHT));
                    mBannerBackgroundImageId = ta.getResourceId(R.styleable.banner_view_banner_default_background_imageview, BannerConfig.DEFAULT_BANNER_IMAGEVIEW_ID);
                    mImageScaleType = ta.getInt(R.styleable.banner_view_banner_image_scale_type, BannerConfig.DEFAULT_BANNER_IMAGEVIEW_SCACLE_TYPE);
                    mPageChangeDuration = ta.getInt(R.styleable.banner_view_page_change_duration, BannerConfig.DEFAULT_PAGE_CHANGE_DURATION);
                    mIntervalTime = ta.getInt(R.styleable.banner_view_interval_time, BannerConfig.DEFAULT_INTERVAL_TIME);
                    mIsAutoPlay = ta.getBoolean(R.styleable.banner_view_is_auto_play, BannerConfig.DEFAULT_IS_AUTO_PLAY);
                    mAllowTouchScrollable = ta.getBoolean(R.styleable.banner_view_is_touch_scroll, BannerConfig.DEFAULT_ALLOW_TOUCH_SCROLLABLE);
                    mIsInfinite = ta.getBoolean(R.styleable.banner_view_is_infinite, BannerConfig.DEFAULT_IS_INFINITE);
                    mBannerPointerGravity = ta.getInt(R.styleable.banner_view_banner_pointer_gravity, BannerConfig.DEFAULT_BANNER_POINTER_GRAVITY);
                    mIndicatorBackgroundColor = ta.getColor(R.styleable.banner_view_indicator_background_color, getResources().getColor(BannerConfig.DEFAULT_INDICATOR_BACKGROUND_COLOR));
                    mIndicatorBackgroundHeight = (int) ta.getDimension(R.styleable.banner_view_indicator_background_height, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_BACKGROUND_HEIGHT));
                    //未选中
                    mPointerUnselected = ta.getResourceId(R.styleable.banner_view_pointer_drawable_unselected, BannerConfig.DEFAULT_POINTER_UNSELECTED);
                    //选中
                    mPointerSelected = ta.getResourceId(R.styleable.banner_view_pointer_drawable_selected, BannerConfig.DEFAULT_POINTER_SELECTED);
                    mTitleTextColor = ta.getColor(R.styleable.banner_view_title_text_color, getResources().getColor(BannerConfig.DEFAULT_INDICATOR_TEXT_COLOR));
                    mTitleTextSize = ta.getDimension(R.styleable.banner_view_title_text_size, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_TEXT_SIZE));
                    //                    数字指示器
                    mNumberPointerTextColor = ta.getColor(R.styleable.banner_view_number_pointer_text_color, getResources().getColor(BannerConfig.DEFAULT_NUMBER_POINTER_TEXT_COLOR));
                    mNumberPointerTextSize = (int) ta.getDimension(R.styleable.banner_view_number_pointer_text_size, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_NUMBER_POINTER_TEXT_SIZE));
                    mNumberPointerPadding = (int) ta.getDimension(R.styleable.banner_view_number_pointer_padding, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_NUMBER_POINTER_PADDING));
                    mNumberPointerBackground = ta.getResourceId(R.styleable.banner_view_number_pointer_background, BannerConfig.DEFAULT_NUMBER_POINTER_BACKGROUND);
                    mPointerHeight = (int) ta.getDimension(R.styleable.banner_view_pointer_height, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_HEIGHT));
                    mPointerWidth = (int) ta.getDimension(R.styleable.banner_view_pointer_width, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_WIDTH));
                    mPointerSelectHeight = (int) ta.getDimension(R.styleable.banner_view_pointer_select_height, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_HEIGHT));
                    mPointerSelectWidth = (int) ta.getDimension(R.styleable.banner_view_pointer_select_width, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_WIDTH));
                    mPointerMargin = (int) ta.getDimension(R.styleable.banner_view_pointer_margin, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_MARGIN));
                    mIndicatorGravity = ta.getInt(R.styleable.banner_view_indicator_gravity, BannerConfig.DEFAULT_INDICATOR_GRAVITY);
                    mPointerType = ta.getInt(R.styleable.banner_view_pointer_type, BannerConfig.DEFAULT_POINTER_TYPE);
                    mSlidEffectIndex = ta.getInt(R.styleable.banner_view_slid_effect, BannerConfig.DEFAULT_BANNER_SLID_EFFECT);
                    mTitleGravity = ta.getInt(R.styleable.banner_view_title_gravity, BannerConfig.DEFAULT_BANNER_TITLE_GRAVITY);
                    mTitleWidth = (int) ta.getDimension(R.styleable.banner_view_title_width, BannerConfig.DEFAULT_BANNER_TITLE_WIDTH);
                    mLeftAndRightMargin = (int) ta.getDimension(R.styleable.banner_view_indicator_left_and_right_margin, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_LEFT_AND_RIGHT_MARGIN));
                    mTopAndBottomMargin = (int) ta.getDimension(R.styleable.banner_view_indicator_top_and_bottom_margin, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_TOP_AND_BOTTOM_MARGIN));
                    mLeftAndRightPadding = (int) ta.getDimension(R.styleable.banner_view_indicator_left_and_right_margin, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_LEFT_AND_RIGHT_PADDING));
                    mTopAndBottomPadding = (int) ta.getDimension(R.styleable.banner_view_indicator_left_and_right_margin, CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_INDICATOR_TOP_AND_BOTTOM_PADDING));
                    mAnimMinAlpha = ta.getFloat(R.styleable.banner_view_anim_min_alpha, BannerConfig.DEFAULT_MIN_ALPHA);
                    mAnimMinScale = ta.getFloat(R.styleable.banner_view_anim_min_scale, BannerConfig.DEFAULT_MIN_SCALE);
                    mAnimMinRotation = ta.getInt(R.styleable.banner_view_anim_min_rotation, BannerConfig.DEFAULT_MIN_ROTATION);
                    mLastIndicatorIsVisible = ta.getBoolean(R.styleable.banner_view_last_indicator_is_visible, BannerConfig.DEFAULT_LAST_INDICATOR_IS_VISIBLE);
                }
            }catch (Throwable t){
                t.printStackTrace();
            }finally {
                ta.recycle();
            }
        }
    }

    private void initData() {
        mImagesList = new ArrayList<>();
        mIndcatorTitles = new ArrayList<>();
        mIndicatorImages = new ArrayList<>();   //Indicator集合
        mSlidEffect = TransformerSlidEffect.valueOf(mSlidEffectIndex);  //滑动效果
        mImageLoader = BannerConfig.DEFAULT_IMAGE_LOADER;
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        initViewUI();
        dispatchIndicatorUi();
    }

    private void initViewUI() {
        AppUtil.checkNotNull(mContext,"context is null");
//     ViewPager布局
       initViewPagerUI();
//        默认占位图
       initBannerDefaultImag();
//        indicator布局
        initIndicatorUI();
//        titles
        initBannerTitleUI();
//        5、设置参数布局位置
        setParamsGravity();
    }

    /****** 1、ViewPager布局 ********/
    private void initViewPagerUI() {
        if (mViewPager != null && this.equals(mViewPager.getParent())) {
            this.removeView(mViewPager);
            mViewPager = null;
        }
        mViewPager = new BannerViewPager(mContext);
        mBannerHeight = mBannerHeight > 0 ? mBannerHeight : BannerConfig.RM;
        LayoutParams viewPagerParams = new LayoutParams(BannerConfig.RM,mBannerHeight);
        addView(mViewPager,viewPagerParams);
        diapatchViewPagerUI();
    }

    /****** 2、默认占位图 ********/
    private void initBannerDefaultImag() {
        if (mBannerDefaultImage == null) {
            mBannerDefaultImage = new ImageView(mContext);
        }
        mBannerDefaultImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (mBannerBackgroundImageId > 0) {
            mBannerDefaultImage.setImageResource(mBannerBackgroundImageId);
        }
        if (mBannerHeight > 0) {
            LayoutParams defaultImageViewParams = new LayoutParams(BannerConfig.RM,mBannerHeight);
            defaultImageViewParams.setMargins(0,0,0,0);
            addView(mBannerDefaultImage,defaultImageViewParams);
        }else {
            addView(mBannerDefaultImage);
        }
        mBannerDefaultImage.setVisibility(GONE);
    }

    /****** 3、indicator布局 ********/
    private void initIndicatorUI() {
        mIndicatorContainerLlyt = new RelativeLayout(mContext);
        mIndicatorContainerLlyt.setPadding(mLeftAndRightPadding,mTopAndBottomPadding,mLeftAndRightPadding,mTopAndBottomPadding);
        mIndicatorContainerLlyt.setBackgroundColor(mIndicatorBackgroundColor);
        mIndicatorParams = new LayoutParams(BannerConfig.RM,mIndicatorBackgroundHeight > 0 ? mIndicatorBackgroundHeight : BannerConfig.RW);
        mIndicatorParams.setMargins(mLeftAndRightMargin,mTopAndBottomMargin,mLeftAndRightMargin,mTopAndBottomMargin);
        if (mIndicatorGravity == RelativeLayout.ALIGN_PARENT_TOP) {   //top
            mIndicatorParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }else {         //bottom
            mIndicatorParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        addView(mIndicatorContainerLlyt,mIndicatorParams);

        /****** 指示器point布局 ********/
        /*数字指示器*/
        mNumIndicatorContainer = new TextView(mContext);
        mNumIndicatorContainer.setId(R.id.numIndicatorContainerTv);
        mNumIndicatorContainer.setSingleLine(true);
        mNumIndicatorContainer.setEllipsize(TextUtils.TruncateAt.END);
        mNumIndicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        mNumIndicatorContainer.setVisibility(INVISIBLE);
        if (mNumberPointerTextSize > 0) {
            mNumIndicatorContainer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mNumberPointerTextSize);
        }
        mNumIndicatorContainer.setTextColor(mNumberPointerTextColor);
        mNumIndicatorContainer.setBackgroundResource(mNumberPointerBackground);
        if (mNumberPointerPadding > 0) {
            mNumIndicatorContainer.setPadding(mNumberPointerPadding, mNumberPointerPadding, mNumberPointerPadding, mNumberPointerPadding);
        }

        /****** 圆点指示器 ********/
        mCircleIndicatorContainer = new LinearLayout(mContext);
        mCircleIndicatorContainer.setId(R.id.circleIndicatorContainerLlyt);
        mCircleIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        mCircleIndicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        mPointParams = new LayoutParams(BannerConfig.RW,BannerConfig.RW);
        mPointParams.addRule(CENTER_VERTICAL);
        mIndicatorContainerLlyt.addView(mNumIndicatorContainer, mPointParams);
        //因为圆形指示器的每个点都设置了rightMargin，导致在最后一个点多出了一个右边距,最后指
        // 示器整体没有居中。因此设置指示器容器一个left的padding，这样刚好左右平衡了
        mCircleIndicatorContainer.setPadding(mPointerMargin,0,0,0);
        mIndicatorContainerLlyt.addView(mCircleIndicatorContainer, mPointParams);
    }

    /****** 4、titles ********/
    /**
     *  注意：若titleParams的width设置为BannerConfig.RW，则由于布局长度是自适应，所以每个页面的title显示会因为长度不一致而乱跳
     */
    private void initBannerTitleUI() {
        mTitleParams = new LayoutParams(mTitleWidth < 0 ? BannerConfig.RW : CommonUtil.dp2px(mContext,mTitleWidth), BannerConfig.RW);
        mTitleParams.addRule(CENTER_VERTICAL);
        if (mBannerTitle == null) {
            mBannerTitle = new TextView(mContext);
        }
        mBannerTitle.setId(R.id.bannerTitleTv);
        mBannerTitle.setSingleLine(true);
        mBannerTitle.setEllipsize(TextUtils.TruncateAt.END);
        mBannerTitle.setGravity(Gravity.CENTER_VERTICAL);
        mBannerTitle.setTextColor(mTitleTextColor);
        if (mTitleTextSize > 0) {
            mBannerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTitleTextSize);
        }
        mIndicatorContainerLlyt.addView(mBannerTitle, mTitleParams);
    }

    /****** 5、设置参数布局位置 ********/
    private void setParamsGravity() {
        /**
         * mTitleGravity 若未指定Title位置，则使用默认的位置
         */
        if (mBannerPointerGravity == RelativeLayout.ALIGN_PARENT_LEFT) {        //左边
            mPointParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.RIGHT_OF | RelativeLayout.ALIGN_PARENT_RIGHT,R.id.numIndicatorContainerTv);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.RIGHT_OF | RelativeLayout.ALIGN_PARENT_RIGHT,R.id.circleIndicatorContainerLlyt);
        }else if (mBannerPointerGravity == RelativeLayout.ALIGN_PARENT_RIGHT){   //右边
            mPointParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.LEFT_OF | RelativeLayout.ALIGN_PARENT_START,R.id.circleIndicatorContainerLlyt);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.LEFT_OF | RelativeLayout.ALIGN_PARENT_START,R.id.numIndicatorContainerTv);
        }else{   //中间
            mPointParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.RIGHT_OF | RelativeLayout.ALIGN_PARENT_LEFT,R.id.circleIndicatorContainerLlyt);
            mTitleParams.addRule(mTitleGravity != -1 ? mTitleGravity : RelativeLayout.RIGHT_OF | RelativeLayout.ALIGN_PARENT_LEFT,R.id.numIndicatorContainerTv);
        }
    }

    private void initTask() {
        mTask = new Runnable() {
            @Override
            public void run() {
                if (mIsInfinite) {  //无限轮播
                    mCurrentItem = mCurrentItem % (mCount + 1) + 1;
                    if (mCurrentItem == 1) {
                        //当ViewPager由最后一页滑动到第一页时，mCurrentItem=1，实际是第一个页面被选中。当ViewPager从最后一页滑动到第二页时，mCurrentItem=2，实际是第一个页面被选中。
                        //因此，这里为了避免第一个页面被重复选中两次
                        mCurrentItem++;
                    }
                }else {
                    mCurrentItem = mCurrentItem % mCount + 1;
                }
                mViewPager.setCurrentItem(mCurrentItem);
                mHandler.postDelayed(mTask, mIntervalTime);
            }
        };
    }

    //    分发IndicatorUi
    private void dispatchIndicatorUi() {
        int visibility = mCount > 1 ? View.VISIBLE : View.GONE;
        mIndicatorContainerLlyt.setVisibility(View.GONE);
        switch (mPointerType){
            case BannerConfig.STYLE_NOT_INDICATOR:       //轮播没有indicator
                break;
            case BannerConfig.STYLE_CIRCLE_INDICATOR:       //indicator为圆圈
                mIndicatorContainerLlyt.setVisibility(VISIBLE);
                mNumIndicatorContainer.setVisibility(GONE);
                mCircleIndicatorContainer.setVisibility(visibility);
                break;
            case BannerConfig.STYLE_NUM_INDICATOR:      //indicator为数字
                mIndicatorContainerLlyt.setVisibility(VISIBLE);
                mCircleIndicatorContainer.setVisibility(GONE);
                mNumIndicatorContainer.setVisibility(visibility);
                break;
        }

        if (!mIndcatorTitles.isEmpty()) {
            AppUtil.checkIsEqual(mIndcatorTitles.size(),mImagesList.size(),"[Banner] --> The number of titles and images size is different");
            mBannerTitle.setVisibility(VISIBLE);
        }else {             //title为空，隐藏
            mBannerTitle.setVisibility(GONE);
        }
    }

    private void diapatchViewPagerUI() {
        if (mViewPager == null) {
            throw new IllegalArgumentException("BannerViewPager is null !");
        }

        if (mViewPager.getAdapter() == mPagerAdpter) {
            mPagerAdpter = null;
        }

        if (mPagerAdpter == null) {
            mPagerAdpter = new BannerPagerAdapter<Integer>(getContext(),null,mViewPager);
        }
        mViewPager.setAdapter(mPagerAdpter);
        mPagerAdpter.setScaleType(mImageScaleType);
        mPagerAdpter.setTransformPage(mPageTransformer);
        mPagerAdpter.setLastIndicatorIsVisible(mLastIndicatorIsVisible);

        mViewPager.setOnPageChangeListener(mPagerAdpter);
        if (mSlidEffect != null) {
            TransformPageImpl transformer = PageTransformerFactory.createTransformer(mSlidEffect);
            if (transformer != null) {
                transformer.setMinAlpha(mAnimMinAlpha);
                transformer.setMinRotation(mAnimMinRotation);
                transformer.setMinScale(mAnimMinScale);
                mPagerAdpter.setTransformPage(transformer);
            }
            mViewPager.setPageTransformer(false, PageTransformerFactory.createTransformer(mSlidEffect));
        }

        if (mPageTransformer != null) {
            mViewPager.setPageTransformer(false,mPageTransformer);
        }
        mViewPager.setFocusable(true);
        setPageChangeDuration(mPageChangeDuration);
        mPagerAdpter.setInfinite(mIsInfinite);

        mPagerAdpter.setBannerClickListener(new OnBannerClickListener<T>() {

            @Override
            public void onBannerClickListener(BannerImageView imageView, T model, int position) {
                if (mBannerClickListener != null) {
                    mBannerClickListener.onBannerClickListener(imageView,model,position);
                }
            }
        });

        mPagerAdpter.setOnSelectedItemCallback(new OnSelectedItemCallback() {
            @Override
            public void onSelectedCallback(int selectedItem) {
                mCurrentItem = selectedItem;
                switchIndicatorAndTitle();
            }
        });

        mPagerAdpter.setOnIndicatorVisibilityListener(new OnIndicatorVisibilityListener() {
            @Override
            public void onIndicatorVisibilityListener(boolean visibility) {
             mIndicatorContainerLlyt.setVisibility(visibility ? VISIBLE : GONE);
            }
        });
    }

    /**
     * @deprecated 切换Indicator
     *  说明：由于真实的ViewPager的item数量比Indicator多两个，因为我们在ViewPager的第一个位置和最后一位都补了一个item，即position=0和position=count，
     *       例如：imagesList.size()=4,ViewPager实际的item有4+2=6个,即position范围是[0,5]，而Indicator只有4个。所以，
     *
     *        1、当ViewPager的position=1的时候，Indicator被选中的应该是第1个。
     *        2、当ViewPager的position=4的时候，Indicator被选中的应该是第4个。
     *       ** 3、当ViewPager的position=0的时候，Indicator被选中的应该是最后一个，即第4个。
     *       ** 4、当ViewPager的position=5的时候，Indicator被选中的应该是第一个，即第1个。
     *
     *  因此，Indicator第1个被选中的情况有两次，即 ViewPager的position=1和position=5的时候。
     *      Indicator第4个被选中的情况有两次，即 ViewPager的position=0和position=4的时候。
     *      中间的都只有一次。
     */
    private void switchIndicatorAndTitle() {
        int indicatorId = 0;
        if (mIsInfinite) {  //无限轮播
            if (mCurrentItem == 0){
                indicatorId = mCount - 1;
            }else if (mCount + 1 == mCurrentItem) {
                indicatorId = 0;
            }else if (mCurrentItem <= mCount){
                indicatorId = mCurrentItem - 1;
            }
        }else {
            indicatorId = mCurrentItem;
        }

        Log.d("indicatorId","indicatorId==" + indicatorId);
        if (mIndicatorImages != null && !mIndicatorImages.isEmpty() &&
                mPointerType == BannerConfig.STYLE_CIRCLE_INDICATOR) {   //圆形指示器
            AppUtil.checkIsEqual(mCount,mIndicatorImages.size(),"IndicatorImages size is not equals imagesList size!");
            for (int i = 0; i < mIndicatorImages.size(); i++) {
                ImageView imageView = mIndicatorImages.get(i);
                if (i == indicatorId) {     //选中
                    mParams = new LinearLayout.LayoutParams(mPointerSelectWidth, mPointerSelectHeight);
                    imageView.setImageResource(mPointerSelected);
                }else {          //未被选中
                    mParams = new LinearLayout.LayoutParams(mPointerWidth, mPointerHeight);
                    imageView.setImageResource(mPointerUnselected);
                }

                mParams.rightMargin = mPointerMargin;
                mParams.gravity = Gravity.CENTER;
                imageView.setLayoutParams(mParams);

            }
        }else if (mPointerType == BannerConfig.STYLE_NUM_INDICATOR){      //数字指示器
            int selectNum = indicatorId + 1;
            mNumIndicatorContainer.setText(selectNum + "/" + mCount);
        }
        //            titles设置
        if (mIndcatorTitles != null && mIndcatorTitles.size() > 0 && mIndcatorTitles.size() > indicatorId) {
            mBannerTitle.setText(mIndcatorTitles.get(indicatorId));
        }
    }


    /*
    * 开始自动播放
    * */
    public void startAutoPlay(){
        if (mHandler != null && mTask != null & mIsAutoPlay) {
            mHandler.removeCallbacks(mTask);
            mHandler.postDelayed(mTask, mIntervalTime);
        }
    }

    /*
    * 停止自动播放
    * */
    public void stopAutoPlay(){
        if (mHandler != null && mTask != null) {
            mHandler.removeCallbacks(mTask);
        }
    }

//    开始轮播
    public BannerView start(){
        stopAutoPlay();
        setIndicatorData();
        dispatchIndicatorUi();
        diapatchViewPagerUI();
        setViewPagerData();
//        自动播放条件：mCount>1,同时是无限轮播
        mIsAutoPlay = mIsAutoPlay && mCount > 1 && mIsInfinite;
        if (mIsAutoPlay)
            mHandler.postDelayed(mTask, mIntervalTime);
        return this;
    }

    /**
     * 引导页设置跳转和进入的View
     * @param skipView      跳转的View
     * @param enterView     进入的View
     * @param callback      回调
     */
    public void setSkipOrEnterListener(View skipView,View enterView,OnSkipOrEnterCallback callback){
        mOnSkipOrEnterCallback = callback;
        if (mPagerAdpter != null) {
            mPagerAdpter.setEnterView(enterView);
            mPagerAdpter.setSkipView(skipView);
        }
        if (skipView != null) {
            skipView.setOnClickListener(new OnBannerViewClickListener() {
                @Override
                public void onBannerViewClick() {
                    if (mOnSkipOrEnterCallback != null) {
                        mOnSkipOrEnterCallback.onSkipOrEnterCallback();
                    }
                }
            });
        }
        if (enterView != null) {
            enterView.setOnClickListener(new OnBannerViewClickListener() {
                @Override
                public void onBannerViewClick() {
                    if (mOnSkipOrEnterCallback != null) {
                        mOnSkipOrEnterCallback.onSkipOrEnterCallback();
                    }
                }
            });
        }
    }

    private void setIndicatorData() {
        if (mImagesList == null || mImagesList.isEmpty()) {
            mBannerDefaultImage.setVisibility(VISIBLE);
            return;
        }
        mBannerDefaultImage.setVisibility(GONE);
        createImageIndicator();
    }

    private void createImageIndicator() {
        if (mPointerType == BannerConfig.STYLE_CIRCLE_INDICATOR) {   //小圆点
            dispatchCircleIndicator();
        }else if (mPointerType == BannerConfig.STYLE_NUM_INDICATOR){       //数字
            mNumIndicatorContainer.setText(mContext.getString(R.string.number_indicator)+ mCount);
        }
    }

    //    图片指示器的创建
    private void dispatchCircleIndicator() {
        if (mIndicatorImages == null) {
            mIndicatorImages = new ArrayList<>();
        }
        //        清空之前的Indicator数据
        mIndicatorImages.clear();
        mCircleIndicatorContainer.removeAllViews();
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //指示器pointer默认宽、高度
            mPointerWidth = mPointerWidth == 0 ? BannerConfig.LW : mPointerWidth;
            mPointerHeight = mPointerHeight == 0 ? BannerConfig.LW : mPointerHeight;
            //若选中的指示器pointer的宽、高大小等于默认的宽、高，则选中的指示器pointer的宽、高大小等于未选中的宽、高。
            //若选中的指示器pointer的宽、高不等于默认的宽、高，则用自定义的选中的宽、高
            if (mPointerSelectHeight == CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_HEIGHT)) {
                mPointerSelectHeight = mPointerHeight;
            }
            if (mPointerSelectWidth == CommonUtil.dp2px(mContext, BannerConfig.DEFAULT_POINTER_WIDTH)) {
                mPointerSelectWidth = mPointerWidth;
            }
            if (i != 0) {
                mParams = new LinearLayout.LayoutParams(mPointerWidth, mPointerHeight);
                imageView.setImageResource(mPointerUnselected);
            }else {     //第一个默认为选中状态
                mParams = new LinearLayout.LayoutParams(mPointerSelectWidth, mPointerSelectHeight);
                imageView.setImageResource(mPointerSelected);
            }
            mParams.rightMargin = mPointerMargin;
            mParams.gravity = Gravity.CENTER;
            imageView.setLayoutParams(mParams);
            mIndicatorImages.add(imageView);
            mCircleIndicatorContainer.addView(imageView);
        }
    }

    //    设置ViewPager数据
    private void setViewPagerData() {
        if (mAllowTouchScrollable && mCount > 1) {
            mViewPager.setAllowTouchScrollable(true);
        }else {
            mViewPager.setAllowTouchScrollable(false);
        }
        if (mPagerAdpter != null) {
            if (mImageLoader != null) {
                mPagerAdpter.setImageLoader(mImageLoader);
            }
            mPagerAdpter.refreshToData(mImagesList,mIsInfinite);
        }
        if (mIsInfinite) {      //无限轮播
            mViewPager.setCurrentItem(1,false);
            mViewPager.setOffscreenPageLimit(mImagesList.size() + 3);
        }else {
            mViewPager.setCurrentItem(0,false);
            mViewPager.setOffscreenPageLimit(mImagesList.size());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP){
            startAutoPlay();
        }else if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE
                || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE){
            stopAutoPlay();
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     *  @deprecated
     *  设置image 数据
     */
    public BannerView setImages(@Nullable List<T> imagesList){
        AppUtil.checkNotNull(imagesList);
        if (mImagesList == null) {
            mImagesList = new ArrayList<>();
        }
        mCount = imagesList.size();
        mImagesList = imagesList;
        return this;
    }

    /**
     *  @deprecated
     *  设置titles 数据
     */
    public BannerView setIndcatorTitles(List<String> indcatorTitles){
        AppUtil.checkListNotNull(indcatorTitles,"setIndcatorTitles params is not null or is empty!");
        mIndcatorTitles = indcatorTitles;
        return this;
    }

    public BannerView setPageChangeDuration(int pageChangeDuration) {
        if (pageChangeDuration >= 0 && pageChangeDuration <= 2000) {
            mViewPager.setPageChangeDuration(pageChangeDuration);
        }
        return this;
    }

    public boolean isAutoPlay() {
        return mIsAutoPlay;
    }

    public BannerView setAutoPlay(boolean autoPlay) {
        mIsAutoPlay = autoPlay;
        return this;
    }

    public BannerView setPageTransformer(TransformerSlidEffect slidEffect){
        mSlidEffect = slidEffect;
        return this;
    }

    public boolean isAllowTouchScrollable() {
        return mAllowTouchScrollable;
    }

    public BannerView setAllowTouchScrollable(boolean allowTouchScrollable) {
        mAllowTouchScrollable = allowTouchScrollable;
        return this;
    }

    public int getIndicatorType() {
        return mPointerType;
    }

    public BannerView setIndicatorType(IndicatorType indicatorType) {
        mPointerType = indicatorType.ordinal();
        return this;
    }

    public TransformerSlidEffect getSlidEffect() {
        return mSlidEffect;
    }

    public BannerView setSlidEffect(TransformerSlidEffect slidEffect) {
        mSlidEffect = slidEffect;
        return this;
    }

//    动画效果
    public BannerView setSlidEffect(TransformPageImpl pageTransformer) {
        mPageTransformer = pageTransformer;
        return this;
    }

    public boolean isInfinite() {
        return mIsInfinite;
    }

    public BannerView setImageLoader(ImageLoaderInterface imageLoader) {
        this.mImageLoader = imageLoader;
        return this;
    }

//    是否无限轮播
    public BannerView setInfinite(boolean infinite) {
        mIsInfinite = infinite;
        return this;
    }

    public <M> BannerView setOnBannerClickListener(OnBannerClickListener<M> listener){
        this.mBannerClickListener = listener;
        return this;
    }

    public  OnSkipOrEnterCallback mOnSkipOrEnterCallback;

    public interface OnSkipOrEnterCallback{
        void onSkipOrEnterCallback();
    }

//    指示器类型
    public enum IndicatorType{
        None,       //没有
        Number,     //数字
        Circle      //圆形
    }
}
