import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class Goals {
    private JPanel panel1;             // Ana panel
    private JPanel resultPanel;        // Sağdaki sonuç paneli
    private JButton calculateButton;   // Hesaplama butonu
    private JTextField weightField;    // Kilo alanı
    private JTextField heightField;    // Boy alanı
    private JTextField bodyFatField;   // Yağ oranı alanı
    private JRadioButton maleRadio;    // Erkek seçimi
    private JRadioButton femaleRadio;  // Kadın seçimi
    private JComboBox<String> activityComboBox; // Aktivite seviyesi
    private JLabel HomeLogo;           // Home butonu
    private JButton logOutButton;
    private JTextField weight;
    private JTextField height;
    private JTextField BodyFat;
    private JTextField ActivityLevel;
    private JTextField gender;
    private JLabel restaurantsLabel;
    private JLabel fitnessLabel;
    private JLabel goalsLabel;
    User user;

    public Goals(User user) {
        this.user = user;
        // JFrame oluştur ve Goals.form'u bağla
        JFrame frame = new JFrame("Goals");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 700);
        frame.setLocationRelativeTo(null);

        // "Calculate" butonu için ActionListener ekle
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        // HomeLogo için MouseListener ekle
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false); // Goals ekranını kapat
                new HomePage(user); // Login ekranını aç
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UserLogin(frame);
            }
        });

        // JFrame'i görünür yap
        restaurantsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                new Restaurants(user);
            }
        });

        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                new Fitness(user);
            }
        });
        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                new FriendsPageFinal(user);
            }
        });
        frame.setVisible(true);
    }

    private void calculateResults() {
        try {
            // Kullanıcıdan girdileri al
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            double weight = Double.parseDouble(weightField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());
            double bodyFat = Double.parseDouble(bodyFatField.getText().trim());
            String activityLevel = (String) activityComboBox.getSelectedItem();

            // Kullanıcı nesnesi oluştur
            GoalsUser user = new GoalsUser(gender, 25, height, weight, bodyFat, 0, activityLevel);

            // Kullanıcının ideal kilosu ve kalori ihtiyacını hesapla
            double idealWeight = GoalPlanner.calculateIdealWeight(height, gender);
            double calorieNeeds = GoalPlanner.calculateCalorieNeeds(user);

            // Kullanıcının hedefine göre durumu belirle
            String goal = GoalPlanner.determineGoal(user);
            Map<String, Double> macros = DietPlan.generateMacros(calorieNeeds, goal);
            List<String> exercises = ExercisePlan.generatePlan(goal);

            // Sonuçları sağ panelde güncelle
            updateResults(goal, idealWeight, calorieNeeds, macros, exercises);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel1, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateResults(String goal, double idealWeight, double calorieNeeds, Map<String, Double> macros, List<String> exercises) {
        resultPanel.removeAll(); // Önceki sonuçları temizle
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        // Kullanıcının durumunu ve hedefini ekle
        resultPanel.add(new JLabel("Your Goal: " + goal));
        resultPanel.add(new JLabel("Ideal Weight: " + String.format("%.2f kg", idealWeight)));
        resultPanel.add(new JLabel("Calorie Needs: " + String.format("%.2f kcal", calorieNeeds)));

        // Makro dağılımını ekle
        resultPanel.add(new JLabel("Macros:"));
        for (Map.Entry<String, Double> macro : macros.entrySet()) {
            resultPanel.add(new JLabel(macro.getKey() + ": " + String.format("%.2f g", macro.getValue())));
        }

        // Egzersiz planını ekle
        resultPanel.add(new JLabel("Exercise Plan:"));
        for (String exercise : exercises) {
            resultPanel.add(new JLabel("- " + exercise));
        }

        // Paneli yeniden çiz
        resultPanel.revalidate();
        resultPanel.repaint();

    }
}

