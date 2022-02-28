package GraphUtil;

import ExpUtil.ParamConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    The data structure of the road network, in order to decrease the usage of the memory, using Adjacency list
 */

public class RoadNetwork {
    // adopt the hashmap to get the Edge faster
    private long earlist_time;//路网通行的最早时间
    private List<Integer> nodes;
    private HashMap<Integer, AdjacencyList> road_net_work;
    //private HashMap<Integer, GeoPoint> geo_points;

    public  RoadNetwork(){
        this.nodes = new ArrayList<Integer>();
        this.road_net_work = new HashMap<Integer, AdjacencyList>();
        this.earlist_time = 0;
       // this.geo_points = new HashMap<Integer, GeoPoint>();
    }

    public  RoadNetwork(long earlist_time){
        this.nodes = new ArrayList<Integer>();
        this.road_net_work = new HashMap<Integer, AdjacencyList>();
        this.earlist_time = earlist_time;
        // this.geo_points = new HashMap<Integer, GeoPoint>();
    }

    public int size(){
        return nodes.size();
    }
   // public int geo_size() {return geo_points.keySet().size();};


    public List<Integer> getNodes() {
        return nodes;
    }

    public long getearlist_time() {
        return earlist_time;
    }

    public void setearlist_time(long earlist_time) {
        earlist_time = earlist_time;
    }

    // add item to the road network
    public boolean add_list_node(Integer s, Integer d, ArrayList<Double> w){
        try {
            LinkNode node = new LinkNode(d, w);
            AdjacencyList list;
            if(road_net_work.containsKey(s)){
                list = road_net_work.get(s);
            }else{
                list = new AdjacencyList(s);
                nodes.add(s);
            }
            list.add_node_to_list(node);
            road_net_work.put(s, list);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // get AdjacencyList of the node
    public AdjacencyList get_adjacency_list(int id){
        return this.road_net_work.get(id);
    }

    // remove item from road network
    public boolean remove_Item(int pos){
        try {
            this.road_net_work.remove(this.nodes.get(pos));
            this.nodes.remove(pos);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

   /*
   add one geo point
    public boolean add_geo_item(Double x, Double y, int id){
        try{
            GeoPoint point = new GeoPoint(id, x, y);
            this.geo_points.put(id, point);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
     add one geo point
    public boolean add_geo_item(int id){
        try{
            GeoPoint point = new GeoPoint(id);
            this.geo_points.put(id, point);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

     get a geo point
    public GeoPoint get_geo_point(int id){
        return this.geo_points.get(id);
    }
    */


    // adopt the Query information into the traffic flow
//    更新点位于中间点
    public void UpdateFlow(int former, int start, int next, ParamConfig paramConfig,long time){
        int tindex = (int)(time - this.earlist_time);
        this.road_net_work.get(former).get_nodeById(start).setEdgeFlow(-1,paramConfig,tindex);//前段边流量减一
        this.road_net_work.get(start).get_nodeById(next).setEdgeFlow(1,paramConfig,tindex);//首段边流量加一
    }

    //更新点位于起点或者终点
    //start表示起点，next表示下一个点，flag表示起点或者终点
    public void UpdateFlow(int start, int next, ParamConfig paramConfig,boolean flag,long time){
        int tindex = (int)(time - this.earlist_time);
        if(flag){
            this.road_net_work.get(start).get_nodeById(next).setEdgeFlow(1,paramConfig,tindex);//首段边流量加一
        }else{
            this.road_net_work.get(start).get_nodeById(next).setEdgeFlow(-1,paramConfig,tindex);//末段边流量加一
        }
    }

    //更新流量，三种情况（开始，中间，结束）
    public void UpdateFlow(int former,int start, int next, ParamConfig paramConfig,int mode,long time){
        int tindex = (int)(time - this.earlist_time);
        if(mode == 0){//起点流量更新，初始化路网流量信息
            this.road_net_work.get(start).get_nodeById(next).setEdgeFlow(1,paramConfig,tindex);//首段边流量加一
        }else if(mode == 1){
            this.road_net_work.get(former).get_nodeById(start).setEdgeFlow(-1,paramConfig,tindex);//前段边流量减一
            this.road_net_work.get(start).get_nodeById(next).setEdgeFlow(1,paramConfig,tindex);//首段边流量加一
        }else if(mode == 2){
            this.road_net_work.get(former).get_nodeById(start).setEdgeFlow(-1,paramConfig,tindex);//末段边流量减一
        }
    }




}
