import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class AndrewsLinkedListTest {

    @Test
    public void test() throws Exception {
        AndrewsLinkedList<Integer> list = new AndrewsLinkedList<Integer>();
        assertTrue(list.size() == 0);
        assertTrue(list.peekFront() == null);
        assertTrue(list.peekEnd() == null);


        for (int i=0; i< 10; i++) {
            list.addEnd(i);
            assertTrue(list.contains(i));
            assertTrue(list.peekEnd() == i);
            assertTrue(list.size() == i+1);
        }

        for (int i=0; i< 10; i++) {
            assertTrue(list.peekFront() == i);
            assertTrue(list.removeFront() == i);
            assertTrue(list.size() == 10-(i+1));
        }
    }

    @Test
    public void testNull() {
        AndrewsLinkedList<Integer> list = new AndrewsLinkedList<Integer>();
        list.addEnd(null);
        assertTrue(list.contains(null));
        assertTrue(list.size() == 1);
    }
}