package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Repo {
    private DBHelper dbHelper;
    public Repo(Context context){
        dbHelper = new DBHelper(context);
    }

    //增
    public int insertBus(Bus bus){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Bus.KEY_name,bus.name);
        values.put(Bus.KEY_originatingStation,bus.oStation);
        values.put(Bus.KEY_terminus,bus.terminus);
        values.put(Bus.KEY_type,bus.type);

        long bus_ID = db.insert(Bus.TABLE,null,values);
        db.close();
        return (int)bus_ID;
    }
    public int insertStation(Station station){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Station.KEY_name,station.name);
        values.put(Station.KEY_Abbreviation,station.abbreviation);
        values.put(Station.KEY_isTransfer,station.isTransfer);

        long station_ID = db.insert(Station.TABLE,null,values);
        db.close();
        return (int)station_ID;

    }
    public int insertTrancepos(Trancepos trancepos){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Trancepos.KEY_bus,trancepos.bus_ID);
        values.put(Trancepos.KEY_station,trancepos.station_ID);
        values.put(Trancepos.KEY_arrive,trancepos.arrive);
        values.put(Trancepos.KEY_leave,trancepos.leave);

        long trancepos_ID = db.insert(Trancepos.TABLE,null,values);
        db.close();
        return (int)trancepos_ID;

    }

    //删
    public void deleteBus(int bus_ID){
        if(!busIsExistById(bus_ID)){
            System.out.println("bus data NOT EXIST!!");
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Bus.TABLE,Bus.KEY_ID+"=?",new String[]{
                String.valueOf(bus_ID)
        });
        db.close();
    }
    public void deleteStation(int station_ID){
        if(!stationIsExistById(station_ID)){
            System.out.println("station data NOT EXIST!!");
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Station.TABLE,Station.KEY_ID+"=?",new String[]{
                String.valueOf(station_ID)
        });
        db.close();
    }
    public void deleteTrancepos(int trancepos_ID){
        if(!tranceposIsExistById(trancepos_ID)){
            System.out.println("trancepos data NOT EXIST!!");
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Trancepos.TABLE,Trancepos.KEY_ID+"=?",new String[]{
                String.valueOf(trancepos_ID)
        });
        db.close();
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
        db.close();
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
        db.close();
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
        db.close();
    }

    //查
    public ArrayList<HashMap<String, String>> getBusList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        db.close();
        return busList;
    }
    public ArrayList<HashMap<String, String>> getStationList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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
        db.close();
        return stationList;

    }
    public ArrayList<HashMap<String, String>> getTranceposList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
                trancepos.put("id",cursor.getString(cursor.getColumnIndex(Trancepos.KEY_ID)));
                trancepos.put("bus_Id",cursor.getString(cursor.getColumnIndex(Trancepos.KEY_bus)));
                tranceposList.add(trancepos);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tranceposList;

    }
    public Bus getBusById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
                bus.stationArrayList = getStationArrayList(bus.bus_ID);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bus;

    }
    public Station getStationById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
                station.busArrayList = getBusArrayList(station.station_ID);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return station;
    }
    public Trancepos getTranceposById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        db.close();
        return trancepos;
    }

    //返回目标公交车经过的所有车站
    //返回目标站所有经过的公交车
    public ArrayList<Station> getStationArrayList(int bus_Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        cursor.close();
        db.close();

        //将所有站实例化
        ArrayList<Station> stationList = new ArrayList<Station>();
        Iterator iterator = stationIdList.iterator();
        while(iterator.hasNext()){
            Station station = getStationById((int)iterator.next());
        }
        return stationList;
    }
    public ArrayList<Bus> getBusArrayList(int station_Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        db.close();

        //将所有站实例化
        ArrayList<Bus> busList = new ArrayList<Bus>();
        Iterator iterator = busIdList.iterator();
        while(iterator.hasNext()){
            Bus bus = getBusById((int)iterator.next());
        }
        return busList;
    }

    //判断记录是否存在
    public boolean busIsExistById(int bus_id){
        ArrayList<HashMap<String, String>> busList = getBusList();
        Iterator<HashMap<String, String>> it = busList.iterator();
        while (it.hasNext()){
            if(String.valueOf(bus_id).equals(it.next().get("bus_Id"))){
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



}
