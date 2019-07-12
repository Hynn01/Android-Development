package com.example.dbdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.dao.Repo;
import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.example.dbdemo.entity.Trancepos;
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

    Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app=(MyApplication) this.getApplication();
        repo=app.getDao();
        insertData();
        System.out.println(app.findPathMiddle(1,12));
        System.out.println(repo.getBusByName("A"));
        System.out.println(repo.getStationById(repo.getStationIdByName("a1")));


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


    public void insertData() {

        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();

        //先清空
        repo.getDbHelper().onUpgrade(repo.getDb(), 3, 4);

        //station表
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                Station station = new Station();
                station.name = "" + (char) (i + 96) + j;
                station.station_ID = (i - 1) * 5 + j;
                //插入station
                stations.add(station);
                //数据库插入
                //TODO
                repo.insertStation(station);
            }
        }
        //bus表
        for (int i = 1; i <= 4; i++) {
            Bus bus = new Bus();
            bus.bus_ID = i;
            bus.name = "" + (char) (i + 64);
            bus.oStation = (i - 1) * 5 + 1;
            bus.terminus = 5 * i;
            buses.add(bus);
            //插入bus到数据库
            //TODO
            repo.insertBus(bus);
        }

        //站次表
        for (int i = 1; i <= 20; i++) {
            Trancepos trans = new Trancepos();
            trans.trancepos_ID = i;
            trans.bus_ID = (i - 1) / 5 + 1;
            trans.station_ID = i;
            //插入站次到数据库
            //TODO
            repo.insertTrancepos(trans);
        }

        //额外再加两个线路
        Bus s1 = new Bus();
        s1.bus_ID = 5;
        s1.name = "E";
        s1.oStation = 2;
        s1.terminus = 17;
        repo.insertBus(s1);

        for (int i = 2; i <= 17; i += 5) {
            Trancepos trans = new Trancepos();
            trans.bus_ID = s1.bus_ID;
            trans.station_ID = i;
            //插入站次到数据库
            repo.insertTrancepos(trans);
        }

        Bus s2 = new Bus();
        s2.bus_ID = 6;
        s2.name = "F";
        s2.oStation = 4;
        s2.terminus = 19;
        repo.insertBus(s2);
        for (int i = 4; i <= 19; i += 5) {
            Trancepos trans = new Trancepos();
            trans.bus_ID = s2.bus_ID;
            trans.station_ID = i;
            //插入站次到数据库
            //TODO
            repo.insertTrancepos(trans);
        }
    }


    }
