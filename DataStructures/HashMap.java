package DataStructures;

public class HashMap<K, V> {
    private Entry<K, V>[] table;
    int size;
    final int DEFAULT_SIZE = 16;
    final double LOAD_FACTOR = 0.75;

    public HashMap() {
        table = new Entry[DEFAULT_SIZE];
        this.size = 0;
    }

    public V get(K key) {
        int index = indexFor(key.hashCode(), table.length);
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key))
                return e.value;
        }
        return null;
    }

    public void addEntry(int hash, K key, V value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
        int threshold = (int) (LOAD_FACTOR*table.length);
        if (size > threshold)
            resize(2*table.length);
    }

    public V put(K key, V val) {
        int index = indexFor(key.hashCode(), table.length);
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            V prev;
            if (e.key.equals(key)) {
                prev = e.value;
                e.value = val;
                return prev;
            }
        }
        addEntry(key.hashCode(), key, val, index);
        return null;
    }

    public Entry<K, V> removeEntry(K key) {
        int index = indexFor(key.hashCode(), table.length);
        Entry<K, V> e = table[index];

        if (key.hashCode() == e.hash) { //first entry
            table[index] = e.next;
            size--;
            return e;
        } else { // inbetween
            Entry<K, V> prev = e;
            for (e = e.next; e != null; e = e.next) {
                if (key.hashCode() == e.hash) {
                    prev.next = e.next;
                    size--;
                    return e;
                }
            }
        }
        return null;
    }

    public V remove(K key) {
        Entry<K, V> e = removeEntry(key);
        return (e == null) ? null : e.value;
    }

    public int indexFor(int hash, int length) {
        return Math.abs(hash % length);
    }

    public int size() {
        return size;
    }

    public void resize(int newSize) {
        Entry[] oldTable = table;
        table = new Entry[newSize];
        size = 0;
        for (Entry<K, V> e : oldTable) {
            while (e != null) {
                put(e.key, e.value);
                e = e.next;
            }
        }
    }

    private static class Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public boolean hasNext() {
            return (next != null);
        }
    }

}
