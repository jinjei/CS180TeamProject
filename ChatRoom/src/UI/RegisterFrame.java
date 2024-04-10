package UI;

import util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
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
    private User user;



    public RegisterFrame(ConcurrentHashMap<String,User> users) {

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
                user.setUser(nameField1.getText(),usernameField1.getText(),passwordField1.getText(),emilField1.getText());

                for (String key : users.keySet()){
                    if (users.get(key).getUsername().equals(user.getUsername())){
                        JOptionPane.showMessageDialog(root,"The current username has already been registered,error!");
                        return;
                    }
                }

                users.put(user.getUsername(),user);
                FileUtil.setUser(users);//保存到文件
                JOptionPane.showMessageDialog(root,"register success");
                frame.dispose();
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("选择的文件路径：" + filePath);
                    user.setBio(new ImageIcon(filePath));
                    JOptionPane.showMessageDialog(root,"success");
                }

            }
        });


    }
}
