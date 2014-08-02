import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * jerry
 * 6/24/14
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {

        return size == 0;
    }

    // return the number of items on the deque
    public int size() {

        return size;
    }

    // insert the item at the front
    public void addFirst(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node temp = first;

        first = new Node(item, temp, null);

        if (isEmpty())
            last = first;
        else
            temp.prev = first;

        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node temp = last;

        last = new Node(item, null, temp);

        if (isEmpty())
            first = last;
        else
            temp.next = last;

        size++;
    }

    // delete and return the item at the front
    public Item removeFirst() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;

        size--;

        if (isEmpty())
            last = null;
        else
            first.prev = null;

        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;

        size--;

        if (isEmpty())
            first = null;
        else
            last.next = null;

        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {

        return new NodeIterator();
    }


    //_________________________________________________________________________

    private class Node {

        private Item item;
        private Node next;
        private Node prev;

        private Node(Item item, Node next, Node prev) {

            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class NodeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {

            return current != null;
        }

        @Override
        public Item next() {

            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException();
        }
    }


    //_________________________________________________________________________

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());

        System.out.println();

        deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);

        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
    }
}
