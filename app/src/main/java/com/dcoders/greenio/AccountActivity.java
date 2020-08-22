package com.dcoders.greenio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //bottomnavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.bottom_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_profile:
                        startActivity(new Intent(getApplicationContext()
                                ,Sample.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.bottom_home:
                        startActivity(new Intent(getApplicationContext()
                                ,ShopActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.bottom_account:
                        return true;
                }

                return false;
            }
        });

        ///Bottomnavigationview



    }
}