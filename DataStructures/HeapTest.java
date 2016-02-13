
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void test() {
        Heap<Integer> h = new Heap();

        h.buildHeap(new Integer[]{5,4,23,1,2,3,5,-1,-3});
        assertTrue(h.size == 9);

        Integer[] arr = new Integer[]{5,4,23,1,2,3,5,-1,-3};
        Arrays.sort(arr);

        for (int i=0;i<9;i++) {
            System.out.println(h.peek());
            assertTrue(arr[8-i] == h.pop());
        }


    }
}