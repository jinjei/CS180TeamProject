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

public class AppServer {
    ServerSocket serverSocket;

    // userMap: database for the whole project
    private static ConcurrentHashMap<String, User> userMap =  FileUtil.getUsers();

    public static void main(String[] args) {
        System.out.println("Total User Number: "+ userMap.size());
        new AppServer();
    }
    public AppServer() {
        try {
            System.out.println("Listening on port 9999……");
            serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) ois.readObject();
                System.out.println("Verify");
                System.out.println(user);

                //construct a message object, ready for reply
                Message message = new Message();

                //verify username and password
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

    //two ways of user verification
    public boolean isUser(String userId, String pw) {
        userMap = FileUtil.getUsers();
        System.out.println("userID "+userId+ " Password "+pw);
        User user = userMap.get(userId);
        //user doesn't exist
        if (user == null) {
            return false;
        }

        //password is incorrect
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