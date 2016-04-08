package Sorting;

import java.util.Comparator;

public class BubbleSort<T extends Comparable<T>> {

    public T[] sort(T[] arr) {
        boolean swap = false;
        for (int i = 0; i < arr.length; i++) {
            int ptr1 = 0;
            int ptr2 = ptr1 + 1;

            while (ptr2 <= arr.length - 1 - i) {
                if (arr[ptr1].compareTo(arr[ptr2]) > 0) { //swap
                    T temp = arr[ptr1];
                    arr[ptr1] = arr[ptr2];
                    arr[ptr2] = temp;
                    swap = true;
                }
                ptr1++;
                ptr2++;
            }
            if (!swap) break;
            swap = false;
        }
        return arr;
    }
}
