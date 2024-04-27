package client.service;

import common.Message;

import java.net.Socket;

/**
 * Team Project(Project 05) -- ClientConnectServerInterface
 * <p>
 * Maintains a continuous connection to the server,
 * handling incoming messages and updating the UI.
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public interface ClientConnectServerInterface {
    void run();

    void actionByMessageType(Message message);

    Socket getSocket();
}
