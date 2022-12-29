package leetcode.sudokusolver;

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public boolean solve(char[][] board) {
        final int RADIX = 10;

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == '.') {
                    for(int solution = 1; solution <= 9; solution++) {
                        final char solutionChar = Character.forDigit(solution, RADIX);
                        if(this.charIsAvailable(board, y, x, solutionChar)) {
                            board[y][x] = solutionChar;
                            final boolean isSolved = solve(board);
                            if(!isSolved) {
                                board[y][x] = '.';
                            } else {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        // stop here
        return true;
    }

    private boolean charIsAvailable(char[][] board, int yPos, int xPos, char charToCheck) {
        // Row
        for (int x = 0; x < 9; x++) {
            final char foundChar = board[yPos][x];
            if (foundChar == charToCheck) {
                return false;
            }
        }

        // Column
        for (int y = 0; y < 9; y++) {
            final char foundChar = board[y][xPos];
            if (foundChar == charToCheck) {
                return false;
            }
        }

        // Square
        final int ySectionPos = (yPos / 3) * 3;
        final int yRangeEnd = ySectionPos + 3;
        final int xSectionPos = (xPos/ 3) * 3;
        final int xRangeEnd = xSectionPos + 3;
        for(int y = ySectionPos; y < yRangeEnd; y++) {
            for(int x = xSectionPos; x < xRangeEnd; x++) {
                char foundChar = board[y][x];
                if (foundChar == charToCheck) {
                    return false;
                }
            }
        }

        return true;
    }
}