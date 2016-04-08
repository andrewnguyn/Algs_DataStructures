package Sorting;

public class InsertionSort<T extends Comparable> {

    public T[] sort(T[] arr) {
        if (arr == null) return arr;

        for (int i = 1; i < arr.length; i++) {
            int anchor = i;
            while (anchor > 0 && arr[anchor-1].compareTo(arr[anchor]) > 0) {
                T temp = arr[anchor-1];
                arr[anchor-1] = arr[anchor];
                arr[anchor] = temp;
                anchor--;
            }
        }
        return arr;
    }

}
