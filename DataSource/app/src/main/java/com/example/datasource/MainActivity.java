package com.example.datasource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.datasource.dao.Repo;
import com.example.datasource.entity.Bus;
import com.example.datasource.entity.Station;
import com.example.datasource.entity.Trancepos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Repo repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repo = ((MyApplication)this.getApplication()).getDao();
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();

        //先清空
        repo.getDbHelper().onUpgrade(repo.getDb() , 3 ,4);

        //station表
        for(int i=1; i<=4; i++){
            for(int j=1; j<=5; j++){
                Station station = new Station();
                station.name = "" + (char)(i+96) + j;
                station.station_ID = (i-1)*5 + j;
                //插入station
                stations.add(station);
                //数据库插入
                //TODO
                repo.insertStation(station);
            }
        }
        //bus表
        for(int i=1; i<=4; i++){
            Bus bus = new Bus();
            bus.bus_ID = i;
            bus.name = "" + (char)(i+64);
            bus.oStation = (i-1)*5+1;
            bus.terminus = 5*i;
            buses.add(bus);
            //插入bus到数据库
            //TODO
            repo.insertBus(bus);
        }

        //站次表
        for(int i=1; i<=20; i++){
            Trancepos trans = new Trancepos();
            trans.trancepos_ID = i;
            trans.bus_ID = (i-1)/5 + 1;
            trans.station_ID = i;
            //插入站次到数据库
            //TODO
            repo.insertTrancepos(trans);
        }

        //额外再加两个线路
        Bus s1 = new Bus();
        s1.bus_ID  = 5;
        s1.name = "E";
        s1.oStation = 2;
        s1.terminus = 17;
        repo.insertBus(s1);

        for(int i=2; i<=17; i+=5){
            Trancepos trans = new Trancepos();
            trans.bus_ID = s1.bus_ID;
            trans.station_ID = i;
            //插入站次到数据库
            repo.insertTrancepos(trans);
        }

        Bus s2 = new Bus();
        s2.bus_ID  = 6;
        s2.name = "F";
        s2.oStation = 4;
        s2.terminus = 19;
        repo.insertBus(s2);
        for(int i=4; i<=19; i+=5){
            Trancepos trans = new Trancepos();
            trans.bus_ID = s2.bus_ID;
            trans.station_ID = i;
            //插入站次到数据库
            //TODO
            repo.insertTrancepos(trans);
        }


//        Bus b = new Bus();
//        b = ((MyApplication)this.getApplication()).getDao().getBusById(1);
//        System.out.println(b);
       // ((MyApplication)this.getApplication()).checkData();

        System.out.println( ((MyApplication) this.getApplication()).findPathMiddle(1,9));
        System.out.println( ((MyApplication) this.getApplication()).findPathMiddle(1,5));
    }
}
