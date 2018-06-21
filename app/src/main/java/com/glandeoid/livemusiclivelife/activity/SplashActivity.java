package com.glandeoid.livemusiclivelife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.glandeoid.livemusiclivelife.MainActivity;
import com.glandeoid.livemusiclivelife.R;
import com.glandeoid.livemusiclivelife.Utils.CacheUtils;

import static com.glandeoid.livemusiclivelife.Utils.StaticUtils.START_MAIN;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否进入过主页面
                boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
                Intent intent;
                if(isStartMain){
                    //如果进入过主页面，直接进入主页面
                    //2.跳转到主页面
                    intent = new Intent(SplashActivity.this,MainActivity.class);
                    //3.渐入--渐出
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                }else{
                    //如果没有进入过主页面，进入引导页面
                    intent = new Intent(SplashActivity.this,GuideActivity.class);
                }
                startActivity(intent);

                //关闭Splash页面
                finish();
                //--渐出
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        }, 2500);
    }
}
