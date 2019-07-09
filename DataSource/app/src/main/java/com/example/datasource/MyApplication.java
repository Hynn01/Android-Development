package com.example.datasource;

import android.app.Application;

import com.example.datasource.dao.Repo;
import com.example.datasource.entity.Bus;
import com.example.datasource.entity.Station;
import com.example.datasource.logic.Graph;
import com.example.datasource.entity.Path;

import java.util.ArrayList;

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
        System.out.println("myapplication has been called!");
        //初始化 bus stations
        initData();

        checkData();
        //建图
        G = new Graph();
        G.construct(buses);

    }
    public void checkData(){
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

        //测试bus.stations
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
}
