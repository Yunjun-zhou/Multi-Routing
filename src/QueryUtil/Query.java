package QueryUtil;

public class Query {
    private int source;//起点
    private int destination;//终点
    private long departure_time;//出发时间
    private Route route;//路径
    private int curr_index;//在哪条边上
    private long currTime;//当前时间

    public Query() {
    }

    public Query(int source, int destination, long departure_time, Route route){
        this.source = source;
        this.departure_time = departure_time;
        this.currTime = departure_time;
        this.destination = destination;
        this.route = route;
        this.curr_index = 0;
    }

    public int getSource(){
        return this.source;
    }

    public void setSource(int source){
        this.source = source;
    }

    public int getDestination(){
        return this.destination;
    }

    public void setDestination(int destination){
        this.destination = destination;
    }

    public long getDeparture_time(){
        return this.departure_time;
    }

    public void setDeparture_time(long departure_time){
        this.departure_time = departure_time;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }

    public int getCurr_index() {
        return curr_index;
    }

    public void setCurr_index(int curr_index) {
        this.curr_index = curr_index;
    }
    public int Complete_state(){
        if(this.curr_index == this.route.pi_a_v.size()-1){
            return 2;//到达
        }else if(this.curr_index == 0){
            return 0;//出发
        }
        else{
            return 1;//中间
        }
    }
}
