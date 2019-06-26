package com.example.library;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.library.TitleLayout;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerActivity extends AppCompatActivity {

    private TextView text;

    private TextView tv1;
    private TextView tv2;
    private ImageView lineTv1;
    private ImageView lineTv2;
    private ViewPager pager;;

    private List<TextView> tvs = new ArrayList<>();
    private List<ImageView> tvlines = new ArrayList<>();
    private List<View> views;

    private List<Fragment> mFragments = new ArrayList<>();
    private BookFragment fragment01;
    private BorrowFragment fragment02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        init();
        text.setText("图书管理员");
        initTextView();
        initView();
        initViewPager();
    }

    public void initViewPager() {
        // TODO Auto-generated method stub
        SectionsPagerAdapter sectionsPagerAdapter;
        pager = findViewById(R.id.pager);
        fragment01 = new BookFragment();
        fragment02 = new BorrowFragment();
        mFragments.add(fragment01);
        mFragments.add(fragment02);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mFragments);
        pager.setAdapter(sectionsPagerAdapter);
        pager.setOffscreenPageLimit(mFragments.size());
        pager.setCurrentItem(0);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                // TODO Auto-generated method stub
                for (int i = 0; i < tvs.size(); i++) {
                    if (i == index) {
                        tvs.get(i).setTextColor(Color.parseColor("#009944"));
                        tvlines.get(i).setVisibility(View.VISIBLE);
                    } else {
                        tvs.get(i).setTextColor(Color.parseColor("#333333"));
                        tvlines.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    //切换
    private void initTextView() {
        tv1.setOnClickListener(new MyClickListener(0));
        tv2.setOnClickListener(new MyClickListener(1));

        lineTv1.setOnClickListener(new MyClickListener(0));
        lineTv2.setOnClickListener(new MyClickListener(1));

        tvs.add(tv1);
        tvs.add(tv2);

        tvlines.add(lineTv1);
        tvlines.add(lineTv2);
    }


    private void initView() {
        LayoutInflater li = getLayoutInflater();
        views = new ArrayList<>();
        views.add(li.inflate(R.layout.fragment_item, null));
        views.add(li.inflate(R.layout.fragment_item, null));
    }
    private void init(){
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        lineTv1=findViewById(R.id.line_tv1);
        lineTv2=findViewById(R.id.line_tv2);
        text=findViewById(R.id.title1);
    }
    private class MyClickListener implements View.OnClickListener {

        private int index;

        public MyClickListener(int index) {
            // TODO Auto-generated constructor stub
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //改变ViewPager当前显示页面
            pager.setCurrentItem(index);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
