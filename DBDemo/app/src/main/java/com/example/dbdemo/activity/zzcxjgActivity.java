package com.example.dbdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;

import java.util.ArrayList;

//站站查询结果
public class zzcxjgActivity extends AppCompatActivity {

    private ArrayList<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.zzcxjg);
        //todo:放一个listview
        ListView listView = (ListView)findViewById(R.id.list_zzcxjg);
        //获取返回结果
        //result=new zzcxActivity()
        //建立ListView对应的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_list,result);
        listView.setAdapter(adapter);
    }

}
