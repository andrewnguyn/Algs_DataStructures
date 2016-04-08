package Problems;

public class SetLL<K, E> {
    protected Node<K, E> head = new Node<K, E>(null);
    protected Node<K, E> tail = new Node<K, E>(null);
    protected int length = 0;

    public SetLL(K k) {
        Node<K, E> n = new Node<K, E>(k, head, head);
        head.next = n;
        tail.next = n;
        length++;
    }

    public void append(SetLL<K, E> y) {
        //link x end to y front
        Node<K, E> end = tail.next;
        end.next = y.head.next;

        //update y par's to new head
        Node<K, E> cursor = end;
        while (cursor.next.key != null) {
            cursor = cursor.next;
            cursor.par = head;
        }
        //update tail
        tail.next = cursor;
        //old list's head
        cursor.next = head;

        length += y.length;
    }

    public K getKey() {
        if (length > 0)
            return head.next.key;
        throw new NullPointerException("set is empty and has no key");
    }

    public void print() {
        Node<K,E> n = head.next;

        while (n.key != null) {
            System.out.print(n.key + " -> ");
            n = n.next;
        }
        System.out.println();
    }

    protected static class Node<K, E> {
        K key;
        E data;
        Node<K, E> next;
        Node<K, E> par;

        Node(K key) {
            this.key = key;
            this.next = null;
            this.par = null;
        }

        Node(K key, Node<K, E> next, Node<K, E> par) {
            this.key = key;
            this.next = next;
            this.par = par;
        }


        Node(K key, E data) {
            this.key = key;
            this.data = data;
        }
    }

}
