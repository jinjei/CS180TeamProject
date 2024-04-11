package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


import client.service.UserClientService;
import common.User;
import util.FileUtil;
import util.FrameUtil;

public class UserInterfaceFrame {
    private JPanel panel1;
    private JPanel p1;
    private JButton sendBtn;
    private JButton setPicButton;
    private JLabel uNameLab;
    private JLabel uImgLab;
    private JButton addButton;
    private JTable table1;
    private JButton removeFriendButton;
    private JButton blockButton;

    private JTextArea chatMsg;
    private JTextArea textMsg;
    private JButton toBlockListButton;
    private JTextArea allChatMsg;
    private JButton sendAllBtn;
    private JTextArea textAllMsg;
    private JButton refreshButton;
    private JLabel fImgLab;
    private JLabel fName;
    private JLabel fImg2;
    private JLabel fNmae2;
    JFrame frame;
    private StringBuffer strBuf = new StringBuffer();
    private StringBuffer strAllBuf = new StringBuffer();

    private ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();
    Object a[][];
    Object name[] = {"","Icon","Username"};
    Set<String> userNameSets = new HashSet<>();
    User currentUser;
    UserClientService userClientService;
    CustomModel mod;
    private UserInterfaceFrame(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserListFrame(users,currentUser);
            }
        });
        toBlockListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BlockListFrame(users,currentUser);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = FileUtil.getUsers();
            }
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ConcurrentHashMap<String, User> getUsers() {
        return users;
    }


    public void flush(){
        init(mod,users);
    }


        public void init(CustomModel mod,Map<String,User> users){
        userNameSets.clear();
        int i = 0;
        a = new Object[users.size()][3];
        mod.setRowCount(0);
        while (i < currentUser.getFriends().size()) {

            a[i][0] = new Boolean(false);
            a[i][1] = users.get(currentUser.getFriends().get(i)).getBio();
            a[i][2] = users.get(currentUser.getFriends().get(i)).getUsername();
            mod.addRow(a[i]);
            i++;
        }
    }
    class CustomModel extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            if (column > 0) {
                System.out.println("1");
                return false;
            } else {
                if (table1.getValueAt(row, column).toString().equalsIgnoreCase("false")) {
                    // If it's selected, put it in set
                    System.out.println("Selected: " + table1.getValueAt(row, 2));
                    userNameSets.add(table1.getValueAt(row, 2).toString());
                }else {
                    System.out.println("Cancelled: "+table1.getValueAt(row,2).toString());
                    userNameSets.remove(table1.getValueAt(row, 2).toString());
                }
                return true;
            }
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }


    public void setMsg(String msg){
        chatMsg.setText(msg);
    }
    public void setAllMsg(String msg){
        allChatMsg.setText(msg);
    }


    public void flushMsg(){
        chatMsg.setText(strBuf.toString());
    }
    public StringBuffer getStrBuf(){
        return strBuf;
    }
    public StringBuffer getStrAllBuf(){
        return strAllBuf;
    }
    private static UserInterfaceFrame instance = new UserInterfaceFrame();
    public static UserInterfaceFrame getInstance(){
        return instance;
    }

    public void init(User currentUser, ConcurrentHashMap<String,User> list, UserClientService userClientService) {
        this.userClientService = userClientService;
        users = list;
        System.out.println(users.size());
        this.currentUser =currentUser;

        mod= new CustomModel();
        for (Object o: name){
            mod.addColumn(o);
        }
        init(mod,users);
        table1.setModel(mod);
        table1.setRowHeight(80);
        //fitTableColumns(table1);
        frame = new JFrame("UserInterfaceFrame");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(800,500);
        frame.setVisible(true);

        uImgLab.setIcon(currentUser.getBio());
        uNameLab.setText(currentUser.getUsername());
        FrameUtil.center(frame);

        setPicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = FileUtil.getUsers();
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("Selected file path：" + filePath);
                    currentUser.setBio(new ImageIcon(filePath));
                    FileUtil.setUser(users);
                    users = FileUtil.getUsers();
                    uImgLab.setIcon(currentUser.getBio());
                    JOptionPane.showMessageDialog(p1,"Image replacement successful!");
                }


            }
        });
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = FileUtil.getUsers();
                if (userNameSets.size() > 0){
                    JOptionPane.showMessageDialog(p1,"success!");
                }else {
                    JOptionPane.showMessageDialog(p1,"Please select at least one row of data!");
                }
                for (String name : userNameSets){
                    currentUser.removeFriend(name);
                    users.get(currentUser.getUsername()).removeFriend(name);
                    userNameSets.clear();
                    FileUtil.setUser(users);
                    users = FileUtil.getUsers();
                    init(mod,users);
                }

            }
        });


        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameSets.size() > 0){
                    JOptionPane.showMessageDialog(p1,"success!");
                }else {
                    JOptionPane.showMessageDialog(p1,"Please select at least one row of data!");
                }
                for (String name : userNameSets){
                    users = FileUtil.getUsers();
                    users.get(currentUser.getUsername()).blockUser(name);
                    currentUser.blockUser(name);
                    FileUtil.setUser(users);
                    init(mod,users);
                }

            }
        });



        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("hehe");
                try {
                    userClientService.closedComm();
                }catch (Exception ex){
                    System.out.println("bao");
                }
                System.out.println("hehe");
            }
        });

        chatMsg.setEditable(false);
        allChatMsg.setEditable(false);
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = FileUtil.getUsers();
                if (userNameSets.size() > 1){
                    JOptionPane.showMessageDialog(p1,"Only one person can be sent a private message at a time!");
                }else if (userNameSets.size() ==0){
                    JOptionPane.showMessageDialog(p1,"Please select at least one person's private message!");
                }else {
                    for (String name : userNameSets){
                        System.out.println("Send message to: "+ name);
                        System.out.println(name);
                        System.out.println(users.get(name).getBlockedUsers());
                        if (users.get(name).isBlockedBy(currentUser.getUsername())){
                            JOptionPane.showMessageDialog(p1,"You have been blocked by this user!");
                        }else {

                            strBuf.append(currentUser.getUsername()+" said to "+name+ " :\n"+textMsg.getText()+"\n");
                            userClientService.Send(name,currentUser.getUsername()+" said to "+name+ ":\n"+textMsg.getText()+"\n");
                            chatMsg.setText(strBuf.toString());
                            textMsg.setText("");
                        }


                    }

                }
            }
        });

        sendAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strAllBuf.append(currentUser.getUsername()+"said to all:\n"+textAllMsg.getText()+"\n");
                userClientService.sendAll(currentUser.getUsername()+" said to all:\n"+textAllMsg.getText()+"\n");
                allChatMsg.setText(strAllBuf.toString());
                textAllMsg.setText("");

            }

        });
    }


}