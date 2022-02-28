package GraphUtil;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
    private List<LinkNode> m_linknodes;//邻接点的链表集合
    private int m_current_node;//当前节点

    public AdjacencyList(int current_node){
        this.m_current_node = current_node;
        this.m_linknodes = new ArrayList<LinkNode>();
    }

    // insert link node to the list
    public boolean add_node_to_list(LinkNode node){
        try {
            m_linknodes.add(node);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public int size(){
        return m_linknodes.size();
    }

    // get one link node by id
    public LinkNode get_nodeById(int id){
        for(LinkNode lNode:m_linknodes){
            if(lNode.getLink_node_id() == id){
                return lNode;
            }
        }
        return null;
    }
    //get one link node by position
    public LinkNode get_link_node(int pos){
        return this.m_linknodes.get(pos);
    }

    // get and set current_node
    public void setM_current_node(int node){
        this.m_current_node = node;
    }

    public int getM_current_node() {
        return m_current_node;
    }
}
