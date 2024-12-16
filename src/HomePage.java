import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JButton restaurantsButton;
    private JButton fitnessButton;
    private JButton goalsButton;
    private JLabel restaurantLabel;
    private JLabel fitnessLabel;
    private JLabel goalsLabel;
    private JPanel homePanel;

    public HomePage() {

        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setContentPane(homePanel);
        homeFrame.setSize(800, 600);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setResizable(true);
        homeFrame.getContentPane().setBackground(Color.WHITE);


        restaurantLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                homeFrame.setVisible(false);
                new Restaurants();
            }
        });

        //****** finish when the fitness page is given
        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
                homeFrame.setVisible(false);
                new UserLogin(homeFrame);
            }
        });
        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(false);
                new Restaurants();
            }
        });
        fitnessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Fitness();
            }
        });
        goalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Goals();
            }
        });

        homeFrame.setVisible(true);
    }


}
