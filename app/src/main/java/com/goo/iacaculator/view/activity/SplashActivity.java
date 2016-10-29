package com.goo.iacaculator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.goo.iacaculator.R;


/**
 * @ClassName: SplashActivity
 * @Description: 欢迎界面
 */
public class SplashActivity extends AppCompatActivity {

    private TextView mTvTitle, mTvSubhead, mTvAuthor, mTvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findAllViewById();
        showAnim();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
                finish();
            }
        }, 2000);

    }

    private void findAllViewById() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvSubhead = (TextView) findViewById(R.id.tv_subhead);
        mTvAuthor = (TextView) findViewById(R.id.tv_author);
        mTvEmail = (TextView) findViewById(R.id.tv_email);
    }

    /**
     * 显示欢迎动画
     */
    private void showAnim() {
        Animation anim = new AlphaAnimation(0, 1);
        anim.setDuration(1500);
        anim.setFillAfter(true);
        mTvTitle.setAnimation(anim);
        mTvSubhead.setAnimation(anim);
        mTvAuthor.setAnimation(anim);
        mTvEmail.setAnimation(anim);
    }

    /**
     * 未登录/注销时候进入登录界面
     */
    private void startMainActivity() {
        Intent mIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mIntent);
        overridePendingTransition(R.anim.transition_right_in, R.anim.transition_not_move);
        finish();
    }

    /**
     * 拦截返回键
     */
    @Override
    public void onBackPressed() {
    }
}
