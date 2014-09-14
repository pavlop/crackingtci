package leetcode;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return node;
        //travese
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> oldToNew = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        LinkedList<UndirectedGraphNode> toVisit = new LinkedList<UndirectedGraphNode>();

        visited.add(node);
        toVisit.addAll(0, getNotVisited(node.neighbors, visited));
        UndirectedGraphNode newHead = cloneNodeNoLinks(node);
        oldToNew.put(node, newHead);

        while (toVisit.size() > 0) {
            UndirectedGraphNode cur = toVisit.removeLast();
            toVisit.addAll(0, getNotVisited(cur.neighbors, visited));

            visited.add(cur);
            oldToNew.put(cur, cloneNodeNoLinks(cur));
        }

        for (UndirectedGraphNode cur : visited) {
            List<UndirectedGraphNode> newneighbors = new ArrayList<UndirectedGraphNode>();

            for (UndirectedGraphNode neighbor : cur.neighbors) {
                newneighbors.add(oldToNew.get(neighbor));
            }
            oldToNew.get(cur).neighbors = newneighbors;
        }

        return newHead;
    }

    public List<UndirectedGraphNode>  getNotVisited( List<UndirectedGraphNode> neighbors, Set<UndirectedGraphNode> visited) {
        List<UndirectedGraphNode>  notVisited = new ArrayList<UndirectedGraphNode>();
        for(UndirectedGraphNode node:neighbors) {
            if(!visited.contains(node)) notVisited.add(node);
        }
        return notVisited;
    }

    UndirectedGraphNode cloneNodeNoLinks(UndirectedGraphNode node) {
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        return newNode;
    }


    @Test
    public void test(){
        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        node0.neighbors.add(node0);
        node0.neighbors.add(node0);

        Assert.assertEquals(2, cloneGraph(node0).neighbors.size());
        Math.pow(0,0);
    }

}

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};