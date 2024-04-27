package server;

import common.Message;
import common.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Team Project(Project 05) -- ManageServerConnectClientThread
 * <p>
 * This class manage thread
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class ManageServerConnectClientThread implements ManageServerInterface {
    public static ConcurrentHashMap<String, ServerConnectClientThread> map = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Vector<Message>> messageMap = new ConcurrentHashMap<>();

    public static String getOnlineFriends() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ServerConnectClientThread> entry : map.entrySet()) {
            builder.append(entry.getKey() + " ");
        }
        return builder.toString();
    }

    public static Socket getSocketById(String userId) {
        ServerConnectClientThread thread = map.get(userId);
        if (thread == null) {
            return null;
        } else {
            return thread.getSocket();
        }
    }

    //Send a message to all connected clients except the message originator himself
    public static void sendAll(Socket socket, ObjectOutputStream oos, Message message) {
        try {
            for (Map.Entry<String, ServerConnectClientThread> entry : map.entrySet()) {
                Socket socket1 = getSocketById(entry.getKey());
                if (socket1 != socket) {
                    //Check whether the currently traversed socket is the message sender
                    oos = new ObjectOutputStream(socket1.getOutputStream());
                    oos.writeObject(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //The server temporarily stores offline messages; userID: receiver; message: the message needs to be stored
    public static void addMessage(String userId, Message message) {
        Vector<Message> vector = messageMap.get(userId);
        if (vector == null) {
            vector = new Vector<>();
            messageMap.put(userId, vector);
        }
        vector.add(message);
    }

    public static synchronized void writeMessage(Message message) {
        try {

            File f = new File("C:\\Users\\shivi\\IdeaProjects\\teamprojv00\\phase3\\UserPic1\\message.txt");

            FileOutputStream fos = new FileOutputStream(f,true);
            PrintWriter pw = new PrintWriter(fos);

            pw.println(STR."\{message.getSender()};\{message.getGetter()};\{message.getContent()};\{message.getSendTime()}");

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Send the server inventory(pending) message; userId: receiver
    public static void sendOffLineMessage(String userId, ObjectOutputStream oos) {
        Vector<Message> vector = messageMap.get(userId); //Get inventory message
        if (!(vector == null || vector.isEmpty())) {
            try {
                //Indicate that the current user has pending messages to send
                Socket socket = getSocketById(userId);
                while (!vector.isEmpty()) {
                    Message message = vector.get(0);
                    //Send messages in order
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);
                    vector.remove(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConcurrentHashMap<String, ServerConnectClientThread> getMap() {
        return map;
    }

    public static void deleteSocket(String userId) {
        map.remove(userId);
    }

    public static void addThread(String useId, ServerConnectClientThread thread) {
        map.put(useId, thread);
    }
}