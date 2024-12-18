import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;

public class Menu {
    private JCheckBox checkBox1;
    private JTextField minValueTextField;
    private JPanel panel1;
    private JTextField maxValueTextField;
    private JTextArea mealDesc1;
    private JLabel mealTitle1;
    private JPanel m1p1;
    private JTextArea mealDesc2;
    private JTextArea mealDesc3;
    private JTextArea mealDesc4;
    private JTextArea mealDesc5;
    private JScrollPane scrollBar;
    private JLabel resPhoto;
    private JLabel resTitle;
    private JLabel mealPhoto1;
    private JLabel mealCalc1;
    private JLabel mealPhoto2;
    private JLabel mealTitle2;
    private JLabel mealCalc2;
    private JLabel mealPhoto3;
    private JLabel mealTitle3;
    private JLabel mealCalc3;
    private JLabel mealPhoto4;
    private JLabel mealTitle4;
    private JLabel mealCalc4;
    private JLabel mealPhoto5;
    private JLabel mealTitle5;
    private JLabel mealCalc5;
    private JLabel navRes;
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JButton filterButton;
    private JLabel goalsLabel;
    private JLabel fitnessLabel;
    private int mealNum;
    private User user;

    public Menu(String resName, User user) {
        this.user = user;
        mealNum = 5;
        TXThandler th = new TXThandler(resName);
        ArrayList<Meal> meals = th.meals;


        resPhoto.setIcon(new ImageIcon("src/images/"+resName+"/"+Meal.getResPhoto()+".png"));
        resTitle.setText(Meal.getResTitle());



        // Create a JFrame for the Restaurants page
        JFrame MenuFrame = new JFrame("Menu");
        MenuFrame.setContentPane(panel1);  // Set the panel as the content pane of the frame
        MenuFrame.setSize(850, 700);
        MenuFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ensure the scroll bar starts at the top
        SwingUtilities.invokeLater(() -> {
            if (scrollBar != null) {
                JScrollBar verticalScrollBar = scrollBar.getVerticalScrollBar();
                verticalScrollBar.setValue(0);  // Scroll to the top
            }
        });

        // Configure text areas
        configureTextArea(mealDesc1);
        configureTextArea(mealDesc2);
        configureTextArea(mealDesc3);
        configureTextArea(mealDesc4);
        configureTextArea(mealDesc5);

        // Disable resizing
        MenuFrame.setResizable(true);

        for (int i = 0; i < Math.min(meals.size(), 5); i++) {
            Meal m = meals.get(i);
            setMealData(i, m, resName);
        }

        // Show the Restaurants frame
        MenuFrame.setVisible(true);


        navRes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame.setVisible(false);

                new Restaurants(user);
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame.setVisible(false);

                new HomePage(user);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuFrame.setVisible(false);
                new UserLogin(MenuFrame);
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxValue = 100000;
                int minValue = 0;

                String maxValueString = maxValueTextField.getText();
                String minValueString = minValueTextField.getText();

                // Parse user input for min and max calorie values
                if (!maxValueString.equals("Value") && !minValueString.equals("Value")) {
                    try {
                        maxValue = Integer.parseInt(maxValueString);
                        minValue = Integer.parseInt(minValueString);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return; // Exit the method if invalid input
                    }

                    if (minValue > maxValue) {
                        JOptionPane.showMessageDialog(null, "Minimum number cannot be greater than maximum number");
                        return;
                    }
                }

                // Iterate through meals and show/hide components based on calorie range
                for (int i = 0; i < Math.min(meals.size(), 5); i++) {
                    Meal m = meals.get(i);
                    boolean withinRange = (m.getMealCal() <= maxValue) && (m.getMealCal() >= minValue);

                    // Get components for this meal
                    JLabel mealPhoto = getMealPhotoLabel(i);
                    JTextArea mealDesc = getMealDescriptionTextArea(i);
                    JLabel mealTitle = getMealTitleLabel(i);
                    JLabel mealCalc = getMealCalLabel(i);

                    // Show or hide components based on the range
                    mealPhoto.setVisible(withinRange);
                    mealDesc.setVisible(withinRange);
                    mealTitle.setVisible(withinRange);
                    mealCalc.setVisible(withinRange);
                }
            }
        });

        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame.setVisible(false);
                new FriendsPageFinal(user);
            }
        });
        fitnessLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame.setVisible(false);
                new Fitness(user);
            }
        });
        goalsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuFrame.setVisible(false);
                new FriendsPageFinal(user);
            }
        });
    }

    // Helper methods for meal data handling
    private void setMealData(int mealIndex, Meal meal, String resName) {
        String imagePath = "src/images/" + resName + "/" + meal.getMealPhoto() + ".png";
        System.out.println("Loading image: " + imagePath);  // Debugging image path

        JLabel mealPhoto = getMealPhotoLabel(mealIndex);
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            mealPhoto.setIcon(new ImageIcon(imagePath));
        } else {
            System.err.println("Image not found: " + imagePath);  // Log if image file doesn't exist
        }

        JTextArea mealDesc = getMealDescriptionTextArea(mealIndex);
        mealDesc.setText(meal.getMealDescription());

        JLabel mealTitle = getMealTitleLabel(mealIndex);
        mealTitle.setText(meal.getMealTitle());

        JLabel mealCalc = getMealCalLabel(mealIndex);
        mealCalc.setText("Calories for 100g: " + meal.getMealCal() + " kcal");
    }


    // Helper methods to get labels for meal images, descriptions, titles, and calorie counts
    private JLabel getMealPhotoLabel(int index) {
        switch (index) {
            case 0: return mealPhoto1;
            case 1: return mealPhoto2;
            case 2: return mealPhoto3;
            case 3: return mealPhoto4;
            case 4: return mealPhoto5;
            default: return null;
        }
    }

    private JTextArea getMealDescriptionTextArea(int index) {
        switch (index) {
            case 0: return mealDesc1;
            case 1: return mealDesc2;
            case 2: return mealDesc3;
            case 3: return mealDesc4;
            case 4: return mealDesc5;
            default: return null;
        }
    }

    private JLabel getMealTitleLabel(int index) {
        switch (index) {
            case 0: return mealTitle1;
            case 1: return mealTitle2;
            case 2: return mealTitle3;
            case 3: return mealTitle4;
            case 4: return mealTitle5;
            default: return null;
        }
    }

    private JLabel getMealCalLabel(int index) {
        switch (index) {
            case 0: return mealCalc1;
            case 1: return mealCalc2;
            case 2: return mealCalc3;
            case 3: return mealCalc4;
            case 4: return mealCalc5;
            default: return null;
        }
    }

    // Configure text area settings
    private void configureTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);  // Enable word wrap
        textArea.setWrapStyleWord(true);  // Ensure wrapping happens at word boundaries
        textArea.setPreferredSize(new Dimension(300, 150)); // Set preferred size
    }


}
