package server;


import common.Message;
import common.MessageType;
import main.AppServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Team Project(Project 05) -- ServerConnectClientThread
 * <p>
 * An object of this class communicates with a specific client
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class ServerConnectClientThread extends Thread implements ServerConnectClientInterface {
    private Socket socket; //socket corresponding to the current user
    private String userId;
    private boolean flag = true; //Indicates whether the login is successful
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.println("User [" + userId + "] connected with server……");
        while (flag) {
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                actionByMessageType(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void actionByMessageType(Message message) {
        try {
            switch (message.getMessageType()) {
                case MessageType.MESSAGE_GET_ONLINE_FRIEND:

                    System.out.println("User [" + message.getSender() + "] wants see the online users list……");
                    Message message1 = new Message();
                    message1.setMessageType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setGetter(message.getSender());//Set the receiver to the person
                    // who just sent the request

                    message1.setContent(ManageServerConnectClientThread.getOnlineFriends());
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);
                    break;
                case MessageType.MESSAGE_CLIENT_EXIT:
                    flag = false; //A sign indicating the end of this thread

                    //Delete this client from the thread collection
                    ManageServerConnectClientThread.deleteSocket(userId);
                    socket.close(); //Close the thread corresponding to the client
                    System.out.println("User [" + userId + "] disconnected！");
                    break;
                case MessageType.MESSAGE_COMM_MES:
                    if (message.getGetter().equals("All")) {
                        //It's a message to all users
                        ManageServerConnectClientThread.sendAll(socket, oos, message);
                        break;
                    }
                    //得到目标的Socket
                    Socket socket = ManageServerConnectClientThread.getSocketById(message.getGetter());
                    Message message2 = new Message();
                    if (AppServer.isUser(message.getGetter())) {//Check if the user is in the database

                        //This user is in the database
                        if (socket == null) {
                            //Indicating that the receiver is offline and has left a message
                            socket = this.socket;
                            message2.setMessageType(MessageType.MESSAGE_CLIENT_OFFLINE);
                            message2.setGetter(message.getGetter());
                            //Put the message in the pending message box
                            ManageServerConnectClientThread.addMessage(message.getGetter(), message);
                        } else {
                            message2 = message;
                        }
                    } else {
                        //This user not in the database
                        socket = this.socket;
                        message2.setMessageType(MessageType.MESSAGE_CLIENT_NO_EXIST);
                        message2.setGetter(message.getGetter());
                    }
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                    break;

                case MessageType.MESSAGE_CLIENT_WITHDRAW:
                    //得到目标的Socket
                     socket = ManageServerConnectClientThread.getSocketById(message.getGetter());
                    message2 = new Message();
                    if (AppServer.isUser(message.getGetter())) {//Check if the user is in the database

                        //This user is in the database
                        if (socket == null) {
                            //Indicating that the receiver is offline and has left a message
                            socket = this.socket;
                            message2.setMessageType(MessageType.MESSAGE_CLIENT_OFFLINE);
                            message2.setGetter(message.getGetter());
                            //Put the message in the pending message box
                            ManageServerConnectClientThread.addMessage(message.getGetter(), message);

                        } else {
                            message2 = message;
                        }
                    } else {
                        //This user not in the database
                        socket = this.socket;
                        message2.setMessageType(MessageType.MESSAGE_CLIENT_NO_EXIST);
                        message2.setGetter(message.getGetter());

                    }
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                    break;
                case MessageType.MESSAGE_CLIENT_WITHDRAWME:

                    //得到目标的Socket
                    socket = ManageServerConnectClientThread.getSocketById(message.getGetter());
                    message2 = new Message();
                    if (AppServer.isUser(message.getGetter())) {//Check if the user is in the database

                        //This user is in the database
                        if (socket == null) {
                            //Indicating that the receiver is offline and has left a message
                            socket = this.socket;
                            message2.setMessageType(MessageType.MESSAGE_CLIENT_OFFLINE);
                            message2.setGetter(message.getGetter());
                            //Put the message in the pending message box
                            ManageServerConnectClientThread.addMessage(message.getGetter(), message);

                        } else {
                            message2 = message;
                        }
                    } else {
                        //This user not in the database
                        socket = this.socket;
                        message2.setMessageType(MessageType.MESSAGE_CLIENT_NO_EXIST);
                        message2.setGetter(message.getGetter());
                        System.out.println("");
                    }
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong！");
        }

    }

    public Socket getSocket() {
        return socket;
    }
}