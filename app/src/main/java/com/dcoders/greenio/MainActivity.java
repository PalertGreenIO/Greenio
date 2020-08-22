package com.dcoders.greenio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int ProgressBar=8000;
    ImageView mlogo;
    TextView mslogan;
    Animation topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mlogo = findViewById(R.id.ilogo);
        mslogan =findViewById(R.id.itext);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);


        mlogo.setAnimation(topAnim);
        mslogan.setAnimation(topAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,OnboardActivity.class);
                startActivity(i);
                finish();
            }
        },ProgressBar);

    }
}