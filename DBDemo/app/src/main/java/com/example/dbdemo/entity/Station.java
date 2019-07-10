package com.example.dbdemo.entity;


public class Station {
    //表名
    public static final String TABLE = "Station";

    //各个域名
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_Abbreviation = "abbreviation";
    public static final String KEY_isTransfer = "isTransfer";

    //属性
    public int station_ID;
    public String name;
    public String abbreviation;
    public int isTransfer;
   // public ArrayList<Bus> buses;

    public int getStation_ID(){
        return  station_ID;
    }
//    public ArrayList<Bus> getBuses(){
//        return  buses;
//    }

    public String toString(){
        return this.station_ID + " " + this.name ;
    }
}
