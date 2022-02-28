package GraphUtil.matrix;

import java.util.ArrayList;
import java.util.List;

public class DoubleMatrix {
    public List<DoubleVector> mat;

    public DoubleMatrix(int H, int W, double InitVal){
        mat = new ArrayList<DoubleVector>();
        for(int i=0; i<H; i++){
            mat.add(new DoubleVector(W, InitVal));
        }
    }

    public boolean set(int y_pos, int x_pos, double val){
        try {
            DoubleVector vec = mat.get(y_pos);
            vec.set(x_pos, val);
            mat.set(y_pos, vec);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public double get(int y_pos, int x_pos){
        return mat.get(y_pos).get(x_pos);
    }

    public List<Integer> size(){
        List<Integer> s = new ArrayList<Integer>();
        s.add(this.mat.size());
        if(this.mat.size() > 0) {
            s.add(this.mat.get(0).size());
        }else{
            s.add(0);
        }
        return s;
    }
}
