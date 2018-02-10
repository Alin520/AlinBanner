package com.alin.lib.bannerview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alin.lib.bannerlib.BannerView;
import com.alin.lib.bannerlib.listener.OnBannerClickListener;
import com.alin.lib.bannerlib.view.BannerImageView;
import com.alin.lib.bannerview.view.DisplayUrlImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/2/1 11:57
 * 版    本   ： ${TODO}
 * 描    述   ：  ${支持图片类型}
 * ================================================
 */
public class ImageTypeActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Integer> mList;
    private ArrayList<String> mTitles;
    private BannerView mBannerView;
    private ArrayList<String> mList2;
    private ArrayList<Drawable> mList3;
    private ArrayList mUrlTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_type);

        mBannerView = findViewById(R.id.image_type_bv);
        findViewById(R.id.id_type_tv).setOnClickListener(this);
        findViewById(R.id.url_type_tv).setOnClickListener(this);
        findViewById(R.id.drawable_type_tv).setOnClickListener(this);
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.banner_1);
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);
        mList2 = new ArrayList<>();
        mList3 = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");

        mUrlTitles = new ArrayList<>();
        mUrlTitles.add("Url路径下的图片，第一页到货发动你的号看到回复");
        mUrlTitles.add("Url路径下的图片");
        mBannerView.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_type_tv:
                mBannerView.setImages(mList)
                        .setIndcatorTitles(mTitles)
                        .start();
                break;
            case R.id.url_type_tv:
                if (!mList2.isEmpty()) {
                    mList2.clear();
                }
                mList2.add("http://images.cyoubike.com/image_park_release.png");
                mList2.add("http://images.cyoubike.com/image_fence_warning.png");
                mBannerView.setImages(mList2)
                        .setIndcatorTitles(mUrlTitles)
                        .setImageLoader(new DisplayUrlImageView())
                        .start();
                break;

            case R.id.drawable_type_tv:
                if (!mList3.isEmpty()) {
                    mList3.clear();
                }
                Drawable drawable1 = getResources().getDrawable(R.mipmap.banner_1);
                Drawable drawable2 = getResources().getDrawable(R.mipmap.banner_2);
                Drawable drawable3 = getResources().getDrawable(R.mipmap.banner_3);
                mList3.add(drawable1);
                mList3.add(drawable2);
                mList3.add(drawable3);
                mBannerView.setImages(mList3)
                        .setIndcatorTitles(mTitles)
                        .start();
                break;
        }
    }
}
