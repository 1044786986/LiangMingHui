package com.example.ljh.liangminghui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.liangminghui.home.HomeFragment;
import com.example.ljh.liangminghui.home.ViewPagerAdapterMain;
import com.example.ljh.liangminghui.personal.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment mFragmentHome;     //首页
    private Fragment mFragmentPeople;   //个人
    private ViewPagerAdapterMain mViewPagerAdapterMain; //ViewPager适配器
    private List<Fragment> mFragmentList;               //页面list
    private int mCurPos = 0;                             //当前页面位置 pos

    private ViewPager mViewPager;
    private LinearLayout mLinearLayoutHome,mLinearLayoutPeople; //首页按钮，个人页面按钮
    private ImageView mIvHome,mIvPeople;
    private TextView mTvHome,mTvPeople;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLayoutHome:
                changePage(0);
                break;
            case R.id.linearLayoutPeople:
                changePage(1);
                break;
        }
    }

    private void init(){
        mFragmentHome = new HomeFragment();
        mFragmentPeople = new PersonalFragment();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(mFragmentHome);
        mFragmentList.add(mFragmentPeople);

        mViewPagerAdapterMain = new ViewPagerAdapterMain(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapterMain);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changePageColor(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView(){
        mViewPager = findViewById(R.id.vpMain);
        mLinearLayoutHome = findViewById(R.id.linearLayoutHome);
        mLinearLayoutPeople = findViewById(R.id.linearLayoutPeople);
        mLinearLayoutHome.setOnClickListener(this);
        mLinearLayoutPeople.setOnClickListener(this);

        mIvHome = findViewById(R.id.ivHome);
        mIvPeople = findViewById(R.id.ivPeople);
        mTvHome = findViewById(R.id.tvHome);
        mTvPeople = findViewById(R.id.tvPeople);
    }

    private void changePage(int pos){
        if(mCurPos == pos){
            return;
        }
        mViewPager.setCurrentItem(pos);
        changePageColor(pos);
//        mCurPos = pos;
    }

    private void changePageColor(int pos){
        if(pos == mCurPos){
            return;
        }
        setAllNormal();
        switch (pos){
            case 0:
                mTvHome.setTextColor(getResources().getColor(R.color.selectText,null));
                mIvHome.setImageResource(R.drawable.home_select_100);
                break;
            case 1:
                mTvPeople.setTextColor(getResources().getColor(R.color.selectText,null));
                mIvPeople.setImageResource(R.drawable.people_select_100);
                break;
        }
        mCurPos = pos;
    }

    private void setAllNormal(){
        mIvHome.setImageResource(R.drawable.home_normal_100);
        mTvHome.setTextColor(getResources().getColor(R.color.normalText,null));
        mIvPeople.setImageResource(R.drawable.people_normal_100);
        mTvPeople.setTextColor(getResources().getColor(R.color.normalText,null));
    }
}
