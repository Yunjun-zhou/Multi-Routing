import ExpUtil.ParamConfig;
import QueryUtil.QueriesSet;
import ToolUtil.RandomRoutes;
import GraphUtil.RoadNetwork;
import QueryUtil.Query;
import QueryUtil.Route;

import java.util.Date;

public class WorkSpace {
    public static void main(String[] args) {
        test_timeCompute();

    }
    public static void create_init_file(){
        // generate the road network
        RoadNetwork roadNetWork = DataUtil.read_net_work_from_file(
                "./datasets/TG.cedge.txt","1970-01-01 08:00:00");
        System.out.println(String.format("%d graph nodes in this process", roadNetWork.size()));
        //System.out.println(String.format("%d geo nodes in this process", roadNetWork.size()));

        // adopt the dijkstra algorithm to get the min distance of two point
//        String filename = "./datasets/TG.shortest.matrix";
//        String dirname = "./datasets/TG.shortest.matrix.split/";

       /* int node_num = roadNetWork.geo_size();
        DataUtil.writeHead(node_num, roadNetWork.geo_size(), filename, true);

        for(int i=0; i<node_num; i++){
            List<Double> shortest = DFSTool.dijkstra(i, roadNetWork);
            DataUtil.save_one_line(shortest, filename, true);

            if(i % 10 == 0){
                System.out.println(String.format("%d shortest path was saved in %s", i+1, filename));
            }
        }

        System.out.println(String.format("All shortest path was saved in %s", filename));*/
    }

    public static void test_RoutesGenerate(){
        // generate the road network
        RoadNetwork roadNetWork = DataUtil.read_net_work_from_file(
                "./datasets/TG.cedge.txt","1970-01-01 08:00:00");
        System.out.println(String.format("%d graph nodes in this process", roadNetWork.size()));
        //System.out.println(String.format("%d geo nodes in this process", roadNetWork.geo_size()));

        // Init the queries set
        int RoutesNum = 1000;
        RandomRoutes routes = new RandomRoutes();
        routes.randomRoutes(RoutesNum,roadNetWork);
        System.out.println(String.format("%d routes was generated",RoutesNum));
        System.out.println(String.format("%d routes total",routes.size()));
        for(Route route:routes.routes){
            System.out.println(route);
        }

    }

    public static void test_timeCompute(){
        // generate the road network
        RoadNetwork roadNetWork = DataUtil.read_net_work_from_file(
                "./datasets/TG.Timeedge.txt","1970-01-01 08:00:00");
        System.out.println(String.format("%d graph nodes in this process", roadNetWork.size()));
        //System.out.println(String.format("%d geo nodes in this process", roadNetWork.geo_size()));

        // Init the route set
        int RoutesNum = 1000;
        RandomRoutes routes = new RandomRoutes();
        routes.randomRoutes(RoutesNum,roadNetWork);
        System.out.println("单路径信息：");
        for(Route route:routes.routes){
            System.out.println(route);
        }

        //Init the query set
        QueriesSet queriesSet = new QueriesSet();
        for(Route route:routes.routes){
            int source = route.get_one_vertex(0);
            int destination = route.get_one_vertex(-1);
            Query query = new Query(source,destination,0,route);
            queriesSet.add(query);
        }
        System.out.println(String.format("%d queries was initialized", RoutesNum));

        // Init the ParamConfig
        ParamConfig config = new ParamConfig(RoutesNum, 0.02, 0.02, 0.15, 20, 2, true, 100);

        int time_1 = (int)(new Date().getTime());
        QueriesSet timeResult = MainAlgorithm.GetArriveTime(roadNetWork, queriesSet, config);
//        for(int i =0;i<timeResult.size();i++){
//            System.out.println(timeResult.get(i).getCurrTime());
//        }
        int time_2 = (int)(new Date().getTime());

        System.out.println(String.format("cost time: %d", time_2-time_1));


    }
}
