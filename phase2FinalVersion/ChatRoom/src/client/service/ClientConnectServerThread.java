package client.service;

import UI.UserInterfaceFrame;
import common.Message;
import common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Team Project(Project 05) -- ClientConnectServerThread
 * <p>
 * Maintains a continuous connection to the server,
 * handling incoming messages and updating the UI.
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class ClientConnectServerThread extends Thread implements ClientConnectServerInterface {
    // The thread needs to have a socket
    private Socket socket;
    private String userId;
    private ObjectInputStream ois;
    private UserInterfaceFrame frame;

    public ClientConnectServerThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
        this.frame = UserInterfaceFrame.getInstance();
    }

    @Override
    public void run() {
        //The client must always keep connecting with the server
        while (true) {
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                // If the input is not read in the stream, it pauses here
                Message message = (Message) ois.readObject();
                actionByMessageType(message); // React according to the MessageType received
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void actionByMessageType(Message message) {
        switch (message.getMessageType()) {
            case MessageType.MESSAGE_RET_ONLINE_FRIEND:
                //The client receives a return message from the server,
                //the message content is the list of online users
                String[] split = message.getContent().split(" ");
                System.out.println("Online Users：");
                for (String name : split) {
                    System.out.println("Name：" + name);
                }
                break;
            case MessageType.MESSAGE_COMM_MES:
                //Received a common message,
                //and print the content, sender, and sending time in the console
                System.out.println(message.getSendTime());
                if (message.getGetter().equals("All")) {

                    StringBuffer strBuf = frame.getStrAllBuf();
                    strBuf.append(message.getContent());
                    System.out.println(strBuf.toString());
                    frame.setAllMsg(strBuf.toString());
                    // This is a message to all users
                    System.out.println("[" + message.getSender() + "] said to [All]：");
                } else {
                    StringBuffer strBuf = frame.getStrBuf();
                    strBuf.append(message.getContent());
                    System.out.println(strBuf.toString());
                    frame.setMsg(strBuf.toString());

                    System.out.println("[" + message.getSender() + "] said to [Me]：");

                }
                //System.out.print(message.getContent() + "\n\nType your choice：");
                break;
            case MessageType.MESSAGE_CLIENT_NO_EXIST:
                // User doesn't exist
                System.out.println("User [" + message.getGetter() + "] doesn't exist, cannot sent message！");
                break;
            case MessageType.MESSAGE_CLIENT_OFFLINE:
                System.out.println("User [" + message.getGetter() + "] is offline now.");
        }

    }

    public Socket getSocket() {
        return socket;
    }

}