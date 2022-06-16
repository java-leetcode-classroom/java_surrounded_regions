# java_surrounded_regions

Given an `m x n` matrix `board` containing `'X'` and `'O'`, *capture all regions that are 4-directionally surrounded by* `'X'`.

A region is **captured** by flipping all `'O'`s into `'X'`s in that surrounded region.

## Examples

**Example 1:**

![https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg](https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg)

```
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

```

**Example 2:**

```
Input: board = [["X"]]
Output: [["X"]]

```

**Constraints:**

- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 200`
- `board[i][j]` is `'X'` or `'O'`.

## 解析

題目給定一個 ‘X’,’O’ 字元矩陣 board 

規則如下  對每一個值是’O’ 的 cell 如果鄰近的cell 都被 ‘X’ 圍起來的話 

該 cell 則會被 從 ‘O’ 改成 ‘X’

而如果 cell 本身是最外邊的 cell 則不會被改

要求實作一個演算法去修改造上面規則 修改輸入的 board

類似於 [695. Max Area of Island](https://www.notion.so/695-Max-Area-of-Island-f8b95a8c91534406ac3c9ca64d684c06) 

因為已知到最周圍的4個邊的 cell 如果是 ‘O’ 一定不會被消滅

所以只要對4個邊的 cell 去做 dfs 找尋與4個邊水平或是垂直方向相鄰的點

把這些點標注起來

然後把 cell 去檢查有沒有被標注與4個邊是 ‘O’ 相連 

沒有的話則做修改 如下圖

![](https://i.imgur.com/SNFRzTM.png)

整個時間複雜度是 O(m *n)

## 程式碼

```java
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

```
## 困難點

1. 理解如何找到不會被圍起來的 cell

## Solve Point

- [x]  Understand what problem to solve
- [x]  Analysis Complexity