package Sorting;

public class MergeSortTopDown<T extends Comparable> {

    public T[] sort(T[] arr) {
        if (arr.length > 0) sortRec(arr, 0, arr.length -1);
        return arr;
    }

    public void sortRec(T[] arr, int start, int end) {
        //base cases - single element arr
        if (start == end) return;

        if (end - start == 1) {
            if (arr[start].compareTo(arr[end]) > 0) {
                swap(start, end, arr);
            }
            return;
        }

        //partition
        int mid = (start+end)/2;
        sortRec(arr, start, mid);
        sortRec(arr, mid+1, end);

        //combine
        int cursor = start;
        int p1 = mid + 1;

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
