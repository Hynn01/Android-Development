package com.example.datasource.logic;

import java.util.ArrayList;

public class Station {
    private int id;
    private boolean isTrans;
    private ArrayList<Bus> buses;
    public Station(int id) {
        this.id = id;
        isTrans = false;
        buses = new ArrayList<Bus>();
    }
    public void addBus(Bus bus){
        buses.add(bus);
    }
    public boolean isTrans() {
        return isTrans;
    }

    public void setTrans(boolean trans) {
        isTrans = trans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }
}
