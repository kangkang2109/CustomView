package com.example.kk.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.kk.customview.view.RandowNumView;

import java.util.Random;

public class RandowNumViewActivity extends AppCompatActivity{


    private RandowNumView mRandowNumView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_randow_num);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        mRandowNumView = findViewById(R.id.randow_num);
        mRandowNumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Random random = new Random(47);   //种子设置为47，所有设置种子为47的random对象，获得的随机数相同
//                int randomInt = random.nextInt(9999); //假如每次都创建一个random对象，那么第一次nextInt总是一样
                Random random = new Random();
                int randomInt = random.nextInt(9999);
                mRandowNumView.setTextNum(randomInt);
            }
        });
    }
}
