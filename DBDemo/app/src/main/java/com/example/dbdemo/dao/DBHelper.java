package com.example.dbdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Station;
import com.example.dbdemo.entity.Trancepos;

public class DBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    //每次你对数据表进行编辑，添加时候，你都需要对数据库的版本进行提升

    //数据库版本
    private static final int DATABASE_VERSION=3;

    //数据库名称
    private static final String DATABASE_NAME="sqlitestudy.db";


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_Bus="CREATE TABLE "+ Bus.TABLE+"("
                +Bus.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Bus.KEY_name+" TEXT, "
                +Bus.KEY_originatingStation+" INTEGER, "
                +Bus.KEY_terminus+" INTEGER,"
                +Bus.KEY_type+" TEXT)";
        String CREATE_TABLE_Station="CREATE TABLE "+ Station.TABLE+"("
                +Station.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Station.KEY_name+" TEXT, "
                +Station.KEY_Abbreviation+" TEXT,"
                +Station.KEY_isTransfer+" INTEGER)";
        String CREATE_TABLE_Trancepos="CREATE TABLE "+ Trancepos.TABLE+"("
                +Trancepos.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Trancepos.KEY_bus+" INTEGER, "
                +Trancepos.KEY_station+" INTEGER, "
                +Trancepos.KEY_arrive+" TEXT, "
                +Trancepos.KEY_leave+" TEXT)";
        db.execSQL(CREATE_TABLE_Bus);
        db.execSQL(CREATE_TABLE_Station);
        db.execSQL(CREATE_TABLE_Trancepos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ Bus.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ Station.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ Trancepos.TABLE);

        //再次创建表
        onCreate(db);
    }
}
