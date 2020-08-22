package com.dcoders.greenio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class OnboardActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        viewPager = findViewById(R.id.viewPager);
        IntroAdapater adapter = new IntroAdapater(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }
}