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
import com.example.dbdemo.dao.Repo;
import com.example.dbdemo.entity.Station;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//车次查询
public class cccxActivity extends Activity {

    private Button bt_query;
    private ImageButton bt_back;
    //private TextView tv_cc;//车次
    private MyApplication application;//数据库操作
    private AutoCompleteTextView autoinput_cccx_cc;
    ArrayList<Station> result;//返回结果
    String input;//输入

    //（1）车次查询模块goTocccxView()
    //（2）切换到车次查询界面
    //    用setContentView（）
    //（3）标识当前界面为车次查询界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cccx);

        Banner banner = (Banner) findViewById(R.id.banner_zzcx);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ad1);
        list.add(R.mipmap.ad2);
        list.add(R.mipmap.ad3);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();

        //（4）查询按钮引用
        bt_query=(Button)findViewById(R.id.searchButton_cccx);

        //（5）返回按钮引用
        bt_back=(ImageButton)findViewById(R.id.back_cccx);

//        //（6）为查询按钮添加监听器
//        //（7）在监听函数中拿到车次输入框的引用
//        //（8）从引用中由文本框获取文本
//        //（9）根据输入的信息查询结果
        autoinput_cccx_cc=findViewById(R.id.autoinput_cccx_cc);
        input=autoinput_cccx_cc.getText().toString();
        result=null;
        application= (MyApplication)this.getApplication();
        String[] str={};//获取数据库中所有的车次
        str=(String[])application.getDao().getBusNameList().toArray(new String[0]);

        //获取输入框中的字符
        String cc_child=autoinput_cccx_cc.getText().toString();
        String[] cc_match=application.Match(str,cc_child);
        ArrayAdapter<String> cc_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,cc_match);
        autoinput_cccx_cc.setAdapter(cc_adapter);

        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:查询
                result=application.getDao().getStationArrayList(application.getDao().getBusIdByName(input));
                //        //（10）如果查询结果为空，则应有无输出信息提示
                //        //（11）这时发toast信息提醒用户
                //        //（12）否则查询结果不为空
                //        //（13）切换到查询结果界面，由listview界面输出结果
                if(result==null){
                    Toast toast = Toast.makeText(cccxActivity.this, "没有查询结果", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(cccxActivity.this,zzcxjgActivity.class);
                    startActivity(intent);
                }
//
            }
        });


        //（14）为返回按钮添加监听
        //（15）在当按下返回按钮时，调用返回到主菜单界面函数返回
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cccxActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
