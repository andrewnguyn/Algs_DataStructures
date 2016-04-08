package Sorting;

public class MergeSortBottomUp<T extends Comparable<T>> {

    public T[] sort(T[] arr) {
        if (arr.length <= 1) return arr;
        int bucketSize = 2;

        while (bucketSize <= arr.length) {
            int start = 0;
            int end = start + bucketSize - 1;

            while (start < arr.length) {
                if (end >= arr.length) end = arr.length - 1;
                merge(arr, start, end, bucketSize);
                start = end + 1;
                end = start + bucketSize - 1;
            }
            bucketSize *= 2;
            if (bucketSize > arr.length) {
                merge(arr, 0, arr.length - 1, bucketSize);
            }
        }
        return arr;
    }

    public void merge(T[] arr, int start, int end, int bucketSize) {
        if (start == end) return;

        int cursor = start;
        int p1 = start + (bucketSize/2);

        while (cursor <= end) {
            if (cursor == p1) {
                break;
            } else if (p1 > end) {
                break;
            } else if (arr[cursor].compareTo(arr[p1]) <= 0) {
                cursor++;
            } else {
                T temp = arr[p1];
                shift(arr, cursor, p1 - 1);
                arr[cursor] = temp;
                p1++;
                cursor++;
            }
        }
    }

    public void shift(T[] arr, int start, int end) {
        for (int i = end; i >= start ; i--) {
            arr[i + 1] = arr[i];
        }
    }

    public void swap(int i, int j, T[] arr) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
