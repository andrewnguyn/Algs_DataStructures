package Problems;

import java.util.*;

public class GraphAlgs<K, E> {
    final static int WHITE = 0;
    final static int GRAY = 1;
    final static int BLACK = 2;
    final static int INFINITY = Integer.MAX_VALUE;
    private static int time;

    public void BFS(AdjList<K, E> g, K source) {
        for ( AdjList<K, E>.Vertex<K, E> u : g.vList.values()) {
            u.color = WHITE;
            u.distanceFromSrc = INFINITY;
            u.predecessor = null;
        }

        AdjList<K, E>.Vertex<K, E> src = g.vList.get(source);
        src.color = GRAY;
        src.predecessor = null;

        Deque<AdjList<K, E>.Vertex<K, E>> queue = new LinkedList<AdjList<K, E>.Vertex<K, E>>();
        queue.addLast(src);

        while (!queue.isEmpty()) {
            AdjList<K, E>.Vertex<K, E> u = queue.removeFirst();
            System.out.print("At: " + u.key + " -> ");
            for (AdjList<K, E>.Vertex<K, E> v : g.eList.get(u.key)){
                if (v.color == WHITE) {
                    v.color = GRAY;
                    v.distanceFromSrc = u.distanceFromSrc + 1;
                    v.predecessor = u;
                    queue.addLast(v);
                    System.out.print(v.key + ", ");
                }
            }
            u.color = BLACK;
            System.out.println();
        }
    }

    public void DFS(AdjList<K, E> g) {
        for (AdjList<K, E>.Vertex<K, E> u : g.vList.values()) {
            u.color = WHITE;
            u.predecessor = null;
        }
        time = 0;
        for (AdjList<K, E>.Vertex<K, E> u : g.vList.values()) {
            if (u.color == WHITE) {
                System.out.println("FOREST: ");
                DFSVist(g, u);
            }
        }
    }

    public void DFSSCC(AdjList<K, E> g) {
        g.vList.values().forEach(u -> {
            u.predecessor = null;
            u.color = WHITE;
        });

        time = 0;
        List<AdjList<K, E>.Vertex<K, E>> vSorted = new ArrayList<AdjList<K, E>.Vertex<K, E>>(g.vList.values());
        Collections.sort(vSorted, (v1, v2) -> v2.sccFTime - v1.sccFTime);

        vSorted.forEach(u -> {
            if (u.color == WHITE) {
                System.out.println("FOREST: ");
                DFSVist(g, u);
            }
        });
    }

    public void DFSVist(AdjList<K, E> g, AdjList<K, E>.Vertex<K, E> u) {
        u.dTime = ++time;
        u.color = GRAY;

        for (AdjList<K, E>.Vertex<K, E> v : g.eList.get(u.key) ) {
            if (v.color == WHITE) {
                v.predecessor = u;
                DFSVist(g, v);
            }
        }
        u.color = BLACK;
        u.fTime = ++time;
        System.out.println("Node: " + u.key + ", Time: " + u.dTime + "/" + u.fTime);
    }

    public Deque<AdjList<K, E>.Vertex<K, E>> TopologicalSort(AdjList<K, E> g) {
        Deque<AdjList<K, E>.Vertex<K, E>> topOrder = new LinkedList<AdjList<K, E>.Vertex<K, E>>();

        DFS(g, topOrder);
        return topOrder;

//        for (AdjList<K, E>.Vertex<K, E> v : topOrder) {
//            String out = String.format("%s (%d/%d) ", v.key, v.dTime, v.fTime);
//            System.out.print(out);
//        }
//        System.out.println();
    }

    public void DFS(AdjList<K, E> g, Deque<AdjList<K, E>.Vertex<K, E>> topOrder) {
        for (AdjList<K, E>.Vertex<K, E> u : g.vList.values()) {
            u.color = WHITE;
            u.predecessor = null;
        }
        time = 0;
        for (AdjList<K, E>.Vertex<K, E> u : g.vList.values()) {
            if (u.color == WHITE)
                DFSVist(g, u, topOrder);
        }
    }

    public void DFSVist(AdjList<K, E> g, AdjList<K, E>.Vertex<K, E> u, Deque<AdjList<K, E>.Vertex<K, E>> topOrder) {
        u.dTime = ++time;
        u.color = GRAY;

        for (AdjList<K, E>.Vertex<K, E> v : g.eList.get(u.key) ) {
            if (v.color == WHITE) {
                v.predecessor = u;
                DFSVist(g, v, topOrder);
            }
        }
        u.color = BLACK;
        u.fTime = ++time;
        topOrder.addFirst(u);
    }

    public void MSTKruskal(AdjList<K, E> g) {
        ArrayList<Tuple<K>> mstEList = new ArrayList<>();

        DisjointSetForest<K, E> ds = new DisjointSetForest<>();

        g.vList.values().forEach( v -> ds.makeSet(v.key));

        Collections.sort(g.eListWeights, new Comparator<AdjList.Edge<K, Integer>>() {
            @Override
            public int compare(AdjList.Edge<K, Integer> e1, AdjList.Edge<K, Integer> e2) {
                return e1.weight - e2.weight;
            }
        });

        g.eListWeights.forEach(e -> {
            if (!ds.sameComp(e.u, e.v)) {
                mstEList.add(new Tuple<K>(e.u, e.v));
                ds.union(e.u, e.v);
            }
        });

        mstEList.forEach( e -> System.out.println(e.first + "->" + e.second));

    }

    public void MSTPrim(AdjList<K, E> g, K s){
        g.vList.values().forEach( v-> {
            v.distanceFromSrc = INFINITY;
            v.predecessor = null;
        });

        //set src node
        AdjList<K, E>.Vertex<K, E> src = g.vList.get(s);
        src.distanceFromSrc = 0;

        PriorityQueue<AdjList<K,E>.Vertex<K, E>> pQueue = new PriorityQueue<>(new Comparator<AdjList<K,E>.Vertex<K, E>>() {
            @Override
            public int compare(AdjList<K, E>.Vertex<K, E> v1, AdjList<K, E>.Vertex<K, E> v2) {
                return v1.distanceFromSrc - v2.distanceFromSrc;
            }
        });

        g.vList.values().forEach(v -> pQueue.offer(v));

        while (pQueue.size() > 0) {
            AdjList<K, E>.Vertex<K, E> u = pQueue.poll();
            if (u.predecessor != null)
                System.out.println(u.predecessor.key + "->" + u.key );
            g.eList.get(u.key).forEach( v -> {
                try {
                    if (pQueue.contains(v) && g.getWeight(u.key, v.key) < v.distanceFromSrc) {
                        v.predecessor = u;
                        v.distanceFromSrc = g.getWeight(u.key, v.key);
                        pQueue.remove(v);
                        pQueue.add(v);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public boolean BellmanFord(AdjList<K, E> g, K s) {
        initSingleSrc(g, s);

        for (int i = 1; i < g.vList.size(); i++) {
            g.eWeights.forEach( (u,e) -> {
                e.forEach( (v,w) -> {
                    relax(g.vList.get(u), v, w);
                });
            });
        }

        for ( Map.Entry<K, LinkedHashMap<AdjList<K, E>.Vertex<K, E>, Integer>> e : g.eWeights.entrySet()) {
            AdjList<K, E>.Vertex<K, E> u = g.vList.get(e.getKey());

            for (Map.Entry<AdjList<K, E>.Vertex<K, E>, Integer> v: e.getValue().entrySet()) {
                if (v.getKey().d > u.d + v.getValue()) {
                    return false;
                }
            }
        }
        printShortestPath(g);
        return true;
    }

    public void printShortestPath(AdjList<K, E> g) {
        g.vList.values().forEach( v -> {
            String out = "";
            while (v.predecessor != null) {
                out += "|" + v.key + ")" + v.d + "(-<" + v.predecessor.key + ")" + v.predecessor.d + "(" + "|";
                v = v.predecessor;
            }
            StringBuilder sb = new StringBuilder(out);
            sb.reverse();
            System.out.println(sb.toString());
        });
    }

    public void initSingleSrc(AdjList<K, E> g, K s) {
        g.vList.values().forEach( v -> {
            v.d = INFINITY;
            v.predecessor = null;
        });
        g.vList.get(s).d = 0;
    }

    public boolean relax(AdjList<K, E>.Vertex<K, E> u, AdjList<K, E>.Vertex<K, E> v, int w) {
        int sum = 0;
        if (u.d == INFINITY || w == INFINITY)
            sum = INFINITY;
        else
            sum = u.d + w;
        if (sum < v.d) {
            v.d = u.d + w;
            v.predecessor = u;
            return true;
        }
        return false;
    }

    public void dagShortestPath(AdjList<K, E> g, K s) {
        Deque<AdjList<K, E>.Vertex<K, E>> topOrder = TopologicalSort(g);
        initSingleSrc(g, s);

        topOrder.forEach( u -> {
            g.eWeights.get(u.key).forEach( (v, w) -> {
                relax(u, v, w);
            });
        });

//        g.vList.values().forEach( v -> {
//            if (v.predecessor != null)
//                System.out.println(v.predecessor.key + "->" + v.key);
//            else
//                System.out.println("null->" + v.key);
//        });

        printShortestPath(g);
    }

    public void dijkstra(AdjList<K, E> g, K s) {
        initSingleSrc(g, s);
        ArrayList<AdjList<K, E>.Vertex<K, E>> set = new ArrayList<>();

        PriorityQueue<AdjList<K, E>.Vertex<K, E>> pQueue = new PriorityQueue<>(new Comparator<AdjList<K, E>.Vertex<K, E>>() {
            @Override
            public int compare(AdjList<K, E>.Vertex<K, E> v1, AdjList<K, E>.Vertex<K, E> v2) {
                return v1.d - v2.d;
            }
        });
        g.vList.values().forEach( v -> pQueue.offer(v));

        while (pQueue.size() > 0) {
            AdjList<K, E>.Vertex<K, E> u = pQueue.poll();
            set.add(u);
            g.eWeights.get(u.key).forEach( (v,w) -> {
                if (relax(u, v, w)) {
                    set.remove(v);
                    set.add(v);
                }
            });
        }

        printShortestPath(g);
    }

    public class Tuple<T> {
        T first;
        T second;

        Tuple(T first, T second) {
            this.first = first;
            this.second = second;
        }
    }

}
