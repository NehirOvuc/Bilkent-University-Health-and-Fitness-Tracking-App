import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fitness {
    private JPanel panel1;
    private JButton moreButtonRunning;
    private JPanel PanelRunning;
    private JPanel PanelSwimming;
    private JPanel PanelWeightlifting;
    private JButton moreButtonSwimming;
    private JButton moreButtonWeightlifting;
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JPanel PanelBilkent;
    private JLabel runningPhoto;
    private JButton getMoreButtonWeightlifting;
    private User user;

    // Constructor
    public Fitness(User user) {

        this.user = user;
        // Create a JFrame for the Restaurants page
        JFrame sportsFrame = new JFrame("Sports");
        sportsFrame.setContentPane(panel1);  // Set the panel as the content pane of the frame
        sportsFrame.setSize(900, 900);
        sportsFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        sportsFrame.pack();
        sportsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Disable resizing
        sportsFrame.setResizable(false);
        // Show the Restaurants frame

        moreButtonRunning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);

                // Open the Restaurants frame
                new SportsMenu("Running", user);
            }
        });
        moreButtonRunning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);

                // Open the Restaurants frame
                new SportsMenu("Swimming", user);
            }
        });
        moreButtonWeightlifting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);

                // Open the Restaurants frame
                new SportsMenu("Weightlifting", user);
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sportsFrame.setVisible(false);
                new HomePage(user);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);
                new UserLogin(sportsFrame);
            }
        });
        moreButtonWeightlifting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);
                new SportsMenu("Weightlifting", user);
            }
        });
        moreButtonRunning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);
                new SportsMenu("Running", user);
            }
        });
        moreButtonSwimming.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsFrame.setVisible(false);
                new SportsMenu("Swimming", user);
            }
        });
        sportsFrame.setVisible(true);
    }

}
