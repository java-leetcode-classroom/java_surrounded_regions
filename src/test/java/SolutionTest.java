import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    final private Solution sol = new Solution();
    @Test
    void solveExample1() {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        sol.solve(board);
        char[][] expected = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        assertArrayEquals(expected, board);
    }
    @Test
    void solveExample2() {
        char[][] board = new char[][]{
                {'X'}
        };
        sol.solve(board);
        char[][] expected = new char[][]{
                {'X'}
        };
        assertArrayEquals(expected, board);
    }
}