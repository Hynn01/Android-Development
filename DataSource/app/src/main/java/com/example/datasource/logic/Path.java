package com.example.datasource.logic;

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
}
