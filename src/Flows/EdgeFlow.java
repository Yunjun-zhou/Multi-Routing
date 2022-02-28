package Flows;

import ExpUtil.ParamConfig;

import java.util.ArrayList;

public class EdgeFlow {
    //存储影响通行速度的属性
    private int count_car;//车辆数，暂时只选择这个属性
    private ArrayList<Double> min_time;//最短花费时间



    public EdgeFlow(ArrayList<Double> min_times) {
        this.count_car = 0;
        min_time = new ArrayList<>();
        this.min_time.addAll(min_times);
    }

    public EdgeFlow(int count_car,ArrayList<Double> min_times) {
        this.count_car = count_car;
        min_time = new ArrayList<>();
        this.min_time.addAll(min_times);
    }

    public EdgeFlow() {
        this.count_car = 0;
        min_time = new ArrayList<>();
    }

    public int getCount_car() {
        return count_car;
    }

    public void setCount_car(int count_car) {
        this.count_car = count_car;
    }

    // get the result of the time flow function
    public double cross_time(ParamConfig config,int time){//计算通行时间
        double alpha = config.getAlpha();
        int phi = config.getPhi();
        double sigma = config.getSigma();
        int beta = config.getBeta();
        boolean mode = config.getTimeFlow_mode();
        int time_interval = config.getTime_interval();
        if(!mode){
            return min_time.get(time >10000?101: time /time_interval) * (1 + alpha * count_car);
        }else{
            return min_time.get(time >10000?101: time /time_interval) * (1 + sigma * Math.pow(count_car/phi, beta));
        }
    }

}
