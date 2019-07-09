package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbdemo.R;

//车站添加
public class cztjActivity extends Activity {

    private Button bt_add;
    private ImageButton bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）车站添加模块goTocztjView()
        //（2）切换到车站添加界面
        //（3）标识当前界面为车站添加界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cztj);

        //（4）获取添加按钮的引用
        //（5）获取返回按钮的引用
        bt_add=(Button)findViewById(R.id.bt_query);
        bt_back=(ImageButton)findViewById(R.id.bt_back);

        //（6）为查询按钮添加监听
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(zzcxActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //（7）如果输入框为空，则返回
        //（8）获取车站名字文本框引用
        //（9）获取名字简称文本框引用
        //（10）取出名字文本
        //（11）简称文本
        //（12）查看简称文本是否只有字母
        //（13）如有问题弹出对话框提醒用户
        //（14）准备数据库查询该名称是否已经存在
        //（15）执行数据库查询
        //（16）如果不为空，说明存在此车站
        //（17）发Toast消息提醒用户
        //（18）插入数据
        //（19）如果插入失败，提醒用户
        //（20）否则为成功插入数据

        //（21）为返回按钮添加监听
        //（22）在监听函数中返回添加功能
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cztjActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
