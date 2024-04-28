package common;

import javax.swing.*;
import java.util.List;
/**
 * Team Project(Project 05) -- UserInterface
 * <p>
 * Interface for User class
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public interface UserInterface {
    void setUsername(String username);
    List<String> getBlockedUsers();

    String getUserId();

    String getName();
    String getUsername();
    Icon getBio();
    void setBio(ImageIcon bio);
    List<String> getFriends();
    void setFriends(List<String> friends);
    String addFriend(String friendUsername);
    void removeFriend(String friendUsername);
    String blockUser(String userToBlock);
    String unblockUser(String userToUnblock);
    boolean Friend(String username);
    boolean Blocked(String username);

    boolean isBlockedBy(String otherUser);
    String getEmail();
    //void setEmail(String email);
    String getPassword();
    void setPassword(String password);


    String toString();
}

