import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleForm {
    private int moves = 0;
    private boolean xPlayerTurn = true;
    private int[][] grid = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5},
    };

    private void resetGrid() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = 5;
            }
        }
    }

    private void setXPlayerTurn(boolean value) {
        this.xPlayerTurn = value;
    }

    private boolean getXPlayerTurn() {
        return this.xPlayerTurn;
    }

    private void setMoves(int value) {
        this.moves = value;
    }

    private int getMoves() {
        return this.moves;
    }

    public SampleForm() {
        upperLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String labelToPass = getXPlayerTurn() ? "X" : "O";
                setLabel(upperLeftButton, labelToPass, 0, 0);
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

    private void setLabel(JButton button, String label, int row, int column) {
        button.setText(label);
        this.setMoves(this.getMoves() + 1);

        grid[row][column] = label == "X" ? 1 : 0;

        /** Invert the value of xPlayerTurn */
        setXPlayerTurn(!getXPlayerTurn());

        System.out.println("The total number of moves is " + this.moves);

        if (this.moves >= 5) {
            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                int[] rowData = this.grid[rowIndex];

                System.out.println("Row " + rowIndex);

                int rowSum = 0;

                for (int rowDataIndex = 0; rowDataIndex < rowData.length; rowDataIndex++) {
                    int cellData = rowData[rowDataIndex];

                    rowSum += cellData;
                }

                System.out.println("Row sum is " + rowSum);

                if (rowSum == 3) {
                    this.setWinnerLabel("X");
                    break;
                } else if (rowSum == 0) {
                    this.setWinnerLabel("O");
                    break;
                }
            }
        }
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
