package com.alin.lib.bannerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alin.lib.bannerlib.BannerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Integer> mList;
    private ArrayList<String> mTitles;
    private BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerView = (BannerView) findViewById(R.id.main_bn);
        findViewById(R.id.indicator_tv).setOnClickListener(this);
        findViewById(R.id.anim_tv).setOnClickListener(this);
        findViewById(R.id.auto_play_tv).setOnClickListener(this);
        findViewById(R.id.welcome_tv).setOnClickListener(this);
        findViewById(R.id.welcome_overlay_tv).setOnClickListener(this);
        findViewById(R.id.image_type_tv).setOnClickListener(this);
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(R.mipmap.banner_1);
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);

        mTitles = new ArrayList<>();
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");
        mBannerView.setImages(mList)
                .setIndcatorTitles(mTitles)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.indicator_tv:
                startActivity(new Intent(MainActivity.this,IndicatorResultActivity.class));
                break;
            case R.id.anim_tv:
                startActivity(new Intent(MainActivity.this,AnimPlayActivity.class));
                break;
            case R.id.auto_play_tv:
                startActivity(new Intent(MainActivity.this,AutoPlayActivity.class));
                break;
            case R.id.image_type_tv:
                startActivity(new Intent(MainActivity.this,ImageTypeActivity.class));
                finish();
                break;
            case R.id.welcome_tv:
                startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                finish();
                break;
            case R.id.welcome_overlay_tv:
                startActivity(new Intent(MainActivity.this,WelcomeOverlayActivity.class));
                finish();
                break;


        }
    }
}
