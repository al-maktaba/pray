package com.example.pray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pray.mainMap.MainMapActivity;

import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    private LottieAnimationView animationView;
    private ImageView background;
    private ImageView prayerRug;
    private MediaPlayer mediaPlayer;
    private ImageView logoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        animationView = findViewById(R.id.animationView);
        background = findViewById(R.id.background);
        prayerRug = findViewById(R.id.prayerRug);
        logoText = findViewById(R.id.logoTextImageView);

        getRandomAnimate();

        //sound effect:
        mediaPlayer = MediaPlayer.create(SplashScreen.this, R.raw.tik);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainMapActivity.class);
                startActivity(intent);
                overridePendingTransition (0, 0);
                finish();
            }
        }, 2700);

    }

    private void getRandomAnimate() {
        int state = new Random().nextInt(2);
        if(state == 0){
            background.animate().translationY(-2800).setDuration(800).setStartDelay(2000);
            animationView.setVisibility(View.VISIBLE);
            prayerRug.setVisibility(View.GONE);
            animationView.animate().translationY(-1800).setDuration(800).setStartDelay(2000);
            logoText.animate().translationY(-1800).setDuration(800).setStartDelay(2000);
        } else if(state == 1){
            background.animate().translationY(-2800).setDuration(800).setStartDelay(2000);
            animationView.setVisibility(View.GONE);
            prayerRug.setVisibility(View.VISIBLE);
            logoText.animate().translationY(-1800).setDuration(800).setStartDelay(2000);
            //prayer mat animation
            YoYo.with(Techniques.BounceInUp)
                    .duration(1000).delay(100)
                    .playOn(findViewById(R.id.prayerRug));
            prayerRug.animate().translationY(-1800).setDuration(800).setStartDelay(2000);
            prayerRug.animate().rotation(-20).setDuration(800);
        }
    }

    }