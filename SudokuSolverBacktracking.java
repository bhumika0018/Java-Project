public class SudokuSolverBacktracking {

    // Size of the Sudoku grid
    private static final int SIZE = 9;

    // Main method to solve Sudoku using backtracking
    public static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // If the current cell is empty, try placing digits from 1 to 9
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        // Check if placing the current number is valid
                        if (isValidMove(grid, row, col, num)) {
                            // Place the number if valid
                            grid[row][col] = num;

                            // Recursively try to solve the rest of the Sudoku
                            if (solveSudoku(grid)) {
                                return true;  // Solution found
                            }

                            // If placing the current number doesn't lead to a solution, backtrack
                            grid[row][col] = 0;
                        }
                    }
                    return false;  // No valid number found for this cell, backtrack
                }
            }
        }
        return true;  // No empty cell found, Sudoku solved
    }

    // Helper method to check if a number can be placed in the given cell
    private static boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check if the number is not already present in the current row and column
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check if the number is not present in the current 3x3 subgrid
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true;  // Number can be placed in the given cell
    }

    // Helper method to print the Sudoku grid
    private static void printSudoku(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example Sudoku grid (0 represents empty cells)
        int[][] sudokuGrid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Sudoku Puzzle:");
        printSudoku(sudokuGrid);
        System.out.println("\nSolving...\n");

        if (solveSudoku(sudokuGrid)) {
            System.out.println("Sudoku Solved:");
            printSudoku(sudokuGrid);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
