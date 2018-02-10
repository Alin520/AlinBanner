
# 一、前序
[AlinBannerView](https://github.com/Alin520/AlinBannerView)是一款Android轮播控件、引导页面滑动框架。支持有限、无限循环，可以自动、收动播放，支持自定义指示器，支持各种样式的自定义和多种图片类型，如Url、drawable加载。也可以选择酷炫切换动画的切换和自定义动画的扩张。是一款简单，易扩展、易使用框架。

虽然市面上已经有很多人在写这个图片轮播框架了，但是质量参差不齐，有很多功能都不全面，或多或少都由这样或者那样的问题。当然，也有不乏优秀的作品，其中，具有代表性的有[BGABanner-Android](https://github.com/bingoogolapple/BGABanner-Android)和 youth5201314的[banner](https://github.com/youth5201314/banner)两个banner框架。个人觉得这两个框架对于一般的需求是都可以满足的，但是也有点小问题：

### 1、banner框架：</br>
	1）对于非无限轮播不支持。</br>
	2）当用手指滑动页至不同页面时，当手指手动离开页面时，轮播图的位置没有处理好，还是手指滑动前的位置。</br>
	3）banner框架是用的静态布局文件布局的，扩展性不好。</br>
### 2、BGABanner-Android：</br>
      1）对于图片的数据展示不能很好扩展。如用户图片想传静态的图片imageId或者drwable、或者url。</br>
      2）自定轮播次数是用的Integer的最大值，轮播次数受限制（这个可以满足正常需求）。</br>
      3）缓存机制做的不好。如动画缓存。</br>
      

因此，为了更好的体验效果和框架的扩展性。自己整了这个框架[AlinBannerView](https://github.com/Alin520/AlinBannerView)。
      希望能解决大家在实际项目中的一些难缠的问题。好了，闲话休叙，直接上图了。
   
点击下载 [AlinBannerView.apk](https://fir.im/ytb9) 或扫描下面的二维码安装</br>
![这里写图片描述](https://user-gold-cdn.xitu.io/2018/2/6/16169ba68119d425?w=192&h=187&f=png&s=18479)

# 联系方式</br>
      github地址：https://github.com/Alin520/AlinBannerView。
      掘金地址：https://juejin.im/post/5a7941c96fb9a0633f0dfe64
      CSDN地址：http://blog.csdn.net/hailin123123/article/details/79266112
      联系方式:
      欢迎加入QQ群：707202045</br>
  ![indicator样式风格](https://user-gold-cdn.xitu.io/2018/2/6/1616a124eff7cae3?w=412&h=562&f=png&s=56165)

# 一、效果展示
## 1、indicator指示器的展示。
支持圆形、数字、自定义的indicator。可以任意设置indicator</br>
的位置，以及title的位置和字体等属性。</br>
![indicator样式风格](https://user-gold-cdn.xitu.io/2018/2/6/16169a8cc778a513?w=287&h=493&f=gif&s=5034674)

## 2、图片切换动画效果展示。
支持各种轮播切换动画效果，并且支持自定义动画。注意这里不是本文的重点，</br>所以动画效果展示的不多，因为网上这样的文章很多，直接拿来用就可以了。</br>
![这里写图片描述](https://user-gold-cdn.xitu.io/2018/2/6/16169a8cc782d1b5?w=600&h=1067&f=gif&s=3379364)


## 3、滑动效果和引导图效果。
支持我们每个APP用到的引导图。支持无限轮播和非无限轮播，</br>以及无限轮播分为自动播放和手动播放效果。</br>
非无限轮播不支持自动播放，只能支持手动播放。</br>
![这里写图片描述](https://user-gold-cdn.xitu.io/2018/2/6/16169a8cc7bae5d0?w=338&h=599&f=gif&s=4938648)

# 二、功能介绍

 1. 支持多种格式的图片数据类型，如int型的ImageId、Drawable、String类型的Url。
 2. 支持自定义加载图片url方式，如Picasso、Fresco，默认是Glide。
 3. 支持无限轮播和有限轮播
 4. 支持手动播放（有限播放只能手动播放）和自动播放，且可自定义切换时间。
 5. 支持banner自定义切换动画方式。
 6. 支持indicator是数字类型、圆形指示器类型、自定义背景图片指示器类型和无indicator类型。
 7.  圆形指示器支持被选中的点和未选中的点大小一样、和被选中的点比未选中的点大小不一样，并且大小可以自定义设置。
 8. 支持indicator自定义参数，如indicator指示器位置（left、right、top、bottom、center）、内间距的padding、margin和指示点间的间距等参数。
 9. 支持bannerView的title展示，并且可以自定义title的文字大小、颜色、位置（left、right、center）等参数。
 10. 支持bannerView对每个ImageView的点击事件。
 11. 支持欢迎页引导图导航效果。
 12. 支持加载网络数据时设置占位图，避免出现整个广告条空白的情况

# 三、使用说明
## step1、添加依赖（ 必选）

```
dependencies {
   implementation 'com.alin:bannerlib:1.0.0'
}
    

```

## step2、在布局文件中添加BannerView（ 必选）

```
<com.alin.lib.bannerlib.BannerView
    android:layout_width="match_parent"
    android:layout_height="180dp"
    android:id="@+id/banner_bv"
    ></com.alin.lib.bannerlib.BannerView>
```
## step3、在 Activity 或者 Fragment 中配置 BannerView的数据（ 必选）

> 本框架使用的是链式编程结构，美观、易懂。

```
        mList = new ArrayList<>();                  //ImageView数据
        mList.add(R.mipmap.banner_1);           
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);
        
        mTitles = new ArrayList<>();                //title数据
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");
        
        mBannerView.setImages(mList)        //ImageView数据
                .setIndcatorTitles(mTitles) //title数据
                .start();                   //开始播放
```
## step4、给BannerView添加点击的监听（可选）

```
 mBannerView.setOnBannerClickListener(new OnBannerClickListener<Integer>() {
            @Override
            public void onBannerClickListener(BannerImageView imageView, Integer model, int position) {
                Toast.makeText(MainActivity.this,"第position=" + position + "被点击了，imageId=" + model,Toast.LENGTH_SHORT).show();
            }
        });
```

## 注意：banner图点击监听的泛型指定			
*OnBannerClickListener<Integer>类型需要与图片的数据类型保持一致。列如：setImages的图片数据是url的String类型，则泛型需要指定为String，若数据类型是Drawable，则泛型指定为Drawable。*

## step5、给BannerView根据不同的数据来源展示方式（可选）

> 本BannerView框架支持setImages的图片数据类型有常见的三种：Drawable、int（ImageId）、String（url）类型。

1）对于Drawable、int（ImageId）数据类型。直接设置mBannerView.setImages（）即可。</br>
2）对于String（url）类型，由于不同项目中使用的加载图片方式不一样，所以本框架提供了ImageLoaderInterface接口，支持自定义扩充。
默认是使用Glide加载url显示ImageView图片。</br>如果使用默认的，则直接跟上述一样设置数据即可。如果使用其他的图片加载方式，如Fresco、Picasso，则需要实现ImageLoaderInterface接口，并且需要指定泛型类型ImageLoaderInterface<数据类型String，BannerImageView需要显示的ImageView>，如果不使用默认的Glide，而用自定义的Picasso加载图片，具体如下：

```
//设置图片加载方式
mBannerView.setImageLoader(new DisplayUrlImageView ())
                      
public class DisplayUrlImageView implements ImageLoaderInterface<String,BannerImageView> {

    @Override
    public BannerImageView createImageView(Context context) {
        return new BannerImageView(context);
    }

    @Override
    public void displayImageView(Context context, String path, BannerImageView imageView) {
        AppUtil.checkNotNull(path,"displayImageView path is null error!");
         //Picasso 加载图片简单用法
        Picasso.with(context).load(path).into(imageView);
    }
}
```
## step6、欢迎引导页，设置「跳过」和[进入]控件View（可选）

> 内部已经做了防止多次点击处理，以及indicator指示器、跳过、进入按钮的显示隐藏处理。直接设置setSkipOrEnterListener（）监听即可使用。

```
TextView skipView = (TextView) findViewById(R.id.skip_tv);
TextView enterView = (TextView) findViewById(R.id.enter_tv);
//设置引导图的跳转、进入按钮        
mBannerView.setSkipOrEnterListener(skipView, enterView, new BannerView.OnSkipOrEnterCallback() {
            @Override
            public void onSkipOrEnterCallback() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
```
# 四、属性说明

```
  <!--bannerView-->
    <declare-styleable name="banner_view">
        <!--bannerView高度-->
        <attr name="banner_height" format="dimension|reference"></attr>
        <!--bannerView默认占位图-->
        <attr name="banner_default_background_imageview" format="reference"></attr>
        <!--indicator高度-->
        <attr name="indicator_background_height" format="dimension|reference"></attr>
        <!--indicator背景色-->
        <attr name="indicator_background_color" format="color|reference"></attr>
        <!--title长度-->
        <attr name="title_width" format="dimension|reference"></attr>
        <!--title字体颜色-->
        <attr name="title_text_color" format="color|reference"></attr>
        <!--title字体大小-->
        <attr name="title_text_size" format="dimension|reference"></attr>
        <!--数字pointer 字体颜色-->
        <attr name="number_pointer_text_color" format="color|reference"></attr>
        <!--数字pointer 字体大小-->
        <attr name="number_pointer_text_size" format="dimension|reference"></attr>
        <!--数字pointer 背景-->
        <attr name="number_pointer_background" format="reference"></attr>
        <!--数字pointer padding-->
        <attr name="number_pointer_padding" format="dimension|reference"></attr>
        <!--pointer 指示器每个小圆点的高度(默认)-->
        <attr name="pointer_height" format="dimension|reference"></attr>
        <!--indicator 指示器每个小圆点的宽度（默认）-->
        <attr name="pointer_width" format="dimension|reference"></attr>
        <!--pointer 指示器每个小圆点的高度（选中）-->
        <attr name="pointer_select_height" format="dimension|reference"></attr>
        <!--pointer 指示器每个小圆点的宽度（选中）-->
        <attr name="pointer_select_width" format="dimension|reference"></attr>
        <!--圆形pointer，被选中的drawable-->
        <attr name="pointer_drawable_selected" format="reference" />
        <!--圆形pointer，未被选中的drawable-->
        <attr name="pointer_drawable_unselected" format="reference" />
        <!--圆形pointer 两点之间的间距 -->
        <attr name="pointer_margin" format="dimension|reference"></attr>
        <!--indicator容器内部左右padding-->
        <attr name="indicator_left_and_right_padding" format="dimension|reference"/>
        <!--indicator容器内部上下padding-->
        <attr name="indicator_top_and_bottom_padding" format="dimension|reference"/>
        <!--indicator容器左右margin-->
        <attr name="indicator_left_and_right_margin" format="dimension|reference"/>
        <!--indicator容器上下margin-->
        <attr name="indicator_top_and_bottom_margin" format="dimension|reference"/>
        <!--是否自动轮播-->
        <attr name="is_auto_play" format="boolean"></attr>
        <!--每个页面间切换的时间-->
        <attr name="interval_time" format="integer"></attr>
        <attr name="page_change_duration" format="integer"></attr>
        <!--是否可以手动切换页面-->
        <attr name="is_touch_scroll" format="boolean"></attr>
        <!--是否无限轮播，注意：false不是无限轮播，此时也不能自动播放-->
        <attr name="is_infinite" format="boolean"></attr>
        <!--动画最小透明度-->
        <attr name="anim_min_alpha" format="float"></attr>
        <!--动画最小缩放大小-->
        <attr name="anim_min_scale" format="float"></attr>
        <!--动画最小旋转角度-->
        <attr name="anim_min_rotation" format="integer"></attr>
        <!--动画效果-->
        <attr name="slid_effect" format="enum">
            <enum name="Default" value="0"></enum>
            <enum name="Alpha" value="1"></enum>
            <enum name="Rotate" value="2"></enum>
            <enum name="Zoom" value="3"></enum>
            <enum name="Translate" value="4"></enum>
            <enum name="Folding" value="5"></enum>
            <enum name="Flip" value="6"></enum>
            <enum name="Cube" value="7"></enum>
            <enum name="Fade" value="8"></enum>
            <enum name="Flashing" value="9"></enum>
        </attr>
        <!--indicator内部pointer（数字、圆形指示器）的位置-->
        <attr name="banner_pointer_gravity" format="enum">
            <enum name="left" value="9" />
            <enum name="center" value="13" />
            <enum name="right" value="11" />
        </attr>
        <!--title位置-->
        <attr name="title_gravity" format="enum">
            <enum name="left" value="9" />
            <enum name="center" value="13" />
            <enum name="right" value="11" />
        </attr>
        <!--indicator在BannerView中位置-->
        <attr name="indicator_gravity" format="enum">
            <enum name="top" value="10" />
            <enum name="bottom" value="12" />
        </attr>
        <!--pointer类型，0没有indicator指示器，1圆形指示器，2数字指示器-->
        <attr name="pointer_type" format="enum">
            <enum name="none" value="0" />
            <enum name="number" value="1" />
            <enum name="circle" value="2" />
        </attr>
        <!--bannerView轮播图 imageView缩放类型-->
        <attr name="banner_image_scale_type" format="enum">
            <enum name="center" value="0" />
            <enum name="center_crop" value="1" />
            <enum name="center_inside" value="2" />
            <enum name="fit_center" value="3" />
            <enum name="fit_end" value="4" />
            <enum name="fit_start" value="5" />
            <enum name="fit_xy" value="6" />
            <enum name="matrix" value="7" />
        </attr>
    </declare-styleable>
```

# 五、可能遇到的问题说明

###### 1、QA：设置BannerView的高度为自适应layout_height="wrap_content"时，bannerView布局高度为0？ 
解决方式：设置高度为layout_height="match_parent"或者具体的一个高度值即可

###### 2、设置的Image的数据长度必须大于等于title的数据长度。 当设置的Image的数据长度是0时，展示的是默认的占位图，当数据长度是1时，bannerView是不能左右切换的。
###### 3、 当bannerView设置setInfinite(false)为有限播放的时候，再设置setAutoPlay（true）自动播放是无效的。因为当bannerView为有限播放的时候，只能是手动播放。


如果你觉得[AlinBannerView](https://github.com/Alin520/AlinBannerView) 能帮到你真正解决项目中的问题，就在博客中个我点个赞，或者去我的[github](https://github.com/Alin520/AlinBannerView)star。
如果项目中有问题，可以直接给我留言。 </br>  
点击下载 [AlinBannerView.apk](https://fir.im/ytb9) 或扫描下面的二维码安装</br>
![这里写图片描述](https://user-gold-cdn.xitu.io/2018/2/6/16169ba68119d425?w=192&h=187&f=png&s=18479)

# 联系方式</br>
      github地址：https://github.com/Alin520/AlinBannerView。
      掘金地址：https://juejin.im/post/5a7941c96fb9a0633f0dfe64
      CSDN地址：http://blog.csdn.net/hailin123123/article/details/79266112
      联系方式:
      欢迎加入QQ群：707202045</br>
  ![indicator样式风格](https://user-gold-cdn.xitu.io/2018/2/6/1616a124eff7cae3?w=412&h=562&f=png&s=56165)
