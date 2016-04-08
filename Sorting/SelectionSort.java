package Sorting;

public class SelectionSort<T extends Comparable<T>> {

    public T[] sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int small = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[small]) < 0) {
                    small = j;
                }
            }

            if (small != i) {
                //swap
                T temp = arr[i];
                arr[i] = arr[small];
                arr[small] = temp;
            }
        }
        return arr;
    }
}
