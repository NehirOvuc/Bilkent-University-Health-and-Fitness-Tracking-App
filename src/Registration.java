import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registration extends JDialog {
    private JPanel registerPanel;
    private JTextField tFUserName;
    private JTextField tFAge;
    private JTextField tFHeight;
    private JTextField tFWeight;
    private JTextField tFBFPercentage;
    private JButton confirmButton;
    private JButton cancelButton;
    private JRadioButton maleMRadioButton;
    private JRadioButton femaleFRadioButton;
    private JPasswordField pFPassword;
    private JButton cancelButton2;
    private ButtonGroup genderButtonGroup;

    public Registration(JFrame parent){
        super(parent);
        setTitle("Create a new user account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(610, 520));
        setModal(true);
        setLocationRelativeTo(parent);
        //setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin userLogin = new UserLogin(parent);
            }
        });

        cancelButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String userName = tFUserName.getText();
        String password = String.valueOf(pFPassword.getPassword());
        String gender = maleMRadioButton.isSelected() ? "M" : "F";
        try{
            int age = Integer.parseInt(tFAge.getText());
            int height = Integer.parseInt(tFHeight.getText());
            int weight = Integer.parseInt(tFWeight.getText());
            int bFPercentage = Integer.parseInt(tFBFPercentage.getText());

            if(userName.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Try again", JOptionPane.ERROR_MESSAGE);
            }
            else if(age <= 0 || height <= 0 || weight <= 0 || bFPercentage <= 0){
                JOptionPane.showMessageDialog(null, "Please enter positive values");
            }
            else if(age < 18){
                JOptionPane.showMessageDialog(null, "You need to be above 18 years old.");
            }
            user = addUserToDatabase(userName, password, gender, age, height, weight, bFPercentage);
            if (user != null){
                JFrame frame = new JFrame();
                dispose();
                new UserLogin(frame);
            }
            else{
                JOptionPane.showMessageDialog(null, "Failed to register new user");
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
        }
    }

    public User user;
    private User addUserToDatabase(String userName, String password, String gender, int age, int height, int weight, int bFPercentage) {
        User user = null;
        final String DB_URL = "jdbc:mysql://ndpm.asuscomm.com:46603/bhfdb";
        final String USERNAME = "root";
        final String PASSWORD = "Bhf123";
        /*
        final String DB_URL = "jdbc:mysql://localhost:3306/bhfdb";
        final String USERNAME = "root";
        final String PASSWORD = "";
        */
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO bhfusers(userName, password, gender, age, height, weight, bFPercentage) " + "Values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, gender);
            pstmt.setInt(4, age);
            pstmt.setInt(5, height);
            pstmt.setInt(6, weight);
            pstmt.setInt(7, bFPercentage);

            int addedRows = pstmt.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.userName = userName;
                user.password = password;
                user.gender = gender;
                user.age = age;
                user.height = height;
                user.weight = weight;
                user.bFPercentage = bFPercentage;
            }

            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        Registration myForm = new Registration(null);
        User user = myForm.user;
        if(user != null){
            System.out.println("Successful registration of: " + user.userName);
        }
        else{
            System.out.println("Registration cancelled");
        }
    }

}
