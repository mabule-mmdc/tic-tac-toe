import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleForm {
    /** Move counter attribute to determine how many moves have been made */
    private int moves = 0;
    /** Determines which player is currently turning, X player by default */
    private boolean xPlayerTurn = true;
    /**
     * Our 3x3 Tic-Tac-Toe data grid
     *
     * By default, the grid has blank values, denoted by the integer 5.
     *
     * Player X has a value of 1, and Player O has a value of 0. If any player
     * presses a button corresponding to the coordinates of the grid, it will
     * assign the value of that player to the specific coordinate pressed.
     */
    private int[][] grid = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5},
    };

    /** Reset the grid by assigning 5 to all coordinates */
    private void resetGrid() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = 5;
            }
        }
    }

    /** Setter for xPlayerTurn */
    private void setXPlayerTurn(boolean value) {
        this.xPlayerTurn = value;
    }

    /** Getter for xPlayerTurn */
    private boolean getXPlayerTurn() {
        return this.xPlayerTurn;
    }

    /** Setter for moves */
    private void setMoves(int value) {
        this.moves = value;
    }

    /** Getter for moves */
    private int getMoves() {
        return this.moves;
    }

    /**
     * Constructor function for SampleForm
     *
     * This is where we initialize all action listeners for the buttons
     * of our Tic-Tac-Toe program
     */
    public SampleForm() {
        upperLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Once a button is clicked, it does the following:
                 */

                /** 1. Check who the current player is and get the assigned label value  */
                String labelToPass = getXPlayerTurn() ? "X" : "O";

                /** 2. call setLabel() with the button instance, the label value, and the coordinate of the button */
                setLabel(upperLeftButton, labelToPass, 0, 0);

                /**
                 * For the button coordinates, it's basically just determining the indexes of
                 * row and column which corresponds to the button. For this instance, the upper left
                 * button corresponds to the 0,0 coordinate of the grid. The upper center button corresponds
                 * to the 0,1 coordinate of the grid, etc.
                 */
            }
        });
        upperCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(upperCenterButton, getXPlayerTurn() ? "X" : "O", 0, 1);
            }
        });
        upperRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(upperRightButton, getXPlayerTurn() ? "X" : "O", 0, 2);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * The reset button basically does the following:
                 *
                 * 1. Reset all button labels to blank strings
                 * 2. Reset xPlayerTurn to true
                 * 3. Reset moves counter to 0
                 * 4. Reset winner label text to blank string
                 * 5. Reset the grid values to 5 -- which denotes blank
                 */

                upperLeftButton.setText("");
                upperCenterButton.setText("");
                upperRightButton.setText("");
                centerLeftButton.setText("");
                centerMiddleButton.setText("");
                centerRightButton.setText("");
                lowerLeftButton.setText("");
                lowerCenterButton.setText("");
                lowerRightButton.setText("");
                setXPlayerTurn(true);
                setMoves(0);
                winnerLabel.setText("");
                resetGrid();
                System.out.println("Reset the board");
            }
        });
        centerLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(centerLeftButton, getXPlayerTurn() ? "X" : "O", 1, 0);
            }
        });
        centerMiddleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(centerMiddleButton, getXPlayerTurn() ? "X" : "O", 1, 1);
            }
        });
        centerRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(centerRightButton, getXPlayerTurn() ? "X" : "O", 1, 2);
            }
        });
        lowerLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(lowerLeftButton, getXPlayerTurn() ? "X" : "O", 2, 0);
            }
        });
        lowerCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(lowerCenterButton, getXPlayerTurn() ? "X" : "O", 2, 1);
            }
        });
        lowerRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel(lowerRightButton, getXPlayerTurn() ? "X" : "O", 2, 2);
            }
        });
    }

    /** The main chunk of our logic can be found here when we start labelling buttons */
    private void setLabel(JButton button, String label, int row, int column) {
        /** For any clicked button, set the label to whatever the label parameter is */
        button.setText(label);

        /** For any clicked button, add 1 to the move counter */
        this.setMoves(this.getMoves() + 1);

        /**
         * For any clicked button, assign the correct value (X = 1, O = 0) to the corresponding
         * coordinate from the row and column parameter
         */
        grid[row][column] = label == "X" ? 1 : 0;

        /** Invert the value of xPlayerTurn which indicates the current player is done with his turn */
        setXPlayerTurn(!getXPlayerTurn());

        /**
         * For any normal Tic-Tac-Toe game, winning conditions usually happen
         * after the 5th move. (non-verbatim) - John Deniel Valencia, 2023
         *
         * For efficiency, our program only starts checking for winning conditions
         * after the 5th move.
         */
        if (this.moves >= 5) {
            /**
             * By default, the first winning condition we try to check is by row
             */
            String winner = checkRowWinner(this.grid);

            /**
             * If the winner label is NOT a blank string, set the winner label
             */
            if (winner != "") {
                this.setWinnerLabel(winner);
                return;
            }

            /**
             * If there is no winning rows, we want to check the columns next
             */

            /**
             * The trick is for us to represent the columns as rows. So, we declared
             * another 3x3 2-dimensional array with blank values.
             */
            int[][] gridTransform = new int[3][3];

            /**
             * We then iterate over the rows and columns of the original grid
             */
            for (int rowIndex = 0; rowIndex < this.grid.length; rowIndex++) {
                /** For each row of the original grid, iterate over the cells */
                for(int columnIndex = 0; columnIndex < this.grid[rowIndex].length; columnIndex++) {
                    /**
                     * THIS IS THE FUN PART OF ALGORITHMS WORK.
                     *
                     * For each cell data of a row in the original grid, assign the value to the
                     * new grid per column.
                     *
                     * Example of a winning column condition:
                     * | 1 | 0 | 5 |
                     * | 1 | 0 | 5 |
                     * | 1 | 5 | 5 |
                     *
                     * Using this algorithm, becomes the new grid:
                     * | 1 | 1 | 1 |
                     * | 0 | 0 | 5 |
                     * | 5 | 5 | 5 |
                     */
                    gridTransform[columnIndex][rowIndex] = this.grid[rowIndex][columnIndex];
                }
            }

            /**
             * After generating the new grid, we just use the original row checker
             * to check for a winning condition -- row style.
             */
            winner = checkRowWinner(gridTransform);

            if (winner != "") {
                this.setWinnerLabel(winner);
                return;
            }

            /**
             * If there's no winning condition for rows and columns,
             * the last to check is a diagonal win
             */

            /** We declare an int to get the sum of numbers in a diagonal fashion */
            int diagonalChecker = 0;

            /**
             * Another algorithm at work here
             */
            for (int rowIndex = 0; rowIndex < this.grid.length; rowIndex++) {
                for(int columnIndex = 0; columnIndex < this.grid[rowIndex].length; columnIndex++) {
                    /**
                     * If the row index is the same as the column index, we add the value of that to the
                     * diagonal checker (ex. [0,0] [1,1] [2,2])
                     *
                     * Try to map these coordinates in your data grid to see. :)
                     */
                    if (rowIndex == columnIndex) {
                        diagonalChecker += this.grid[rowIndex][columnIndex];
                    }
                }
            }

            /**
             * Determine if there's a winner for an upper-left to bottom-right diagonal and vice versa
             */
            winner = checkDiagonalWinner(diagonalChecker);

            if (winner != "") {
                this.setWinnerLabel(winner);
                return;
            }

            /**
             * For the upper-right to bottom-left diagonal winning condition, we re-used the diagonal
             * checker integer, and declared a column index value holder
             */
            diagonalChecker = 0;
            int columnIndexer = 0;

            for (int rowIndex = 0; rowIndex < this.grid.length; rowIndex++) {
                /**
                 * For the first iteration, just set the column index value holder to
                 * get the last element of the array in the first row
                 *
                 * |   |   | x |
                 * |   |   |   |
                 * |   |   |   |
                 */
                if (rowIndex == 0) {
                    columnIndexer = this.grid[rowIndex].length - 1;
                }

                /**
                 * For every iteration, add the value of the row given the row index
                 * and the column index
                 */
                diagonalChecker += this.grid[rowIndex][columnIndexer];

                /**
                 * After getting the value of the element with the said column index,
                 * subtract 1 to the column index before moving to the next row.
                 *
                 * Map this out on your data grid. :)
                 */
                columnIndexer -= 1;
            }

            /**
             * Check for another winning condition from upper-right to bottom-left.
             */
            winner = checkDiagonalWinner(diagonalChecker);

            if (winner != "") {
                this.setWinnerLabel(winner);
                return;
            }
        }
    }

    private String checkDiagonalWinner(int diagonalChecker) {
        /** Same logic really about sums */
        if (diagonalChecker == 3) {
            return "X";
        } else if (diagonalChecker == 0) {
            return "O";
        }

        return "";
    }

    private String checkRowWinner(int[][] checkGrid) {
        /**
         * For any 2-dimensional 3x3 grid, iterate over the rows first
         *
         * |>|>|>| first row
         * |>|>|>| second row
         * |>|>|>| third row
         */
        for (int rowIndex = 0; rowIndex < checkGrid.length; rowIndex++) {
            /**
             * Since it is a 2-dimensional array, each row is an array of integers
             */
            int[] rowData = checkGrid[rowIndex];

            /**
             * Let's declare a variable to collect the sum of each row with 0 as default value
             */
            int rowSum = 0;

            /** Iterate over each array of integers per row */
            for (int columnIndex = 0; columnIndex < rowData.length; columnIndex++) {
                /** Let's call an element of the array for each row a cell */
                int cellData = rowData[columnIndex];

                /** Add the cell value to the sum of the row  */
                rowSum += cellData;
            }

            /**
             * Assuming X = 1, a row with 3 X's would have a sum of 3
             *
             * Return X as the winning label
             */
            if (rowSum == 3) {
                return "X";

                /**
                 * Assuming O = 0, a row with 3 O's would have a sum of 0
                 *
                 * Return O as the winning label
                 */
            } else if (rowSum == 0) {
                return "O";
            }
        }

        /**
         * Since blank elements of the grid have 5 as default value, any mix
         * of X's, O's, and blank elements will result to another value.
         *
         * Return a blank string for this scenario
         *
         * Example: A row with all blank boxes will have 15 as the sum.
         */
        return "";
    }

    private void setWinnerLabel(String winner) {
        this.winnerLabel.setText("Player " + winner + " wins!");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SampleForm");
        frame.setContentPane(new SampleForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel rootPanel;
    private JLabel header;
    private JButton upperLeftButton;
    private JButton upperCenterButton;
    private JButton upperRightButton;
    private JButton centerMiddleButton;
    private JButton centerRightButton;
    private JButton centerLeftButton;
    private JButton lowerCenterButton;
    private JButton lowerRightButton;
    private JButton lowerLeftButton;
    private JButton resetButton;
    private JLabel winnerLabel;
}
