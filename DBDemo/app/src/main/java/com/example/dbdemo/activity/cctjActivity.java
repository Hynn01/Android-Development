package com.example.dbdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dbdemo.MyApplication;
import com.example.dbdemo.R;
import com.example.dbdemo.entity.Bus;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//车次添加
public class cctjActivity extends Activity {

    private MyApplication app;

    private Button bt_add;
    private ImageButton bt_back;

    private EditText et_cc;
    private EditText et_cx;
    private EditText et_qsz;
    private EditText et_zdz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app=(MyApplication) this.getApplication();

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
                String qsz=et_qsz.getText().toString();
                String zdz=et_zdz.getText().toString();

                System.out.println("cc:"+cc);
                System.out.println("cx:"+cx);
                System.out.println("qsz:"+qsz);
                System.out.println("zdz:"+zdz);

                boolean null_flag=false;

                if(cc=="" || cx=="" || qsz=="" || zdz==""){
                    Toast toast = Toast.makeText(cctjActivity.this,"有未填的项，请填写完整",Toast.LENGTH_SHORT);
                    toast.show();
                    null_flag=true;
                }

                //（14）查询是否有该列车的数据库语句并进行查询
                //（15）如果有车则发toast信息提示返回
                int bus_id=app.getDao().getBusIdByName(cc);
//                boolean bus_exist=app.getDao().busIsExistById(bus_id);
                boolean bus_exist=app.getDao().busIsExistByName(cc);
                if(bus_exist==true){
                    Toast toast = Toast.makeText(cctjActivity.this,"车次存在，请重新输入",Toast.LENGTH_SHORT);
                    toast.show();
                }

                //（16）查询是否有该始发站数据库语句并进行查询
                //（17）如果没有则发toast信息提示返回
                int start_station_id=app.getDao().getStationIdByName(qsz);
//                boolean start_station_exist=app.getDao().stationIsExistById(start_station_id);
                boolean start_station_exist=app.getDao().stationIsExistByName(qsz);
                if(start_station_exist==false){
                    Toast toast = Toast.makeText(cctjActivity.this,"起始车站不存在，请重新输入",Toast.LENGTH_SHORT);
                    toast.show();
                }

                //（18）查询是否有该终点站的数据库语句并进行查询
                //（19）如果没有则发toast信息提示返回
                int end_station_id=app.getDao().getStationIdByName(zdz);
//                boolean end_station_exist=app.getDao().stationIsExistById(end_station_id);
                boolean end_station_exist=app.getDao().stationIsExistByName(zdz);
                if(end_station_exist==false){
                    Toast toast = Toast.makeText(cctjActivity.this,"终点车站不存在，请重新输入",Toast.LENGTH_SHORT);
                    toast.show();
                }

                //（20）插入用户需要添加的列车
                //（21）如果添加失败
                //（22）发toast消息提醒用户
                if(bus_exist==false && start_station_exist==true && end_station_exist==true && null_flag==false){
                    Bus bus=new Bus(cc,cx,start_station_id,end_station_id);
                    app.getDao().insertBus(bus);//在数据库中插入
                    app.getBuses().add(bus);//在内存中插入
                    Toast toast = Toast.makeText(cctjActivity.this,"车次添加成功",Toast.LENGTH_SHORT);
                    toast.show();
                    System.out.println("aaaaa:"+app.getDao().getBusById1(1));
                }


            }
        });

        //（23）为返回按钮添加监听
        //（24）在监听函数中返回到附加功能界面
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(cctjActivity.this,tjgnActivity.class);
//                startActivity(intent);
                cctjActivity.this.finish();
            }
        });
    }

}
