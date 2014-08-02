/**
 * jerry
 */
public class Solver {

    private SearchNode goal;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        MinPQ<SearchNode> priorityQueue = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<SearchNode>();

        priorityQueue.insert(new SearchNode(null, initial, 0));
        twinPriorityQueue.insert(new SearchNode(null, initial.twin(), 0));

        boolean completed = false;
        while (!completed) {

            SearchNode current = priorityQueue.delMin();
            if (!current.boardIsGoal()) {

                for (Board neighbour : current.boardNeighbors()) {

                    SearchNode node =
                            new SearchNode(current, neighbour, current.moves + 1);
                    if (!node.equals(current.previous))
                        priorityQueue.insert(node);
                }

            } else {

                goal = current;
                completed = true;
            }

            SearchNode twin = twinPriorityQueue.delMin();
            if (!twin.boardIsGoal()) {

                for (Board neighbour : twin.boardNeighbors()) {

                    SearchNode node =
                            new SearchNode(twin, neighbour, twin.moves + 1);
                    if (!node.equals(twin.previous))
                        twinPriorityQueue.insert(node);
                }

            } else {

                goal = null;
                completed = true;
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {

        return goal != null;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {

        if (isSolvable())
            return goal.moves;
        else
            return -1;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {

        if (isSolvable()) {

            Stack<Board> boards = new Stack<Board>();
            boards.push(goal.board);

            SearchNode previous = goal.previous;
            while (previous != null) {

                boards.push(previous.board);
                previous = previous.previous;
            }

            return boards;
        }
        return null;
    }


    //_________________________________________________________________________

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }


    //_________________________________________________________________________

    private class SearchNode implements Comparable<SearchNode> {

        private SearchNode previous;
        private Board board;
        private int moves;

        private SearchNode(SearchNode previous, Board board, int moves) {

            this.previous = previous;
            this.board = board;
            this.moves = moves;
        }

        public int priority() {

            return board.manhattan() + moves;
        }

        public boolean boardIsGoal() {

            return board.isGoal();
        }

        public Iterable<Board> boardNeighbors() {

            return board.neighbors();
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SearchNode that = (SearchNode) o;
            return board.equals(that.board);
        }

        @Override
        public int compareTo(SearchNode other) {

            return this.priority() - other.priority();
        }
    }
}
