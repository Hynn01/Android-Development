package com.example.dbdemo;

import android.app.Application;

import com.example.dbdemo.dao.Repo;
import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.example.dbdemo.logic.Graph;
import com.example.dbdemo.entity.Path;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyApplication extends Application {
    private Repo repo;
    private ArrayList<Station> stations;
    private ArrayList<Bus> buses;
    private Graph G;

    /**
     * 创建时调用
     * */
    @Override
    public void onCreate() {
        super.onCreate();
        repo = new Repo(this);
        stations = new ArrayList<>();
        buses = new ArrayList<>();
        G = new Graph();
        System.out.println("myapplication has been called!");
        //初始化 bus stations
        initData();
        checkData();
        //建图
        G.construct(buses , repo.getStationCount());
        //看图
        System.out.println("看图");
        G.show();
    }
    public void checkData(){
        System.out.println("检查数据：");
        for(Bus bus : buses){
            System.out.println(bus.toString());
            for(Station station : bus.stations){
                System.out.println(station);
            }
        }
    }
    public void initData(){
        int n = repo.getBusCount();
        //station 数据
        int m = repo.getStationCount();
        //看一下记录数是否正确
        System.out.println("StationCount , BusCount = " + m + " "+ n);
        for(int i=1;i<=n;i++){
            buses.add(repo.getBusById(i));
        }
        for(int i=1;i<=m;i++){
            stations.add(repo.getStationById(i));
        }

    }
    //增加记录后要修改stations 和 buses
    //寻路方法1
    public ArrayList<String> findPathDirectly(String start , String end){
        int u = repo.getStationIdByName(start);
        int v = repo.getStationIdByName(end);
        ArrayList<String> busName = new ArrayList<>();  //结果集合

        ArrayList<Bus> bus_u = repo.getBusArrayList(u);
        ArrayList<Bus> bus_v = repo.getBusArrayList(v);

        for(Bus b1 : bus_u){
            for(Bus b2 : bus_v){
                if(b1.bus_ID == b2.bus_ID){
                    busName.add(b1.name);
                }
            }
        }
        return busName;
    }
    //寻路方法2
    public ArrayList<Path> findPathMiddle(int u, int v){
        return  G.findPathMiddle(stations , buses , u , v);
    }
    /**
     * 后台进程终止，前台程序需要内存时调用此方法，用于释放内存
     * 用于关闭数据库连接
     * */

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        repo.close();
    }

    //数据匹配函数
    public String[] Match(String[] str,String child){
        String [] matchStr;
        ArrayList<String> list = new ArrayList<String>();
        Pattern p=Pattern.compile(child);

        int i;
        for(i=0;i<str.length;i++) {
            Matcher m=p.matcher(str[i]);
            if(m.find()) {
                list.add(str[i]);
            }
        }

        matchStr=(String[])list.toArray(new String[0]);
        return matchStr;
    }

    public Repo getDao() {
        return repo;
    }
    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public Boolean insertStaionBusRelation(String bus_name , String station_name){
        System.out.println("InsertRelation Called : " + bus_name + " " + station_name);
        boolean result = repo.insertTransByValue(bus_name , station_name, null,null);
        if(!result) return false;
        System.out.println("result = " + result);
        //更新 buses
        for(Bus b : buses){
            if(b.getName() == bus_name){
                Station s = repo.getStationByName(station_name);
                System.out.println("要插入的站 ：" + s+" 要插入的Bus"+b);
                b.addStation(s); //更新 buser 里的该bus
                stations.add(s); //更新 stations
                break;
            }
        }
        System.out.println("Buses Arrarlist: ");
        for(Bus bus : buses){
            System.out.println(bus);
            for(Station station : bus.stations){
                System.out.println(station);
            }
        }
        G = new Graph();
        G.construct(buses,stations.size());
        System.out.println("Graph @!!!!!!!!!!");
        G.show();
        return true;

    }

}
// 车次查询  参数：线路名字  返回车次对象BUS

//车站查询  参数 车站名字  返回车站对象Station

//车次添加 添加车次号  车型  起始站  终点站  返回是否插入成功 Boolean

//车站添加 车站名字  车站简称   返回是否插入成功 Boolean

// 站次关系 添加 车站  车次    返回是否插入成功 Boolean