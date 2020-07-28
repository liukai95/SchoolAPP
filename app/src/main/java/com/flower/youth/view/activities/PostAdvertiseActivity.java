package com.flower.youth.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flower.youth.R;
import com.flower.youth.presenter.PostInfoPre;
import com.flower.youth.view.interfaces.OnAdvPost;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostAdvertiseActivity extends SwipeBackActivity implements OnAdvPost{

    @BindView(R.id.adv_station)
    EditText stationEdit;

    @BindView(R.id.adv_company)
    EditText companyEdit;

    @BindView(R.id.adv_salary)
    EditText salaryEdit;

    @BindView(R.id.adv_address)
    EditText addressEdit;

    @BindView(R.id.adv_time)
    EditText timeEdit;

    @BindView(R.id.adv_desc)
    EditText descEdit;

    @BindView(R.id.adv_post_btn)
    Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_advertise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.adv_post_btn)
    void post(){
        String station = stationEdit.getEditableText().toString().trim();
        String company = companyEdit.getEditableText().toString().trim();
        String salary = salaryEdit.getEditableText().toString().trim();
        String address = addressEdit.getEditableText().toString().trim();
        String time = timeEdit.getEditableText().toString().trim();
        String desc = descEdit.getEditableText().toString().trim();

        if(station.equals("") || company.equals("")
                || salary.equals("") || address.equals("")
                || time.equals("") || desc.equals("")){
            Toast.makeText(this, "以上选项均不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        PostInfoPre pre = new PostInfoPre(this);
        pre.postAdv(station, company, salary, address, time, desc);

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
    public void postSuccess() {
        runOnUiThread(()->{
            Toast.makeText(this, "发帖成功", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        });
    }

    @Override
    public void postFail(String error) {
        runOnUiThread(()->{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}
