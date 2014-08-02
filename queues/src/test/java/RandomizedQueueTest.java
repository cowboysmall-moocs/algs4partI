import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * jerry
 */
public class RandomizedQueueTest {

    @Test
    public void testRandomizedQueue_Basic() {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        assertEquals(6, queue.size());

        List<Integer> integers = new ArrayList<Integer>();
        integers.add(queue.dequeue());
        integers.add(queue.dequeue());
        integers.add(queue.dequeue());
        integers.add(queue.dequeue());
        integers.add(queue.dequeue());
        integers.add(queue.dequeue());

        assertEquals(6, integers.size());
        assertEquals(0, queue.size());

        assertTrue(integers.remove(new Integer(1)));
        assertTrue(integers.remove(new Integer(2)));
        assertTrue(integers.remove(new Integer(3)));
        assertTrue(integers.remove(new Integer(4)));
        assertTrue(integers.remove(new Integer(5)));
        assertTrue(integers.remove(new Integer(6)));

        assertEquals(0, integers.size());
    }

    @Test
    public void testRandomizedQueue_Iterator() {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        for (Integer value: queue)
            assertTrue(integers.contains(value));
    }

    @Test(expected = NullPointerException.class)
    public void testRandomizedQueue_AddLast_Null() {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRandomizedQueue_RemoveFirst_Empty() {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRandomizedQueue_Iterator_Empty() {

        new RandomizedQueue<Integer>().iterator().next();
    }
}
