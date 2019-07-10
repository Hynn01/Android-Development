package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;
import com.example.dbdemo.entity.Station;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//车站添加
public class cztjActivity extends Activity {

    private Button bt_add;
    private ImageButton bt_back;

    private EditText et_station;
    private  EditText et_abbreviation;

    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app=(MyApplication) this.getApplication();

        //（1）车站添加模块goTocztjView()
        //（2）切换到车站添加界面
        //（3）标识当前界面为车站添加界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cztj);

        Banner banner = (Banner) findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

        //（4）获取添加按钮的引用
        //（5）获取返回按钮的引用
        bt_add=(Button)findViewById(R.id.bt_add);
        bt_back=(ImageButton)findViewById(R.id.bt_back);

        //（8）获取车站名字文本框引用
        //（9）获取名字简称文本框引用
        et_station=(EditText)findViewById(R.id.autoinput_cztj_name);
        et_abbreviation=(EditText)findViewById(R.id.autoinput_cztj_simname);

        //（6）为添加按钮添加监听
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //（10）取出名字文本
                //（11）简称文本
                String station_name=et_station.getText().toString();
                String station_sim=et_abbreviation.getText().toString();

                //（7）如果输入框为空，则返回
                //（12）查看简称文本是否只有字母
                //（13）如有问题弹出对话框提醒用户
                //字母检查这块已经在输入框中做了限制
                if(station_name==null || station_sim==null){

                }else{
                    //（14）准备数据库查询该名称是否已经存在
                    //（15）执行数据库查询
                    int station_id=app.getDao().getStationIdByName(station_name);
                    boolean station_exist=app.getDao().stationIsExistById(station_id);
                    //（16）如果不为0，说明存在此车站
                    //（17）发Toast消息提醒用户
                    if(station_exist==true){
                        Toast toast = Toast.makeText(cztjActivity.this,"存在此车站，请重新输入",Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        //（18）插入数据
                        //（19）如果插入失败，提醒用户
                        //（20）否则为成功插入数据
                        Station station=new Station(station_name,station_sim);
                        //int insert_flag=app.getDao().insertStation(station);
                        app.getDao().insertStation(station);
//                        if(insert_flag==false){
//                            Toast toast = Toast.makeText(cztjActivity.this,"插入失败",Toast.LENGTH_SHORT);
//                            toast.show();
//                        }else{
                            Toast toast = Toast.makeText(cztjActivity.this,"车站添加成功",Toast.LENGTH_SHORT);
                            toast.show();
//                        }

                    }

                }
            }
        });

        //（21）为返回按钮添加监听
        //（22）在监听函数中返回添加功能
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cztjActivity.this,tjgnActivity.class);
                startActivity(intent);
            }
        });

    }



}
