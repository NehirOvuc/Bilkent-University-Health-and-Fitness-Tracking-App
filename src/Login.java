import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JButton menuButton;
    private JPanel panel1;

    public Login() {
        // Initialize the components
        panel1 = new JPanel();
        menuButton = new JButton("Go to Restaurants");

        // Add the button to the panel
        panel1.add(menuButton);

        // Create the JFrame for the login
        JFrame frame = new JFrame("Login");
        frame.setContentPane(panel1);  // Set the content pane to the panel
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen

        // Add action listener to the button
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the current frame
                frame.setVisible(false);

                // Open the Restaurants frame
                new Restaurants();
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create the Login frame
        new Login();
    }
}
