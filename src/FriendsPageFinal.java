import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FriendsPageFinal extends JFrame {
    private JPanel friendManagerPanel;
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JButton addFriendButton;
    private JButton removeFriendButton;
    private JButton challengeButton;
    private JLabel restaurantButton;
    private JLabel fitnessButton;
    private JButton showFriendsButton;
    private JTextField friendIDTextField;
    private JTextArea friendsListArea;
    private JLabel userIDLabel;
    private JLabel goalsButton;

    private User currentUser; // Logged-in user
    private int userID;       // User's ID retrieved from the database


    // Database connection details
    final String DB_URL = "jdbc:mysql://ndpm.asuscomm.com:46603/bhfdb";
    final String USERNAME = "root";
    final String PASSWORD = "Bhf123";

    public FriendsPageFinal(User user) {
        this.currentUser = user;

        // Initialize components
        setTitle("Friend Manager");
        setContentPane(friendManagerPanel);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fetch the logged-in user's ID
        this.userID = fetchUserID();
        userIDLabel.setText(userID + "");

        if (userID == -1) {
            JOptionPane.showMessageDialog(this, "Could not retrieve your user ID. Exiting.");
            dispose();
            return;
        }

        // Action listener for "Add Friend" button
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFriend();
            }
        });

        // Action listener for "Show Friends" button
        showFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFriends();
            }
        });

        // Action listener for "Remove Friend" button
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFriend();
            }
        });

        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Goals(user);
            }
        });
        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                new HomePage(user);
            }
        });
        restaurantButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                new Restaurants(user);
            }
        });
        fitnessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                //new Fitness();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new JFrame("Login");
                new UserLogin(frame);
            }
        });
        setVisible(true);
    }

    /**
     * Fetch the userID of the currently logged-in user based on userName.
     */
    private int fetchUserID() {
        int fetchedID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String query = "SELECT userID FROM bhfusers WHERE userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, currentUser.userName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fetchedID = rs.getInt("userID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error while retrieving user ID.");
        }

        return fetchedID;
    }

    /**
     * Adds a new friend to the database.
     */
    private void addFriend() {
        int friendID;

        try {
            friendID = Integer.parseInt(friendIDTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid User ID. Please enter a number.");
            return;
        }

        if (friendID == userID) {
            JOptionPane.showMessageDialog(this, "You cannot add yourself as a friend!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // Check if the friendship already exists
            String checkFriendshipSQL = "SELECT * FROM bhffriends WHERE (friend1ID = ? AND friend2ID = ?) OR (friend1ID = ? AND friend2ID = ?)";
            PreparedStatement checkStmt = conn.prepareStatement(checkFriendshipSQL);
            checkStmt.setInt(1, userID);
            checkStmt.setInt(2, friendID);
            checkStmt.setInt(3, friendID);
            checkStmt.setInt(4, userID);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "You are already friends with this user.");
                return;
            }

            // Insert the new friendship
            String insertFriendshipSQL = "INSERT INTO bhffriends (friend1ID, friend2ID) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertFriendshipSQL);
            insertStmt.setInt(1, userID);
            insertStmt.setInt(2, friendID);

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Friend added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add friend.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error. Could not add friend.");
        }
    }

    /**
     * Removes a friend from the database.
     */
    private void removeFriend() {
        int friendID;

        try {
            friendID = Integer.parseInt(friendIDTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid User ID. Please enter a number.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // Delete friendship where friend1ID and friend2ID match in either direction
            String removeFriendSQL = "DELETE FROM bhffriends WHERE (friend1ID = ? AND friend2ID = ?) OR (friend1ID = ? AND friend2ID = ?)";
            PreparedStatement pstmt = conn.prepareStatement(removeFriendSQL);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, friendID);
            pstmt.setInt(3, friendID);
            pstmt.setInt(4, userID);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Friend removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No friendship found with the provided User ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error. Could not remove friend.");
        }
    }

    /**
     * Displays the list of friends for the current user.
     */
    private void showFriends() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String getFriendsSQL = "SELECT u.userName " +
                    "FROM bhfusers u " +
                    "JOIN bhffriends f ON (u.userID = f.friend2ID AND f.friend1ID = ?) " +
                    "OR (u.userID = f.friend1ID AND f.friend2ID = ?)";
            PreparedStatement pstmt = conn.prepareStatement(getFriendsSQL);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, userID);

            ResultSet rs = pstmt.executeQuery();

            // Display the list of friends in the text area
            StringBuilder friendsList = new StringBuilder("Your Friends:\n");
            while (rs.next()) {
                friendsList.append("Friend Name: " + rs.getString("userName") ).append("\n");
            }

            friendsListArea.setText(friendsList.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving friends list.");
        }
    }
}

