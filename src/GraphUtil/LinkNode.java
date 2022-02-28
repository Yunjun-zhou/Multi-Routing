package GraphUtil;

import ExpUtil.ParamConfig;
import Flows.EdgeFlow;

import java.util.ArrayList;

public class LinkNode {
    private int link_node_id;
    private double edge_travel_time;//权重
    private EdgeFlow edgeFlow;


    // Two construction function to suit different situation
    public LinkNode(int link_node_id, ArrayList<Double> edge_travel_time){
        this.link_node_id = link_node_id;
        this.edgeFlow = new EdgeFlow(edge_travel_time);
        this.edge_travel_time = edge_travel_time.get(0);
    }

    //根据流量实现函数构造
    public LinkNode(int link_node_id, EdgeFlow edgeFlow,ParamConfig paramConfig,int time) {
        this.link_node_id = link_node_id;
        this.edgeFlow = edgeFlow;
        this.edge_travel_time = edgeFlow.cross_time(paramConfig,time);

    }

    public LinkNode(){
        link_node_id = -1;
        edge_travel_time = -1.0;
        edgeFlow = new EdgeFlow();
    }

    // set function and get function
    public void setLink_node_id(int link_node_id){
        this.link_node_id = link_node_id;
    }

    public void setEdge_travel_time(Double edge_travel_time){
        this.edge_travel_time = edge_travel_time;
    }

    public int getLink_node_id(){
        return this.link_node_id;
    }

    public double getEdge_travel_time(){
        return this.edge_travel_time;
    }

    public EdgeFlow getEdgeFlow() {
        return edgeFlow;
    }

    public void setEdgeFlow(int n,ParamConfig paramConfig,int time) {
        this.edgeFlow.setCount_car(this.edgeFlow.getCount_car()+n);
        this.edge_travel_time = edgeFlow.cross_time(paramConfig,time);
    }

}
