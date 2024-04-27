package server;

import common.Message;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Interface for ManageServerConnectClientThread
 */
public interface ManageServerInterface {
    ConcurrentHashMap<String, ServerConnectClientThread> map = null;
    ConcurrentHashMap<String, Vector<Message>> messageMap = null;
}
