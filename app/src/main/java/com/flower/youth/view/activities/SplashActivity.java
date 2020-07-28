package com.flower.youth.view.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.flower.youth.R;
import com.flower.youth.presenter.SplashCheckUserPre;
import com.flower.youth.view.interfaces.OnSplashTurn;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements OnSplashTurn{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        new Handler().postDelayed(()->{
            checkUser();
        }, 2000);
    }

    private void checkUser() {
        SplashCheckUserPre pre = new SplashCheckUserPre(this);
        pre.checkUser();
    }

    @Override
    public void turnToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        finish();
    }

    @Override
    public void turnToLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        finish();
    }
}
