import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] elements;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        int initialCapacity = 51;
        size = 0;
        elements = (Item[]) new Object[initialCapacity];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (size() == elements.length) resizeArray(elements.length * 2);
        elements[size()] = item;
        size++;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = (int) (Math.random() * size());
        Item toReturn = elements[randomIndex];
        size--;
        elements[randomIndex] = elements[size];
        return toReturn;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = (int) (Math.random() * size());
        return elements[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private RandomizedQueue<Item> q = new RandomizedQueue<Item>();

            {
                q.elements = Arrays.copyOf(elements, RandomizedQueue.this.size());
                q.size = RandomizedQueue.this.size();
            }

            public boolean hasNext() {
                return q.size() > 0;
            }

            public Item next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                return q.dequeue();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);


        for (int i : q) {
            System.out.println(i);
        }
        System.out.println("size " + q.size());
        System.out.println("removed " + q.dequeue());
        System.out.println("removed " + q.dequeue());
        System.out.println("removed " + q.dequeue());
        System.out.println("size " + q.size());
    }

    private void resizeArray(int newSize) {
        System.out.println("resizeArray=" + newSize);
        //need to copy all the lelements which are to the right to head to the begining of the array
        Item[] newElements = (Item[]) new Object[newSize];
//        * @param      src      the source array.
//        * @param      srcPos   starting position in the source array.
//        * @param      dest     the destination array.
//        * @param      destPos  starting position in the destination data.
//        * @param      length   the number of array elements to be copied
        System.arraycopy(elements, 0, newElements, 0, size());
        elements = newElements;
    }

}