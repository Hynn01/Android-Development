package com.example.myapplication;

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
    public ArrayList<Station> stationArrayList;

}
