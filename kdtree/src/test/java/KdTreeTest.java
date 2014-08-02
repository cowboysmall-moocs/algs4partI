import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * jerry
 */
public class KdTreeTest {

    @Test
    public void something() {

        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.206107, 0.095492));
        kdTree.insert(new Point2D(0.975528, 0.654508));
        kdTree.insert(new Point2D(0.024472, 0.345492));
        kdTree.insert(new Point2D(0.793893, 0.095492));
        kdTree.insert(new Point2D(0.793893, 0.904508));
        kdTree.insert(new Point2D(0.975528, 0.345492));
        kdTree.insert(new Point2D(0.206107, 0.904508));
        kdTree.insert(new Point2D(0.500000, 0.000000));
        kdTree.insert(new Point2D(0.024472, 0.654508));
        kdTree.insert(new Point2D(0.500000, 1.000000));
        kdTree.insert(new Point2D(0.793893, 0.095492));

        assertEquals(10, kdTree.size());

        assertTrue(kdTree.contains(new Point2D(0.206107, 0.095492)));
        assertTrue(kdTree.contains(new Point2D(0.975528, 0.654508)));
        assertTrue(kdTree.contains(new Point2D(0.024472, 0.345492)));
        assertTrue(kdTree.contains(new Point2D(0.793893, 0.095492)));
        assertTrue(kdTree.contains(new Point2D(0.793893, 0.904508)));
        assertTrue(kdTree.contains(new Point2D(0.975528, 0.345492)));
        assertTrue(kdTree.contains(new Point2D(0.206107, 0.904508)));
        assertTrue(kdTree.contains(new Point2D(0.500000, 0.000000)));
        assertTrue(kdTree.contains(new Point2D(0.024472, 0.654508)));
        assertTrue(kdTree.contains(new Point2D(0.500000, 1.000000)));

        assertFalse(kdTree.contains(new Point2D(1.000000, 1.000000)));
        assertFalse(kdTree.contains(new Point2D(0.000000, 0.000000)));
    }
}
