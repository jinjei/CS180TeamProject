package main;

import common.User;

import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Interface for ServerConnectClientThread
 */
public interface AppServerInterface {
    ServerSocket serverSocket = null;
    ConcurrentHashMap<String, User> userMap = null;

    boolean isUser(String userId, String pw);
}
