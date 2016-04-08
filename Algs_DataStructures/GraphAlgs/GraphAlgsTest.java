package Problems;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphAlgsTest {

    @Test
    public void runSimpleGraph() throws Exception {
        AdjList<Integer, Integer> g = new AdjList<Integer, Integer>();

        for (int i = 0; i <= 4; i++) {
            g.addVertex(i);
        }

        g.addDirEdge(0, 1);
        g.addDirEdge(0, 4);

        g.addDirEdge(1, 0);
        g.addDirEdge(1, 2);
        g.addDirEdge(1, 3);
        g.addDirEdge(1, 4);

        g.addDirEdge(2, 1);
        g.addDirEdge(2, 3);

        g.addDirEdge(3, 1);
        g.addDirEdge(3, 2);
        g.addDirEdge(3, 4);

        g.addDirEdge(4, 0);
        g.addDirEdge(4, 1);
        g.addDirEdge(4, 3);

        Algs<Integer, Integer> algs = new Algs<Integer, Integer>();
        algs.testBFS(g, 0);
        algs.testDFS(g);
    }

    @Test
    public void runClothingSort() throws Exception {
        AdjList<String, Integer> g = new AdjList();
        String[] clothes = {"socks", "undershorts", "pants", "shoes", "watch", "shirt", "belt", "tie", "jacket"};
        for (int i = 0; i < clothes.length; i++) {
            g.addVertex(clothes[i]);
        }

        g.addDirEdge("socks", "shoes");

        g.addDirEdge("undershorts", "pants");
        g.addDirEdge("undershorts", "shoes");

        g.addDirEdge("pants", "shoes");
        g.addDirEdge("pants", "belt");

        g.addDirEdge("shirt", "belt");
        g.addDirEdge("shirt", "tie");

        g.addDirEdge("belt", "jacket");

        g.addDirEdge("tie", "jacket");

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testTopSort(g);
    }

    @Test
    public void runDFSForest() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"a", "b", "c", "d", "e", "f", "g", "h"};

        for (int i = 0; i < nodes.length; i++)
            g.addVertex(nodes[i]);

        g.addDirEdge("a", "b");

        g.addDirEdge("b", "c");
        g.addDirEdge("b", "e");
        g.addDirEdge("b", "f");

        g.addDirEdge("c", "d");
        g.addDirEdge("c", "g");

        g.addDirEdge("d", "c");
        g.addDirEdge("d", "h");

        g.addDirEdge("e", "a");
        g.addDirEdge("e", "f");

        g.addDirEdge("f", "g");

        g.addDirEdge("g", "f");
        g.addDirEdge("g", "h");

        g.addDirEdge("h", "h");

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testDFS(g);
    }

    @Test
    public void runDisconnectedGraph() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"a", "b"};

        for (int i = 0; i < nodes.length; i++)
            g.addVertex(nodes[i]);

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testDFS(g);
    }

    @Test
    public void runDAGGraph() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"a", "b", "c", "d", "e", "f", "g", "h"};

        Arrays.asList(nodes).forEach(v -> g.addVertex(v));

        g.addDirEdge("a", "b");

        g.addDirEdge("b", "c");
        g.addDirEdge("b", "e");
        g.addDirEdge("b", "f");

        g.addDirEdge("c", "d");
        g.addDirEdge("c", "g");

        g.addDirEdge("d", "c");
        g.addDirEdge("d", "h");

        g.addDirEdge("e", "a");
        g.addDirEdge("e", "f");

        g.addDirEdge("f", "g");

        g.addDirEdge("g", "f");
        g.addDirEdge("g", "h");

        g.addDirEdge("h", "h");

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testSCC(g);
    }

    @Test
    public void runMSTKruskal() throws Exception {
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

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testMSTKruskal(g);
    }

    @Test
    public void runMSTPrim() throws Exception {
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

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testMSTPrim(g, "a");
    }

    @Test
    public void runBellman() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"s", "t", "x", "y", "z"};

        Arrays.asList(nodes).forEach(v -> g.addVertex(v));

        g.addDirEdge("s", "t", 6);
        g.addDirEdge("s", "y", 7);

        g.addDirEdge("t", "x", 5);
        g.addDirEdge("t", "y", 8);
        g.addDirEdge("t", "z", -4);

        g.addDirEdge("x", "t", -2);

        g.addDirEdge("y", "x", -3);
        g.addDirEdge("y", "z", 9);

        g.addDirEdge("z", "s", 2);
        g.addDirEdge("z", "x", 7);

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testBellman(g, "s");
    }

    @Test
    public void runDagShortest() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"r", "s", "t", "x", "y", "z"};

        Arrays.asList(nodes).forEach(v -> g.addVertex(v));

        g.addDirEdge("r", "s", 5);
        g.addDirEdge("r", "t", 3);

        g.addDirEdge("s", "t", 2);
        g.addDirEdge("s", "x", 6);

        g.addDirEdge("t", "x", 7);
        g.addDirEdge("t", "y", 4);
        g.addDirEdge("t", "z", 2);

        g.addDirEdge("x", "y", -1);
        g.addDirEdge("x", "z", 1);

        g.addDirEdge("y", "z", -2);

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testDagShortest(g, "s");

    }

    @Test
    public void runDijkstra() throws Exception {
        AdjList<String, Integer> g = new AdjList<String, Integer>();
        String[] nodes = {"s", "t", "x", "y", "z"};

        Arrays.asList(nodes).forEach(v -> g.addVertex(v));

        g.addDirEdge("s", "t", 10);
        g.addDirEdge("s", "y", 5);

        g.addDirEdge("t", "x", 1);
        g.addDirEdge("t", "y", 2);

        g.addDirEdge("x", "z", 4);

        g.addDirEdge("y", "t", 3);
        g.addDirEdge("y", "x", 9);
        g.addDirEdge("y", "z", 2);

        g.addDirEdge("z", "s", 7);
        g.addDirEdge("z", "x", 6);

        Algs<String, Integer> algs = new Algs<String, Integer>();
        algs.testDijkstra(g, "s");

    }

    public static class Algs<K, E> {
        public void testBFS(AdjList<K, E> g, K source) throws Exception {
            g.print();
            System.out.println("BFS");
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.BFS(g, source);
        }

        public void testDFS(AdjList<K, E> g) throws Exception {
            g.print();
            System.out.println("DFS");
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.DFS(g);
        }

        public void testTopSort(AdjList<K, E> g) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.TopologicalSort(g);
        }

        public void testMSTKruskal(AdjList<K, E> g) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.MSTKruskal(g);
        }

        public void testMSTPrim(AdjList<K, E> g, K s) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.MSTPrim(g, s);
        }

        public void testBellman(AdjList<K, E> g, K s) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.BellmanFord(g, s);
        }

        public void testDagShortest(AdjList<K, E> g, K s) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.dagShortestPath(g, s);
        }

        public void testDijkstra(AdjList<K, E> g, K s) {
            g.print();
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.dijkstra(g, s);
        }

        public void testSCC(AdjList<K, E> g) {
            GraphAlgs<K, E> ga = new GraphAlgs<K, E>();
            ga.DFS(g);
            g.print();
            AdjList gT = transpose(g);
            System.out.println();
            ga.DFSSCC(gT);
//
//            AdjList<K, E>  gT = transpose(g);
//
//            gT.vList.values().forEach(v -> {
//                v.sccFTime = v.fTime;
//            });
//
//            ga.DFSSCC(gT);
        }

        public AdjList<K, E> transpose(AdjList<K, E> g) {
            AdjList<K, E> gT = new AdjList<K, E>();

            g.vList.values().forEach(v -> gT.addVertex(v));

            g.eList.entrySet().forEach( e -> {
                K u = e.getKey();
                e.getValue().forEach( v -> {
                    gT.addDirEdge(v.key, u);
                });
            });
            return gT;
        }
    }
}