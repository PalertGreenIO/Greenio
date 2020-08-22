package com.dcoders.greenio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FifthFragment extends Fragment {
    TextView done;
    TextView back;
    ViewPager viewPager;


    public FifthFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fifth, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);

        done = view.findViewById(R.id.Donebtn);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getActivity(),"Welcome to Green.io",Toast.LENGTH_SHORT).show();
            }
        });


        back = view.findViewById(R.id.backbtn4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });

        return view;
    }
}