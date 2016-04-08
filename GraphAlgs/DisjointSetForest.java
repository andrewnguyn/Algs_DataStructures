package Problems;

import java.util.HashMap;

public class DisjointSetForest<K, E> {
    private HashMap<K, SetForest.Node<K, E>> nList = new HashMap<>();
    private HashMap<K, SetForest<K, E>> setList = new HashMap<>();

    public void makeSet(K k) {
        SetForest<K, E> s = new SetForest<>(k);
        s.rank = 0;
        setList.put(k, s);
        nList.put(k, s.root);
    }

    public K findSetKey(K k) {
        SetForest.Node<K, E> n = nList.get(k);

        n.par = findSetRec(n.par);

        return n.par.key;
    }

    //return head of Node
    public SetForest.Node<K, E> findSetRec(SetForest.Node<K, E> n) {
        if (n.par != n) {
            n.par = findSetRec(n.par);
        }
        return n.par;
    }

    public void union(K x, K y) {
        link(setList.get(findSetKey(x)), setList.get(findSetKey(y)));
    }

    public void link(SetForest<K, E> x, SetForest<K, E> y) {
        if (x.rank > y.rank) {
            setList.remove(y.root.key);
            y.root.par = x.root;
            x.tail.next = y.root;
            x.tail = y.tail;
            setList.put(x.root.key, x);
        } else {
            setList.remove(x.root.key);
            x.root.par = y.root;
            y.tail.next = x.root;
            y.tail = x.tail;
            setList.put(y.root.key, y);
            if (x.rank == y.rank)
                y.rank++;
        }
    }

    public boolean sameComp(K x, K y) {
        return (findSetKey(x) == findSetKey(y));
    }

    public void print() {
        setList.values().forEach( s -> s.print());

    }
}
