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

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;
import com.example.dbdemo.dao.Repo;
import com.example.dbdemo.entity.Path;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


//站站查询
public class zzcxActivity extends Activity {

    private Button bt_query;
    private ImageButton bt_back;
    private AutoCompleteTextView tv_start;
    private AutoCompleteTextView tv_end;
    private MyApplication application;//数据库操作

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
//初始化application
        application= (MyApplication)this.getApplication();
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
        String[] str={};//获取数据库中所有的车站
        str=(String[])application.getDao().getStationNameList().toArray(new String[0]);

        //获取输入框中的字符
        String start_child=tv_start.getText().toString();
        String end_child=tv_end.getText().toString();

        //获取输入字符与数据库匹配的车站
        String[] start_match=application.Match(str,start_child);
        String[] end_match=application.Match(str,end_child);

        ArrayAdapter<String> start_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,start_match);
        ArrayAdapter<String> end_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,end_match);
        tv_start.setAdapter(start_adapter);
        tv_end.setAdapter(end_adapter);
//


//        //（17）声明存放结果集的向量这里可以使用Vector变量
//        //（18）判断如果需要进行中转站查询则
//        //（19）调用数据库进行查询
//        //（20）如果没有查询就通过Toast消息提醒用户
//        //（21）否则进行不带中转站查询
//        //（23）调用数据库查询


        //（10）为查询按钮添加监听
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //（14）由引用获取初始站文本
            //（15）由引用获取终点站文本
            //（16）由引用获取中转站文本
            final String startStation=tv_start.getText().toString();
            final String endStation=tv_end.getText().toString();

            //result=new String[]{"a1,b1,c2","a2,s2,d3","s2,d2,e3"};
            //result=(String[]) application.findPathDirectly(startStation,endStation).toArray(new String[0]);
            ArrayList<String> result=new ArrayList<String>();//返回结果
//            result=null;
            result=application.findPathDirectly(startStation,endStation);

            if(result.size()==0){
                int station1=application.getDao().getStationIdByName(startStation);
                int station2=application.getDao().getStationIdByName(endStation);
                ArrayList<Path> result1=new ArrayList<Path>();
                result1=application.findPathMiddle(station1,station2);
                if(result1.size()==0){
                    Toast toast = Toast.makeText(zzcxActivity.this, "没有查询结果", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String[] result2=new String[100];
                    for(int i=0;i<result1.size();i++){
                        result2[i]=result1.get(i).toString();
                        System.out.println("string:"+result2[i]);
                    }
                    Intent intent = new Intent(zzcxActivity.this,zzcxjgActivity.class);
                    intent.putExtra("result",result2);
                    startActivity(intent);
//                    Toast toast = Toast.makeText(zzcxActivity.this, "有查询结果", Toast.LENGTH_SHORT);
//                    toast.show();
                }
            }else{
                String[] result3=new String[100];
                for(int i=0;i<result.size();i++){
                    result3[i]=result.get(i).toString();
                }
                Intent intent = new Intent(zzcxActivity.this,zzcxjgActivity.class);
                intent.putExtra("result",result3);
                startActivity(intent);
            }

            }
        });
//        //（24）如果没有查询就通过Toast消息提醒用户
//        //（25）如有查询结果则进一步调用查询结果界面用listview展示

        //（26）为返回按钮添加监听
        //（27）返回主菜单界面
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(zzcxActivity.this,MainActivity.class);
//                startActivity(intent);
                zzcxActivity.this.finish();
            }
        });
    }
}
