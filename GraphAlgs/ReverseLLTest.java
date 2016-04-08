package Problems;

import org.junit.Test;

public class ReverseLLTest {

    @Test
    public void testReverseLL() throws Exception {
        AndrewsLinkedList<Integer> ll = new AndrewsLinkedList<Integer>();

        for (int i = 0; i <= 10; i++) {
            ll.addEnd(i);
        }

        ReverseLL.reverseLL(ll);
        ll.print();

    }
}