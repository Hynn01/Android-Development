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

//关系添加(站次添加)
public class gxtjActivity extends Activity {

    private Button bt_add;
    private ImageButton bt_back;

    private EditText et_zm;
    private EditText et_cc;
    //private EditText et_zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）站次添加功能 goTogxtjView()
        //（2）切换到关系添加界面
        //（3）标识当前界面为关系添加界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gxtj);

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

        //（6）为站名输入框添加适配器
        //（8）获取车次输入框的引用
        //（9）获取站名输入框的引用
        //（10）获取到站时间输入框的引用
        //（11）获取发车时间输入框的引用
        et_zm=(EditText) findViewById(R.id.autoinput_gxtj_zm);
        et_cc=(EditText) findViewById(R.id.autoinput_gxtj_cc);
        //et_zc=(EditText) findViewById(R.id.autoinput_gxtj_zc);

        //（7）为添加按钮添加监听
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //（12）取出对应输入框中的文本信息
                //（13）从数据库中取得最大的它ID值
                //（14）列车对应的ID变量
                //（15）车站对应的ID变量
                //（16）判断各个输入框是否为空
                //（17）查询该列车对应的ID
                //（18）查询得到结果向量
                //（19）判断是否有该车
                //（20）有则取出其对应结果
                //（21）否则发消息提醒用户，无该车
                //（22）查询该车对应于该站的关系是否存在
                //（23）如果己经存在发消息提醒用户
                //（24）具体的插入相应数据库数据
                //（25）如果插入失败，发消息提醒用户
                //（26）否则为插入成功

            }
        });

        //（27）为返回按钮添加监听
        //（28）在监控中切换到添加功能界而
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gxtjActivity.this,tjgnActivity.class);
                startActivity(intent);
            }
        });
    }


}
