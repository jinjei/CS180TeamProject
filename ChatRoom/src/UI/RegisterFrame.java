package UI;

import util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

import common.User;
import util.FrameUtil;

public class RegisterFrame {
    private JPanel root;
    private JButton registerButton;
    private JLabel nameLab;
    private JTextField nameField1;
    private JLabel userLab;
    private JTextField usernameField1;
    private JTextField passwordField1;
    private JButton uploadButton;
    private JTextField emilField1;
    private User user;  // The user registering
    private ConcurrentHashMap<String,User> users;



    public RegisterFrame(ConcurrentHashMap<String,User> users) {
        this.users = users;
        JFrame frame = new JFrame("RegisterFrame");
        FrameUtil.center(frame);
        frame.setSize(600,400);
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
        user = new User();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set user's Info
                setUsers();
                user.setUser(nameField1.getText(),usernameField1.getText(),passwordField1.getText(),emilField1.getText());

                for (String key : users.keySet()){
                    if (users.get(key).getUsername().equals(user.getUsername())){
                        JOptionPane.showMessageDialog(root,"The current username has already been registered,error!");
                        return;
                    }
                }
                users.put(user.getUsername(),user);
                FileUtil.setUser(users);//保存到文件
                JOptionPane.showMessageDialog(root,"Register Success");
                frame.dispose();
            }
        });

        // Upload user's profile picture
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("Selected file path：" + filePath);
                    user.setBio(new ImageIcon(filePath));
                    JOptionPane.showMessageDialog(root,"Success!");
                }

            }
        });


    }

    public void setUsers() {
        this.users =FileUtil.getUsers();
    }
}
