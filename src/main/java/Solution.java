import java.util.HashSet;
import java.util.Objects;

public class Solution {
    static class Pair {
        private int row;
        private int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    public void solve(char[][] board) {
      int ROW = board.length;
      int COL = board[0].length;
      HashSet<Pair> lives = new HashSet<>();
      for (int row = 0; row < ROW; row++) {
          dfs(row, 0, board, lives);
          dfs(row, COL-1, board, lives);
      }
      for (int col = 0; col < COL; col++) {
         dfs(0, col, board, lives);
         dfs(ROW-1, col, board, lives);
      }
      for (int row =0; row < ROW; row++) {
          for (int col =0; col < COL ; col++) {
              Pair cell = new Pair(row, col);
              if (board[row][col] == 'O'&& !lives.contains(cell)) {
                  board[row][col] = 'X';
              }
          }
      }
    }
    public void dfs(int row, int col, char[][] board, HashSet<Pair> lives) {
        int ROW = board.length;
        int COL = board[0].length;
        Pair cell = new Pair(row, col);
        if (row < 0 || row >= ROW || col < 0 || col >= COL || board[row][col] == 'X' || lives.contains(cell)) {
            return;
        }
        lives.add(cell);
        dfs(row-1, col, board, lives);
        dfs(row+1, col, board, lives);
        dfs(row, col-1, board, lives);
        dfs(row, col+1, board, lives);
    }
}
