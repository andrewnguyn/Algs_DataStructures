package DataStructures;

import java.util.ArrayList;

//no dups
public class BinarySearchTree<E extends Comparable<E>> {
    protected BNode<E> root;
    protected int size;

    public BinarySearchTree() {
        root = null;
        size=0;
    }

    public BinarySearchTree(BNode<E> root) {
        this.root = root;
    }

    public void inOrderWalk(BNode<E> x) {
        if (x == null) return;
        inOrderWalk(x.left);
        System.out.println(x.key);
        inOrderWalk(x.right);
    }

    public ArrayList<E> inOrderWalkOut(BNode<E> x) {
        ArrayList<E> out = new ArrayList<E>();
        if (x == null) return out;
        out.addAll(inOrderWalkOut(x.left));
        out.add(x.key);
        out.addAll(inOrderWalkOut(x.right));
        return out;
    }

    public static void preOrderWalk(BNode x) {
        if (x == null) return;
        System.out.println(x.key);
        preOrderWalk(x.left);
        preOrderWalk(x.right);
    }

    public static void postOrderWalk(BNode x) {
        if (x == null) return;
        postOrderWalk(x.left);
        postOrderWalk(x.right);
        System.out.println(x.key);
    }

    public BNode<E> search(BNode<E> tree, E key) {
        while (tree != null) {
            if (key.compareTo(tree.key) < 0)
                tree = tree.left;
            else if (key.compareTo(tree.key) > 0)
                tree = tree.right;
            else
                return tree;
        }
        return null;
    }

    public BNode<E> min(BNode<E> tree) {
        if (tree == null) return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    public void deleteMin(BNode<E> tree) {
        if (tree.left == null && tree.right == null) tree = null;
        else if (tree.left == null && tree.right != null)
            tree = tree.right;
        else {
            BNode<E> par;
            BNode<E> anchor = tree;

            do {
                par = anchor;
                anchor = anchor.left;
            } while (anchor.left != null);

            par.left = null;
        }
    }

    public BNode<E> max(BNode<E> tree) {
        if (tree == null) return null;

        while (tree != null && tree.right != null)
            tree = tree.right;
        return tree;
    }

    public void insert(BinarySearchTree<E> tree, E key) {
        if (tree.root == null)
            tree.root = new BNode<E>(key, null, null);
        else
            insert(root, key);
    }

    public void insert(BNode<E> tree, E key) {
        BNode<E> in = new BNode<E>(key, null, null);

        if (tree == null) { //empty tree
            tree = new BNode<E>(key, null, null);
            return;
        }

        //find place
        BNode<E> anchor = tree;
        while (anchor != null) {
            //go left
            if (key.compareTo(anchor.key) <= 0) {
                if (anchor.left == null) {
                    anchor.left = in;
                    return;
                }
                anchor = anchor.left;
            } else { //go right
                if (anchor.right == null) {
                    anchor.right = in;
                    return;
                }
                anchor = anchor.right;
            }
        }
    }

    public void insertAll(BinarySearchTree<E> tree, E[] arr) {
        for (E e: arr)
            insert(tree, e);
    }

    public void delete(BinarySearchTree<E> tree, E key) {
        tree.root = delete(tree.root, key);
    }

    public BNode<E> delete(BNode<E> tree, E key) {
        if (tree == null) return tree; // empty tree

        BNode<E> par = null;
        BNode<E> anchor = tree;

        do {
            if (key.compareTo(anchor.key) < 0) {
                par = anchor;
                anchor = anchor.left;
            }
            else if (key.compareTo(anchor.key) > 0) {
                par = anchor;
                anchor = anchor.right;
            }
            else { //found
                if (par != null) { //NOT ROOT
                    if (anchor.left == null && anchor.right == null) { //leaf node
                        if (par.left == anchor)
                            par.left = null;
                        else
                            par.right = null;
                        return tree;
                    } else if (anchor.left != null && anchor.right == null) { // single left
                        if (par.left == anchor)
                            par.left = anchor.left;
                        else
                            par.right = anchor.left;
                        return tree;
                    } else if (anchor.left == null && anchor.right != null) { // single right
                        if (par.left == anchor)
                            par.left = anchor.right;
                        else
                            par.right = anchor.right;
                        return tree;
                    } else { //two children
                        System.out.println("successor 1 called");
                        BNode<E> sucessor = min(anchor.right);
                        anchor.right = delete(anchor.right, sucessor.key);
                        if (par.left == anchor)
                            par.left = sucessor;
                        else
                            par.right = sucessor;
                        sucessor.left = anchor.left;
                        sucessor.right = anchor.right;
                        return tree;
                    }
                } else { //ROOT DELETE
                    if (anchor.left == null && anchor.right == null) { //leaf node
                        return null;
                    } else if (anchor.left != null && anchor.right == null) { // single left
                        return anchor.left;
                    } else if (anchor.left == null && anchor.right != null) { // single right
                        return anchor.right;
                    } else { //two children
                        System.out.println("successor 2 called");
                        BNode<E> sucessor = min(anchor.right);
                        anchor.right = delete(anchor.right, sucessor.key);
                        sucessor.left = anchor.left;
                        sucessor.right = anchor.right;
                        return sucessor;
                    }
                }
            }
        } while (anchor != null);
        return tree;
    }

    public boolean isEmpty(BNode<E> tree) {
        return (tree == null);
    }

    private static class BNode<E> {
        E key;
        BNode<E> left, right;

        public BNode(E key, BNode<E> left, BNode<E> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

    }




}
