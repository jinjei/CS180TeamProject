package main;

import common.Message;
import common.MessageType;
import common.User;
import server.ManageServerConnectClientThread;
import server.ServerConnectClientThread;
import util.FileUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class QQServer {
    ServerSocket serverSocket;
    //模拟用户数据库
    private static ConcurrentHashMap<String, User> userMap =  FileUtil.getUsers();


    public static void main(String[] args) {
        System.out.println(userMap.size());
        new QQServer();
    }
    public QQServer() {
        try {
            System.out.println("在9999端口监听……");
            serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) ois.readObject();
                System.out.println("看看");
                System.out.println(user);
                //构建一个Message对象，准备回复
                Message message = new Message();
                //验证账号密码是否正确
                if (isUser(user.getUsername(), user.getPassword())) {

                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCESS);
                    oos.writeObject(message);
                    ServerConnectClientThread thread = new ServerConnectClientThread(socket, user.getUsername());
                    thread.start();
                    ManageServerConnectClientThread.addThread(user.getUsername(), thread);
                    ManageServerConnectClientThread.sendOffLineMessage(user.getUsername(), oos);
                } else {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConcurrentHashMap<String, User> getUserMap() {
        return userMap;
    }

    public boolean isUser(String userId, String pw) {
        userMap = FileUtil.getUsers();
        System.out.println("用户名"+userId+ "密码"+pw);
        User user = userMap.get(userId);
        //没有这个用户
        if (user == null) {
            return false;
        }
        //密码不正确
        System.out.println(user);
        if (!user.getPassword().equals(pw)) {
            return false;
        }
        return true;
    }

    public static boolean isUser(String userId) {
        User user = userMap.get(userId);
        if (user == null) {
            return false;
        }
        return true;
    }
}
