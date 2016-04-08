package Sorting;

public class CountingSort {

    // postfix count array
    public Integer[] sort(Integer[] arr, int k) {
        if (arr != null && arr.length <= 1) return arr;

        int[] count = new int[k+1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]] += 1; //count up elements
        }

        for (int j = 1; j < count.length; j++) {
            count[j] += count[j-1]; //aggregate
        }

        Integer[] out = new Integer[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            int idx = count[num];
            out[idx-1] = num;
            count[num] -= 1;
        }
        return out;
    }

    // prefix count array
    public Integer[] sort1(Integer[] arr, int k) {
        if (arr != null && arr.length <= 1) return arr;

        int[] count = new int[k+1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]] += 1; //count up elements
        }

        int total = 0;
        for (int j = 0; j < count.length; j++) {
            int oldVal = count[j];
            count[j] = total;
            total += oldVal;
        }

        Integer[] out = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int idx = count[num];
            out[idx] = num;
            count[num] += 1;
        }
        return out;
    }

    // modified count array to size (k-m) for better space complexity
    public Integer[] sort2(Integer[] arr, int k) {
        if (arr != null && arr.length <= 1) return arr;

        int min = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[min])
                min = i;
        }
        min = arr[min];

        int[] count = new int[k+1-min];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]-min] += 1; //count up elements
        }

        for (int j = 1; j < count.length; j++) {
            count[j] += count[j-1]; //aggregate
        }

        Integer[] out = new Integer[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            int idx = count[num-min];
            out[idx-1] = num;
            count[num-min] -= 1;
        }
        return out;
    }


}
