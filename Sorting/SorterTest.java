package Sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SorterTest {
    private int testCases = 0;

    @Test
    public void driver() {

        //test empty
        Integer[] in = {};
        testInput(in);
        testPositiveSort(in);

        //test single
        Integer[] in1 = {-1};
        testInput(in1);

        //test double
        Integer[] in2 = {-1,0};
        testInput(in2);

        //test double dup
        Integer[] in3 = {-1,-1};
        testInput(in3);

        //test arr
        Integer[] in4 = {6,5,3,1,8,7,2,4};
        testInput(in4);
        testPositiveSort(in);

        //test all dups
        Integer[] in5 = {0,0,0,0,0,0,0,0};
        testInput(in5);
        testPositiveSort(in);

        //test already sorted
        Integer[] in6 = {1,2,3,4,5,6,7};
        testInput(in6);
        testPositiveSort(in6);

        Integer[] in11 = {531,274,112,545,-917,73,785,145,349};
        testInput(in11);

        Integer[] in12 = {60,-70,-374};
        testInput(in12);


        //rand -/+ input
        for (int i=0; i<10; i++) {
            int arrSize = (int) (Math.random() * 1000);
            Integer[] in7 = new Integer[arrSize];
            for (int j = 0; j < in7.length; j++) {
                in7[j] = (int)(Math.random() * 2000) - 1000;
            }
            testInput(in7);
        }

        //rand + input
        for (int i=0; i<10; i++) {
            int arrSize = (int) (Math.random() * 1000);
            Integer[] in7 = new Integer[arrSize];
            for (int j = 0; j < in7.length; j++) {
                in7[j] = (int)(Math.random() * 1000);
            }
            testInput(in7);
            testPositiveSort(in7);
        }
    }

    public void testInput(Integer[] in) {
        Integer[] res = in.clone();
        Arrays.sort(res);

        BubbleSort<Integer> bubblesorter = new BubbleSort<Integer>();
        SelectionSort<Integer> selsorter = new SelectionSort<Integer>();
        MergeSortTopDown<Integer> mersortertopdown = new MergeSortTopDown<Integer>();
        MergeSortBottomUp<Integer> mersorterbotup = new MergeSortBottomUp<Integer>();
        QuickSort<Integer> quicksorter = new QuickSort<Integer>();
        InsertionSort<Integer> insertsorter = new InsertionSort<Integer>();

        Integer[] out1 = bubblesorter.sort(in.clone());
        Integer[] out2 = selsorter.sort(in.clone());
        Integer[] out3 = mersortertopdown.sort(in.clone());
        Integer[] out4 = mersorterbotup.sort(in.clone());
        Integer[] out5 = quicksorter.sort(in.clone());
        Integer[] out6 = insertsorter.sort(in.clone());

        assertTrue(Arrays.equals(res, out1));
        assertTrue(Arrays.equals(res, out2));
        assertTrue(Arrays.equals(res, out3));
        assertTrue(Arrays.equals(res, out4));
        assertTrue(Arrays.equals(res, out5));
        assertTrue(Arrays.equals(res, out6));
    }

    public void testPositiveSort(Integer[] in) {
        Integer[] res = in.clone();
        Arrays.sort(res);
        CountingSort countsorter = new CountingSort();
        BinaryRadixSort binradsorter = new BinaryRadixSort();
        HexRadixSort hexradsorter = new HexRadixSort();

        int k = (res.length > 0) ? res[res.length-1] : 0;
        Integer[] out7 = countsorter.sort(in.clone(), k);
        Integer[] out8 = countsorter.sort1(in.clone(), k);
        Integer[] out11 = countsorter.sort1(in.clone(), k);
        assertTrue(Arrays.equals(res, out7));
        assertTrue(Arrays.equals(res, out8));
        assertTrue(Arrays.equals(res, out11));

        Integer[] out9 = binradsorter.sort(in.clone());
        assertTrue(Arrays.equals(res, out9));

        Integer[] out10 = hexradsorter.sort(in.clone());
        assertTrue(Arrays.equals(res, out10));
    }

    public void testMergeSortTop(Integer[] in) {
        Integer[] res = in.clone();
        Arrays.sort(res);

        MergeSortTopDown<Integer> mersortertopdown = new MergeSortTopDown<Integer>();
        Integer[] out = mersortertopdown.sort(in.clone());

        printArr(in);
        printArr(out);
        printArr(res);
    }

    public void printArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }
}