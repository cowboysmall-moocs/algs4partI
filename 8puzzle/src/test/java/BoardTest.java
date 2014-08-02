import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * jerry
 */
public class BoardTest {

    @Test
    public void testBoard_Goal() {

        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        assertEquals(0, board.hamming());
        assertEquals(0, board.manhattan());
    }

    @Test
    public void testBoard_Basic() {

        Board board = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});

        assertEquals(5, board.hamming());
        assertEquals(10, board.manhattan());
    }

    @Test
    public void testBoard_Equality() {

        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Board board2 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        Board board3 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        assertFalse(board1.equals(board2));
        assertTrue(board1.equals(board3));
    }

    @Test
    public void testBoard_IsGoal() {

        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Board board2 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});

        assertTrue(board1.isGoal());
        assertFalse(board2.isGoal());
    }

    @Test
    public void testBoard_Twin() {

        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Board twin = new Board(new int[][]{{2, 1, 3}, {4, 5, 6}, {7, 8, 0}});

        assertEquals(twin, board.twin());
    }

    @Test
    public void testBoard_Neighbours() {

        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 5}, {7, 8, 6}});

        List<Board> boards = new ArrayList<Board>();
        boards.add(new Board(new int[][]{{1, 0, 3}, {4, 2, 5}, {7, 8, 6}}));
        boards.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}}));
        boards.add(new Board(new int[][]{{1, 2, 3}, {4, 8, 5}, {7, 0, 6}}));
        boards.add(new Board(new int[][]{{1, 2, 3}, {0, 4, 5}, {7, 8, 6}}));

        for (Board neighbour : board.neighbors())
            assertTrue(boards.contains(neighbour));
    }
}
