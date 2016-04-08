package Sorting;

public class BinaryRadixSort {

    public Integer[] sort(Integer[] arr) {
        if (arr != null && arr.length <= 1) return arr;

        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int p1 = 0;
            int wall = arr.length;
            while (p1 < wall) {
                if ( (arr[p1] & mask) == 0) {
                    p1++;
                } else {
                    //move to end
                    int temp = arr[p1];
                    shiftLeft(arr, p1 + 1, arr.length - 1);
                    arr[arr.length - 1] = temp;
                    wall--;
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
