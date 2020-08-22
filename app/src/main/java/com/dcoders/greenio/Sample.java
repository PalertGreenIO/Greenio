package com.dcoders.greenio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Sample extends AppCompatActivity {

    GridView gridView;

    String[] items = {"Sell on Green IO","Settings","Help","FAQ","Logout","About us"};
    Integer [] logo = {R.drawable.ic_contact_mail,R.drawable.ic_settings,R.drawable.ic_baseline_help_24,R.drawable.ic_answer,R.drawable.ic_logout,R.drawable.ic_aboutus};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

       /* CustomAdapter adapter = new CustomAdapter(Sample.this,items,logo);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemValue = (String) gridView.getItemAtPosition(i);
                Toast.makeText(Sample.this,itemValue,Toast.LENGTH_SHORT).show();
            }
        });*/


      //bottomnavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
       bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.bottom_profile:
                   return true;
                   case R.id.bottom_home:
                       startActivity(new Intent(getApplicationContext()
                               ,ShopActivity.class));
                       overridePendingTransition(0, 0);
                       return true;
                   case R.id.bottom_account:
                       startActivity(new Intent(getApplicationContext()
                               ,AccountActivity.class));
                       overridePendingTransition(0, 0);

                       return true;
               }

               return false;
           }
       });

      ///Bottomnavigationview




    }
   public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        Toast.makeText(Sample.this,"Logged out",Toast.LENGTH_SHORT).show();
        finish();


    }


    ///classAdater
    class CustomAdapter extends ArrayAdapter<String>{
        private final Context context;
        private final String[] items;
        private final Integer[] logo;


        public CustomAdapter(@NonNull Context context, String[] items, Integer[] logo) {
            super(context, R.layout.layout_custom,items);
            this.context = context;
            this.items = items;
            this.logo = logo;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.layout_custom,null,true);

            TextView title = (TextView) rowView.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.grid_image);

            title.setText(items[position]);
            imageView.setImageResource(logo[position]);

            return rowView;
        }
    }
}