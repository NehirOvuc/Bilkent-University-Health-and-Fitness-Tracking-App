import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    // Constructor
    public Restaurants() {

        // Create a JFrame for the Restaurants page
        JFrame restaurantsFrame = new JFrame("Restaurants");
        restaurantsFrame.setContentPane(panel1);  // Set the panel as the content pane of the frame
        restaurantsFrame.setSize(900, 900);
        restaurantsFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        restaurantsFrame.pack();
        restaurantsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Disable resizing
        restaurantsFrame.setResizable(false);
        // Show the Restaurants frame
        restaurantsFrame.setVisible(true);

        menuButtonBMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("BMC");
            }
        });
        menuButtonMozart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("Mozart");
            }
        });
        menuButtonKirac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);

                // Open the Restaurants frame
                new Menu("Kirac");
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                restaurantsFrame.setVisible(false);
                new HomePage();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantsFrame.setVisible(false);
                new UserLogin(restaurantsFrame);
            }
        });
    }

}
