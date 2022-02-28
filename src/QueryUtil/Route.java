package QueryUtil;

import java.util.ArrayList;
import java.util.List;

public class Route implements Cloneable {
    public final List<Integer> pi_a_v;   // The up-to-date route from s to v_a [vertex]

    public Route() {
        this.pi_a_v = new ArrayList<Integer>();
    }


    public Route(Route n_route){
        this.pi_a_v = new ArrayList<Integer>();
        for(int i=0; i<n_route.size(); i++){
            this.pi_a_v.add(n_route.get_one_vertex(i));
        }
    }

    public int get_one_vertex(int pos){
        return pi_a_v.get((pi_a_v.size() +pos) % pi_a_v.size());
    }

    public void add_edge(int node){
        this.pi_a_v.add(node);
    }

    public List<Integer> get_vertex(){
        return this.pi_a_v;
    }

    public int size(){
        return pi_a_v.size();
    }

//    public boolean isComplete(){
//        if(this.index == this.pi_a_v.size()-1){
//            return true;
//        }else{
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return "Route{" +
                "pi_a_v=" + pi_a_v +
                '}';
    }
}
