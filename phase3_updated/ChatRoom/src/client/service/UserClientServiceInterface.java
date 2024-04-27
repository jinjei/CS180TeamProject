package client.service;

import java.io.IOException;

/**
 * Interface for the UserClientService class.
 */
public interface UserClientServiceInterface {
    boolean checkUser(String userId, String pwd);

    void onlineFriendList();

    void closedComm();

    void Send(String name, String contents);

    void sendAll(String contents);

    String formatTime();

    void withdraw(String name, String contents);

    void withdrawMe(String name, String contents);
}
