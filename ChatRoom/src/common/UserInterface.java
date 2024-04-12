package common;

import javax.swing.*;
import java.util.List;

public interface UserInterface {
    void setUsername(String username);
    List<String> getBlockedUsers();
    void setBlockedUsers(List<String> blockedUsers);
    String getUserId();
    void setUserId(String userId);
    String getPasswd();
    void setPasswd(String passwd);
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
    void showFriends();
    void showBlockedUsers();
    void receiveMessage(String message);
    void readMessages();
    boolean isBlockedBy(String otherUser);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
    boolean login(String password);
    @Override
    String toString();
}

