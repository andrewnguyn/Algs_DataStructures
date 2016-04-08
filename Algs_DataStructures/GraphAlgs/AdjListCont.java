package Problems;

import java.util.*;

public class AdjListCont<K, E> {
    protected HashMap<K, Vertex<K, E>> vertices;
    protected HashMap<K, LinkedHashMap<Vertex<K, E>, Integer>> edges;

    public AdjListCont() {
        vertices = new HashMap<K, Vertex<K, E>>();
        edges = new HashMap<K, LinkedHashMap<Vertex<K, E>, Integer>>();
    }

    public void addVertex(K k, E v) {
        vertices.put(k, new Vertex(k, v));
        edges.put(k, new LinkedHashMap<Vertex<K, E>, Integer>());
    }

    public void addDirEdge(K k1, K k2, int weight) {
        if (!vertices.containsKey(k1) && !vertices.containsKey(k2)) throw new IllegalArgumentException("vertex does not exist");

        LinkedHashMap<Vertex<K, E>, Integer> eList = edges.get(k1);

        //check if edge exist, exist => update, nonexist => add
        boolean existent = false;
        for (Map.Entry<Vertex<K, E>, Integer> e : eList.entrySet()) {
            if (e.getKey().key.equals(k2)) {
                existent = true;
                e.setValue(weight);
            }
        }
        if (!existent)
            eList.put(vertices.get(k2), weight);
    }

    public void addUndirectedEdge(K k1, K k2, int weight) {
        addDirEdge(k1, k2, weight);
        addDirEdge(k2, k1, weight);
    }

    private class Vertex<K, E> {
        K key;
        E data;
        int color;
        Vertex predecessor;

        Vertex(K key, E data) {
            this.key = key;
            this.data = data;
        }
    }

    public boolean hasVertex(K u) {
        return vertices.containsKey(u);
    }
}
