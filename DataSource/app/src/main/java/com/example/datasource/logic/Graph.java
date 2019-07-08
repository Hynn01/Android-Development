package com.example.datasource.logic;

import java.util.ArrayList;

public class Graph {
    private static final int  SIZE = 500;
    private int[] head;//head[i]表示i起点的最后一条边的编号
    private int cnt;//边的个数
    private ArrayList<Node> edges;
    private int V = 1;//顶点个数
    private ArrayList< ArrayList<Integer> > paths;
    private ArrayList< ArrayList<Integer> > nodePaths;
    private ArrayList<Integer> nodePath;
    private ArrayList<Integer> edgePath;
    private boolean[] vis;
    public Graph(){
        head = new int[SIZE];
        cnt = 1;
        edges = new ArrayList<Node>();
        nodePath = new ArrayList<Integer>();
        edgePath = new ArrayList<Integer>();

        paths = new ArrayList< ArrayList<Integer> >();
        nodePaths = new ArrayList< ArrayList<Integer> >();
        vis = new boolean[SIZE];
    }
    public void setV(int v){
        V = v;
    }
    public void show(){
        for(int i=1; i<=V; i++){
            for(int j=head[i]; j!=0; j=edges.get(j).next){
                System.out.println("顶点："+i+" "+edges.get(j).v+" 边："+ edges.get(j).w);
            }
        }
    }

    public void addEdge(int u, int v , int w){
        edges.get(u).w = w;
        edges.get(u).v = v;
        edges.get(u).next = head[u];
        head[u] = cnt++;
    }
    public void constructOneLine(Bus bus){
        int p = 0;
        int q = 1;
        ArrayList<Station> stations = bus.getStations();
        while (q < stations.size()){
            int u = stations.get(p).getId();
            int v = stations.get(q).getId();
            addEdge(u , v , bus.getId());
            addEdge(v , u, bus.getId());
            p = q;
            q++;
        }
    }

    public void construct(ArrayList<Bus> buses){
        for(Bus bus : buses){
            constructOneLine(bus);
        }
    }

    public void dfs(int cur, int dst){
        nodePath.add(cur);
        vis[cur] = true;
        if(cur == dst){
            nodePaths.add(nodePath);
            paths.add(edgePath);
        }
        else{
            for(int i=head[cur]; i!=0; i=edges.get(i).next){
                int v = edges.get(i).v;
                if(!vis[v]){
                    edgePath.add(edges.get(i).w);
                    dfs(v , dst);
                }
            }
        }
        nodePath.remove(nodePath.size()-1);
        edgePath.remove(edgePath.size()-1);
        vis[cur] = false;
    }


    public ArrayList<Integer> findPathDirectly(ArrayList<Station>stations , int u, int v){
        Station start = stations.get(u);
        Station end = stations.get(v);
        ArrayList<Integer> result = new ArrayList<>();
        for(Bus b1 : start.getBuses()){
            for(Bus b2 : end.getBuses()){
                if(b1.getId() == b2.getId()){
                    result.add(b1.getId());
                }
            }
        }
        return result;
    }
    public void dealStupidPaths(){
        for(int i=0; i<paths.size(); i++){
            // 1 2 2 2 3 1 4
            //1 2 2 3 4 5
            boolean[] vis = new boolean[SIZE];
            ArrayList<Integer> cur = paths.get(i);
            int pre = -1;
            for(int x : cur){
                if(x != pre){
                    vis[x] = true;
                    pre = x;
                    if(vis[x]){
                        paths.remove(i);
                        break;
                    }
                }
            }
        }
    }
    public ArrayList<Path> findPathMiddle(ArrayList<Station>stations , ArrayList<Bus>buses , int u,int v){
        dfs(u , v); //填充paths
        Station start = stations.get(u);
        Station end = stations.get(v);
        //处理paths
        //1. 去除傻逼路径
        dealStupidPaths();
        //2. 添加换乘信息
        ArrayList<Path> result = new ArrayList<Path>();
        for(ArrayList<Integer> edgePath : paths){
            Path path = new Path(start , end );
            int pre = edgePath.get(0);//第一条边
            int offset = 1;//同样边的个数
            int curStation = u;//刚开始当前站肯定是出发点嘛
            for(int i=1; i<edgePath.size(); i++){
                if(edgePath.get(i) == pre) offset++;
                else{
                    //表示有新的换乘站加入
                    Station trans = buses.get(pre).seachOffset(u , v, curStation , offset);
                    path.addTrans(trans);
                    curStation = trans.getId();//当前站变更
                    offset = 1;//偏移量重置
                }
            }
            result.add(path);
            //最后一个不算换乘啦，已经到啦
        }
        return result;
    }
}

