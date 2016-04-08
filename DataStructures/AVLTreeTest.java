package DataStructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    public void test() throws Exception {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        for (int i = 1; i <= 7; i++)
            tree.insert(i);

        tree.remove(4);
        tree.remove(2);
        tree.remove(1);
        tree.PrintTree();
    }
}