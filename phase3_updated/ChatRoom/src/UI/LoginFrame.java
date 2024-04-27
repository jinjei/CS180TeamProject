package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentHashMap;
import client.service.UserClientService;
import common.User;
import util.FileUtil;
import util.FrameUtil;

/**
 * Provides the GUI for user login into the chat system.
 * This class creates the main login window, allowing users to either log in to the system
 * using their credentials or navigate to the registration page to create a new account.
 */
public class LoginFrame {
    private static UserClientService userClientService = new UserClientService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat System - Login");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        FrameUtil.center(frame);

        Color lightGrey = new Color(245, 245, 245);
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelGreen = new Color(144, 238, 144);
        Font labelFont = new Font("Sans Serif", Font.BOLD, 16);

        frame.getContentPane().setBackground(lightGrey);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        JTextField usernameField = new JTextField(15);
        usernameField.setFont(labelFont);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(labelFont);

        JButton loginButton = new JButton("Login");
        styleButton(loginButton, pastelBlue, labelFont);

        JButton registerButton = new JButton("Register");
        styleButton(registerButton, pastelGreen, labelFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(usernameLabel, gbc);
        gbc.gridy++;
        frame.add(usernameField, gbc);
        gbc.gridy++;
        frame.add(passwordLabel, gbc);
        gbc.gridy++;
        frame.add(passwordField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(loginButton, gbc);
        gbc.gridy++;
        frame.add(registerButton, gbc);

        // Action Listeners
        addActionListeners(loginButton, registerButton, usernameField, passwordField, frame);

        frame.setVisible(true);
    }

    private static void styleButton(JButton button, Color bgColor, Font font) {
        button.setBackground(bgColor);
        button.setFont(font);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
    }

    private static void addActionListeners(JButton loginButton, JButton registerButton, JTextField usernameField,
                                           JPasswordField passwordField, JFrame frame) {
        registerButton.addActionListener(e -> new RegisterFrame(FileUtil.getUsers()));

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your username and password",
                        "Prompt", JOptionPane.WARNING_MESSAGE);
            } else {
                if (userClientService.checkUser(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    UserInterfaceFrame instance = UserInterfaceFrame.getInstance();
                    instance.init(FileUtil.getUsers().get(username), FileUtil.getUsers(), userClientService);
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
