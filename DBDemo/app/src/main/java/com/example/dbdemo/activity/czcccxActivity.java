package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;
import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.youth.banner.Banner;
import com.example.dbdemo.dao.*;

import java.util.ArrayList;
import java.util.List;

//去车站所有车次查询
public class czcccxActivity extends Activity {


    private Button bt_query;
    private ImageButton bt_back;
   // private TextView tv_cz;//车次
    private MyApplication application;//数据库操作
    private AutoCompleteTextView autoinput_czcx_zc;

    ArrayList<Bus> result;//返回结果
    String input;//输入
    //（1）车站查询，goToczcccxView()
    //（2）切换到车站查询界面
    //    用setContentView（）
    //（3）标识当前界度为车站杏询界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.czcccx);
        //初始化数据库
        application= (MyApplication)this.getApplication();

        Banner banner = (Banner) findViewById(R.id.banner_zzcx);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

        //（4）获取杳询按钮的引用
        //（5）获取返回按钮的引用
        bt_query=(Button)findViewById(R.id.searchButton_czcx);
        bt_back=(ImageButton)findViewById(R.id.back_czcx);
//
//        //（6）为车站输入文本框添加适配器
//        //（7）为查询按钮添加监听
//        //（8）在监听函数里获取车站输入框中的文本
//        //（9）调用方法查询
//        tv_cz=(TextView) findViewById(R.id.autoinput_czcx_zc);
//        input=tv_cz.getText().toString();

        application= (MyApplication)this.getApplication();
        String[] str={};//获取数据库中所有的车站
        str=(String[])application.getDao().getStationList().toArray(new String[0]);

        //获取输入框中的字符
        String cz_child=autoinput_czcx_zc.getText().toString();
        String[] cz_match=application.Match(str,cz_child);
        ArrayAdapter<String> cz_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,cz_match);
        autoinput_czcx_zc.setAdapter(cz_adapter);




        result=null;
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:查询
                int back=application.getDao().getStationIdByName(input);
                result=application.getDao().getBusArrayList(back);
                //        //（10）如果查询结果为空
//        //（11）发toast消息提醒
//        //（12）否则切换到结果listview界面
                if(result==null){
                    Toast toast = Toast.makeText(czcccxActivity.this, "没有查询结果", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(czcccxActivity.this,zzcxjgActivity.class);
                    startActivity(intent);
                }

            }
        });
//


        //（13）为返回按钮添加监听
        //（14）在监听函数中返回主菜单
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(czcccxActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
