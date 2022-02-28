import GraphUtil.RoadNetwork;
import QueryUtil.QueriesSet;
import QueryUtil.Query;
import QueryUtil.Route;
import ToolUtil.TimeConvert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DataUtil {
    public static RoadNetwork read_net_work_from_file(String edg_file,String earlisttime){
        RoadNetwork roadNetWork = new RoadNetwork(TimeConvert.getStringToDate(earlisttime));
        int current_line = 0;
        // read file
        try {
            BufferedReader in = new BufferedReader(new FileReader(edg_file));
            //BufferedReader in_geo = new BufferedReader(new FileReader(node_file));

            String line;
            while((line = in.readLine()) != null) {
                String[] temp = line.split(" ");
               // System.out.println("边权长度："+temp.length);
                int a = Integer.parseInt(temp[1]);
                int b = Integer.parseInt(temp[2]);
                ArrayList<Double> w = new ArrayList<>();
                for(int i = 3;i<temp.length;i++){
                    w.add(Double.parseDouble(temp[i]));
                }

                // add item from one reading line
                roadNetWork.add_list_node(a, b, w);
                roadNetWork.add_list_node(b, a, w);
                current_line += 1;

                if(current_line % 10000 == 0){//限制边的容量
                    System.out.println(String.format("%d edge records have been visited", current_line));
                }
            }

//            current_line = 0;
//
//            while ((line = in_geo.readLine())!=null){
//                String[] temp = line.split(" ");
//                int id = Integer.parseInt(temp[0]);
//                Double x = Double.parseDouble(temp[1]);
//                Double y = Double.parseDouble(temp[2]);
//
//                // add geo item
//                roadNetWork.add_geo_item(x, y, id);
//                current_line += 1;
//
//                if(current_line % 10000 == 0){
//                    System.out.println(String.format("%d geo records have been visited", current_line));
//                }
//            }
            in.close();
           // in_geo.close();

        }catch (IOException ex){
            System.out.println(String.format("Error in reading"));
        }
        System.out.println("The road file was read");
        return roadNetWork;
    }
    public static QueriesSet Queries_from_file(String queries_file){
        QueriesSet queriesSet = new QueriesSet();
        int current_line = 0;
        // read file
        try {
            BufferedReader queries = new BufferedReader(new FileReader(queries_file));

            String line;
            while ((line = queries.readLine()) != null) {

                String[] temp = line.split(" ");
                // System.out.println("边权长度："+temp.length);
                int sour = Integer.parseInt(temp[0]);
                int dest = Integer.parseInt(temp[1]);
                long time = TimeConvert.getStringToDate(temp[2]);
                Route w = new Route();
                for (int i = 3; i < temp.length; i++) {
                    w.add_edge(Integer.parseInt(temp[i]));
                }

                // add item from one reading line
                Query q = new Query(sour,dest,time,w);
                queriesSet.add(q);
                current_line += 1;

                if (current_line % 10000 == 0) {//限制查询的容量
                    System.out.println(String.format("%d edge records have been visited", current_line));
                }
            }
        }catch (IOException ex){
            System.out.println(String.format("Error in reading"));
        }
        System.out.println("The Query file was read");
        return queriesSet;
    }

    public static boolean save_one_line(List<Double> line_data, String filename, boolean mode){
        try{
            String line = "";
            for(int i=0; i<line_data.size(); i++){
                String current = String.format("%.6f", line_data.get(i));
                line = line + current + " ";
            }
            line = line + "\r\n";

            File file = new File(filename);

            // true = append file
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), mode);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);

            bufferedWriter.close();

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static boolean writeHead(int H, int W, String filename, boolean mode){
        try{
            String str = Integer.toString(H) + " " + Integer.toString(W) + "\r\n";
            File file = new File(filename);
            if (file.exists()) {
                boolean del = file.delete();
            }
            final boolean newFile = file.createNewFile();
            // true = append file
            FileWriter fileWriter = new FileWriter(filename, mode);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str);

            bufferedWriter.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static void split_file(String filename, int piece, String save_path){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String head = in.readLine();
            String[] heads = head.split(" ");
            int H = Integer.parseInt(heads[0]);
            int W = Integer.parseInt(heads[1]);
            in.close();

            // write the head to the file
            String nfile = save_path + "config.txt";
            FileWriter fileWriter = new FileWriter(nfile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String the_head = Integer.toString(H) + " " + Integer.toString(W) + " " + Integer.toString(piece);
            bufferedWriter.write(the_head);
            bufferedWriter.close();

            // Read the data from origin shortest path file
            FileReader nin = new FileReader(filename);
            LineNumberReader reader = new LineNumberReader(nin);
            String s = reader.readLine();

            int cap = piece;
            int current_num = 0;

            nfile = save_path + Integer.toString(current_num)+".txt";
            fileWriter = new FileWriter(nfile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            while(H > 0){
                // write the line read to the file
                s = reader.readLine() + "\r\n";
                bufferedWriter.write(s);

                cap --;
                if(cap == 0){
                    // add the current_num
                    current_num += 1;

                    // create new file and turn the using file
                    bufferedWriter.close();
                    nfile = save_path + Integer.toString(current_num)+".txt";
                    fileWriter = new FileWriter(nfile, true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    cap = piece;

                    System.out.println(String.format("%s was saved", nfile));
                }

                H --;
            }

            bufferedWriter.close();
            reader.close();
            nin.close();

        }catch (Exception ignored){

        }
    }
}
