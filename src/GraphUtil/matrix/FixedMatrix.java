package GraphUtil.matrix;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FixedMatrix {
    private double[][] matrix;
    private int size;

    public FixedMatrix(int size){
        matrix = new double[size][size];
        this.size = size;
    }

    public double get(int y_pos, int x_pos){
        return matrix[y_pos][x_pos];
    }

    public void InitMatrix(String dirname){
        try{
            FileReader in = new FileReader(dirname);
            LineNumberReader reader = new LineNumberReader(in);
            reader.readLine();
            for(int i=0; i<this.size; i++){
                String current_line = reader.readLine();
                String[] temp = current_line.split(" ");
                for(int j=0; j<temp.length; j++){
                    double val = Double.parseDouble(temp[j]);
                    this.matrix[i][j] = val;
                }
                if(i%1000 == 0){
                    System.out.println(String.format("%d line was read to the tm matrix", i));
                }
            }
        }catch (IOException ex){
            System.out.println("Error happened when we read the file");
        }
    }

    public void setMatrix(int pos_y, int pos_x, double val){
        matrix[pos_y][pos_x] = val;
    }
}
