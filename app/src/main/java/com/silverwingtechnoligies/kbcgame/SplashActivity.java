package com.silverwingtechnoligies.kbcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    ImageView image;
    MediaPlayer player;
    TextView tv_play;
    ImageView iv_setting;
    ImageView iv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        player=MediaPlayer.create(this,R.raw.timer);

        image = findViewById(R.id.image);
        tv_play = findViewById(R.id.tv_play);
        iv_setting = findViewById(R.id.iv_setting);
        iv_info = findViewById(R.id.iv_info);

        player=MediaPlayer.create(this,R.raw.kbc_background);



        player.start();
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        final Animation animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);


        image.startAnimation(animationFadeOut);


        image.startAnimation(animationFadeIn);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_play.setVisibility(View.VISIBLE);
                iv_setting.setVisibility(View.VISIBLE);
                iv_info.setVisibility(View.VISIBLE);
            }
        }, 5000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(player!=null){
            player.pause();
            player.stop();
        }
    }

    public void playNow(View view) {
        player.pause();
        player.stop();
        if (SaveData.Name.length() > 2) {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }else {

            Toast.makeText(this, "Complete your profile", Toast.LENGTH_SHORT).show();
            player.pause();
            player.stop();
            startActivity(new Intent(SplashActivity.this,SettingActivity.class));
        }

    }

    public void goSetting(View view) {

        player.pause();
        player.stop();
        startActivity(new Intent(SplashActivity.this,SettingActivity.class));
    }

    public void goInfo(View view) {
        player.pause();
        player.stop();
        startActivity(new Intent(SplashActivity.this,InfoActivity.class));
    }
}
