import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * jerry
 * 6/24/14
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;

    private int pointer = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {

        queue = (Item[]) new Object[1];
    }

    // is the queue empty?
    public boolean isEmpty() {

        return pointer == 0;
    }

    // return the number of items on the queue
    public int size() {

        return pointer;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null)
            throw new NullPointerException();

        if (pointer == queue.length)
            resize(queue.length * 2);

        queue[pointer++] = item;
    }

    // delete and return a random item
    public Item dequeue() {

        if (isEmpty())
            throw new NoSuchElementException();

        swapItems(randomIndex(), --pointer);

        Item item = queue[pointer];
        queue[pointer] = null;

        if (pointer > 0 && pointer == queue.length / 4)
            resize(queue.length / 2);

        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {

        if (isEmpty())
            throw new NoSuchElementException();

        return queue[randomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomizedQueueIterator();
    }


    //_________________________________________________________________________

    private int randomIndex() {

        return StdRandom.uniform(pointer);
    }

    private void swapItems(int first, int second) {

        Item temp = queue[first];
        queue[first] = queue[second];
        queue[second] = temp;
    }

    private void resize(int size) {

        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < pointer; i++)
            copy[i] = queue[i];
        queue = copy;
    }


    //_________________________________________________________________________

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] indexes = new int[pointer];
        private int current = 0;

        private RandomizedQueueIterator() {

            for (int i = 0; i < indexes.length; i++)
                indexes[i] = i;
            StdRandom.shuffle(indexes);
        }

        @Override
        public boolean hasNext() {

            return current < pointer;
        }

        @Override
        public Item next() {

            if (!hasNext())
                throw new NoSuchElementException();

            return queue[indexes[current++]];
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException();
        }
    }

    //_________________________________________________________________________

    // unit testing
    public static void main(String[] args) {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
