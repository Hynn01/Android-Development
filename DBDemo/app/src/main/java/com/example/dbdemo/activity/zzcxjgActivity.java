package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

//站站查询结果
public class zzcxjgActivity extends Activity {

    private String[] result;
    private ImageButton bt_back;
    private ArrayList<String> result_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.zzcxjg);
        result_arr = new ArrayList<>();

        //（5）返回按钮引用
        bt_back=(ImageButton)findViewById(R.id.back_zzcxjg);

        ListView listView = (ListView)findViewById(R.id.list_zzcxjg);
        //获取返回结果
        Intent i = getIntent();
        //result=new String[]{"a1,b1,c2","a2,s2,d3","s2,d2,e3"};
        result=i.getStringArrayExtra("result");

        for(int j=0; j<100; j++){
            if(result[j] == null) break;
            result_arr.add(result[j]);
            System.out.println(result[j] + " ... ");
        }
        //result=new zzcxActivity()
        //建立ListView对应的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result_arr);
        listView.setAdapter(adapter);

        //（14）为返回按钮添加监听
        //（15）在当按下返回按钮时，调用返回到主菜单界面函数返回
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zzcxjgActivity.this.finish();
            }
        });

    }

}
