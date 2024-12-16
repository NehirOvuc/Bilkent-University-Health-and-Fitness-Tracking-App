//CODE IS NOT FINISHED THE IMPLEMENTATIONS ARE NOT ADDED YET


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class Friends extends JFrame {
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JButton ADDButton;
    private JButton REMOVEButton;
    private JFormattedTextField runWithAliFormattedTextField;
    private JTextField swimming10kmWithSelimTextField;
    private JTextField cycling3kmWithCeylinTextField;
    private JFormattedTextField selimFormattedTextField;
    private JFormattedTextField nehirFormattedTextField;
    private JFormattedTextField fatimaFormattedTextField;
    private JFormattedTextField leylaFormattedTextField;
    private JFormattedTextField emirFormattedTextField;
    private JButton clickHereToCreateButton;
    private JFormattedTextField yourContactSudeHasFormattedTextField;
    private JFormattedTextField yourContactEmirHasFormattedTextField;
    private JLabel restaurantLabel;
    private JLabel fitnessLabel;
    private JLabel goalsLabel;


    public Friends() {
        JFrame friendsPage = new JFrame("Friends");
        friendsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsPage.setSize(900, 900);
        friendsPage.setLocationRelativeTo(null);
        friendsPage.setResizable(true);
        friendsPage.getContentPane().setBackground(Color.WHITE);


        // Create a JPanel for the top bar
        JPanel topBar = new JPanel(new BorderLayout());

// Create a panel for HomeLogo, Restaurants, and Goals
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Adjust spacing

        restaurantLabel = new JLabel("Restaurants");
        fitnessLabel = new JLabel("Fitness");
        goalsLabel = new JLabel("Goals");

        leftPanel.add(HomeLogo);
        leftPanel.add(restaurantLabel);
        leftPanel.add(fitnessLabel);
        leftPanel.add(goalsLabel);

        topBar.add(leftPanel, BorderLayout.WEST);

        topBar.add(logOutButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topBar, BorderLayout.NORTH);

        JPanel blueLine = new JPanel();
        blueLine.setBackground(new Color(0, 0, 98));
        blueLine.setPreferredSize(new Dimension(900, 4));
        // mainPanel.add(blueLine, BorderLayout.SOUTH);

        friendsPage.add(mainPanel);
        friendsPage.setVisible(true);


        HomeLogo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                friendsPage.setVisible(false);
                new UserLogin(friendsPage);
            }
        });

        restaurantLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                friendsPage.setVisible(false);
                new Restaurants();
            }
        });

        //****** finish when the fitness page is given
        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                friendsPage.setVisible(false);
                //new Fitness();
            }
        });

        //****** finish when the goals page is given
        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //new Goals();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendsPage.setVisible(false);
                new UserLogin(friendsPage);
            }
        });
    }
}
