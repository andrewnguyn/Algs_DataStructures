package Problems;

public class SetForest<K, E> {
    protected Node<K, E> root;
    protected Node<K, E> tail;
    protected int rank;

    public SetForest(K k) {
        root = new Node<>(k, null, null);
        root.par = root;
        tail = root;
    }

    public K getKey() {
        return root.key;
    }

    public Node<K, E> getRoot() {
        return root;
    }

    public void print() {
        Node<K, E> n = root;

        while ( n != null) {
            System.out.print(n.key + " -> ");
            n = n.next;
        }
        System.out.println();
    }

    protected static class Node<K, E> {
        K key;
        E data;
        Node<K, E> par;
        Node<K, E> next;

        Node(K k, Node<K, E> par) {
            this.key = k;
            this.par = par;
            this.next = null;
        }

        Node(K k, Node<K, E> par, Node<K, E> next) {
            this.key = k;
            this.par = par;
            this.next = next;
        }

    }

}
