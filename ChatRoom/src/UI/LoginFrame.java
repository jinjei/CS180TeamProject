package UI;

import UI.RegisterFrame;
import UI.UserInterfaceFrame;
import client.service.UserClientService;
import common.User;
import util.FileUtil;
import util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentHashMap;

public class LoginFrame {
    private static UserClientService userClientService = new UserClientService();
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Chat system");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));
        FrameUtil.center(frame);
        ConcurrentHashMap<String,User> users = FileUtil.getUsers();
        User currentUser = new User(); // Current user

        // Create username, password input fields
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();


        // Login and register button
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        frame.add(new JLabel("       username:"));
        frame.add(usernameField);
        frame.add(new JLabel("       password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);

        // Click "register" button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                new RegisterFrame(users);
            }
        });

        // Click "login" button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // If username or password is empty，prompt user type again
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your username and password", "prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }else {
                    // Verify user on server-side
                    if (userClientService.checkUser(username,password)){
                        System.out.println("Login Success");
                        JOptionPane.showMessageDialog(frame,"Login Success");
                        frame.dispose();
                        UserInterfaceFrame instance = UserInterfaceFrame.getInstance();
                        instance.init(users.get(username),users,userClientService);

                        return;
                    }

                    JOptionPane.showMessageDialog(frame,"Incorrect username or password!");

                }
 
            }

        });
 
        // Show frame
        frame.setVisible(true);
    }
}