package Problems;

public class AndrewsLinkedList<E> {
    protected Entry<E> head;
    protected int size;

    public AndrewsLinkedList() {
        this.head = new Entry<E>(null, null, null);
        head.prev = head.next = head;
        this.size = 0;
    }

    //add to end
    public void addEnd(E val) {
        Entry<E> newEntry = new Entry<E>(val, null, null);
        if (size == 0) {
            head.next = head.prev = newEntry;
            newEntry.next = newEntry.prev = head;
        } else {
            //join end
            head.prev.next = newEntry;
            newEntry.prev = head.prev;

            //update head end
            head.prev = newEntry;
            newEntry.next = head;
        }
        size++;
    }

    //add to front
    public void addFront(E val) {
        Entry<E> newEntry = new Entry<E>(val, null, null);
        if (size == 0) {
            head.next = head.prev = newEntry;
            newEntry.next = newEntry.prev = head;
        } else {
            //join front
            newEntry.next = head.next;
            head.next.prev = newEntry;

            //update head front
            head.next = newEntry;
            newEntry.prev = head;
        }
        size++;
    }

    public E removeEnd() {
        if (size == 0) return null;
        if (size == 1) {
            E out = head.prev.val;
            head.prev = head.next = head;
            size--;
            return out;
        } else {
            E out = head.prev.val;
            Entry<E> newEnd = head.prev.prev;
            newEnd.next = head;
            head.prev = newEnd;
            size--;
            return out;
        }
    }

    public E removeFront() {
        if (size == 0) return null;
        if (size == 1) {
            E out = head.next.val;
            head.prev = head.next = head;
            size--;
            return out;
        } else {
            E out = head.next.val;
            Entry<E> newFront = head.next.next;
            newFront.prev = head;
            head.next = newFront;
            size--;
            return out;
        }
    }

    public E peekEnd() {
        return head.prev.val;
    }

    public E peekFront() {
        return head.next.val;
    }

    public boolean contains(E val) {
        for (Entry<E> anchor = head.next; anchor != head; anchor = anchor.next) {
            if (val == null && anchor.val == null) return true;
            if (anchor.val.equals(val)) return true;
        }
        return false;
    }

    public void clear() {
        head.prev = head.next = head;
        size = 0;
    }

    public int size() {
        return size;
    }

    protected static class Entry<E> {
        E val;
        Entry<E> prev;
        Entry<E> next;

        Entry(E val, Entry<E> prev, Entry<E> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    public void print() {
        Entry<E> p = head.next;
        while (p.val != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }



}