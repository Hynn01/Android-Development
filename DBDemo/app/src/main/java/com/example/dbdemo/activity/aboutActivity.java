package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.dbdemo.R;

public class aboutActivity extends Activity {

    private ImageButton bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）一开始进入莱单界面方法就是goToMainMenu()函数
        //（2）切换到莱单界面 使用setContentView（）
        //（3）标识当前界面为菜单界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        bt_back=(ImageButton)findViewById(R.id.bt_back);

        //（14）为返回按钮添加监听
        //（15）在当按下返回按钮时，调用返回到主菜单界面函数返回
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(aboutActivity.this,MainActivity.class);
//                startActivity(intent);
                aboutActivity.this.finish();
            }
        });

    }
}
