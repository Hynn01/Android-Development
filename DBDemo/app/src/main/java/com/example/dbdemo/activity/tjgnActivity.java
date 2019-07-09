package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbdemo.R;

//附加功能
public class tjgnActivity extends Activity {

    private Button bt_tjgx;//添加关系
    private Button bt_tjcz;//添加车站
    private Button bt_tjcc;//添加车次

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）添加功能 goTofjgnView()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjgn);

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

    }

}
