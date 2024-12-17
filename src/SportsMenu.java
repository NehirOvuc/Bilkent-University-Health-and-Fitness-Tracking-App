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
    private SportType running;
    private SportType swimming;
    private SportType weightlifting;

    public SportsMenu(String sportName) {
        running = new SportType("Running", "running", 480, 620, 800);
        swimming = new SportType("Swimming", "swimming", 180, 300, 420);
        weightlifting = new SportType("Weightlifting", "weightlifting", 390, 440, 630);
        TXThandler th = new TXThandler(sportName);
        textFieldCalculation.setText("");

        sportPhoto.setIcon(new ImageIcon("src/images/Sports/"+sportName.toLowerCase()+"/.png"));
        sportTitle.setText(SportType.getSportTitle());

        // Create a JFrame for the Restaurants page
        JFrame SportsFrame = new JFrame("SportsMenu");
        SportsFrame.setContentPane(panelS);  // Set the panel as the content pane of the frame
        SportsFrame.setSize(850, 900);
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

                new Fitness();
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SportsFrame.setVisible(false);

                new Login();
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
                TextField textFieldCalculation = new TextField();

                if(sportTitle.getText().equalsIgnoreCase("running"))
                {
                    if(lowRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + running.getSportLowTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in low tempo.");
                    }
                    else if(mediumRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + running.getSportMediumTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in medium tempo.");
                    }
                    else if(highRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + running.getSportHighTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in high tempo.");
                    }
                }
                else if(sportTitle.getText().equalsIgnoreCase("swimming"))
                {
                    if(lowRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + swimming.getSportLowTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in low tempo.");
                    }
                    else if(mediumRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + swimming.getSportMediumTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in medium tempo.");
                    }
                    else if(highRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + swimming.getSportHighTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in high tempo.");
                    }
                }
                else if(sportTitle.getText().equalsIgnoreCase("weightlifting"))
                {
                    if(lowRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + weightlifting.getSportLowTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in low tempo.");
                    }
                    else if(mediumRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + weightlifting.getSportMediumTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in medium tempo.");
                    }
                    else if(highRadioButton.isSelected())
                    {
                        textFieldCalculation.setText("You can burn approximately " + weightlifting.getSportHighTempoBurnedCaloriesPerHour() + " calories by 1 hour " + SportType.getSportTitle() +" in high tempo.");
                    }
                }
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
