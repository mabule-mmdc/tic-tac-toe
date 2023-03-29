import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorPHEmployeeApp {
    private JPanel mainPanel;
    private JTextField employeeNumber;
    private JButton computePayrollButton;
    private JTextField dateRange;
    private JLabel showContent;

    public MotorPHEmployeeApp() {
        computePayrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showContent.setText(employeeNumber.getText() + dateRange.getText());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MotorPHEmployeeApp");
        frame.setContentPane(new MotorPHEmployeeApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
