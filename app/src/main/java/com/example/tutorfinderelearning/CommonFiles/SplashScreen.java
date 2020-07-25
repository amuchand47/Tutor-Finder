package com.example.tutorfinderelearning.CommonFiles;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tutorfinderelearning.R;

public class SplashScreen extends AppCompatActivity {

    private Animation top_animation, bottom_animation;
    private ImageView up_logo, down_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        up_logo = (ImageView)findViewById(R.id.up_logo);
        down_logo = (ImageView)findViewById(R.id.down_logo);

        up_logo.setAnimation(top_animation);
        down_logo.setAnimation(bottom_animation);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent it = new Intent(SplashScreen.this, DashBoardScreen.class);
              Pair[] pairs = new Pair[1];
              pairs[0]= new Pair<View, String>(up_logo, "logo_up_image");
              ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
              startActivity(it, options.toBundle());

          }
      },7000);


    }
}