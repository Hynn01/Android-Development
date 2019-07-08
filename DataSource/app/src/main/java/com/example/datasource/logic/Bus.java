package com.example.datasource.logic;

import java.util.ArrayList;

public class Bus {
    private ArrayList<Station> stations;
    private  int id;
   // private Station start; 默认起始栈在0处 末站在size-1处
  //  private Station end;
    public Bus(int id) {
        stations = new ArrayList<Station>();
        this.id = id;
    }
    public void addStation(Station station){
        stations.add(station);
    }
    public int getId() {
        return id;
    }

    //看u到v是否是正方向
    public boolean judgeDirection(int u, int v){
        int pos = 0;
        for(int i=0; i<stations.size(); i++){
            if(stations.get(i).getId() == u){
                pos = i;
                break;
            }
        }
        for(int i=pos+1; i<stations.size(); i++){
            if(stations.get(i).getId() == v) return true;
        }
        return false;
    }
    //找换乘点
    public Station seachOffset(int st , int ed , int cur , int offset){
        for(int i=0; i<stations.size(); i++){
            if(stations.get(i).getId() == cur){
                if(judgeDirection(st ,ed)) return stations.get(cur + offset);
                else stations.get(cur - offset);
            }
        }
        return null;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }
}
