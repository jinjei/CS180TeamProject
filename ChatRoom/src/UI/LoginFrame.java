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
        // 创建窗体
        JFrame frame = new JFrame("Chat system");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));
        FrameUtil.center(frame);
        ConcurrentHashMap<String,User> users = FileUtil.getUsers();
        User currentUser = new User();//当前用户

        // 创建用户名、密码输入框和角色选择按钮组
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        ButtonGroup roleGroup = new ButtonGroup();
 

 
        // 创建登录和注册按钮
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
 
        // 将组件添加到窗体中
        frame.add(new JLabel("       username:"));
        frame.add(usernameField);
        frame.add(new JLabel("       password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                new RegisterFrame(users);
            }
        });
 
        // 设置登录按钮点击事件的处理逻辑
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
 
                // 如果用户名和密码为空，则提示并返回
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your username and password", "prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }else {
                    //服务端校验
                    if (userClientService.checkUser(username,password)){
                        System.out.println("login success");
                        JOptionPane.showMessageDialog(frame,"login success");
                        frame.dispose();
                        UserInterfaceFrame instance = UserInterfaceFrame.getInstance();
                        instance.init(users.get(username),users,userClientService);

                        return;
                    }

                    JOptionPane.showMessageDialog(frame,"Incorrect username or password!");

                }
 

            }
 
        });
 
        // show frame
        frame.setVisible(true);
    }
}