package com.example.dbdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemo.R;

//站站查询结果
public class czcxjgActivity extends Activity {
    private String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.czcxjg);
        //todo:放一个listview
        ListView listView = (ListView)findViewById(R.id.list_czcxjg);
        //获取返回结果
        result=new String[]{"a1,b1,c2","a2,s2,d3","s2,d2,e3"};
        //result=new zzcxActivity()
        //建立ListView对应的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
        listView.setAdapter(adapter);
    }
}
