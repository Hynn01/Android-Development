package com.example.datasource.logic;

import com.example.datasource.entity.Bus;
import com.example.datasource.entity.Path;
import com.example.datasource.entity.Station;

import java.util.ArrayList;

//链式前向星建立图
public class Graph {
    private static final int  SIZE = 500;
    private int[] head;//head[i]表示i起点的最后一条边的编号
    private int cnt;//边的个数
    private ArrayList<Node> edges;//图的边
    private int V = 1;//顶点个数
    private ArrayList< ArrayList<Integer> > paths; //边的结果路径 重要
    private ArrayList< ArrayList<Integer> > nodePaths;//点的结果路径
    private ArrayList<Integer> nodePath;//当前结点栈
    private ArrayList<Integer> edgePath;//当前边栈
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
    //加边
   public void addEdge(int u, int v , int w){
        edges.get(u).w = w;
        edges.get(u).v = v;
        edges.get(u).next = head[u];
        head[u] = cnt++;
    }
    //一条线路建图
    //方便新加入线路造成的修改
    public void constructOneLine(Bus bus){
        int p = 0;
        int q = 1;
        ArrayList<Station> stations = bus.getStations();
        while (q < stations.size()){
            int u = stations.get(p).getStation_ID();
            int v = stations.get(q).getStation_ID();
            addEdge(u , v , bus.getBus_ID());
            addEdge(v , u, bus.getBus_ID());
            p = q;
            q++;
        }
    }
    //用所有的线路建图
    public void construct(ArrayList<Bus> buses){
        for(Bus bus : buses){
            constructOneLine(bus);
        }
    }

    //回溯法计算两点间所有路径
    public void dfs(int cur, int dst){
        nodePath.add(cur);//结点路径加入当前结点
        vis[cur] = true;//当前结点在栈中
        if(cur == dst){ //如果到了终点 则找到一条新路径
            nodePaths.add(nodePath); //点结果路径
            paths.add(edgePath); //边结果路径
        }
        else{ //如果不是终点 就要进一步往下搜索
            for(int i=head[cur]; i!=0; i=edges.get(i).next){
                int v = edges.get(i).v;
                if(!vis[v]){ //如果这个点不在栈上 则以这个点为起点继续搜索
                    edgePath.add(edges.get(i).w);//这条边的权值（公交线路号）入边路径
                    dfs(v , dst);//继续搜索
                }
            }
        }
        nodePath.remove(nodePath.size()-1); // 返回时 弹出路径栈顶结点
        edgePath.remove(edgePath.size()-1); //边栈与点栈同进退
        vis[cur] = false; //这个点已经退栈，标记为未访问过
    }


    //去除傻逼路径
    public void dealStupidPaths(){
        for(int i=0; i<paths.size(); i++){//遍历所有路径
            // 1 2 2 2 3 1 4
            //1 2 2 3 4 5
            boolean[] vis = new boolean[SIZE];
            ArrayList<Integer> cur = paths.get(i);//取出当前路径
            int pre = -1;//记前一个边 为-1
            for(int x : cur){  //遍历所有的边
                if(x != pre){ //要换乘了，看一下将要换乘的线路是不是之前下来的
                    if(vis[x]){ //如果是之前下来的，说明这是傻逼路径
                        paths.remove(i);
                        break;
                    }
                    vis[x] = true;
                    pre = x;
                }
            }
        }
    }

    //中转站查询 返回的是Path的ArrayList
    //每一个Path都是一条路径 里面有起始栈  以及有序中转站集合
    public ArrayList<Path> findPathMiddle(ArrayList<Station>stations , ArrayList<Bus>buses , int u, int v){
        dfs(u , v); //计算所有路径 填充paths 边结果路径
        Station start = stations.get(u-1);
        Station end = stations.get(v-1);
        //处理paths
        //1. 去除傻逼路径
        dealStupidPaths();
        //2. 添加换乘信息
        //1111122223333
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
                    curStation = trans.getStation_ID();//当前站变更
                    offset = 1;//偏移量重置
                }
            }
            result.add(path);
        }
        //最后一个不算换乘啦，已经到啦
        return result;
    }
}

