package Problems;

public class ReverseLL {

    public static void reverseLL(AndrewsLinkedList<Integer> ll) {
        AndrewsLinkedList.Entry<Integer> p = ll.head.next;

        while (p != ll.head) {
            AndrewsLinkedList.Entry<Integer> pNext = p.next;
            AndrewsLinkedList.Entry<Integer> pPrev = p.prev;
            p.next = pPrev;
            p.prev = pNext;
            p = pNext;
        }
        AndrewsLinkedList.Entry<Integer> pNext = p.next;
        AndrewsLinkedList.Entry<Integer> pPrev = p.prev;
        p.next = pPrev;
        p.prev = pNext;
    }
}
