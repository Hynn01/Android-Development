package com.example.dbdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.dbdemo.R;

//车次添加
public class cctjActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //（1）添加功能函数goTocctjView()
        //（2）切换到车次添加界面
        //（3）标识当前所在界面为车次添加界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cctj);
    }

    //（4）获取查询按钮的引用
    //（5）获取返回按钮的引用
    //（6）为始发站输入框添加适配器
    //（7）为终点站输入框添加适配器
    //（8）为添加按钮添加监听器
    //（9）在监听器函数中获取车次名字输入框的引用
    //（10）获取车次类型输入框的引用
    //（11）获取车次始发站愉入框弓
    //（12）获取车次终点站输入框引用
    //（13）取出相应的文本
    //（14）查询是否有该列车的数据库语句并进行查询
    //（15）如果有车则发toast信息提示返回
    //（16）查询是否有该始发站数据库语句并进行查询
    //（17）如果没有则发toast信息提示返回
    //（18）查询是否有该终点站的数据库语句并进行查询
    //（19）如果没有则发toast信息提示返回
    //（20）插入用户需要添加的列车
    //（21）如果添加失败
    //（22）发toast消息提醒用户
    //（23）为返回按钮添加监听
    //（24）在监听函数中返回到附加动能界面

}
