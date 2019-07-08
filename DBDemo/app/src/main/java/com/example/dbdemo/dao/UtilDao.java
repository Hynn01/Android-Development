package com.example.dbdemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dbdemo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/10/16.
 */

public class UtilDao {
    private DatabaseUtil du;
    private SQLiteDatabase db;

    public UtilDao(Context context){
        du = new DatabaseUtil(context);
        db = du.getWritableDatabase();
    }


    /**
     * 添加数据
     * */
    public void addData(String tableName,String[] key,String[] values){
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < key.length; i ++){
            contentValues.put(key[i],values[i]);
        }
        db.insert(tableName,null,contentValues);
        contentValues.clear();
    }

    /**
     * 删除数据
     * */
    public int delData(String where,String[] values){
        int del_data;
        del_data = db.delete("UserInfo",where,values);
        return del_data;
    }

    /**
     * 修改数据
     * */
    public void update(String[] values){
        db.execSQL("update UserInfo set userName=?,userPhone=? where userName=? ",values);
    }

    /**
     * 查询数据
     * */
    public List<User> inquireData(){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select userName,userPhone" +
                " from UserInfo",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String phone = cursor.getString(1);

            User user = new User();
            user.setName(name);
            user.setPhone(phone);

            list.add(user);
        }

        return list;
    }

    /**
     * 关闭数据库连接
     * */
    public void getClose(){
        if(db != null){
            db.close();
        }
    }
}
