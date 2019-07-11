package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemo.R;

//车站查询结果
public class czcxjgActivity extends Activity {
    private String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.czcxjg);

        ListView listView = (ListView)findViewById(R.id.list_czcxjg);
        //初始化result
        result=new String[]{};
        //获取返回结果
        Intent i = getIntent();
        result=i.getStringArrayExtra("result1");
        //result=new String[]{"a1,b1,c2","a2,s2,d3","s2,d2,e3"};

        //建立ListView对应的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
    }
}
