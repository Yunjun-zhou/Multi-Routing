package GraphUtil.matrix;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileMatrix {
    private String dirname;
    //    矩阵宽、高、最大条目数？
    private int H, W, piece;

    public FileMatrix(String dirname){
        this.dirname = dirname;
        try{
            Init_matrix();
        }catch (Exception ignore){
            System.out.println("Error when Init the matrix");
        }
    }

    // get the val in direct pos
    public double get(int y_pos, int x_pos){
        if(y_pos <0 || y_pos >= H || x_pos < 0 || x_pos >= W){
            return 0.0;
        }
        int current_file = y_pos / this.piece;
        int current_line_loc = y_pos % this.piece;

        String filename = dirname + Integer.toString(current_file) + ".txt";
        try {
            FileReader in = new FileReader(filename);
            LineNumberReader reader = new LineNumberReader(in);
            String s = "";
            for(int i=0; i<current_line_loc+1; i++){
                s = reader.readLine();
            }
            String[] temp = s.split(" ");
            return Double.parseDouble(temp[x_pos]);
        }catch (Exception ignore){
            System.out.printf("Error when reading the file %s%n", filename);
            return 0.0;
        }
    }

    // Init the matrix with the dirname
    public void Init_matrix() throws IOException {
        // read the config file and Init the parameters
        String config_filename = this.dirname + "config.txt";
        FileReader in = new FileReader(config_filename);
        LineNumberReader reader = new LineNumberReader(in);
        String line = reader.readLine();
        String[] temp = line.split(" ");
        this.H = Integer.parseInt(temp[0]);
        this.W = Integer.parseInt(temp[1]);
        this.piece = Integer.parseInt(temp[2]);
        reader.close();
        in.close();
    }
}
