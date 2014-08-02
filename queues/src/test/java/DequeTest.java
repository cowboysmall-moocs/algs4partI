import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * jerry
 * 6/24/14
 */
public class DequeTest {

    @Test
    public void testDeque_Basic() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        assertFalse(deque.isEmpty());

        assertEquals(6, deque.size());
        assertFalse(deque.isEmpty());
        assertEquals(new Integer(3), deque.removeFirst());
        assertEquals(new Integer(6), deque.removeLast());

        assertEquals(4, deque.size());
        assertFalse(deque.isEmpty());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(new Integer(5), deque.removeLast());

        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(new Integer(4), deque.removeLast());

        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testDeque_Stack() {

        Deque<Integer> stack = new Deque<Integer>();
        stack.addFirst(6);
        stack.addFirst(5);
        stack.addFirst(4);
        stack.addFirst(3);
        stack.addFirst(2);
        stack.addFirst(1);

        int counter = 0;
        for (Integer val: stack)
            assertEquals(new Integer(++counter), val);
    }

    @Test
    public void testDeque_Queue() {

        Deque<Integer> queue = new Deque<Integer>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);
        queue.addLast(6);

        int counter = 0;
        for (Integer val: queue)
            assertEquals(new Integer(++counter), val);
    }

    @Test(expected = NullPointerException.class)
    public void testDeque_AddFirst_Null() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDeque_AddLast_Null() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeque_RemoveFirst_Empty() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeque_RemoveLast_Empty() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.removeLast();
    }

    @Test
    public void testDeque_Iterator() {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        int counter = 0;
        for (Integer val: deque)
            assertEquals(new Integer(++counter), val);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeque_Iterator_Empty() {

        new Deque<Integer>().iterator().next();
    }
}
