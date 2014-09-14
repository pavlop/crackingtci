import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int head, tail;
    private Item[] elements;
    private int size;

    public Deque() {
        int initialCapacity = 51;
        size = 0;
        elements = (Item[]) new Object[initialCapacity];
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        if (size() == 0) {
            set(head, item);
        } else {
            if (elements.length == size) doubleCapacity();
            head = previousPointerCyclic(head);
            set(head, item);
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        if (size() == 0) {
            set(tail, item);
        } else {
            if (elements.length == size) doubleCapacity();
            tail = nextPointerCyclic(tail);
            set(tail, item);
        }
        size++;
    }

    public Item removeFirst() {
        assert (!isEmpty() || isEmpty() && head == tail);
        if (isEmpty()) throw new NoSuchElementException();
        Item first = get(head);
        elements[head] = null;
        size--;
        if (size != 0) head = nextPointerCyclic(head);
        return first;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item last = get(tail);
        elements[tail] = null;
        size--;
        if (size != 0) tail = previousPointerCyclic(tail);
        return last;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int iterHead = head;
            private int id = 0;

            public boolean hasNext() {
                return id < size();
            }

            public Item next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                id++;
                Item toreturn = get(iterHead);
                iterHead = nextPointerCyclic(iterHead);
                return toreturn;
            }

            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }


    //PRIVATE

    /**
     * Doubles the capacity of this deque.  Call only when full, i.e.,
     * when head and tail have wrapped around to become equal.
     */
    private void doubleCapacity() {
        resizeArray(elements.length << 1);
    }

    private void resizeArray(int newSize) {
        System.out.println("resizeArray=" + newSize);
        //need to copy all the lelements which are to the right to head to the begining of the array
        int curArraySize = elements.length;
        int rnumOfElementsToTheRight = curArraySize - head; // number of elements to the right of head
        int newCapacity = newSize;
        Item[] newElements = (Item[]) new Object[newCapacity];
//        * @param      src      the source array.
//        * @param      srcPos   starting position in the source array.
//        * @param      dest     the destination array.
//        * @param      destPos  starting position in the destination data.
//        * @param      length   the number of array elements to be copied
        System.arraycopy(elements, head, newElements, 0, rnumOfElementsToTheRight);
        System.arraycopy(elements, 0, newElements, rnumOfElementsToTheRight, head);
        elements = newElements;
        this.head = 0;
        tail = curArraySize - 1;
    }


    private Item get(int i) {
        return elements[i];
    }

    private void set(int i, Item val) {
        elements[i] = val;
    }

    private int nextPointerCyclic(int p) {
        if (p == elements.length - 1) return 0;
        else return p + 1;
    }

    private int previousPointerCyclic(int p) {
        if (p == 0) return elements.length - 1;
        else return p - 1;
    }


    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());

        d.addFirst(5);
        d.addFirst(4);
        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        //assert (3 == d.size());
//        System.out.println(Arrays.toString(d.elements));
//        d.addLast(40);
//        d.addLast(50);
//        d.addLast(60);
//        d.addLast(70);
//        d.addLast(80);
//        d.addLast(90);
//        d.addLast(100);
        System.out.println(Arrays.toString(d.elements));
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());

        d.addFirst(-100);
        System.out.println(Arrays.toString(d.elements));
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());

        System.out.println("remove first and last");
        d.removeFirst();
        d.removeLast();
        System.out.println(Arrays.toString(d.elements));
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());

        System.out.println("Iterator Test");
        for (int i : d) {
            System.out.print(i + " ");
        }

        System.out.println("\nremove all last");
        while (d.size() != 0) {
            System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());
            System.out.println("removed:" + d.removeLast());
        }
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());
        System.out.println("isempty " + d.isEmpty());


        System.out.println("Add again");
        d.addFirst(1);
        System.out.println(Arrays.toString(d.elements));
        System.out.println("first " + d.get(d.head) + " last " + d.get(d.tail) + " size " + d.size());


    }
}