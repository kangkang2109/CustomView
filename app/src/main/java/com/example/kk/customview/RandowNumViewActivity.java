package com.example.kk.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.kk.customview.view.RandowNumView;

import java.util.ArrayList;
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
    }
}
