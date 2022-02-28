package QueryUtil;

import GraphUtil.matrix.FixedMatrix;
import QueryUtil.Query;

import java.util.ArrayList;
import java.util.List;

public class QueriesSet {
    private final List<Query> queries;

    public QueriesSet(){
        this.queries = new ArrayList<Query>();
    }

    public int size(){
        return queries.size();
    }

    public void add(Query query){
        this.queries.add(query);
    }

    public Query get(int pos){
        return queries.get(pos);
    }

    public void del(int pos){
        queries.remove(pos);
    }

    public void change(int pos, int s, int d, int T){//改变查询的信息（起点，终点，出发时间）
        Query query = this.queries.get(pos);
        query.setSource(s);
        query.setDestination(d);
        query.setDeparture_time(T);
    }

//    public void RandomQueries(int size, int num){//随机产生num个查询请求
//        for(int i=0; i<num; i++){
//            int s = (int) (Math.random() * (size-1));
//            int d = (int) (Math.random() * (size-1));
//            if(s == d){
//                d = (d + 1) % num;//(size-1)
//            }
//            Query query = new Query(s, d, 0);
//            query.setQuery_id(i);
//            queries.add(query);
//        }
//    }

    public double GT_star(FixedMatrix tm){//?????
        double ret = 0;
        for(Query query: queries){
            ret += tm.get(query.getSource(), query.getDestination());
        }
        return ret;
    }
}
