package com.example.dbdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ControlActivity_old extends Activity
{
    WhichView_old curr;

    Handler hd=new Handler()//声明消息处理器
    {
        @Override
        public void handleMessage(Message msg)//重写方法
        {
            switch(msg.what)
            {
                case 0://进入欢迎界面
                    goToWelcomeView();
                    break;
                case 1://进入菜单界面
                    goToMainMenu();
                    break;
                case 2://进入关于界面
                    //setContentView(R.layout.about);
                    curr= WhichView_old.ABOUT_VIEW;
                    break;
                case 3://进入帮助界面
                    //setContentView(R.layout.help);
                    curr= WhichView_old.HELP_VIEW;
                    break;

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initLisit();//初始化数组
        this.hd.sendEmptyMessage(0);
    }

    private void initLisit() {
    }

    public void goToWelcomeView()//发送消息进入欢迎界面
    {

    }
    public void goToMainMenu()//去主菜单
    {

    }
    public void goTozzcxView()//去站站查询
    {

    }
    public void goTocccxView()//去车次查询界面
    {

    }
    public void goToczcccxView()//去车站所有车次查询
    {

    }
    public void goTofjgnView()//去附加功能界面
    {

    }
    public void goTocctjView()//去车次添加界面
    {

    }
    public void goTocztjView()//去车站添加界面
    {

    }
    public void goTogxtjView()//去关系添加界面
    {

    }
    public void goToListView(String[][]mssg)//去ListView界面
    {

    }

    //某列车经过的所有车站。去经过车站界面
    public void goToPassStationView(String[][]mssg)
    {

    }

}

