package com.example.dbdemo.entity;

public class Trancepos {
    //表名
    public static final String TABLE = "Trancepos";

    //各个域名
    public static final String KEY_ID = "id";
    public static final String KEY_bus = "bus_ID";
    public static final String KEY_station= "station_ID";
    public static final String KEY_arrive = "arrive";
    public static final String KEY_leave = "leave";

    //属性
    public int trancepos_ID;
    public int bus_ID;
    public int station_ID;
    public String arrive;
    public String leave;

    public Trancepos(){

    }

    public Trancepos(int bus_ID,int station_ID){
        this.bus_ID=bus_ID;
        this.station_ID=station_ID;
    }

}
