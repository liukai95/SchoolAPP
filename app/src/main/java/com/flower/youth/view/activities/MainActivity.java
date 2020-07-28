package com.flower.youth.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.flower.youth.R;
import com.flower.youth.adapter.MainPageAdapter;
import com.flower.youth.presenter.MainRefreshPre;
import com.flower.youth.util.DisplayUtil;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.fragments.AdvertiseFragment;
import com.flower.youth.view.fragments.LostFoundFragment;
import com.flower.youth.view.fragments.PostgraduateFragment;
import com.flower.youth.view.interfaces.OnMainRefresh;
import com.flower.youth.view.ui.PopWinPost;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMainRefresh {

    private static final int ADVERTISE = 0;
    private static final int LOST_FOUND = 1;
    private static final int POSTGRADUATE = 2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.bottomBar_title)
    TextView bottomBar_title;

    @BindView(R.id.school_layout)
    RelativeLayout schoolLayout;

    @BindView(R.id.bottomBar_school)
    TextView bottomBar_school_title;

    @BindView(R.id.bottomBar_school_indicator)
    ImageView school_indicator;

    private CircleImageView headImg;

    private TextView jobTitle;

    private TextView nickName;

    private MaterialDialog logOutDialog = null;

    private MainRefreshPre mainRefreshPre = null;

    // 菜单弹窗
    private PopWinPost popWinPost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        jobTitle.setText(SharedPrefrenceUtil.getJobTitle());
        nickName.setText(SharedPrefrenceUtil.getNickName());

        if (mainRefreshPre == null){
            mainRefreshPre = new MainRefreshPre(this);
        }
        mainRefreshPre.refreshHeadImg(SharedPrefrenceUtil.getHeadUrl());
    }

    private void initView(){
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 侧拉菜单布局
        View headerLayout = navigationView.getHeaderView(0);
        headImg = (CircleImageView) headerLayout.findViewById(R.id.nav_head_img);
        jobTitle = (TextView) headerLayout.findViewById(R.id.nav_jobTitle);
        nickName = (TextView) headerLayout.findViewById(R.id.nav_nickname);

        // 初始化viewPager
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AdvertiseFragment());
        fragmentList.add(new LostFoundFragment());
        fragmentList.add(new PostgraduateFragment());
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, true);
                refreshToolBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 点击
        bottomBar.setOnTabSelectListener(tabId -> {
            int index = 0;
            switch (tabId){
                case R.id.tab_advertise:
                    index = ADVERTISE;
                    break;
                case R.id.tab_lost_found:
                    index = LOST_FOUND;
                    break;
                case R.id.tab_postgraduate:
                    index = POSTGRADUATE;
                    break;
            }
            viewPager.setCurrentItem(index);
            refreshToolBar(index);
        });

        // 双击
        bottomBar.setOnTabReselectListener(tabId -> {
            // 滚动到头部刷新

        });

    }

    private void refreshToolBar(int flag){
        toolbar.setTitle("");
        switch (flag){
            case ADVERTISE:
                schoolLayout.setVisibility(View.INVISIBLE);
                bottomBar_title.setVisibility(View.VISIBLE);
                bottomBar_title.setText("招聘");

                break;
            case LOST_FOUND:        // 失物招领根据学校确定
                schoolLayout.setVisibility(View.VISIBLE);
                bottomBar_title.setVisibility(View.INVISIBLE);
                // 根据本地存储设置学校名称
                bottomBar_school_title.setText("华中农业大学");
                school_indicator.setImageResource(R.drawable.school_down);

                break;
            case POSTGRADUATE:
                schoolLayout.setVisibility(View.INVISIBLE);
                bottomBar_title.setVisibility(View.VISIBLE);
                bottomBar_title.setText("考研");

                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_post:
                if (popWinPost == null){
                    popWinPost = new PopWinPost(MainActivity.this,
                            DisplayUtil.dip2px(MainActivity.this, 160),
                            DisplayUtil.dip2px(MainActivity.this, 160),
                            view -> {
                                switch (view.getId()){
                                    case R.id.post_advertise:
                                        startActivity(new Intent(MainActivity.this, PostAdvertiseActivity.class));
                                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                                        break;
                                    case R.id.post_lost_found:
                                        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.post_postgraduate:
                                        Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            });
                    popWinPost.getContentView().setOnFocusChangeListener((view, b) -> {
                        if (!b){
                            popWinPost.dismiss();
                        }
                    });
                }
                popWinPost.setFocusable(true);
                popWinPost.showAsDropDown(toolbar, Gravity.END, 0);
                popWinPost.update();
                break;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.my_thread:    // 我的帖子

                break;
            case R.id.my_concern:   // 我的关注

                break;
            case R.id.my_store:     // 我的收藏

                break;
            case R.id.perfect_info: // 完善信息
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, InfoPerfectActivity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.log_out:      // 退出登录
                logOut();
                break;
        }

        return true;
    }

    private void logOut(){
        if (logOutDialog == null){
            logOutDialog = new MaterialDialog.Builder(this)
                    .title("确认退出登录?")
                    .positiveText("确定")
                    .onPositive((dialog, which) -> {
                        SharedPrefrenceUtil.clearAll();
                        logOutDialog.dismiss();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                        finish();
                    })
                    .negativeText("取消")
                    .onNegative((dialog, which) -> {
                        logOutDialog.dismiss();
                    })
                    .build();
        }

        logOutDialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState();
    }

    @Override
    public void refreshHeadImg(Bitmap bitmap) {
        if (bitmap != null){
            headImg.setImageBitmap(bitmap);
        }
    }

    @Override
    public void refreshHeadImgFail(String error) {
        runOnUiThread(()->{
            Toast.makeText(this, "头像加载失败 - " + error, Toast.LENGTH_SHORT).show();
        });
    }
}
