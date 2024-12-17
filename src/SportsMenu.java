import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SportsMenu {

    private JScrollPane scrollBar;
    private JPanel panelS;
    private JLabel HomeLogo;
    private JLabel navRes;
    private JButton logOutButton;
    private JLabel sportPhoto;
    private JLabel sportTitle;
    private JRadioButton lowRadioButton;
    private JRadioButton highRadioButton;
    private JRadioButton mediumRadioButton;
    private JButton calculateButton;
    private JTextField textFieldCalculation;
    private JLabel forGoalsLabel;
    private JRadioButton lowRadioButton1;
    private JRadioButton mediumRadioButton1;
    private JRadioButton highRadioButton1;
    private JComboBox HoursComboBox;
    private JButton addToTheGoalsButton;
    private JTextArea textAreaGoals;
    private JLabel fitnessLabel;
    private JLabel goalsLabel;
    private SportType running;
    private SportType swimming;
    private SportType weightlifting;
    private User user;

    public SportsMenu(String sportName, User user) {
        this.user = user;
        running = new SportType("Running", "running", 480, 620, 800);
        swimming = new SportType("Swimming", "swimming", 180, 300, 420);
        weightlifting = new SportType("Weightlifting", "weightlifting", 390, 440, 630);
        textFieldCalculation.setText("");


        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/Sports/"+sportName.toLowerCase()+".jpg"));
        Image resizedImage = originalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        sportPhoto.setIcon(new ImageIcon(resizedImage));

        sportTitle.setText(sportName);

        // Create a JFrame for the Restaurants page
        JFrame SportsFrame = new JFrame("SportsMenu");
        SportsFrame.setContentPane(panelS);  // Set the panel as the content pane of the frame
        SportsFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        SportsFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        SportsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ensure the scroll bar starts at the top
        SwingUtilities.invokeLater(() -> {
            if (scrollBar != null) {
                JScrollBar verticalScrollBar = scrollBar.getVerticalScrollBar();
                verticalScrollBar.setValue(0);  // Scroll to the top
            }
        });


        navRes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SportsFrame.setVisible(false);
                new Restaurants(user);
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SportsFrame.setVisible(false);
                new HomePage(user);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SportsFrame.setVisible(false);
                new UserLogin(SportsFrame);
            }
        });
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SportType sport;
                if (sportName.equalsIgnoreCase("running")) {
                    sport = running;
                } else if (sportName.equalsIgnoreCase("swimming")) {
                    sport = swimming;
                } else {
                    sport = weightlifting;
                }

                if (lowRadioButton.isSelected()) {
                    textFieldCalculation.setText("You can burn approximately " + sport.getSportLowTempoBurnedCaloriesPerHour() + " calories in 1 hour of " + sport.getSportTitle() + " at low tempo.");
                } else if (mediumRadioButton.isSelected()) {
                    textFieldCalculation.setText("You can burn approximately " + sport.getSportMediumTempoBurnedCaloriesPerHour() + " calories in 1 hour of " + sport.getSportTitle() + " at medium tempo.");
                } else if (highRadioButton.isSelected()) {
                    textFieldCalculation.setText("You can burn approximately " + sport.getSportHighTempoBurnedCaloriesPerHour() + " calories in 1 hour of " + sport.getSportTitle() + " at high tempo.");
                }

            }
        });
        SportsFrame.setVisible(true);
        addToTheGoalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!highRadioButton1.isSelected() && !mediumRadioButton1.isSelected() && !lowRadioButton1.isSelected()) {
                    textAreaGoals.setText("Please select the intensity level.");
                }
                else {
                    textAreaGoals.setText("Successfully added to the goals list!");
                }

            }
        });
        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SportsFrame.setVisible(false);
                new Fitness(user);
            }
        });
        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SportsFrame.setVisible(false);
                new FriendsPageFinal(user);
            }
        });
    }

    // Configure text area settings
    private void configureTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);  // Enable word wrap
        textArea.setWrapStyleWord(true);  // Ensure wrapping happens at word boundaries
        textArea.setPreferredSize(new Dimension(300, 150)); // Set preferred size
    }

}
