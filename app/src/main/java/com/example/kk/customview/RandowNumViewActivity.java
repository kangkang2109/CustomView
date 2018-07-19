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

    import smartisanos.app.MenuDialog;
    import smartisanos.app.MenuDialogListAdapter;
    import smartisanos.app.MenuDialogMultiAdapter;

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
//                Random random = new Random();
//                int randomInt = random.nextInt(9999);
//                mRandowNumView.setTextNum(randomInt);
                showMenuDialog();

            }
        });
        findViewById(R.id.switch_ex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuMultiDialog();
            }
        });
    }

        private void showMenuDialog() {
            MenuDialog dialog = new MenuDialog(this);
            dialog.setTitle("TiTle");
            ArrayList<String> list = new ArrayList<String>();
            list.add("Apple");
            list.add("Samsung");
            list.add("XiaoMi");
            list.add("Sony");

            ArrayList<View.OnClickListener> listener = new ArrayList<View.OnClickListener>();
            for (String s : list) {
                listener.add(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RandowNumViewActivity.this, "single", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            MenuDialogListAdapter adapter = new MenuDialogListAdapter(this, list, listener);
            dialog.setAdapter(adapter);

            dialog.show();
        }

        private void showMenuMultiDialog() {
            MenuDialog dialog = new MenuDialog(this);
            dialog.setTitle("MultiDialog");
            ArrayList<String> list = new ArrayList<String>();
            list.add("Apple");
            list.add("Samsung");
            list.add("XiaoMi");
            list.add("Sony");
            list.add("Smartisan");
            list.add("Vivo");
            list.add("360");

            MenuDialogMultiAdapter adapter = new MenuDialogMultiAdapter(this, list);
            dialog.setAdaper(adapter, new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(RandowNumViewActivity.this, "multi", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
}
