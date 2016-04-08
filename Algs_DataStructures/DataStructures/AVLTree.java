package DataStructures;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {
    protected Node<T> root;
    private static final int LEFT_HEAVY = -1;
    private static final int RIGHT_HEAVY = 1;

    public AVLTree() {
        root = null;
    }

    public T min() {
        Node<T> min = minAVL(root);
        return (min == null) ? null : min.val;
    }

    public Node<T> minAVL(Node<T> n) {
        if (n == null) return null;

        while (n.l != null) {
            n = n.l;
        }
        return n;
    }

    public T max() {
        Node<T> max = maxAVL(root);
        return (max == null) ? null : max.val;
    }

    public Node<T> maxAVL(Node<T> n) {
        if (n == null) return null;

        while (n.r != null) {
            n = n.r;
        }
        return n;
    }

    //search
    public Node<T> search(T val) {
        if (root == null) return null;

        Node<T> p = root;
        while (p != null) {
            if (p.val.compareTo(val) < 0) {
                p = p.l;
            } else if (p.val.compareTo(val) > 0) {
                p = p.r;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(T val) {
        root = insertAVL(root, val);
    }

    public Node<T> insertAVL(Node<T> n, T val) {
        if (n == null) return new Node<T>(val);
        else if (val.compareTo(n.val) < 0) {
            n = new Node<T>(n.val, insertAVL(n.l, val), n.r);
        } else if (val.compareTo(n.val) > 0) {
            n = new Node<T>(n.val, n.l, insertAVL(n.r, val));
        } else {return n;}

        if (n.bal > RIGHT_HEAVY) {
            if (n.r.bal == LEFT_HEAVY) {
                n.r = rightRotate(n.r);
                n = leftRotate(n);
            } else {
                n = leftRotate(n);
            }
        } else if (n.bal < LEFT_HEAVY) {
            if (n.l.bal == RIGHT_HEAVY) {
                n.l = leftRotate(n.l);
                n = rightRotate(n);
            } else {
                n = rightRotate(n);
            }
        }
        return n;
    }

    public void remove(T val) {
        root = removeAVL(root, val);
    }

    //delete
    public Node<T> removeAVL(Node<T> n, T val) {
        if (n == null) return null;

        if (val.compareTo(n.val) == 0) {
            if (n.l == null && n.r == null) return null;
            else if (n.l != null && n.r == null) return n.l;
            else if (n.l == null && n.r != null) return n.r;
            else {
                Node<T> sucessor = maxAVL(n.l);
                n = new Node<T>(sucessor.val, removeAVL(n.l, sucessor.val), n.r);
            }
        } else if (val.compareTo(n.val) < 0) {
            n = new Node<T>(n.val, removeAVL(n.l, val), n.r);
        } else {
            n = new Node<T>(n.val, n.l, removeAVL(n.r, val));
        }

        //balancing
        if (n.bal > RIGHT_HEAVY) {
            if (n.r.bal == LEFT_HEAVY) {
                n.r = rightRotate(n.r);
                n = leftRotate(n);
            } else {
                n = leftRotate(n);
            }
        } else if (n.bal < LEFT_HEAVY) {
            if (n.l.bal == RIGHT_HEAVY) {
                n.l = leftRotate(n.l);
                n = rightRotate(n);
            } else {
                n = rightRotate(n);
            }
        }
        return n;
    }

    //tree rotation
    public Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.l;
        Node<T> a = x.l;
        Node<T> b = x.r;
        Node<T> c = y.r;

        y = new Node<T>(y.val, b, c);
        x = new Node<T>(x.val, a, y);
        return x;
    }

    public Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.r;
        Node<T> a = x.l;
        Node<T> b = y.l;
        Node<T> c = y.r;

        x = new Node<T>(x.val, a, b);
        y = new Node<T>(y.val, x, c);
        return y;
    }

    public void PrintTree() {
        root.level = 0;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.println(node.toString());
            int level = node.level;
            Node<T> left = node.l;
            Node<T> right = node.r;
            if (left != null) {
                left.level = level + 1;
                queue.add(left);
            }
            if (right != null) {
                right.level = level + 1;
                queue.add(right);
            }
        }
    }

    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        T val;
        Node<T> l;
        Node<T> r;
        int h;
        int bal;
        public int level;

        Node(T val) {
            this.val = val;
            l = null;
            r = null;
            h = 1;
            bal = 0;
        }

        Node(T val, Node<T> l, Node<T> r) {
            this.val = val;
            this.l = l;
            this.r = r;

            if (l == null && r == null) {
                h = 1;
                bal = 0;
            } else if (l != null && r == null) {
                h = l.h + 1;
                bal = - l.h;
            } else if (l == null && r != null) {
                h = r.h + 1;
                bal = r.h;
            } else {
                h = Math.max(l.h, r.h) + 1;
                bal = r.h - l.h;
            }
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.val.compareTo(o.val);
        }

        @Override
        public String toString() {
            if (this.l != null && this.r != null) {
                return "Level " + level + ": " + val + " || left " + this.l.val + " | right " + this.r.val;
            } else if (this.l != null && this.r == null) {
                return "Level " + level + ": " + val + " || left " + this.l.val;
            } else if (this.l == null && this.r != null) {
                return "Level " + level + ": " + val + " || right " + this.r.val;
            }
            return "Level " + level + ": " + val;
        }
    }



}
