package com.example.datasource.entity;

import java.util.ArrayList;

public class Bus {
    //表名
    public static final String TABLE = "Bus";

    //各个域名
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_originatingStation ="OStation";
    public static final String KEY_terminus = "terminus";
    public static final String KEY_type = "type";

    //属性
    public int bus_ID;
    public String name;
    public int oStation;
    public int terminus;
    public String type;
    public ArrayList<Station> stations;

    public Bus(){stations = new ArrayList<Station>();}
    public Bus(int id) {
        stations = new ArrayList<Station>();
        this.bus_ID = id;
    }
    public void addStation(Station station){
        stations.add(station);
    }
    public int getBus_ID() {
        return bus_ID;
    }

    //看u到v是否是正方向
    public boolean judgeDirection(int u, int v){
        int pos = 0;
        for(int i=0; i<stations.size(); i++){
            if(stations.get(i).getStation_ID() == u){
                pos = i;
                break;
            }
        }
        for(int i=pos+1; i<stations.size(); i++){
            if(stations.get(i).getStation_ID() == v) return true;
        }
        return false;
    }
    //找换乘点
    public Station seachOffset(int st , int ed , int cur , int offset){
        for(int i=0; i<stations.size(); i++){
            if(stations.get(i).getStation_ID() == cur){
                if(judgeDirection(st ,ed)) return stations.get(cur + offset);
                else stations.get(cur - offset);
            }
        }
        return null;
    }
    public void setBus_ID(int bus_ID) {
        this.bus_ID = bus_ID;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }
    public String toString(){
       String result = "线路id = " + this.bus_ID + " 线路名:" + this.name;
        return result;
    }
}
