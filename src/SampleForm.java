import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleForm {
    public SampleForm() {
        sampleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setX(sampleButton);
            }
        });
        upperCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setX(upperCenterButton);
            }
        });
        upperRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setX(upperRightButton);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sampleButton.setText("");
                upperCenterButton.setText("");
                upperRightButton.setText("");
            }
        });
    }

    private void setX(JButton button) {
        button.setText("X");
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
    private JButton sampleButton;
    private JButton upperCenterButton;
    private JButton upperRightButton;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton resetButton;
}
