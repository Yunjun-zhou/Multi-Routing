import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TimeFlowRandomUtil {
    private int maxTime;
    private String dirname;

    public TimeFlowRandomUtil(int maxTime,String dirname) {
        this.maxTime = maxTime;
        this.dirname = dirname;
    }

    public TimeFlowRandomUtil() {
        this.maxTime = 100;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void RandomTimeFlow() throws IOException {

        // read the config file and Init the parameters
        String flow_filename = this.dirname + "TG.cedge.txt";
        String Timeflow_filename = this.dirname + "TG.Timeedge.txt";
//        定义读写流
        BufferedReader in = new BufferedReader(new FileReader(flow_filename));
        BufferedWriter out = new BufferedWriter(new FileWriter(Timeflow_filename)) ;
        LineNumberReader reader = new LineNumberReader(in);
        //开始读写
        String str = null;
        while((str = in.readLine())!= null){
            StringBuffer newbuff = new StringBuffer(str);
            newbuff.append(" ");
            ArrayList<Double> flows = randomTimeFlow();
            for(Double i: flows){
                newbuff.append(i.toString());
                newbuff.append(" ");
            }
            newbuff.append("\n");
            out.write(newbuff.toString());
            out.flush();
        }
        in.close();
        out.close();

    }

    public ArrayList<Double> randomTimeFlow(){
        ArrayList<Double> res = new ArrayList<>(100);
        Random random = new Random();
        for(int i = 0;i<maxTime;i++){
            res.add(random.nextDouble()*100+1.2);
        }
        return res;
    }

//    public static void main(String[] args) throws IOException {
//        TimeFlowRandomUtil ti = new TimeFlowRandomUtil(100,"./datasets/");
//        ti.RandomTimeFlow();
//
//    }
}
