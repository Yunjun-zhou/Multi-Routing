package ToolUtil;

import GraphUtil.AdjacencyList;
import GraphUtil.RoadNetwork;
import QueryUtil.Route;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomRoutes {
    public List<Route> routes ;

    public RandomRoutes() {
        routes =  new LinkedList<>();
    }

    public RandomRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route getRoutes(int pos) {
        return routes.get(pos);
    }
    public void add(Route route){
        this.routes.add(route);
    }
    public int size(){
        return routes.size();
    }

    public  List<Route> randomRoutes(int size, RoadNetwork roadNetwork){


        for(int index = 0;index< size ;index++){//循环随机产生路线

            Route route = new Route();

            // Initialize the List
            List<Integer> traveled = new ArrayList<Integer>();
            for(int i=0; i<roadNetwork.size(); i++){
                traveled.add(0);
            }

            int start = randomInt(roadNetwork.size()); //随机产生起点
            traveled.set(start, 1);//设置起始点为访问状态
            route.add_edge(start);
           // System.out.println("route start at:"+start);
            for(int i =0;i<30;i++){
                AdjacencyList adjacencyList_s = roadNetwork.get_adjacency_list(start);//获得起点邻接表
                int next = adjacencyList_s.get_link_node(randomInt(adjacencyList_s.size())).getLink_node_id();
               // System.out.println("route next at:"+next);
                int num = 0;
                boolean finish = false;
                while(traveled.get(next) == 1){
//                    System.out.print("next:"+"  "+next+" ");
//                    System.out.println("index:"+"  "+index);
                    next = adjacencyList_s.get_link_node(randomInt(adjacencyList_s.size())).getLink_node_id();
                    num++;
                    if(num >10){
                        finish = true;
                        break;
                    }
                }
                if(!finish){
                    traveled.set(next, 1);
                    route.add_edge(next);
                    start = next;
                }else{
                    break;
                }

            }
            this.routes.add(route);
           // System.out.println("one route had been added!");
        }
        return routes;
    }


    public static int randomInt(int bound){
        Random temp= new Random();
        return temp.nextInt(bound);
    }
}
