package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dbdemo.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//车次添加
public class cctjActivity extends Activity {

    private Button bt_add;
    private ImageButton bt_back;

    private EditText et_cc;
    private EditText et_cx;
    private EditText et_qsz;
    private EditText et_zdz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）添加功能函数goTocctjView()
        //（2）切换到车次添加界面
        //（3）标识当前所在界面为车次添加界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctj);

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

        //（6）为始发站输入框添加适配器
        //（7）为终点站输入框添加适配器

        //（9）在监听器函数中获取车次名字输入框的引用
        //（10）获取车次类型输入框的引用
        //（11）获取车次始发站输入框的引用
        //（12）获取车次终点站输入框引用
        et_cc=(EditText)findViewById(R.id.autoinput_cctj_cc);
        et_cx=(EditText)findViewById(R.id.autoinput_cctj_cx);
        et_qsz=(EditText)findViewById(R.id.autoinput_cctj_qsz);
        et_zdz=(EditText)findViewById(R.id.autoinput_cctj_zdz);


        //（8）为添加按钮添加监听器
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //（13）取出相应的文本
                String cc=et_cc.getText().toString();
                String cx=et_cx.getText().toString();
                String qsz=et_cx.getText().toString();
                String zdz=et_cx.getText().toString();

//                //（7）如果输入框为空，则返回
//                //（12）查看简称文本是否只有字母
//                //（13）如有问题弹出对话框提醒用户
//                //字母检查这块已经在输入框中做了限制
//                if(station==null || station_sim==null){
//
//                }else{
//                    //（14）准备数据库查询该名称是否已经存在
//                    //（15）执行数据库查询
//                    //todo:result=quiry(station)
//                    //（16）如果不为空，说明存在此车站
//                    //（17）发Toast消息提醒用户
//                    if(result!=null){
//
//                    }else{
//                        //（18）插入数据
//                        //（19）如果插入失败，提醒用户
//                        //（20）否则为成功插入数据
//                        insert_flag=insert();
//                        if(insert_flag==false){
//                            Toast toast = Toast.makeText(cztjActivity.this,"插入失败",Toast.LENGTH_SHORT);
//                            toast.show();
//                        }else{
//                            if(insert_flag==false){
//                                Toast toast = Toast.makeText(cztjActivity.this,"插入成功",Toast.LENGTH_SHORT);
//                                toast.show();
//                            }
//                        }
//
//                    }
//
//                }
            }
        });

        //（23）为返回按钮添加监听
        //（24）在监听函数中返回到附加功能界面
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cctjActivity.this,tjgnActivity.class);
                startActivity(intent);
            }
        });

    }


    //（14）查询是否有该列车的数据库语句并进行查询
    //（15）如果有车则发toast信息提示返回
    //（16）查询是否有该始发站数据库语句并进行查询
    //（17）如果没有则发toast信息提示返回
    //（18）查询是否有该终点站的数据库语句并进行查询
    //（19）如果没有则发toast信息提示返回
    //（20）插入用户需要添加的列车
    //（21）如果添加失败
    //（22）发toast消息提醒用户

}
