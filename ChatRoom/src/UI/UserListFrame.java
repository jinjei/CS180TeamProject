package UI;

import common.User;
import util.FileUtil;
import util.FrameUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserListFrame {
    private JPanel root;
    private JTable table1;
    private JButton addButton;
    private JTextField selField;
    private JButton selBtnButton;
    Object a[][];
    Object name[] = {"","头像","姓名"};
    Set<String> userNameSets = new HashSet<>();
    User currentUser;
    JFrame frame;
    ConcurrentHashMap<String,User> users;




    public void init(CustomModel mod, Map<String, User> users){
        int i = 0;
        a = new Object[users.size()][3];
        mod.setRowCount(0);
        for (String key : users.keySet()) {

            a[i][0] = new Boolean(false);
            a[i][1] = users.get(key).getBio();
            a[i][2] = users.get(key).getUsername();
            mod.addRow(a[i]);
            i++;
        }
    }

    public void init(CustomModel mod, Map<String, User> users,String name){
        int i = 0;
        a = new Object[users.size()][3];
        mod.setRowCount(0);
        for (String key : users.keySet()) {
            if (key.contains(name) || name.equals("")){
                a[i][0] = new Boolean(false);
                a[i][1] = users.get(key).getBio();
                a[i][2] = users.get(key).getUsername();
                mod.addRow(a[i]);
                i++;
            }

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

    public UserListFrame(ConcurrentHashMap<String,User> users, User user) {
        this.users = users;
        frame = new JFrame("UserList");
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.pack();
        frame.setSize(500,300);
        frame.setVisible(true);
        FrameUtil.center(frame);
        currentUser =user;
        final CustomModel mod = new CustomModel();
        for (Object o: name){
            mod.addColumn(o);
        }
        init(mod,users);
        table1.setModel(mod);
        table1.setRowHeight(80);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (userNameSets.size() == 0){

                    System.out.println("Please select one person!");
                    JOptionPane.showMessageDialog(root,"Please select one person!");
                }else if (userNameSets.size() > 1){
                    JOptionPane.showMessageDialog(root,"Add up to one friend at a time!");
                }else {
                    for (String name: userNameSets){
                        JOptionPane.showMessageDialog(root, currentUser.addFriend(name));
                        FileUtil.setUser(users);
                        UserInterfaceFrame instance = UserInterfaceFrame.getInstance();
                        instance.flush();
                    }

                }
            }
        });

        selBtnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = selField.getText();
                init(mod,users,name);
            }
        });
    }



}
