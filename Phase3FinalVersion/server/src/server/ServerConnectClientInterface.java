package server;

import common.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Interface for ServerConnectClientThread
 */
public interface ServerConnectClientInterface {
    Socket socket = null; //socket corresponding to the current user
    String userId = null;
    boolean flag = true; //Indicates whether the login is successful
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    void actionByMessageType(Message message);

    Socket getSocket();


}