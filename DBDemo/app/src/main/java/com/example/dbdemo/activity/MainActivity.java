package com.example.dbdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.youth.banner.Banner;

import com.example.dbdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ImageButton bt_about;
    private ImageButton bt_help;
    private ImageButton bt_zzcx;
    private ImageButton bt_cccx;
    private ImageButton bt_czcx;
    private ImageButton bt_tjgn;

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app=(MyApplication) this.getApplication();
        //先清空
//        app.getDao().getDbHelper().onUpgrade(app.getDao().getDb() , 3 ,4);
//        Bus bus = new Bus();
//        bus.name = "a";
//        app.getDao().insertBus(bus);
//        Station station = new Station();
//        station.name = "cuc";

        //（1）一开始进入莱单界面方法就是goToMainMenu()函数
        //（2）切换到莱单界面 使用setContentView（）
        //（3）标识当前界面为菜单界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Banner banner = (Banner) findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();
//        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        banner.setImages(images);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();

        //（4）获取菜单界面下的各个按钮的引用（包括，关于help，站站查询，车次查询，车站查询，添加功能的按钮）
        bt_about=(ImageButton)findViewById(R.id.bt_about);
        bt_help=(ImageButton)findViewById(R.id.bt_help);
        bt_zzcx=(ImageButton)findViewById(R.id.bt_zzcx);
        bt_cccx=(ImageButton)findViewById(R.id.bt_cccx);
        bt_czcx=(ImageButton)findViewById(R.id.bt_czcx);
        bt_tjgn=(ImageButton)findViewById(R.id.bt_tjgn);

        //以下为各个按钮监听的处理的实现
        //（5）为关干按钮添加监听 关于按钮的监听 发消息进入关于界面，参考上面send MSG的方法
        //给bt1添加点击事件
        bt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,aboutActivity.class);
                startActivity(intent);
            }
        });

        //（6）帮助查询的监听 发消息进入帮助界面
        bt_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,helpActivity.class);
                startActivity(intent);
            }
        });

        //（7）站站查询按钮的监听 进入站站查询模块
        bt_zzcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,zzcxActivity.class);
                startActivity(intent);
            }
        });

        //（8）车次查询按钮的监听 进入车次查询模块
        bt_cccx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,cccxActivity.class);
                startActivity(intent);
            }
        });

        //（9）车站所有车次查询 进入车站查询模块
        bt_czcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,czcccxActivity.class);
                startActivity(intent);
            }
        });

        //（10）添加功能按钮的监听 进入添加功能模块
        bt_tjgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,tjgnActivity.class);
                startActivity(intent);
            }
        });
    }

}
