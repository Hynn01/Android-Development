package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbdemo.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//附加功能
public class tjgnActivity extends Activity {

    private Button bt_tjgx;//添加关系
    private Button bt_tjcz;//添加车站
    private Button bt_tjcc;//添加车次

    private ImageButton bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）添加功能 goTofjgnView()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjgn);
        bt_back=(ImageButton)findViewById(R.id.back_zctj);

        Banner banner = (Banner) findViewById(R.id.banner_tjgn);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

        //（2）关系添加按钮引用
        bt_tjgx=(Button)findViewById(R.id.bt_tjgx);
        bt_tjcz=(Button)findViewById(R.id.bt_tjcz);
        bt_tjcc=(Button)findViewById(R.id.bt_tjcc);

        //（3）为车次添加按钮添加监听
        //（4）切换到车次添加模块
        bt_tjgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tjgnActivity.this,gxtjActivity.class);
                startActivity(intent);
            }
        });

        //（5）为车站添加按钮添加监听
        //（6）切换到车站添加模块
        bt_tjcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tjgnActivity.this,cztjActivity.class);
                startActivity(intent);
            }
        });

        //（7）为关系添加按钮添加监听
        //（8）切换到关系添加模块
        bt_tjcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tjgnActivity.this,cctjActivity.class);
                startActivity(intent);
            }
        });

        //（26）为返回按钮添加监听
        //（27）返回主菜单界面
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tjgnActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
