import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainUI {
    private JFrame frame;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JRadioButton sedentaryButton;
    private JRadioButton lightlyActiveButton;
    private JRadioButton moderatelyActiveButton;
    private JRadioButton veryActiveButton;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField bodyFatField;
    private JPanel resultPanel;

    public MainUI() {
        frame = new JFrame("Health Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // Left Panel for Inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 0, 153), 2), "User Inputs", 0, 0, new Font("Arial", Font.BOLD, 16), new Color(102, 0, 153)));

        // Gender selection using Radio Buttons
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        genderPanel.setBackground(Color.WHITE);
        maleButton.setFont(new Font("Arial", Font.BOLD, 16));
        femaleButton.setFont(new Font("Arial", Font.BOLD, 16));
        maleButton.setForeground(new Color(102, 0, 153));
        femaleButton.setForeground(new Color(102, 0, 153));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        inputPanel.add(createInputRow("Gender", genderPanel));

        ageField = createStyledTextField();
        inputPanel.add(createInputRow("Age", ageField));

        heightField = createStyledTextField();
        inputPanel.add(createInputRow("Height (cm)", heightField));

        weightField = createStyledTextField();
        inputPanel.add(createInputRow("Weight (kg)", weightField));

        bodyFatField = createStyledTextField();
        inputPanel.add(createInputRow("Body Fat (%)", bodyFatField));

        // Activity Level selection using Radio Buttons
        sedentaryButton = new JRadioButton("Sedentary");
        lightlyActiveButton = new JRadioButton("Lightly Active");
        moderatelyActiveButton = new JRadioButton("Moderately Active");
        veryActiveButton = new JRadioButton("Very Active");
        ButtonGroup activityGroup = new ButtonGroup();
        activityGroup.add(sedentaryButton);
        activityGroup.add(lightlyActiveButton);
        activityGroup.add(moderatelyActiveButton);
        activityGroup.add(veryActiveButton);

        JPanel activityPanel = new JPanel(new GridLayout(2, 2));
        activityPanel.setBackground(Color.WHITE);
        sedentaryButton.setFont(new Font("Arial", Font.BOLD, 16));
        lightlyActiveButton.setFont(new Font("Arial", Font.BOLD, 16));
        moderatelyActiveButton.setFont(new Font("Arial", Font.BOLD, 16));
        veryActiveButton.setFont(new Font("Arial", Font.BOLD, 16));
        sedentaryButton.setForeground(new Color(102, 0, 153));
        lightlyActiveButton.setForeground(new Color(102, 0, 153));
        moderatelyActiveButton.setForeground(new Color(102, 0, 153));
        veryActiveButton.setForeground(new Color(102, 0, 153));
        activityPanel.add(sedentaryButton);
        activityPanel.add(lightlyActiveButton);
        activityPanel.add(moderatelyActiveButton);
        activityPanel.add(veryActiveButton);

        inputPanel.add(createInputRow("Activity Level", activityPanel));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(Color.WHITE);
        calculateButton.setForeground(new Color(102, 0, 153));
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(new CalculateButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(calculateButton);
        inputPanel.add(buttonPanel);

        frame.add(inputPanel, BorderLayout.WEST);

        // Result Panel
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 0, 153), 2), "Results", 0, 0, new Font("Arial", Font.BOLD, 16), new Color(102, 0, 153)));
        frame.add(resultPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createInputRow(String label, JComponent component) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);

        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fieldLabel.setForeground(new Color(102, 0, 153));

        row.add(fieldLabel, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);

        return row;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setBackground(new Color(102, 0, 153));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private void updateResults(String idealWeight, String calorieNeeds, String goal, Map<String, Double> macros, List<String> exercises) {
        resultPanel.removeAll();

        JLabel resultsTitle = new JLabel("Your Results");
        resultsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        resultsTitle.setForeground(Color.WHITE);
        resultsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(resultsTitle);

        resultPanel.add(createResultBox("Ideal Weight", idealWeight));
        resultPanel.add(createResultBox("Daily Calorie Needs", calorieNeeds));
        resultPanel.add(createResultBox("Goal", goal));

        JPanel macroPanel = createResultBox("Diet Plan (Macros)", "");
        macros.forEach((k, v) -> {
            JLabel macroLabel = new JLabel(k + ": " + String.format("%.2f g", v));
            macroLabel.setFont(new Font("Arial", Font.BOLD, 16));
            macroLabel.setForeground(Color.WHITE);
            macroLabel.setHorizontalAlignment(SwingConstants.CENTER);
            macroPanel.add(macroLabel);
        });
        resultPanel.add(macroPanel);

        JPanel exercisePanel = createResultBox("Exercise Plan", "");
        exercises.forEach(ex -> {
            JLabel exerciseLabel = new JLabel(ex);
            exerciseLabel.setFont(new Font("Arial", Font.BOLD, 16));
            exerciseLabel.setForeground(Color.WHITE);
            exerciseLabel.setHorizontalAlignment(SwingConstants.CENTER);
            exercisePanel.add(exerciseLabel);
        });
        resultPanel.add(exercisePanel);

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private JPanel createResultBox(String title, String content) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(new Color(102, 0, 153));
        box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 1), title, 0, 0, new Font("Arial", Font.BOLD, 14), Color.WHITE));

        if (!content.isEmpty()) {
            JLabel contentLabel = new JLabel(content);
            contentLabel.setFont(new Font("Arial", Font.BOLD, 16));
            contentLabel.setForeground(Color.WHITE);
            contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            box.add(contentLabel);
        }

        return box;
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String gender = maleButton.isSelected() ? "Male" : "Female";
                String activityLevel = sedentaryButton.isSelected() ? "Sedentary"
                        : lightlyActiveButton.isSelected() ? "Lightly Active"
                        : moderatelyActiveButton.isSelected() ? "Moderately Active"
                        : "Very Active";
                int age = Integer.parseInt(ageField.getText().trim());
                double height = Double.parseDouble(heightField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                double bodyFat = Double.parseDouble(bodyFatField.getText().trim());
    
                double idealWeight = GoalPlanner.calculateIdealWeight(height, gender);
                String calorieNeeds = String.format("%.2f kcal", GoalPlanner.calculateCalorieNeeds(new GoalsUser(gender, age, height, weight, bodyFat, idealWeight, activityLevel)));
                String goal = GoalPlanner.determineGoal(new GoalsUser(gender, age, height, weight, bodyFat, idealWeight, activityLevel));
    
                Map<String, Double> macros = DietPlan.generateMacros(Double.parseDouble(calorieNeeds.split(" ")[0]), goal);
                List<String> exercises = ExercisePlan.generatePlan(goal);
    
                updateResults(String.format("%.2f kg", idealWeight), calorieNeeds, goal, macros, exercises);
    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid inputs.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}    

