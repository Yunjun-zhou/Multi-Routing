import ExpUtil.ParamConfig;
import GraphUtil.RoadNetwork;
import QueryUtil.QueriesSet;
import QueryUtil.Query;
import ToolUtil.TimeConvert;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainAlgorithm {

    static Comparator<Query> cmp = new Comparator<Query>() {
        @Override
        public int compare(Query o1, Query o2) {
            if(o1.getCurrTime() > o2.getCurrTime()){
                return 1;
            }else{
                return -1;
            }
        }
    };

    public static QueriesSet GetArriveTime1(RoadNetwork G, QueriesSet queriesSet, ParamConfig config){
        Queue<Query> H = new PriorityQueue<>(cmp);
//        System.out.println("tm matrix is initialized");

        //初始化路网信息，把查询的流量加入路网中
        for(int i = 0;i<queriesSet.size();i++){
            //流量更新
            int startNode = queriesSet.get(i).getSource();
            int nextNode = queriesSet.get(i).getRoute().pi_a_v.get(1);
            G.UpdateFlow(startNode,nextNode,config,true,queriesSet.get(i).getCurrTime());//更新流量
            H.add(queriesSet.get(i));
        }

        while (!H.isEmpty()){
            Query curr_query = H.poll();//获取出发时间最早的查询

            //获取当前出发点和终到点
            int startNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index());
            int nextNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index()+1);

            //获取花费时间
            long timeCost = (long) (G.get_adjacency_list(startNode).get_nodeById(nextNode).getEdge_travel_time()+0.5);
            curr_query.setCurrTime(curr_query.getCurrTime()+timeCost);

            //更新下一条边的流量
            curr_query.setCurr_index(curr_query.getCurr_index()+1);
            if(curr_query.Complete_state() == 1){
                //还未到终点
                int lastNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index()+1);
                G.UpdateFlow(startNode,nextNode,lastNode,config,curr_query.getCurrTime());//更新流量
                H.add(curr_query);//将下一个请求插入队列
            }else{//到达了终点
                G.UpdateFlow(startNode,nextNode,config,false,curr_query.getCurrTime());//更新流量
                TimeOutput(curr_query);//输出查询的到达时间
            }
        }
        return queriesSet;
    }

    public static QueriesSet GetArriveTime(RoadNetwork G, QueriesSet queriesSet, ParamConfig config){
        Queue<Query> H = new PriorityQueue<>(cmp);
        //初始化路网信息，把查询加入优先队列中
        for(int i = 0;i<queriesSet.size();i++){
            //将初始查询加入开始的优先队列实现排序,双优先队列实现流量初始化以及中间并行
            H.add(queriesSet.get(i));
        }

        while (!H.isEmpty() ){
            Query curr_query = H.poll();//存储最早出发的查询

            //获取当前出发点和下一点以及前点
            int startNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index());
            int nextNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index()+1);
            int forNode = curr_query.getRoute().get_one_vertex(curr_query.getCurr_index()-1);

            //更新流量
            G.UpdateFlow(forNode,startNode,nextNode,config,curr_query.Complete_state(),curr_query.getCurrTime());

            if(curr_query.Complete_state() != 2){//未到达终点
                //获取花费时间
                long timeCost = (long) (G.get_adjacency_list(startNode).get_nodeById(nextNode).getEdge_travel_time()+0.5);
//            更新当前时间及位置索引
                curr_query.setCurrTime(curr_query.getCurrTime()+timeCost);
                curr_query.setCurr_index(curr_query.getCurr_index()+1);
                H.add(curr_query);//将下一个请求插入队列
            }else{//到达了终点
                TimeOutput(curr_query);//输出查询的到达时间
            }

                    }
        return queriesSet;
    }

    private static void TimeOutput(Query curr_query) {
        System.out.println("起点为"+curr_query.getSource()+",终点为"+curr_query.getDestination()
        +"的查询已经完成！");
        System.out.println("其出发时间为:"+ TimeConvert.getDateToString((long) curr_query.getDeparture_time()) +
                ",到达时间为："+TimeConvert.getDateToString((long) curr_query.getCurrTime())+"\n\n");

    }
}
