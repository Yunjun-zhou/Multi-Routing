package GraphUtil.matrix;

import java.util.ArrayList;
import java.util.List;

// Double vector to save the Shortest Path of one node
public class DoubleVector {
    private List<Double> vec;

    public DoubleVector(int size, double init_val){
        this.vec = new ArrayList<Double>();
        for(int i=0; i<size; i++){
            this.vec.add(init_val);
        }
    }

    public boolean set(int pos, double val){
        try{
            vec.set(pos, val);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public double get(int pos){
        return vec.get(pos);
    }

    public int size(){
        return this.vec.size();
    }
}
