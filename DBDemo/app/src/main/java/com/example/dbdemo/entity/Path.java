package com.example.dbdemo.entity;

import com.example.dbdemo.entity.Station;

import java.util.ArrayList;

public class Path {
    private Station start;
    private Station end;
    ArrayList<Station> transferStation;
    public Path(Station st, Station ed){
        start =st;
        end = ed;
        transferStation = new ArrayList<Station>();
    }
    public void addTrans(Station station){
        transferStation.add(station);
    }

    public String toString(){
        String res = "";
        res += start.name;
        for(Station station : transferStation){
            res += station.name + " ";
        }
        res += end.name;
        return res;
    }
}

