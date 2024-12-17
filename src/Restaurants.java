import javax.swing.*;
import java.awt.event.*;

public class Restaurants {
    private JPanel panel1;
    private JButton menuButtonBMC;
    private JPanel PanelMozart;
    private JPanel PanelKirac;
    private JPanel PanelBilkent;
    private JButton menuButtonMozart;
    private JButton menuButtonKirac;
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JLabel restaurantsLabel;
    private JLabel fitnessLabel;
    private JLabel goalsLabel;
    private User user;

    // Constructor
    public Restaurants(User user) {

        this.user = user;
        // Create a JFrame for the Restaurants page
        JFrame restaurantsFrame = new JFrame("Restaurants");
        restaurantsFrame.setContentPane(panel1);  // Set the panel as the content pane of the frame
        restaurantsFrame.setSize(850, 700);
        restaurantsFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        restaurantsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        restaurantsFrame.setResizable(true);
        // Show the Restaurants frame
        restaurantsFrame.setVisible(true);

        menuButtonBMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("BMC", user);
            }
        });
        menuButtonMozart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("Mozart", user);
            }
        });
        menuButtonKirac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("Kirac", user);
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                restaurantsFrame.setVisible(false);
                new HomePage(user);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);
                new UserLogin(restaurantsFrame);
            }
        });
        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                restaurantsFrame.setVisible(false);
                new Fitness(user);
            }
        });

        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                restaurantsFrame.setVisible(false);
                new FriendsPageFinal(user);
            }
        });
    }

}
