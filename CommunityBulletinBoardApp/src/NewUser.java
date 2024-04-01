import java.util.ArrayList;
import java.util.List;

public interface NewUser {

    public String getName();
    public String getUsername();
    public String getBio();
    public String[] getFriends();
    public void setFriends(String[] friends1);
    public void updateBio(String newBio);
    public void addFriend(String friendUsername);
    public void removeFriend(String friendUsername);
    public void blockUser(String userToBlock);
    public void unblockUser(String userToUnblock);
    public boolean Friend(String username);
    public boolean Blocked(String username);
    public boolean contains(String[] array, String value);
    public void showFriends();
    public void showBlockedUsers();
    public String[] addToArray(String[] array, String value);
    public String[] removeFromArray(String[] array, String value);

    public void sendMessage(User recipient, String message);
    public void receiveMessage(String message);
    public void readMessages();
    public boolean isBlockedBy(User otherUser);
    public String getEmail();
    public void setEmail(String email);
    public String getPassword();
    public void setPassword(String password);
    public boolean login(String password);
    public String toString();



}
