package com.example.kk.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static final Map<String, Class> mapping = new HashMap<>();

    static {
        mapping.put("Random Num", RandowNumViewActivity.class);
        mapping.put("自定义View 2", CustomImageViewActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_content);
        final SimpleAdapter adapter = new SimpleAdapter(new LinkedList<String>(mapping.keySet()));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) adapter.getItem(i);
                Class target = mapping.get(name);
                Intent intent = new Intent(MainActivity.this, target);
                startActivity(intent);
            }
        });
    }



    private class SimpleAdapter extends BaseAdapter {

        List<String> data = new LinkedList<>();

        public SimpleAdapter() {
            data = new LinkedList<>();
        }

        public SimpleAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View root = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            ((TextView)root).setText((String)getItem(i));
            return root;
        }
    }
}
