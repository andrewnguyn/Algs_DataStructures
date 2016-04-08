package DataStructures;

public class Heap<T extends Comparable<T>> {
    private final int DEFAULT_SIZE = 16;
    private final double LOAD_FACTOR = 0.75;

    protected T[] arr;
    protected int size;

    public Heap() {
        arr = (T[])new Comparable[DEFAULT_SIZE];
        size = 0;
    }

    public void buildHeap(T[] vals) {
        arr = vals;
        size = vals.length;
        for (int i= (int) Math.floor(size/2) -1; i>= 0; i--) {
            siftDownIndex(i);
        }
    }

    public void siftDownIndex(int i) {
        while (hasLeftChild(i)) {
            int l = left(i);
            int r = right(i);

            //compare with l
            int largest = (l < size && arr[l].compareTo(arr[i]) > 0) ? l : i;

            //compare with r
            if (largest == l) {
                largest = (r < size && arr[r].compareTo(arr[l]) > 0) ? r : l;
            } else {
                largest = (r < size && arr[r].compareTo(arr[i]) > 0) ? r : i;
            }

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    public void siftDown() {
        int i = 0;
        while (hasLeftChild(i)) {
            int l = left(i);
            int r = right(i);

            //compare with l
            int largest = (l < size && arr[l].compareTo(arr[i]) > 0) ? l : i;

            //compare with r
            if (largest == l) {
                largest = (r < size && arr[r].compareTo(arr[l]) > 0) ? r : l;
            } else {
                largest = (r < size && arr[r].compareTo(arr[i]) > 0) ? r : i;
            }

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    public void siftUp() {
        int i = size - 1;
        while (hasParent(i)) {
            int p = parent(i);
            int largest = (arr[p].compareTo(arr[i]) > 0) ? p : i;

            if (largest != p) {
                swap(largest, p);
                i = p;
            } else {
                break;
            }
        }
    }

    /**
     * Find min/max or heap
     * @return min/max item of heap, null if empty
     */
    public T peek() {
        return arr[0];
    }

    public void push(T key) {
        arr[size] = key;
        size++;
        siftUp();
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        T out = arr[0];
        //remove from heap
        arr[0] = arr[size-1];
        arr[size-1] = null;
        size--;
        siftDown();
        return out;
    }

    public void delete() {
        if (size == 0) {
            return;
        } else {
            //remove from heap
            arr[0] = arr[size-1];
            arr[size-1] = null;
            size--;
            siftDown();
        }
    }

    public void replace(T key) {
        arr[0] = key;
        siftDown();
    }

    public T get(int i) {
        return arr[i];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private int parent(int i) {
        return (int) Math.ceil(i/2 - 1);
    }

    private int left(int i) {
        return 2*i + 1;
    }

    private int right(int i) {
        return 2*i + 2;
    }

    public boolean hasLeftChild(int i) {
        return (left(i) < size -1);
    }

    public boolean hasRightChild(int i) {
        return (right(i) < size -1);
    }

    public boolean hasParent(int i) {
        return (parent(i) < size -1);
    }

    public void swap(int i, int k) {
        T temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }
}
