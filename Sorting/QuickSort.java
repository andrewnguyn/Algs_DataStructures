package Sorting;

public class QuickSort<T extends Comparable<T>> {

    public T[] sort(T[] arr) {
        if (arr != null && arr.length <= 1) {
            return arr;
        }
        sortRec(arr, 0, arr.length - 1);
        return arr;
    }

    public void sortRec(T[] arr, int start, int end) {
        if (end - start == 0) return;

        int wall = start;
        int anchor = end;
        int lAnchor = anchor - 1;

        while (anchor > wall) {
            if (arr[lAnchor].compareTo(arr[anchor]) >= 0) {
                T temp = arr[lAnchor];
                arr[lAnchor] = arr[anchor];
                arr[anchor] = temp;
                lAnchor--;
                anchor--;
            } else {
                T temp = arr[wall];
                arr[wall] = arr[lAnchor];
                arr[lAnchor] = temp;
                wall++;
            }
        }

        if (wall > start) sortRec(arr, start, wall - 1);
        if (wall < end) sortRec(arr, wall + 1, end);
    }
}
