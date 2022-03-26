package leetcode.validsudoku;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        final int horizontalLength = board.length;
        final int verticalLength = board[0].length;
        final boolean[][] horizontalUsed = new boolean[horizontalLength][10];
        final boolean[][] verticalUsed = new boolean[verticalLength][10];
        final int boxesInEachSquare = 3;
        final boolean[][][] squaresUsed = new boolean[horizontalLength / boxesInEachSquare][verticalLength / boxesInEachSquare][10];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                final char boardValue = board[i][j];
                if (boardValue != '.') {
                    final int intValue = Integer.parseInt(String.valueOf(boardValue));

                    // Check if each row already has the number
                    if (horizontalUsed[i][intValue]) {
                        return false;
                    } else {
                        horizontalUsed[i][intValue] = true;
                    }

                    // Check if each column already has the number
                    if (verticalUsed[j][intValue]) {
                        return false;
                    } else {
                        verticalUsed[j][intValue] = true;
                    }

                    // Now check if the digits within the squares are valid
                    final int horizontalSquarePosition = (int) Math.floor((double) i / (double) boxesInEachSquare);
                    final int verticalSquarePosition = (int) Math.floor((double) j / (double) boxesInEachSquare);
                    if (squaresUsed[horizontalSquarePosition][verticalSquarePosition][intValue]) {
                        return false;
                    } else {
                        squaresUsed[horizontalSquarePosition][verticalSquarePosition][intValue] = true;
                    }
                }
            }
        }

        return true;
    }
}