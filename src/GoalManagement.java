import javax.swing.*;
import java.awt.*;

class GoalManagementUI {
    private JFrame frame;
    private JTextField calorieGoalField;
    private JTextField waterGoalField;
    private JTextArea progressArea;

    public GoalManagementUI() {
        frame = new JFrame("Goal Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        calorieGoalField = new JTextField();
        waterGoalField = new JTextField();
        progressArea = new JTextArea();

        JButton setGoalsButton = new JButton("Set Goals");
        setGoalsButton.addActionListener(e -> setGoals());

        panel.add(new JLabel("Calorie Goal:"));
        panel.add(calorieGoalField);
        panel.add(new JLabel("Water Goal (ml):"));
        panel.add(waterGoalField);
        panel.add(setGoalsButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(progressArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void setGoals() {
        try {
            int calorieGoal = Integer.parseInt(calorieGoalField.getText());
            int waterGoal = Integer.parseInt(waterGoalField.getText());
            
            progressArea.setText("Goals Set!\nCalorie Goal: " + calorieGoal + "\nWater Goal: " + waterGoal + " ml");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
