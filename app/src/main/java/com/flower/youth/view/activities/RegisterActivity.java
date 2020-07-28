package com.flower.youth.view.activities;

import android.content.Intent;
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
import com.flower.youth.presenter.RegisterPre;
import com.flower.youth.util.PhoneNumCheck;
import com.flower.youth.view.interfaces.OnRegister;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends SwipeBackActivity implements OnRegister{

    @BindView(R.id.register_user_Id)
    EditText accountEdit;

    @BindView(R.id.register_user_Pwd)
    EditText passEdit;

    @BindView(R.id.register_user_Pwd2)
    EditText surePassEdit;

    @BindView(R.id.register_button)
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.register_button)
    void register(){
        String account = accountEdit.getEditableText().toString().trim();
        String pass = passEdit.getEditableText().toString().trim();
        String surePass = surePassEdit.getEditableText().toString().trim();

        if (account.equals("") || pass.equals("") || surePass.equals("")){
            Toast.makeText(this, "以上选项均不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!PhoneNumCheck.isPhoneNum(account)){
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(surePass)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterPre pre = new RegisterPre(this);
        pre.register(account, pass);

    }

    @Override
    public void registerSuccess(String account, String pass) {
        runOnUiThread(()->{
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

            finish();
        });
    }

    @Override
    public void registerError(String error) {
        runOnUiThread(()->{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
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
}
