import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void test() throws Exception {
        HashMap map = new HashMap();

        map.put("key", "value");
        assertTrue(map.size() == 1);
        assertTrue(map.get("key").equals("value"));
        map.put(1, 2);
        assertTrue(map.size() == 2);
        assertTrue(map.get(1).equals(2));
        map.put(1, "andrew");
        assertTrue(map.size() == 2);
        assertTrue(map.get(1).equals("andrew"));

        //collision
        map.put(17, "andrew");
        assertTrue(map.size() == 3);
        assertTrue(map.get(17).equals(map.get(1)));
        map.remove("key");
        map.remove(1);
        map.remove(17);
        assertTrue(map.size() == 0);
    }

    @Test
    public void test1() {
        HashMap map = new HashMap();

        for (int i=0; i<50;i++) {
            map.put(i,i);
        }

        assertTrue(map.size() == 50);

        for (int i=0; i<50;i++) {
            assertTrue(map.remove(i).equals(i));
        }

    }
}