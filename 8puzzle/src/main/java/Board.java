import java.util.Arrays;

/**
 * jerry
 */
public class Board {

    private int[][] tiles;
    private int N;

    private int manhattanDistance = 0;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {

        N = blocks.length;
        tiles = copyBoard(blocks);
    }

    // board dimension N
    public int dimension() {

        return N;
    }

    // number of blocks out of place
    public int hamming() {

        int hammingDistance = 0;
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                if (tiles[row][col] != 0
                        && tiles[row][col] != (row * N) + col + 1)
                    hammingDistance++;
        return hammingDistance;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

        if (manhattanDistance == 0) {

            for (int row = 0; row < N; row++) {

                for (int col = 0; col < N; col++) {

                    int value = tiles[row][col];
                    if (value != 0) {

                        int targetRow = (value - 1) / N;
                        int targetCol = (value - 1) % N;
                        manhattanDistance +=
                                Math.abs(row - targetRow)
                                        + Math.abs(col - targetCol);
                    }
                }
            }
        }
        return manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {

        return hamming() == 0;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {

        int[][] twin = copyBoard(tiles);
        for (int row = 0; row < N; row++) {

            for (int col = 0; col < N - 1; col++) {

                if (twin[row][col] != 0 && twin[row][col + 1] != 0) {

                    int temp = twin[row][col];
                    twin[row][col] = twin[row][col + 1];
                    twin[row][col + 1] = temp;
                    return new Board(twin);
                }
            }
        }
        return null;
    }

    // does this board equal y?
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;
        for (int row = 0; row < N; row++)
            if (!Arrays.equals(tiles[row], board.tiles[row]))
                return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        Queue<Board> neighbours = new Queue<Board>();
        int zeroRow = 0;
        int zeroCol = 0;
        for (int row = 0; row < N; row++) {

            for (int col = 0; col < N; col++) {

                if (tiles[row][col] == 0) {

                    zeroRow = row;
                    zeroCol = col;
                }
            }
        }

        if (zeroRow > 0)
            neighbours.enqueue(
                    new Board(
                            swapTile(tiles, zeroRow, zeroCol, zeroRow - 1, zeroCol)
                    )
            );
        if (zeroRow < N - 1)
            neighbours.enqueue(
                    new Board(
                            swapTile(tiles, zeroRow, zeroCol, zeroRow + 1, zeroCol)
                    )
            );
        if (zeroCol > 0)
            neighbours.enqueue(
                    new Board(
                            swapTile(tiles, zeroRow, zeroCol, zeroRow, zeroCol - 1)
                    )
            );
        if (zeroCol < N - 1)
            neighbours.enqueue(
                    new Board(
                            swapTile(tiles, zeroRow, zeroCol, zeroRow, zeroCol + 1)
                    )
            );

        return neighbours;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(N).append("\n");
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++)
                stringBuilder.append(String.format("%2d ", tiles[i][j]));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    //_________________________________________________________________________

    private int[][] swapTile(
            int[][] array, int fromRow, int fromCol, int toRow, int toCol) {

        int[][] copy = copyBoard(array);

        int temp = copy[fromRow][fromCol];
        copy[fromRow][fromCol] = copy[toRow][toCol];
        copy[toRow][toCol] = temp;

        return copy;
    }

    private int[][] copyBoard(int[][] blocks) {

        int[][] copy = new int[N][N];
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                copy[row][col] = blocks[row][col];
        return copy;
    }
}
