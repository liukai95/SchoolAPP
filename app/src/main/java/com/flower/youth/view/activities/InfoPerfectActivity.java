package com.flower.youth.view.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.flower.youth.R;
import com.flower.youth.adapter.PerfectInfoAdapter;
import com.flower.youth.dao.PerfectInfoItem;
import com.flower.youth.presenter.PerfectInfoPre;
import com.flower.youth.util.CameraPermission;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.interfaces.OnItemClickListener;
import com.flower.youth.view.interfaces.OnInfoPerfect;
import com.flower.youth.view.ui.DividerItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class InfoPerfectActivity extends SwipeBackActivity implements OnItemClickListener,
        OnInfoPerfect {

    @BindView(R.id.perfect_info_recycle)
    RecyclerView recyclerView;

    private MaterialDialog headDialog = null;
    private MaterialDialog nickNameDialog = null;
    private MaterialDialog jobTitleDialog = null;
    private MaterialDialog passDialog = null;

    private PerfectInfoPre perfectInfoPre = new PerfectInfoPre(this);

    private static final int REQUEST_CAMERA = 125;
    private static final int CHANGE_HEAD = 126;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initView();
    }

    private void initView(){
        List<PerfectInfoItem> list = Arrays.asList(
                new PerfectInfoItem(R.drawable.perfect_info_head, "上传头像", R.drawable.perfect_info_turn),
                new PerfectInfoItem(R.drawable.perfect_info_nickname, "修改昵称", 0),
                new PerfectInfoItem(R.drawable.perfect_info_jobtitle, "修改职称", 0),
                new PerfectInfoItem(R.drawable.perfect_info_pass, "修改密码", 0));

        PerfectInfoAdapter adapter = new PerfectInfoAdapter(list, this);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @Override
    public void onClick(View view, int position, Object obj) {
        switch (position){
            case 0:
                if (CameraPermission.isPermited(InfoPerfectActivity.this)){
                    turnOnSelector();
                }else {
                    CameraPermission.requestPer(InfoPerfectActivity.this, REQUEST_CAMERA);
                }
                break;
            case 1:
                changeNickName();
                break;
            case 2:
                changeJobTitle();
                break;
            case 3:
                changePass();
                break;
        }
    }

    @Override
    public void OnLongClick(View view, int position, Object obj) {

    }

    private void turnOnSelector(){
        MultiImageSelector.create(this)
                .showCamera(true)   // 是否显示相机. 默认为显示
                .single()           // 单选模式
                .start(InfoPerfectActivity.this, CHANGE_HEAD);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    private void changeHead(String path){
        if (headDialog == null){
            headDialog = new MaterialDialog.Builder(this)
                    .content("上传中...")
                    .autoDismiss(false)
                    .build();
        }
        headDialog.show();

        perfectInfoPre.changeHead(path);
    }

    private void changeNickName(){
        if (nickNameDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.perfect_info_dialog, null);
            TextView title = (TextView) view.findViewById(R.id.dialog_title);
            title.setText("修改昵称");
            EditText edit = (EditText) view.findViewById(R.id.dialog_edit);

            nickNameDialog = new MaterialDialog.Builder(this)
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> {
                        String s = edit.getEditableText().toString().trim();
                        if (s.equals("")){
                            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                        }else {
                            perfectInfoPre.changeNickName(s);
                        }
                    })
                    .onNegative((dialog, which) -> {
                        nickNameDialog.dismiss();
                    })
                    .customView(view, false)
                    .autoDismiss(false)
                    .build();
        }
        nickNameDialog.show();
    }

    private void changeJobTitle(){
        if (jobTitleDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.perfect_info_dialog, null);
            TextView title = (TextView) view.findViewById(R.id.dialog_title);
            title.setText("修改职称");
            EditText edit = (EditText) view.findViewById(R.id.dialog_edit);

            jobTitleDialog = new MaterialDialog.Builder(this)
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> {
                        String s = edit.getEditableText().toString().trim();
                        if (s.equals("")){
                            Toast.makeText(this, "职称不能为空", Toast.LENGTH_SHORT).show();
                        }else {
                            perfectInfoPre.changJobTitle(s);
                        }
                    })
                    .onNegative((dialog, which) -> {
                        jobTitleDialog.dismiss();
                    })
                    .customView(view, false)
                    .autoDismiss(false)
                    .build();
        }
        jobTitleDialog.show();
    }

    private void changePass(){
        if (passDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.perfect_info_dialog_pass, null);
            TextView title = (TextView) view.findViewById(R.id.dialog_pass_title);
            title.setText("为了账号安全,请先验证原密码");
            EditText oldPassEdit = (EditText) view.findViewById(R.id.dialog_pass_old);
            EditText edit = (EditText) view.findViewById(R.id.dialog_pass_new);

            passDialog = new MaterialDialog.Builder(this)
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> {
                        String s = edit.getEditableText().toString().trim();
                        String old = oldPassEdit.getEditableText().toString().trim();
                        if (s.equals("") || old.equals("")){
                            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }else if (!old.equals(SharedPrefrenceUtil.getPass())){
                            Toast.makeText(this, "原密码不正确", Toast.LENGTH_SHORT).show();
                        }else {
                            perfectInfoPre.changePass(s);
                        }
                    })
                    .onNegative((dialog, which) -> {
                        passDialog.dismiss();
                    })
                    .customView(view, false)
                    .autoDismiss(false)
                    .build();
        }
        passDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                turnOnSelector();

            } else {
                CameraPermission.showCameraNotPer(this, null);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_HEAD){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (path.size() > 0){
                    changeHead(path.get(0));
                }

            }else if (resultCode != RESULT_CANCELED){
                Toast.makeText(this, "图片路径获取错误,请重试", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void changeHeadSuccess() {
        runOnUiThread(()->{
            headDialog.dismiss();
            Toast.makeText(this, "头像上传成功", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changeHeadFail(String error) {
        runOnUiThread(()->{
            headDialog.dismiss();
            Toast.makeText(this, "头像上传失败 - " + error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changeNickNameSuccess() {
        runOnUiThread(()->{
            nickNameDialog.dismiss();
            Toast.makeText(this, "昵称修改成功", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changeNickNameFail(String error) {
        runOnUiThread(()->{
            nickNameDialog.dismiss();
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changeJobTitleSuccess() {
        runOnUiThread(()->{
            jobTitleDialog.dismiss();
            Toast.makeText(this, "职称修改成功", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changeJobTitleFail(String error) {
        runOnUiThread(()->{
            jobTitleDialog.dismiss();
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changePassSuccess() {
        runOnUiThread(()->{
            passDialog.dismiss();
            Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void changePassFail(String error) {
        runOnUiThread(()->{
            passDialog.dismiss();
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}
