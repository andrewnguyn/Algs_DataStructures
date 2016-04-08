package Sorting;

import java.util.*;

public class HexRadixSort {
    private static final String[] HEX_ARR = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public Integer[] sort(Integer[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        LinkedHashMap<String, List<Integer>> buckets = new LinkedHashMap<String, List<Integer>>();
        for (int i = 0; i < 16; i++) {
            List<Integer> l = new ArrayList<Integer>();
            buckets.put(HEX_ARR[i], l);
        }

        for (int i = 3; i >= 0; i--) {

            //empty buckets
            for (int j = 0; j < HEX_ARR.length; j++) {
                buckets.get(HEX_ARR[j]).clear();
            }

            for (int j = 0; j < arr.length; j++) {
                String hexVal = Integer.toHexString(arr[j]);
                while (hexVal.length() < 4)
                    hexVal = "0" + hexVal;

                //add to corresponding bucket
                buckets.get( Character.toString(hexVal.charAt(i)) ).add(arr[j]);
            }

            //transfer sorted buckets to arr
            int cursor = 0;
            for (Map.Entry<String, List<Integer>> e: buckets.entrySet()) {
                List<Integer> l = e.getValue();
                Iterator<Integer> itr = l.iterator();

                while (itr.hasNext()) {
                    arr[cursor] = itr.next();
                    cursor++;
                }
            }
        }
        return arr;
    }

    public void shiftRight(Integer[] arr, int start, int end) {
        for (int i = end; i >= start ; i--) {
            arr[i + 1] = arr[i];
        }
    }

    public void shiftLeft(Integer[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            arr[i - 1] = arr[i];
        }
    }

    public void swap(int i, int j, Integer[] arr) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}