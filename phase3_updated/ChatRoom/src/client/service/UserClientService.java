package client.service;


import UI.UserInterfaceFrame;
import common.Message;
import common.MessageType;
import common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Team Project(Project 05) -- UserClientService
 * <p>
 * This class is used to send client data(message) to the server
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class UserClientService implements UserClientServiceInterface {
    private User user = new User(); //current user
    private Socket socket; //socket corresponding to the current user
    private boolean flag = false; //Indicates whether the login is successful
    private ObjectOutputStream oos;
    private ObjectInputStream ois;


    public boolean checkUser(String userId, String pwd) {
        try {
            //Send a user to the server for verification
            user.setUsername(userId);
            user.setPassword(pwd);
            //Connect to the server
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            oos = new ObjectOutputStream(socket.getOutputStream());
            //Send the user with oos
            oos.writeObject(user);
            ois = new ObjectInputStream(socket.getInputStream());

            Message message = (Message) ois.readObject();
            //login success
            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                ClientConnectServerThread thread = new ClientConnectServerThread(socket, userId);
                thread.start();
                flag = true;
            } else {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //Get online users list
    public void onlineFriendList() {
        //A message of online users
        Message message = new Message(user.getUserId(), MessageType.MESSAGE_GET_ONLINE_FRIEND);
        try {
            //The stream is rebound each time it is used
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Send a message to the server indicating that a user (client) has exited
    public void closedComm() {
        try {
            Message message = new Message(user.getUserId(), MessageType.MESSAGE_CLIENT_EXIT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            socket.close(); //Closes the socket for the current user
            System.exit(0);//Terminates the process and all threads raised by it
        } catch (IOException e) {

        }
    }

    //Send private message with other user
    public void Send(String name, String contents) {

        try {
            Message message1 = new Message();
            message1.setMessageType(MessageType.MESSAGE_COMM_MES);//This si a common message
            message1.setGetter(name);//receiver
            message1.setContent(contents);
            message1.setSender(user.getUsername());
            message1.setSendTime(formatTime());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendAll(String contents) {
        Send("All", contents);
    }

    //withdraw
    public void withdraw(String name, String contents) {

        try {
            Message message1 = new Message();
            message1.setMessageType(MessageType.MESSAGE_CLIENT_WITHDRAW);//The message need to be withdrawn
            message1.setGetter(name);//receiver
            message1.setContent(contents);
            message1.setSender(user.getUsername());
            message1.setSendTime(formatTime());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //withdraw
    public void withdrawMe(String name, String contents) {

        try {
            Message message1 = new Message();
            message1.setMessageType(MessageType.MESSAGE_CLIENT_WITHDRAWME);//This si a withdraw message
            message1.setGetter(name);//receiver
            message1.setContent(contents);
            message1.setSender(user.getUsername());
            message1.setSendTime(formatTime());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String formatTime() {
        Calendar instance = Calendar.getInstance();
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int min = instance.get(Calendar.MINUTE);
        StringBuilder builder = new StringBuilder(""); //sp changed "\n" to ""
        if (hour < 10)
            builder.append("0");
        builder.append(hour + ":");
        if (min < 10)
            builder.append("0");
        builder.append(min);
        return builder.toString();
    }
}
