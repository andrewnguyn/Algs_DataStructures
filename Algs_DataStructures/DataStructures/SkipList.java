//package DataStructures;
//
//public class SkipList<T extends Comparable> {
//    private int levels;
//    private SkipLinkedList<T> topList;
//    private SkipLinkedList<T> botList;
//    private SkipLinkedList.Node<T> topHead;
//    private SkipLinkedList.Node<T> botHead;
//
//    public SkipList()  {
//        this.levels = 1;
//        this.topList = new SkipLinkedList<T>();
//        this.botList = topList;
//        this.topHead = topList.head;
//        this.botHead = topList.head;
//    }
//
//    public boolean search(T val) {
//        SkipLinkedList.Node<T> anchor = topHead;
//        int curLevel = levels;
//
//        while (true) {
//            if (curLevel > 1) {
//                if (val.compareTo(anchor.val) == 0) return true;
//                else if (anchor.next == null) {
//                    anchor = anchor.bot;
//                    curLevel--;
//                } else if (val.compareTo(anchor.next.val) < 0) {
//                    anchor = anchor.bot;
//                    curLevel--;
//                } else {
//                    anchor = anchor.next;
//                }
//            } else {
//                if (val.compareTo(anchor.val) == 0) return true;
//                else if (val.compareTo(anchor.val) >= 0) anchor = anchor.next;
//                else return false;
//            }
//        }
//    }
//
//    public void insert(T val) {
//        SkipLinkedList.Node<T> anchor = topHead;
//        int curLevel = levels;
//
//        while (true) {
//            if (curLevel > 1) {
//                if (val.compareTo(anchor.val) == 0) return;
//                else if (anchor.next == null) {
//                    anchor = anchor.bot;
//                    curLevel--;
//                } else if (val.compareTo(anchor.next.val) < 0) {
//                    anchor = anchor.bot;
//                    curLevel--;
//                } else {
//                    anchor = anchor.next;
//                }
//            } else {
//                if (val.compareTo(anchor.val) == 0) return;
//                else if (anchor.next == null) {
//                    //insert
//                    SkipLinkedList.Node<T> n = new SkipLinkedList.Node<T>(val);
//                    anchor.next = n;
//                    n.prev = anchor;
//                    n.next = null;
//
//
//                }
//                else if (val.compareTo(anchor.next.val) > 0) anchor = anchor.next;
//                else if (val.compareTo(anchor.next.val) < 0) {
//                    //insert
//                };
//            }
//        }
//
//    }
//
//    public int randLevel() {
//        int level = 0;
//        while (Math.random() * 2 == 1) {
//            level++;
//        }
//        return level;
//    }
//
//    private static class SkipLinkedList<T> {
//        Node<T> head;
//        SkipLinkedList() {
//            this.head = new Node<T>(null);
//        }
//
//        private static class Node<T> {
//            T val;
//            Node<T> prev;
//            Node<T> next;
//            Node<T> top;
//            Node<T> bot;
//
//            Node(T val) {
//                this.val = val;
//                this.next = null;
//                this.top = null;
//                this.bot = null;
//            }
//
//            Node(T val, Node<T> prev, Node<T> next, Node<T> top, Node<T> bot) {
//                this.val = val;
//                this.next = next;
//                this.top = top;
//                this.bot = bot;
//            }
//
//        }
//    }
//
//
//
//
//}
