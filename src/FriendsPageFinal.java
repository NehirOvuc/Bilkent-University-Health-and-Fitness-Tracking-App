import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendsPageFinal {
    private JPanel panel1;
    private JLabel HomeLogo;
    private JButton logOutButton;
    private JButton ADDButton;
    private JButton REMOVEButton;
    private JFormattedTextField challenge1;
    private JFormattedTextField challenge2;
    private JFormattedTextField challenge3;
    private JFormattedTextField friend1;
    private JFormattedTextField friend2;
    private JFormattedTextField friend3;
    private JFormattedTextField friend4;
    private JFormattedTextField friend5;
    private JButton CLICKHERETOCREATEButton;
    private JLabel restaurantButton;
    private JLabel fitnessButton;
    private JLabel goalsButton;

    // The currently logged-in user
    private User currentUser;

    // Database credentials
    private final String DB_URL = "jdbc:mysql://ndpm.asuscomm.com:46603/bhfdb";
    private final String DB_USERNAME = "bhfapp"; // Updated to use dedicated user
    private final String DB_PASSWORD = "SecurePassword123"; // Use a strong password

    // List to hold friends
    private List<String> friendsList = new ArrayList<>();

    // Constructor accepting the current user
    public FriendsPageFinal(User user) {
        this.currentUser = user;

        JFrame friendsFrame = new JFrame("Friends");
        friendsFrame.setContentPane(panel1);
        friendsFrame.setSize(900, 900);
        friendsFrame.setLocationRelativeTo(null);
        friendsFrame.pack();
        friendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsFrame.setResizable(true);
        friendsFrame.setVisible(true);

        // Initialize the Friends Table
        initializeFriendsTable();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            loadFriends(conn);
            displayFriends();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(friendsFrame, "Database Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            friendsFrame.dispose();
            return;
        }

        HomeLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsFrame.setVisible(false);
                new UserLogin(null);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendsFrame.setVisible(false);
                new UserLogin(null);
            }
        });

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddFriendDialog(friendsFrame);
            }
        });

        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRemoveFriendDialog(friendsFrame);
            }
        });

        CLICKHERETOCREATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //not done yet
            }
        });

        restaurantButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsFrame.setVisible(false);
                new Restaurants();
            }
        });

        fitnessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsFrame.setVisible(false);
                // new Fitness();
            }
        });

        goalsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsFrame.setVisible(false);
                // new Goals();
            }
        });
    }

    private void initializeFriendsTable() {

    }

    /**
     * loading the list of friends from the database into friendsList.
     *
     * @param conn The active database connection.
     * @throws SQLException If a database access error occurs.
     */
    private void loadFriends(Connection conn) throws SQLException {
        String query = "SELECT "
                + "CASE "
                + "WHEN firstUser = ? THEN secondUser "
                + "ELSE firstUser "
                + "END AS friendName "
                + "FROM bhffriends "
                + "WHERE firstUser = ? OR secondUser = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, currentUser.getUserName());
            pstmt.setString(2, currentUser.getUserName());
            pstmt.setString(3, currentUser.getUserName());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String friendName = rs.getString("friendName");
                    friendsList.add(friendName);
                }
            }
        }
    }

    private void displayFriends() {
        friend1.setText("");
        friend2.setText("");
        friend3.setText("");
        friend4.setText("");
        friend5.setText("");

        for (int i = 0; i < friendsList.size() && i < 5; i++) {
            String friend = friendsList.get(i);
            switch (i) {
                case 0:
                    friend1.setText(friend);
                    break;
                case 1:
                    friend2.setText(friend);
                    break;
                case 2:
                    friend3.setText(friend);
                    break;
                case 3:
                    friend4.setText(friend);
                    break;
                case 4:
                    friend5.setText(friend);
                    break;
                default:
                    break;
            }
        }
    }

    private void openAddFriendDialog(JFrame parentFrame) {
        JDialog addFriendDialog = new JDialog(parentFrame, "Add Friend", true);
        addFriendDialog.setSize(350, 200);
        addFriendDialog.setLayout(new BorderLayout());
        addFriendDialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("Enter Friend's Username:");
        JTextField friendUsernameField = new JTextField();
        JPanel buttonsPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        buttonsPanel.add(submitButton);
        buttonsPanel.add(cancelButton);

        panel.add(label);
        panel.add(friendUsernameField);
        panel.add(buttonsPanel);

        addFriendDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendUsername = friendUsernameField.getText().trim();

                if (friendUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(addFriendDialog, "Username cannot be empty.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (friendUsername.equals(currentUser.getUserName())) {
                    JOptionPane.showMessageDialog(addFriendDialog, "You cannot add yourself as a friend.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidUsername(friendUsername)) {
                    JOptionPane.showMessageDialog(addFriendDialog, "Invalid username format.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                    if (!isUserExists(conn, friendUsername)) {
                        JOptionPane.showMessageDialog(addFriendDialog, "User does not exist.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (isFriendshipExists(conn, currentUser.getUserName(), friendUsername)) {
                        JOptionPane.showMessageDialog(addFriendDialog, "You are already friends with this user.",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    addFriendship(conn, currentUser.getUserName(), friendUsername);
                    JOptionPane.showMessageDialog(addFriendDialog, "Friend added successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    friendsList.add(friendUsername);
                    displayFriends();

                    addFriendDialog.dispose();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addFriendDialog, "Database Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFriendDialog.dispose();
            }
        });

        addFriendDialog.setVisible(true);
    }


    private void openRemoveFriendDialog(JFrame parentFrame) {
        JDialog removeFriendDialog = new JDialog(parentFrame, "Remove Friend", true);
        removeFriendDialog.setSize(350, 200);
        removeFriendDialog.setLayout(new BorderLayout());
        removeFriendDialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("Enter Friend's Username:");
        JTextField friendUsernameField = new JTextField();
        JPanel buttonsPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        buttonsPanel.add(submitButton);
        buttonsPanel.add(cancelButton);

        panel.add(label);
        panel.add(friendUsernameField);
        panel.add(buttonsPanel);

        removeFriendDialog.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendUsername = friendUsernameField.getText().trim();

                if (friendUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(removeFriendDialog, "Username cannot be empty.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (friendUsername.equals(currentUser.getUserName())) {
                    JOptionPane.showMessageDialog(removeFriendDialog, "You cannot remove yourself.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidUsername(friendUsername)) {
                    JOptionPane.showMessageDialog(removeFriendDialog, "Invalid username format.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                    if (!isUserExists(conn, friendUsername)) {
                        JOptionPane.showMessageDialog(removeFriendDialog, "User does not exist.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!isFriendshipExists(conn, currentUser.getUserName(), friendUsername)) {
                        JOptionPane.showMessageDialog(removeFriendDialog, "You are not friends with this user.",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    removeFriendship(conn, currentUser.getUserName(), friendUsername);
                    JOptionPane.showMessageDialog(removeFriendDialog, "Friend removed successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    friendsList.remove(friendUsername);
                    displayFriends();

                    removeFriendDialog.dispose();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(removeFriendDialog, "Database Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFriendDialog.dispose();
            }
        });

        removeFriendDialog.setVisible(true);
    }

    /**
     * removes a friend from the friendsList and updates the UI.
     *
     * @param friendUsername The username of the friend to remove.
     */
    private void removeFriendFromTable(String friendUsername) {
        friendsList.remove(friendUsername);
        displayFriends();
    }

    /**
     * checks if a user exists in the 'bhfusers' table.
     *
     * @param conn      The active database connection.
     * @param userName The username to check.
     * @return True if the user exists, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private boolean isUserExists(Connection conn, String userName) throws SQLException {
        String query = "SELECT userName FROM bhfusers WHERE userName = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * checks if a friendship exists between two users in the 'bhffriends' table.
     *
     * @param conn       The active database connection.
     * @param userName1  First user's username.
     * @param userName2  Second user's username.
     * @return True if the friendship exists, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private boolean isFriendshipExists(Connection conn, String userName1, String userName2) throws SQLException {
        String query = "SELECT * FROM bhffriends WHERE "
                + "(firstUser = ? AND secondUser = ?) OR (firstUser = ? AND secondUser = ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName1);
            pstmt.setString(2, userName2);
            pstmt.setString(3, userName2);
            pstmt.setString(4, userName1);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * adds a friendship between two users in the 'bhffriends' table.
     *
     * @param conn        The active database connection.
     * @param userName1   First user's username.
     * @param userName2   Second user's username.
     * @throws SQLException If a database access error occurs.
     */
    private void addFriendship(Connection conn, String userName1, String userName2) throws SQLException {
        // Sort usernames to maintain consistency and avoid duplicates
        String[] sortedUsers = sortUsernames(userName1, userName2);
        String insertSQL = "INSERT INTO bhffriends (firstUser, secondUser) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, sortedUsers[0]);
            pstmt.setString(2, sortedUsers[1]);
            pstmt.executeUpdate();
        }
    }

    /**
     * removes a friendship between two users from the 'bhffriends' table.
     *
     * @param conn        The active database connection.
     * @param userName1   First user's username.
     * @param userName2   Second user's username.
     * @throws SQLException If a database access error occurs.
     */
    private void removeFriendship(Connection conn, String userName1, String userName2) throws SQLException {
        String deleteSQL = "DELETE FROM bhffriends WHERE "
                + "(firstUser = ? AND secondUser = ?) OR (firstUser = ? AND secondUser = ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, userName1);
            pstmt.setString(2, userName2);
            pstmt.setString(3, userName2);
            pstmt.setString(4, userName1);
            pstmt.executeUpdate();
        }
    }

    private String[] sortUsernames(String user1, String user2) {
        if (user1.compareToIgnoreCase(user2) < 0) {
            return new String[]{user1, user2};
        } else {
            return new String[]{user2, user1};
        }
    }


    private boolean isValidUsername(String username) {
        // Example: Username must be 3-20 characters, alphanumeric and underscores
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }


//    public static void main(String[] args) {
//
////
////        // For testing, create a dummy user
////
////        User newUser = new User();
////        newUser.userName = "niir";
////        newUser.password = "niir.123";
////        newUser.gender = "Female";
////        newUser.age = 20;
////        newUser.height = 158;
////        newUser.weight = 45000;
////        newUser.bFPercentage = 20;
////
////        SwingUtilities.invokeLater(new Runnable() {
////            @Override
////            public void run() {
////                new FriendsPageFinal(newUser);
////            }
////        });
////        new FriendsPageFinal(null);
//    }
}
