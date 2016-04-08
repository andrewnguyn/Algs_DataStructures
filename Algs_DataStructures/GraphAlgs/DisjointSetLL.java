package Problems;

import java.util.HashMap;

public class DisjointSetLL<K, E> {
    private HashMap<K, SetLL<K, E>> setList = new HashMap<>();
    private HashMap<K, SetLL.Node<K, E>> nList = new HashMap<>();

    public void makeSet(K k) {
        SetLL<K, E> s = new SetLL<>(k);
        setList.put(k, s);
        nList.put(k, s.head.next);
    }

    public K findSet(K k) {
        return nList.get(k).par.next.key;
    }

    public void union(K x, K y) {
        SetLL<K, E> setX = setList.get(findSet(x));
        SetLL<K, E> setY = setList.get(findSet(y));

        if (setX.length > setY.length) {
            setList.remove(setY.getKey());
            //append y's list to x
            setX.append(setY);
        } else {
            setList.remove(setX.getKey());
            setY.append(setX);
        }
    }

    public boolean sameComp(K x, K y) {
        return (findSet(x) == findSet(y));
    }

    public void print() {
        setList.values().forEach( s -> s.print());
    }


}
