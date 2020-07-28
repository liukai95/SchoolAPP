package com.flower.youth.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flower.youth.R;
import com.flower.youth.presenter.LoginPre;
import com.flower.youth.util.PhoneNumCheck;
import com.flower.youth.util.SharedPrefrenceUtil;
import com.flower.youth.view.interfaces.OnLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements OnLogin{

    @BindView(R.id.login_user_Id)
    EditText accountEdit;

    @BindView(R.id.login_user_Pwd)
    EditText passwordEdit;

    @BindView(R.id.login_button)
    Button loginBtn;

    @BindView(R.id.rem_pwd)
    AppCompatCheckBox rememberPass;

    @BindView(R.id.fast_register)
    TextView register;
 //   ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initView();




    }

    private void initView(){
        // 下划线
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        register.getPaint().setAntiAlias(true);

        accountEdit.setText(SharedPrefrenceUtil.getAccount());
        passwordEdit.setText(SharedPrefrenceUtil.getPass());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        accountEdit.setText(SharedPrefrenceUtil.getAccount());
        passwordEdit.setText(SharedPrefrenceUtil.getPass());
    }

    @OnClick(R.id.login_button)
    void login(){
        String account = accountEdit.getEditableText().toString().trim();
        String password = passwordEdit.getEditableText().toString().trim();

        if(account.equals("") || password.equals("")){
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!PhoneNumCheck.isPhoneNum(account)){
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog pd = new ProgressDialog(LoginActivity.this);;
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条风格，风格为圆形，旋转的
        pd.setMessage("登录中，请稍等片刻。。。");// 设置ProgressDialog提示信息
        pd.setCancelable(false); // 设置ProgressDialog 是否可以按退回键取消
        pd.show(); // 让ProgressDialog显示
        //启用一个新线程
        new Thread(new Runnable() {
            @Override
            public void run() {

                //模拟等待，等待5秒
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LoginPre pre = new LoginPre(LoginActivity.this);
                pre.login(account, password);
                pd.dismiss();
            }
        }).start();
    }

    @OnClick(R.id.fast_register)
    void toRegister(){


        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @OnCheckedChanged(R.id.rem_pwd)
    void remember(){
        SharedPrefrenceUtil.storeRember(rememberPass.isChecked());
    }

    @Override
    public void loginSuccess(String account, String pass) {
        if (!rememberPass.isChecked()){
            SharedPrefrenceUtil.storeAccount("","");
        }

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        finish();
    }

    @Override
    public void loginError(String error) {
        runOnUiThread(()->{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}
