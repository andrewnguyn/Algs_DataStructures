package DataStructures;


public class RBTree<E extends Comparable<E>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    protected RBNode<E> root;
    protected final RBNode<E> NIL = new RBNode<E>(null, root);

    public RBTree() {
        root = null;
    }

    public void leftRotate(RBNode<E> x) {
        RBNode<E> y = x.right;
        //turns y's left subtree into x's right
        if (y.left != NIL)
            y.left.parent = x;

        //link x's parent to y
        y.parent = x.parent;

        //put x on y's left
        x.parent = y;
        y.left = x;
    }

    public void rightRotate(RBNode<E> y) {
        RBNode<E> x = y.left;
        //turns x's right subtree into y's left
        if (x.right != NIL)
            x.right.parent = y;

        //link y's parent to x
        x.parent = y.parent;

        //put y on x's right
        y.parent = x;
        x.right = y;
    }

    public void insert(RBNode<E> tree, E key) {
        RBNode<E> in = new RBNode<E>(key, NIL);
        if (tree == null) tree = in;

        //find par of insert
        RBNode<E> par;
        RBNode<E> anchor = tree;
        do {
            par = anchor;
            //go left
            if (key.compareTo(anchor.key) <= 0) {
                anchor = anchor.left;
            } else {
                anchor = anchor.right;
            }
        } while (anchor != NIL);

        //insert as child of par
        if (key.compareTo(par.key) <= 0) {
            par.left = in;
            in.parent = par;
        } else {
            par.right = in;
            in.parent = par;
        }
        in.color = RED;
        insertFixUp(tree, in);
    }

    public void insertFixUp(RBNode<E> tree, RBNode<E> z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                RBNode<E> y = z.parent.parent.right;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                RBNode<E> y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        tree.color = BLACK;
    }

    private static class RBNode<E> {
        E key;
        RBNode<E> parent;
        RBNode<E> left = null;
        RBNode<E> right = null;
        boolean color = BLACK;

        public RBNode(E key, RBNode<E> parent) {
            this.key = key;
            this.parent = parent;
        }

        public RBNode(E key, RBNode<E> parent, boolean color) {
            this.key = key;
            this.parent = parent;
            this.color = color;
        }
    }
}
