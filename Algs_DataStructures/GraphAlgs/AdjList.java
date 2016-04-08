package Problems;

import java.util.*;

public class AdjList<K, E> {
    HashMap<K, Vertex<K, E>> vList = new HashMap<>();
    LinkedHashMap<K, LinkedList<Vertex<K, E>>> eList = new LinkedHashMap<>();
    HashMap<K, LinkedHashMap<Vertex<K, E>, Integer>> eWeights = new HashMap<>();

    ArrayList<Edge<K, Integer>> eListWeights = new ArrayList<>();

    ArrayList<EdgeMap<K>> edges = new ArrayList<>();
    HashMap<EdgeMap<K>, Integer> weights = new HashMap<>();

    protected static class EdgeMap<K> {
        K u;
        K v;

        EdgeMap(K u, K v) {
            this.u = u;
            this.v = v;
        }
    }

    protected static class Edge<K, Integer> {
        K u;
        K v;
        Integer weight;

        Edge(K u, K v, Integer weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            String k = u.toString() + "," + v.toString();
            return k.hashCode();
        }
    }

    int vertexCount = 0;
    int edgeCount = 0;
    final int WHITE = 0;
    final int GRAY = 1;
    final int BLACK = 2;

    public AdjList() {}

    public void addVertex(K v) {
        if (!vList.containsKey(v)) {
            vList.put(v, new Vertex<K, E>(v));
            eList.put(v, new LinkedList<Vertex<K, E>>());
            eWeights.put(v, new LinkedHashMap<Vertex<K, E>, Integer>());
            vertexCount++;
        }
    }

    public void addVertex(Vertex<K, E> v) {
        if (!vList.containsKey(v.key)) vertexCount++;

        vList.put(v.key, v);
        eList.put(v.key, new LinkedList<Vertex<K, E>>());
        eWeights.put(v.key, new LinkedHashMap<Vertex<K, E>, Integer>());
    }

    //directed, weight = 1
    public void addDirEdge(K u, K v) {
        if (vList.containsKey(u) && vList.containsKey(v)) {
            eList.get(u).add(vList.get(v));
            eWeights.get(u).put(vList.get(v), 1);
            edgeCount++;
        }
    }

    public void addUndirectedEdge(K u, K v) {
        addDirEdge(u, v);
        addDirEdge(v, u);
    }

    //directed, weight = 1
    public void addDirEdge(K u, K v, int weight) {
        if (vList.containsKey(u) && vList.containsKey(v)) {
            eList.get(u).add(vList.get(v));
            eListWeights.add(new Edge<K, Integer>(u, v, weight));
            eWeights.get(u).put(vList.get(v), weight);
            EdgeMap<K> e = new EdgeMap<>(u, v);
            edges.add(e);
            weights.put(e, weight);
            edgeCount++;
        }
    }

    public int getWeight(K u, K v) throws Exception{
        for (EdgeMap<K> e : edges) {
            if (e.u == u && e.v == v) {
                return weights.get(e);
            }
        }
        throw new IllegalArgumentException("no weight for edge");
    }

    public void addUndirectedEdge(K u, K v, int weight) {
        addDirEdge(u, v, weight);
        addDirEdge(v, u, weight);
    }

    public void print() {
        eList.entrySet().forEach( e -> {
            Vertex u = vList.get(e.getKey());
            String out = String.format("%s (%s/%s) -> ", u.key, u.dTime, u.fTime);
            System.out.print(out);
            e.getValue().forEach(v -> System.out.print(v.key));
            System.out.println();
        });
    }

    class Vertex<K, E> {
        K key;
        E data;

        Vertex<K, E> predecessor;
        int color;
        int distanceFromSrc;
        int d;

        int dTime;
        int fTime;
        int sccFTime;

        Vertex(K key) {
            this.key = key;
        }

        Vertex(K key, E data) { this.key = key; this.data = data; }
    }

}
