package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dbdemo.R;
import java.util.ArrayList;

//去车站所有车次查询
public class czcccxActivity extends Activity {


    private Button bt_query;
    private ImageButton bt_back;
    private TextView tv_cz;//车次

    //（1）车站查询，goToczcccxView()
    //（2）切换到车站查询界面
    //    用setContentView（）
    //（3）标识当前界度为车站杏询界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cccx);

        //（4）获取杳询按钮的引用
        //（5）获取返回按钮的引用
        bt_query=(Button)findViewById(R.id.bt_query);
        bt_back=(ImageButton)findViewById(R.id.bt_back);

        //（6）为车站输入文本框添加适配器
        //（7）为查询按钮添加监听
        //（8）在监听函数里获取车站输入框中的文本
        //（9）调用方法查询
        tv_cz=(ImageButton)findViewById(R.id.tv_cc);
        String input=tv_cz.getText().toString();
        ArrayList<String> result=null;
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:查询
                //result=query(input)
            }
        });

        //（10）如果查询结果为空
        //（11）发toast消息提醒
        //（12）否则切换到结果listview界面
        if(result==null){
            Toast toast = Toast.makeText(czcccxActivity.this, "没有查询结果", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Intent intent = new Intent(czcccxActivity.this,zzcxjgActivity.class);
            startActivity(intent);
        }

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
