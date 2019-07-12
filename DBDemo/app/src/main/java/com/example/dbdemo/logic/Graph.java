package com.example.dbdemo.logic;

import com.example.dbdemo.entity.Bus;
import com.example.dbdemo.entity.Path;
import com.example.dbdemo.entity.Station;

import java.util.ArrayList;

//链式前向星建立图
public class Graph {
    private static final int  SIZE = 500;
    private int[] head;//head[i]表示i起点的最后一条边的编号
    private int cnt;//边的个数
    private Node[] edges;//图的边
    private int V = 1;//顶点个数
    private ArrayList< ArrayList<Integer> > edgePaths; //边的结果路径 重要
    private ArrayList< ArrayList<Integer> > nodePaths;//点的结果路径
    private ArrayList<Integer> nodePath;//当前结点栈
    private ArrayList<Integer> edgePath;//当前边栈
    private boolean[] vis;
    public Graph(){
        head = new int[SIZE];
        cnt = 1;
        edges = new Node[SIZE];
        for(int i=0; i<SIZE; i++) edges[i] = new Node();

        nodePath = new ArrayList<Integer>();
        edgePath = new ArrayList<Integer>();

        edgePaths = new ArrayList< ArrayList<Integer> >();
        nodePaths = new ArrayList< ArrayList<Integer> >();

        vis = new boolean[SIZE];
    }
    public  void clear(){
        nodePaths.clear();
        edgePaths.clear();
        nodePath.clear();
        edgePath.clear();
        for(int i=0; i<SIZE; i++) vis[i] = false;//清空标志数组
    }
    public void setV(int v){
        V = v;
    }

    public void show(){
        System.out.println("我的图呢？");
        for(int i=1; i<=V; i++){
            for(int j=head[i]; j!=0; j=edges[j].next){
                System.out.println("始点："+i+" 终点："+edges[j].v+" 边："+ edges[j].w);
            }
        }
    }
    //加边
   public void addEdge(int u, int v , int w){
        edges[cnt].w = w;
        edges[cnt].v = v;
        edges[cnt].next = head[u];
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
            System.out.println("顶点u = " + u + " 顶点v = " + v + " 边 = "+ bus.getBus_ID());
            addEdge(u , v , bus.getBus_ID());
            addEdge(v , u, bus.getBus_ID());
            p = q;
            q++;
        }
    }
    //用所有的线路建图
    public void construct(ArrayList<Bus> buses){
        System.out.println("由数据源建图如下：");
        for(Bus bus : buses){
            System.out.println(bus.toString());
            for(Station station : bus.stations){
                System.out.println(station);
            }
        }

        for(Bus bus : buses){
            constructOneLine(bus);
        }
    }
    //回溯法计算两点间所有路径
    public void dfs(int cur, int dst){
        nodePath.add(cur);//结点路径加入当前结点
        vis[cur] = true;//当前结点在栈中
        if(cur == dst){ //如果到了终点 则找到一条新路径
            System.out.println("找到一条点路径！");
            System.out.println(nodePath);
            System.out.println("找到一条边路径！");
            System.out.println(edgePath);
            ArrayList<Integer> newNodePath = new ArrayList<>();
            ArrayList<Integer> newEdgePath = new ArrayList<>();
            newEdgePath.addAll(edgePath);
            newNodePath.addAll(nodePath);
            nodePaths.add(newNodePath); //点结果路径
            edgePaths.add(newEdgePath); //边结果路径
            System.out.println("查看结果点路径集合" + nodePaths);
            System.out.println("查看结果边路径集合" + edgePaths);

        }
        else{ //如果不是终点 就要进一步往下搜索
            for(int i=head[cur]; i!=0; i=edges[i].next){
                int v = edges[i].v;
                if(!vis[v]){ //如果这个点不在栈上 则以这个点为起点继续搜索
                    edgePath.add(edges[i].w);//这条边的权值（公交线路号）加入边路径
                    dfs(v , dst);//继续搜索
                    edgePath.remove(edgePath.size()-1); //边 退栈
                }
            }
        }
        nodePath.remove(nodePath.size()-1); // 返回时 弹出路径栈顶结点
        vis[cur] = false; //这个点已经退栈，标记为未访问过

    }

    //去除傻逼路径
    public void dealStupidPaths(){
        for(int i = 0; i< edgePaths.size(); i++){//遍历所有路径
            // 1 2 2 2 3 1 4
            //1 2 2 3 4 5
            boolean[] visit = new boolean[SIZE];
            ArrayList<Integer> cur = edgePaths.get(i);//取出当前路径
            System.out.println("当前检测的路径 = " + cur);
            int pre = -1;//记前一个边 为-1
            for(int x : cur){  //遍历所有的边
                if(x != pre){ //要换乘了，看一下将要换乘的线路是不是之前下来的
                    if(visit[x]){ //如果是之前下来的，说明这是傻逼路径
                        edgePaths.remove(i);
                        nodePaths.remove(i);
                        i--;
                        break;
                    }
                    visit[x] = true;
                    pre = x;
                }
            }
        }
        System.out.println("去除傻逼路径之后：");
        System.out.println("边路径集合 "+ edgePaths);
        System.out.println("点路径集合 " + nodePaths);
    }

    //中转站查询 返回的是Path的ArrayList
    //每一个Path都是一条路径 里面有起始栈  以及有序中转站集合
    public ArrayList<Path> findPathMiddle(ArrayList<Station>stations , ArrayList<Bus>buses , int u, int v){
        clear();//清空结果集合
        dfs(u , v); //计算所有路径 填充paths 边结果路径
        System.out.println("点路径的条数 ： " + nodePaths.size());
        System.out.println("边路径的条数" + edgePaths.size());
        System.out.println("点路径集合 ： " + nodePaths);
        System.out.println("边路径集合 ： " + edgePaths);

        Station start = stations.get(u-1);
        Station end = stations.get(v-1);
        System.out.println("start = " + start.station_ID + " end = " + end.station_ID);
        //处理paths
        //1. 去除傻逼路径
        dealStupidPaths();
        //2. 添加换乘信息
        //1111122223333
        ArrayList<Path> result = new ArrayList<Path>();
        if(edgePaths == null)  return  null;
        int cnt = 0;//遍历到那一条路径了
        for(ArrayList<Integer> edgePath : edgePaths){
            System.out.println("edgePath = " + edgePath);
            Path path = new Path(start , end );
            int pre = edgePath.get(0);//第一条边
            int startStation = u;//刚开始当前站肯定是出发点嘛
            int curStation = nodePaths.get(cnt).get(1);//当前走到的站  nodePath
            for(int i=1; i<edgePath.size(); i++){
                if(edgePath.get(i) == pre){
                    curStation = nodePaths.get(cnt).get(i+1);
                }
                else{
                    //表示有新的换乘站
                    System.out.println("新换乘站ID" + curStation);
                    path.addTrans(stations.get(curStation - 1));
                    startStation = curStation;//当前站变更
                    curStation = nodePaths.get(cnt).get(i+1);
                    pre = edgePath.get(i);
                }
            }
            cnt++;
            System.out.println("得到新转车路径---------------" + path);
            result.add(path);
        }
        //最后一个不算换乘啦，已经到啦
        return result;
    }
}

