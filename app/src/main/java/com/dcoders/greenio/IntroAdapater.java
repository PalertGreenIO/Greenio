package com.dcoders.greenio;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class IntroAdapater extends FragmentPagerAdapter {


    public IntroAdapater(FragmentManager f) {
        super(f);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            case 3:
                return new FourthFragment();
            case 4:
                return new FifthFragment();


            default:

                return null;
        }



    }

    @Override
    public int getCount() {
        return 5;
    }
}

