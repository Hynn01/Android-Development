package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dbdemo.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//站站查询
public class zzcxActivity extends Activity {

    private Button bt_query;
    private ImageButton bt_back;
    private AutoCompleteTextView tv_start;
    private AutoCompleteTextView tv_end;

    //（1）站站查询goTozzcxView()
    //（2）切换到站站查询界面
    //    用setContentView（）
    //（3）标示站站查询
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zzcx);

        Banner banner = (Banner) findViewById(R.id.banner_zzcx);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

        //（4）获得查询按钮ID
        bt_query=(Button)findViewById(R.id.searchButton);

        //（5）返回按钮ID
        bt_back=(ImageButton)findViewById(R.id.back_zzcx);

//        //（10）为查询按钮添加监听
//        bt_query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //todo:查询
//            }
//        });
//
        //（11）获得出发站文本框引用
        //（12）获得中转站文本框引用
        //（13）获得终点站文本框引用
        tv_start=(AutoCompleteTextView) findViewById(R.id.tv_start);
        tv_end=(AutoCompleteTextView) findViewById(R.id.tv_end);
//
//        //（6）发站文本框添加适配器
//        // //（7）为中转站文本框添加适配
//        //（8）为终点站文本框添加适配器
//        // //（9）中转站CheckBox引用
//        String[] str={};
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,str);
//        tv_start.setAdapter(adapter);
//        tv_end.setAdapter(adapter);
//
//        //（14）由引用获取初始站文本
//        //（15）由引用获取终点站文本
//        //（16）由引用获取中转站文本
//        String startStation=tv_start.getText().toString();
//        String endStation=tv_end.getText().toString();
//
//        //（17）声明存放结果集的向量这里可以使用Vector变量
//        //（18）判断如果需要进行中转站查询则
//        //（19）调用数据库进行查询
//        //（20）如果没有查询就通过Toast消息提醒用户
//        //（21）否则进行不带中转站查询
//        //（23）调用数据库查询
//        //todo:调用数据库查询
//        ArrayList<String> result=null;
//
//        //（24）如果没有查询就通过Toast消息提醒用户
//        //（25）如有查询结果则进一步调用查询结果界面用listview展示
//        if(result==null){
//            Toast toast = Toast.makeText(zzcxActivity.this, "没有查询结果", Toast.LENGTH_SHORT);
//            toast.show();
//        }else{
//            Intent intent = new Intent(zzcxActivity.this,zzcxjgActivity.class);
//            startActivity(intent);
//        }

        //（26）为返回按钮添加监听
        //（27）返回主菜单界面
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(zzcxActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
