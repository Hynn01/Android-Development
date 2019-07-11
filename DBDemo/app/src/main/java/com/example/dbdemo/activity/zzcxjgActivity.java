package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

//站站查询结果
public class zzcxjgActivity extends Activity {

    private String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.zzcxjg);
        //todo:放一个listview
        ListView listView = (ListView)findViewById(R.id.list_zzcxjg);
        //获取返回结果
        Intent i = new Intent();
        //result=new String[]{"a1,b1,c2","a2,s2,d3","s2,d2,e3"};
        result=i.getStringArrayExtra("result");
        //result=new zzcxActivity()
        //建立ListView对应的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
    }

}
