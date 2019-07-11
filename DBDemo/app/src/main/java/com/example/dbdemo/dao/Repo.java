package com.example.dbdemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.example.dbdemo.entity.Trancepos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Repo {


    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public Repo(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //增
    public int insertBus(Bus bus){
        ContentValues values = new ContentValues();
        values.put(Bus.KEY_name,bus.name);
        values.put(Bus.KEY_originatingStation,bus.oStation);
        values.put(Bus.KEY_terminus,bus.terminus);
        values.put(Bus.KEY_type,bus.type);

        System.out.println("bbbb:"+bus);

        long bus_ID = db.insert(Bus.TABLE,null,values);
        return (int)bus_ID;
    }
    public int insertStation(Station station){
        ContentValues values = new ContentValues();
        values.put(Station.KEY_name,station.name);
        values.put(Station.KEY_Abbreviation,station.abbreviation);
        values.put(Station.KEY_isTransfer,station.isTransfer);

        long station_ID = db.insert(Station.TABLE,null,values);
        return (int)station_ID;

    }
    public int insertTrancepos(Trancepos trancepos){
        ContentValues values = new ContentValues();
        values.put(Trancepos.KEY_bus,trancepos.bus_ID);
        values.put(Trancepos.KEY_station,trancepos.station_ID);
        values.put(Trancepos.KEY_arrive,trancepos.arrive);
        values.put(Trancepos.KEY_leave,trancepos.leave);

        long trancepos_ID = db.insert(Trancepos.TABLE,null,values);
        return (int)trancepos_ID;

    }

    //删
    public void deleteBus(int bus_ID){
        if(!busIsExistById(bus_ID)){
            System.out.println("bus data NOT EXIST!!");
            return;
        }
        db.delete(Bus.TABLE,Bus.KEY_ID+"=?",new String[]{
                String.valueOf(bus_ID)
        });
    }
    public void deleteStation(int station_ID){
        if(!stationIsExistById(station_ID)){
            System.out.println("station data NOT EXIST!!");
            return;
        }
        db.delete(Station.TABLE,Station.KEY_ID+"=?",new String[]{
                String.valueOf(station_ID)
        });
    }
    public void deleteTrancepos(int trancepos_ID){
        if(!tranceposIsExistById(trancepos_ID)){
            System.out.println("trancepos data NOT EXIST!!");
            return;
        }
        db.delete(Trancepos.TABLE,Trancepos.KEY_ID+"=?",new String[]{
                String.valueOf(trancepos_ID)
        });
    }

    //改
    public void updateBus(Bus bus){
        if(!busIsExistById(bus.bus_ID)){
            System.out.println("bus data NOT EXIST!!");
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Bus.KEY_name,bus.name);
        values.put(Bus.KEY_originatingStation,bus.oStation);
        values.put(Bus.KEY_terminus,bus.terminus);
        values.put(Bus.KEY_type,bus.type);

        db.update(Bus.TABLE,values,Bus.KEY_ID+"=?",new String[]{
                String.valueOf(bus.bus_ID)
        });
    }
    public void updateStation(Station station){
        if(!stationIsExistById(station.station_ID)){
            System.out.println("station data NOT EXIST!!");
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Station.KEY_name,station.name);
        values.put(Station.KEY_Abbreviation,station.abbreviation);
        values.put(Station.KEY_isTransfer,station.isTransfer);

        db.update(Station.TABLE,values,Station.KEY_ID+"=?",new String[]{
                String.valueOf(station.station_ID)
        });
    }
    public void updateTrancepos(Trancepos trancepos){
        if(!tranceposIsExistById(trancepos.trancepos_ID)){
            System.out.println("trancepos data NOT EXIST!!");
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Trancepos.KEY_bus,trancepos.bus_ID);
        values.put(Trancepos.KEY_station,trancepos.station_ID);
        values.put(Trancepos.KEY_arrive,trancepos.arrive);
        values.put(Trancepos.KEY_leave,trancepos.leave);

        db.update(Trancepos.TABLE,values,Trancepos.KEY_ID+"=?",new String[]{
                String.valueOf(trancepos.trancepos_ID)
        });
    }

    //查
    public ArrayList<HashMap<String, String>> getBusList(){
        String selectQuery = "SELECT "+
                Bus.KEY_ID+","+
                Bus.KEY_name+","+
                Bus.KEY_originatingStation+","+
                Bus.KEY_terminus+","+
                Bus.KEY_type+" FROM "+Bus.TABLE;

        ArrayList<HashMap<String, String>> busList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> bus = new HashMap<String,String>();
                bus.put("id",cursor.getString(cursor.getColumnIndex(Bus.KEY_ID)));
                bus.put("name",cursor.getString(cursor.getColumnIndex(Bus.KEY_name)));
                busList.add(bus);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return busList;
    }
    public ArrayList<HashMap<String, String>> getStationList(){
        //select id,name,abbreviation from station
        String selectQuery = "SELECT "+
                Station.KEY_ID+","+
                Station.KEY_name+","+
                Station.KEY_Abbreviation+","+
                Station.KEY_isTransfer+" FROM "+Station.TABLE;

        ArrayList<HashMap<String, String>> stationList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> station = new HashMap<String,String>();
                station.put("id",cursor.getString(cursor.getColumnIndex(Station.KEY_ID)));
                station.put("name",cursor.getString(cursor.getColumnIndex(Station.KEY_name)));
                stationList.add(station);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return stationList;

    }
    public ArrayList<HashMap<String, String>> getTranceposList(){
        String selectQuery = "SELECT "+
                Trancepos.KEY_ID+","+
                Trancepos.KEY_bus+","+
                Trancepos.KEY_station+","+
                Trancepos.KEY_arrive+","+
                Trancepos.KEY_leave+" FROM "+Trancepos.TABLE;

        ArrayList<HashMap<String, String>> tranceposList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> trancepos = new HashMap<String,String>();
                trancepos.put("station_Id",cursor.getString(cursor.getColumnIndex(Trancepos.KEY_station)));
                trancepos.put("bus_Id",cursor.getString(cursor.getColumnIndex(Trancepos.KEY_bus)));
                tranceposList.add(trancepos);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return tranceposList;

    }


    public Bus getBusById(int Id){
        //System.out.println("getBusById been called~!");
        Bus bus = getBusById1(Id);
        bus.stations = getStationArrayList(Id);
        //for (Station a:bus.stations) {
        //    System.out.println("station:"+a.station_ID+a.name);
        //}
        return bus;
    }

    public Bus getBusById1(int Id){
        String selectQuery = "SELECT "+
                Bus.KEY_ID+","+
                Bus.KEY_name+","+
                Bus.KEY_originatingStation+","+
                Bus.KEY_terminus+","+
                Bus.KEY_type+
                " FROM "+Bus.TABLE
                +" WHERE "+
                Bus.KEY_ID + "=?";
        int iCount = 0;
        Bus bus = new Bus();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                bus.bus_ID = cursor.getInt(cursor.getColumnIndex(Bus.KEY_ID));
                bus.name = cursor.getString(cursor.getColumnIndex(Bus.KEY_name));
                bus.oStation = cursor.getInt(cursor.getColumnIndex(Bus.KEY_originatingStation));
                bus.terminus = cursor.getInt(cursor.getColumnIndex(Bus.KEY_terminus));
                bus.type = cursor.getString(cursor.getColumnIndex(Bus.KEY_type));
            }while (cursor.moveToNext());
        }
        cursor.close();
        //System.out.println(bus.bus_ID+" "+bus.name);
        return bus;

    }
    public Station getStationById(int Id){
        String selectQuery = "SELECT "+
                Station.KEY_ID+","+
                Station.KEY_name+","+
                Station.KEY_Abbreviation+","+
                Station.KEY_isTransfer+
                " FROM "+Station.TABLE
                +" WHERE "+
                Station.KEY_ID + "=?";
        int iCount = 0;
        Station station = new Station();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do {
                station.station_ID = cursor.getInt(cursor.getColumnIndex(Station.KEY_ID));
                station.name = cursor.getString(cursor.getColumnIndex(Station.KEY_name));
                station.abbreviation = cursor.getString(cursor.getColumnIndex(Station.KEY_Abbreviation));
                station.isTransfer = cursor.getInt(cursor.getColumnIndex(Station.KEY_isTransfer));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return station;
    }
    public Trancepos getTranceposById(int Id){
        String selectQuery = "SELECT "+
                Trancepos.KEY_ID+","+
                Trancepos.KEY_bus+","+
                Trancepos.KEY_station+","+
                Trancepos.KEY_arrive+","+
                Trancepos.KEY_leave+
                " FROM "+Trancepos.TABLE
                +" WHERE "+
                Trancepos.KEY_ID + "=?";
        int iCount = 0;
        Trancepos trancepos = new Trancepos();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do {
                trancepos.trancepos_ID = cursor.getInt(cursor.getColumnIndex(Trancepos.KEY_ID));
                trancepos.bus_ID = cursor.getInt(cursor.getColumnIndex(Trancepos.KEY_bus));
                trancepos.station_ID = cursor.getInt(cursor.getColumnIndex(Trancepos.KEY_station));
                trancepos.arrive = cursor.getString(cursor.getColumnIndex(Trancepos.KEY_arrive));
                trancepos.leave = cursor.getString(cursor.getColumnIndex(Trancepos.KEY_leave));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return trancepos;
    }

    //返回目标公交车经过的所有车站
    //返回目标站所有经过的公交车
    public ArrayList<Station> getStationArrayList(int bus_Id){
        String selectQuery = "SELECT "+
                Trancepos.KEY_station+
                " FROM "+Trancepos.TABLE
                +" WHERE "+
                Trancepos.KEY_bus + "=?";
        int iCount = 0;

        //stationList存所有该公交车经过的车站
        ArrayList<Integer> stationIdList = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(bus_Id)});
        if(cursor.moveToFirst()){
            do {
                stationIdList.add(cursor.getInt(cursor.getColumnIndex(Trancepos.KEY_station)));
            }while (cursor.moveToNext());
        }
        //System.out.println(stationIdList);
        cursor.close();

        //将所有站实例化
        ArrayList<Station> stationList = new ArrayList<Station>();
        Iterator iterator = stationIdList.iterator();
        while(iterator.hasNext()){
            Station station = getStationById((int)iterator.next());
            stationList.add(station);
            //System.out.println("实例化stationList"+station);
        }
        return stationList;
    }
    public ArrayList<Bus> getBusArrayList(int station_Id){
        String selectQuery = "SELECT "+
                Trancepos.KEY_bus+
                " FROM "+Trancepos.TABLE
                +" WHERE "+
                Trancepos.KEY_station + "=?";
        int iCount = 0;

        //stationList存所有该公交车经过的车站
        ArrayList<Integer> busIdList = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(station_Id)});
        if(cursor.moveToFirst()){
            do {
                busIdList.add(cursor.getInt(cursor.getColumnIndex(Trancepos.KEY_bus)));
            }while (cursor.moveToNext());
        }
        cursor.close();

        //将所有站实例化
        ArrayList<Bus> busList = new ArrayList<Bus>();
        Iterator iterator = busIdList.iterator();
        while(iterator.hasNext()){
            Bus bus = getBusById1((int)iterator.next());
        }
        return busList;
    }

    //判断记录是否存在
    public boolean busIsExistById(int bus_id){
        ArrayList<HashMap<String, String>> busList = getBusList();
        Iterator<HashMap<String, String>> it = busList.iterator();
        while (it.hasNext()){
            if(String.valueOf(bus_id).equals(it.next().get("id"))){
                return true;
            }
        }
        return false;
    }

    //判断记录是否存在
    public boolean busIsExistByName(String bus_name){
        ArrayList<HashMap<String, String>> busList = getBusList();
        Iterator<HashMap<String, String>> it = busList.iterator();
        while (it.hasNext()){
            if(bus_name.equals(it.next().get("name"))){
                return true;
            }
        }
        return false;
    }

    public boolean stationIsExistById(int station_id){
        ArrayList<HashMap<String, String>> stationList = getStationList();
        Iterator<HashMap<String, String>> it = stationList.iterator();
        while (it.hasNext()){
            if(String.valueOf(station_id).equals(it.next().get("id"))){
                return true;
            }
        }
        return false;
    }

    public boolean stationIsExistByName(String station_name){
        ArrayList<HashMap<String, String>> stationList = getStationList();
        Iterator<HashMap<String, String>> it = stationList.iterator();
        while (it.hasNext()){
            if(station_name.equals(it.next().get("name"))){
                return true;
            }
        }
        return false;
    }

    public boolean tranceposIsExistById(int trancepos_id){
        ArrayList<HashMap<String, String>> tranceposList = getTranceposList();
        Iterator<HashMap<String, String>> it = tranceposList.iterator();
        while (it.hasNext()){
            if(String.valueOf(trancepos_id).equals(it.next().get("id"))){
                return true;
            }
        }
        return false;
    }

    public boolean tranceposIsExistByName(String bus_name,String station_name){
        ArrayList<HashMap<String, String>> tranceposList = getTranceposList();
        Iterator<HashMap<String, String>> it = tranceposList.iterator();
        while (it.hasNext()){
            HashMap<String, String> trans = it.next();
            System.out.println("trans:"+trans);
            System.out.println("String.valueOf(getBusIdByName(bus_name)):"+String.valueOf(getBusIdByName(bus_name)));
            System.out.println("String.valueOf(getStationIdByName(station_name)):"+String.valueOf(getStationIdByName(station_name)));
            if(String.valueOf(getBusIdByName(bus_name)).equals(trans.get("bus_Id"))&&String.valueOf(getStationIdByName(station_name)).equals(trans.get("station_Id"))){
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public void close(){
        dbHelper.close();
    }

    //计数
    public int getBusCount() {
        ArrayList<HashMap<String, String>> busList = getBusList();
        return busList.size();
    }
    public int getStationCount() {
        ArrayList<HashMap<String, String>> stationList = getStationList();
        return stationList.size();
    }

    //name id 映射
    public int getBusIdByName(String bus_name){
        String selectQuery = "SELECT "+
                Bus.KEY_ID+
                " FROM "+Bus.TABLE
                +" WHERE "+
                Bus.KEY_name + "=?";
        int iCount = 0;
        int bus_id = 0;
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(bus_name)});
        if(cursor.moveToFirst()){
            do{
                bus_id = cursor.getInt(cursor.getColumnIndex(Bus.KEY_ID));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return bus_id;
    }
    public int getStationIdByName(String station_name){
        String selectQuery = "SELECT "+
                Station.KEY_ID+
                " FROM "+Station.TABLE
                +" WHERE "+
                Station.KEY_name + "=?";
        int iCount = 0;
        int station_id = 0;
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(station_name)});
        if(cursor.moveToFirst()){
            do{
                station_id = cursor.getInt(cursor.getColumnIndex(Station.KEY_ID));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return station_id;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

}
