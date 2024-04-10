package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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
    private JButton setBtn;
    private JLabel uNameLab;
    private JLabel uImgLab;
    private JButton addBtn;
    private JTable table1;
    private JButton removeFriendButton;
    private JButton blockButton;

    private JTextArea chatMsg;
    private JTextArea textMsg;
    private JButton toBlockListButton;
    private JTextArea allChatMsg;
    private JButton sendAllBtn;
    private JTextArea textAllMsg;
    private JButton refreshedButton;
    private JLabel fImgLab;
    private JLabel fName;
    private JLabel fImg2;
    private JLabel fNmae2;
    JFrame frame;
    private StringBuffer strBuf = new StringBuffer();
    private StringBuffer strAllBuf = new StringBuffer();

    private ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();
    Object a[][];
    Object name[] = {"","icon","name"};
    Set<String> userNameSets = new HashSet<>();
    User currentUser;
    UserClientService userClientService;
    CustomModel mod;
    private UserInterfaceFrame(){
        addBtn.addActionListener(new ActionListener() {
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
        refreshedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = FileUtil.getUsers();
            }
        });
    }




    //设置列宽随表格内容自动调整
    public void fitTableColumns(JTable myTable) { // 設置table的列寬隨內容調整
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());

            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
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
                    //如果被选中就放到set里
                    System.out.println("被选中" + table1.getValueAt(row, 2));
                    userNameSets.add(table1.getValueAt(row, 2).toString());
                }else {
                    System.out.println("被取消"+table1.getValueAt(row,2).toString());
                    userNameSets.remove(table1.getValueAt(row, 2).toString());
                }
                return true;
            }
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }

/*    public static void main(String[] args) {

        new UserInterfaceFrame();

    }*/
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
/*        ImageIcon icon2=new ImageIcon("D:\\imgs\\jzh.jpg");
        icon2.setImage(icon2.getImage().getScaledInstance(properties.Properties.PROFILE_PICTURE_WIDE,properties.Properties.PROFILE_PICTURE_HIGH,1));
        ImageIcon icon3=new ImageIcon("D:\\imgs\\jzb.png");
        icon3.setImage(icon3.getImage().getScaledInstance(properties.Properties.PROFILE_PICTURE_WIDE,properties.Properties.PROFILE_PICTURE_HIGH,1));
        JLabel jLabel2 = new JLabel();
        jLabel2.setIcon(icon2);
        JLabel jLabel3 = new JLabel();
        jLabel3.setIcon(icon3);
        users.add(new User("简自豪",icon2));
        users.add(new User("简自悲",icon3));*/

        mod= new CustomModel();
        for (Object o: name){
            mod.addColumn(o);
        }
        init(mod,users);
        table1.setModel(mod);
        table1.setRowHeight(80);
        frame = new JFrame("UserInterfaceFrame");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);

        uImgLab.setIcon(currentUser.getBio());
        uNameLab.setText(currentUser.getUsername());
        FrameUtil.center(frame);

        setBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("选择的文件路径：" + filePath);
                    currentUser.setBio(new ImageIcon(filePath));
                    //currentUser.setUsername("简自悲");
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
                if (userNameSets.size() > 0){
                    JOptionPane.showMessageDialog(p1,"success!");
                }else {
                    JOptionPane.showMessageDialog(p1,"Please select at least one row of data!");
                }
                for (String name : userNameSets){
                    currentUser.removeFriend(name);
                    userNameSets.clear();
                    users = FileUtil.getUsers();
                    FileUtil.setUser(users);
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
                flushUsers();
                if (userNameSets.size() > 1){
                    JOptionPane.showMessageDialog(p1,"Only one person can be sent a private message at a time!");
                }else if (userNameSets.size() ==0){
                    JOptionPane.showMessageDialog(p1,"Please select at least one person's private message!");
                }else {
                    for (String name : userNameSets){

                        if (users.get(name).isBlockedBy(currentUser.getUsername())){
                            JOptionPane.showMessageDialog(p1,"You have been blocked by him and cannot send messages to him!");
                        }else {

                            strBuf.append(currentUser.getUsername()+" said to "+name+ " :\n"+textMsg.getText()+"\n");
                            userClientService.Send(name,currentUser.getUsername()+" said to"+name+ ":\n"+textMsg.getText()+"\n");
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
                flushUsers();
                strAllBuf.append(currentUser.getUsername()+"said to all:\n"+textAllMsg.getText()+"\n");
                userClientService.sendAll(currentUser.getUsername()+" said to all:\n"+textAllMsg.getText()+"\n");
                allChatMsg.setText(strAllBuf.toString());
                textAllMsg.setText("");





            }

        });
    }

    public void flushUsers(){
        users = FileUtil.getUsers();
    }

    //
}
