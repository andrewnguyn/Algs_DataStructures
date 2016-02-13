import com.sun.deploy.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class BTreeTest {

    @Test
    public void test() throws Exception {
        BTree<Integer> tree = new BTree<Integer>();

        for (int i=0; i<10; i++)
            tree.insert(tree, i);

        tree.inOrderWalk(tree.root);
    }

    @Test
    public void test1() {
        BTree<Integer> tree = new BTree<Integer>();

        for (int i=0; i<10; i++)
            tree.insert(tree, i);

        //delete evens
        for (int i = 2; i<10; i+=2)
            tree.delete(tree.root, i);

        tree.inOrderWalk(tree.root);
    }

    @Test
    public void test2() {
        for (int a=0; a<100;a++) {
            BTree<Integer> tree = new BTree<Integer>();

            //build input
            ArrayList<Integer> input = new ArrayList<Integer>();
            for (int i = 0; i < 100; i++) {
                input.add(i);
            }
            Collections.shuffle(input);

            //create sorted output
            ArrayList<Integer> output = (ArrayList<Integer>) input.clone();
            Collections.sort(output);

            //build tree with input
            tree.insertAll(tree, input.toArray(new Integer[input.size()]));

            ArrayList<Integer> myOutput = tree.inOrderWalkOut(tree.root);

            assertTrue(myOutput.equals(output));

            //randomly delete one entry from both & compare
            int idx = (int) (Math.random() * output.size());
            ArrayList<Integer> output1 = (ArrayList<Integer>) output.clone();
            output1.remove(idx);

            System.out.println("DELETE: " + output.get(idx));
            tree.delete(tree, output.get(idx));
            myOutput = tree.inOrderWalkOut(tree.root);
            System.out.println("correct");
            System.out.println(output1.toString());
            System.out.println("myoutput");
            System.out.println(myOutput.toString());
            assertTrue(output1.equals(myOutput));
        }
    }

    @Test
    public void test3() {
        int[] input = new int[]{57, 84, 44, 39, 61, 60, 17, 65, 63, 32, 72, 41, 8, 98, 18, 29, 40, 58, 16, 62, 4, 3, 70, 10, 64, 92, 14, 43, 78, 90, 86, 12, 31, 69, 23, 24, 30, 35, 42, 79, 83, 71, 77, 66, 55, 56, 85, 48, 5, 25, 68, 1, 9, 37, 11, 76, 33, 53, 99, 93, 22, 6, 45, 67, 13, 73, 27, 88, 75, 46, 28, 96, 19, 59, 50, 54, 89, 95, 26, 51, 82, 36, 52, 38, 49, 97, 91, 34, 80, 2, 20, 47, 21, 15, 81, 74, 87, 0, 7, 94};
        int delete = 92;

        BTree<Integer> tree = new BTree<Integer>();
        for (int i=0; i<input.length; i++)
            tree.insert(tree, input[i]);

        tree.delete(tree.root, delete);
        tree.inOrderWalk(tree.root);

    }
}