package ExpUtil;

/*
    class used for parameters merging
    流量参数设置
 */
public class ParamConfig {
    private int QueryNumber;//查询数量？
    private double refine;//精炼？
    private double alpha;//阈值？
    private double sigma;//阈值？
    private int phi;//
    private int beta;//
    private boolean time_flow_mode;//
    private  int time_interval;//路网更新时间间隔

    public ParamConfig(int QueryNumber, double refine, double alpha, double sigma, int phi, int beta, boolean time_flow_mode, int time_interval){
        this.QueryNumber = QueryNumber;
        this.refine = refine;
        this.alpha = alpha;
        this.sigma = sigma;
        this.phi = phi;
        this.beta = beta;
        this.time_flow_mode = time_flow_mode;
        this.time_interval = time_interval;
    }

    public void setQueryNumber(int QueryNumber){
        this.QueryNumber = QueryNumber;
    }

    public int getQueryNumber(){
        return this.QueryNumber;
    }

    public void setRefine(double refine){
        this.refine = refine;
    }

    public double getRefine(){
        return this.refine;
    }

    public void setAlpha(double alpha){
        this.alpha = alpha;
    }

    public double getAlpha(){
        return this.alpha;
    }

    public void setSigma(double sigma){
        this.sigma = sigma;
    }

    public double getSigma(){
        return sigma;
    }

    public void setPhi(int phi){
        this.phi = phi;
    }

    public int getPhi(){
        return phi;
    }

    public void setBeta(int beta){
        this.beta = beta;
    }

    public int getBeta(){
        return this.beta;
    }

    public void setTime_flow_mode(boolean time_flow_mode){
        this.time_flow_mode = time_flow_mode;
    }

    public boolean getTimeFlow_mode(){
        return this.time_flow_mode;
    }

    public int getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(int time_interval) {
        this.time_interval = time_interval;
    }
}
