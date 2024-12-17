import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserLogin extends JDialog {
    private JTextField tFUserName;
    private JPasswordField pFPassword;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel userLoginPanel;

    public UserLogin(JFrame parent) {
        super(parent);
        setTitle("User Login");
        setContentPane(userLoginPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tFUserName.getText();
                String password = String.valueOf(pFPassword.getPassword());

                user = getAuthenticatedUser(userName, password);

                if(user != null) {
                    //TODO: send the user to the class
                    new HomePage(user);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(parent, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Registration register = new Registration(parent);
            }
        });
        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String userName, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://ndpm.asuscomm.com:46603/bhfdb";
        final String USERNAME = "root";
        final String PASSWORD = "Bhf123";
        /*
        final String DB_URL = "jdbc:mysql://localhost:3306/bhfdb";
        final String USERNAME = "root";
        final String PASSWORD = "";
        */


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM bhfusers WHERE userName = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                user = new User();
                user.userName = rs.getString("userName");
                user.gender = rs.getString("gender");
                user.age = rs.getInt("age");
                user.height = rs.getInt("height");
                user.weight = rs.getInt("weight");
                user.bFPercentage = rs.getInt("bFPercentage");
            }

            stmt.close();
            conn.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin(null);
        User user = userLogin.user;
        if(user != null) {
            System.out.println("Successful Authentication of: " + user.userName);
        }
        else{
            System.out.println("Authentication Canceled");
        }
    }
}

