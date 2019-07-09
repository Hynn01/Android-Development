package com.example.dbdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.dbdemo.R;

public class aboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）一开始进入莱单界面方法就是goToMainMenu()函数
        //（2）切换到莱单界面 使用setContentView（）
        //（3）标识当前界面为菜单界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
}
