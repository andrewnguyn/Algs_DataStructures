package Problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DisjointSetTest {

    @Test
    public void testDisjointSetLL() throws Exception {
        String[] vList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        ArrayList<String[]> eList = new ArrayList<String[]>();
        eList.add(new String[]{"b", "d"});
        eList.add(new String[]{"e", "g"});
        eList.add(new String[]{"a", "c"});
        eList.add(new String[]{"h", "i"});
        eList.add(new String[]{"a", "b"});
        eList.add(new String[]{"e", "f"});
        eList.add(new String[]{"b", "c"});

        //create graph
        AdjList<String, Integer> g = new AdjList<>();
        for (int i = 0; i < vList.length; i++) {
            g.addVertex(vList[i]);
        }

        eList.forEach( e -> g.addUndirectedEdge(e[0], e[1]));

        //create disjoint set
        DisjointSetLL<String, Integer> ds = new DisjointSetLL<>();

        //make set per v
        g.vList.keySet().forEach( v -> ds.makeSet(v));

        System.out.println("BEFORE:");
        ds.print();

        g.eList.forEach( (u, edges) -> {
            edges.forEach( v -> {
                if (!ds.sameComp(u, v.key))
                    ds.union(u, v.key);
            });
        });

        System.out.println("AFTER:");
        ds.print();

    }

    @Test
    public void testDisjointSetForest() throws Exception {
        String[] vList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        ArrayList<String[]> eList = new ArrayList<String[]>();
        eList.add(new String[]{"b", "d"});
        eList.add(new String[]{"e", "g"});
        eList.add(new String[]{"a", "c"});
        eList.add(new String[]{"h", "i"});
        eList.add(new String[]{"a", "b"});
        eList.add(new String[]{"e", "f"});
        eList.add(new String[]{"b", "c"});

        //create graph
        AdjList<String, Integer> g = new AdjList<>();
        for (int i = 0; i < vList.length; i++) {
            g.addVertex(vList[i]);
        }

        eList.forEach( e -> g.addUndirectedEdge(e[0], e[1]));

        //create disjoint set
        DisjointSetForest<String, Integer> ds = new DisjointSetForest<>();

        //make set per v
        g.vList.keySet().forEach( v -> ds.makeSet(v));

        System.out.println("BEFORE:");
        ds.print();

        g.eList.forEach( (u, edges) -> {
            edges.forEach( v -> {
                if (!ds.sameComp(u, v.key))
                    ds.union(u, v.key);
            });
        });

        System.out.println("AFTER:");
        ds.print();

    }

    @Test
    public void testDisjointSetForest2() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};

        Arrays.asList(nodes).forEach(v -> g.addVertex(v));

        g.addUndirectedEdge("a", "b", 4);
        g.addUndirectedEdge("a", "h", 8);

        g.addUndirectedEdge("b", "c", 8);
        g.addUndirectedEdge("b", "h", 11);

        g.addUndirectedEdge("c", "d", 7);
        g.addUndirectedEdge("c", "f", 4);
        g.addUndirectedEdge("c", "i", 2);

        g.addUndirectedEdge("d", "e", 9);
        g.addUndirectedEdge("d", "f", 14);

        g.addUndirectedEdge("e", "f", 10);

        g.addUndirectedEdge("f", "g", 2);

        g.addUndirectedEdge("g", "h", 1);
        g.addUndirectedEdge("g", "i", 6);

        g.addUndirectedEdge("h", "i", 7);

        //create disjoint set
        DisjointSetForest<String, Integer> ds = new DisjointSetForest<>();

        //make set per v
        g.vList.keySet().forEach( v -> ds.makeSet(v));

        System.out.println("BEFORE:");
        ds.print();

        g.eList.forEach( (u, edges) -> {
            edges.forEach( v -> {
                if (!ds.sameComp(u, v.key))
                    ds.union(u, v.key);
            });
        });

        System.out.println("AFTER:");
        ds.print();
    }



}