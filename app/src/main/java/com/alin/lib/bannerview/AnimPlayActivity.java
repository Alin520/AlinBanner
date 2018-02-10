package com.alin.lib.bannerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alin.lib.bannerlib.BannerView;
import com.alin.lib.bannerlib.pagetransformer.TransformerSlidEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者   ： hailinhe
 * github地址 ： https://github.com/Alin520/Mvp-Rxjava-Retrofit
 * CSDN地址   ： http://blog.csdn.net/hailin123123/article/details/78643330
 * 创建时间    ： 2018/1/31 13:27
 * 版    本   ： ${TODO}
 * 描    述   ：  ${BannerView 动画效果展示}
 * ================================================
 */
public class AnimPlayActivity extends AppCompatActivity {

    private ListView mListView;
    private BannerView mBannerView;
    private int[]         mDatas;
    private List<Integer> mList;
    private ArrayList<String> mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_play);
        mListView = (ListView) findViewById(R.id.list_view);
        mBannerView = (BannerView) findViewById(R.id.anim_bv);

        initData();
        BannerAnimAdapter adapter = new BannerAnimAdapter(mBannerView);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnclickData(true,position);
            }
        });
    }

    private void initData() {
        mDatas = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        mList = new ArrayList<>();
        mList.add(R.mipmap.banner_1);
        mList.add(R.mipmap.banner_2);
        mList.add(R.mipmap.banner_3);

        mTitles = new ArrayList<>();
        mTitles.add("第一页到货发动你的号看到回复");
        mTitles.add("第二页佛挡");
        mTitles.add("第三页大佛我as的后if好的搜iuuuu家的三");
        mBannerView
                .setImages(mList)
                .setInfinite(true)
                .setIndcatorTitles(mTitles)
                .setAutoPlay(true)
                .start();
    }

    public void setBannerView(boolean isAutoPlay,TransformerSlidEffect slidEffect){
        mBannerView
                .setImages(mList)
                .setInfinite(true)
                .setIndcatorTitles(mTitles)
                .setAutoPlay(isAutoPlay)
                .setPageTransformer(slidEffect)
                .start();
    }

    private void setOnclickData(boolean isAutoPlay,int mPosition) {
        TransformerSlidEffect slidEffect = null;
        switch (mPosition) {
            case 0:
                slidEffect = TransformerSlidEffect.Default;
                break;
            case 1:
                slidEffect = TransformerSlidEffect.Alpha;
                break;
            case 2:
                slidEffect = TransformerSlidEffect.Rotate;
                break;
            case 3:
                slidEffect = TransformerSlidEffect.Zoom;
                break;
            case 4:
                slidEffect = TransformerSlidEffect.Translate;
                break;
            case 5:
                slidEffect = TransformerSlidEffect.Folding;
                break;
            case 6:
                slidEffect = TransformerSlidEffect.Flip;
                break;
            case 7:
                slidEffect = TransformerSlidEffect.Cube;
                break;
            case 8:
                slidEffect = TransformerSlidEffect.Fade;
                break;
            case 9:
                slidEffect = TransformerSlidEffect.Flashing;
                break;
        }
        setBannerView(isAutoPlay, slidEffect);
    }

    private class BannerAnimAdapter extends BaseAdapter {
        private BannerView mBannerView;
        private TextView mTitleTv;
        private int mPosition;
        private boolean mFlage = false;

        public BannerAnimAdapter(BannerView bannerView) {
            mBannerView = bannerView;
        }

        @Override
        public int getCount() {
            return mDatas.length;
        }

        @Override
        public Object getItem(int position) {
            return mDatas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @SuppressLint({"InflateParams", "ViewHolder"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mPosition = position;
            View view = getLayoutInflater().inflate(R.layout.banner_anim_item, null);
            mTitleTv = (TextView) view.findViewById(R.id.title_bn);
            setData();
            return view;
        }
        private void setData() {
            switch (mPosition){
                case 0:
                    mTitleTv.setText("Default");
                    break;
                case 1:
                    mTitleTv.setText("Alpha");
                    break;
                case 2:
                    mTitleTv.setText("Rotate");
                    break;
                case 3:
                    mTitleTv.setText("Zoom");
                    break;
                case 4:
                    mTitleTv.setText("Translate");
                    break;
                case 5:
                    mTitleTv.setText("Folding");
                    break;
                case 6:
                    mTitleTv.setText("Flip");
                    break;
                case 7:
                    mTitleTv.setText("Cube");
                    break;
                case 8:
                    mTitleTv.setText("Fade");
                    break;
                case 9:
                    mTitleTv.setText("Flashing");
                    break;
            }
        }
    }
}