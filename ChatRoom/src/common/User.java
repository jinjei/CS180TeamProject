package common;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Team Project(Project 05) -- User
 * <p>
 * This class define various properties and methods of the user,
 * such as getters and setters, add/remove friends, block/unblock friends,
 * and methods related to direct message
 *
 * @author Lab01, Team 4
 * @version Apr 1, 2024
 */
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    private String userId; //用户ID
    private String passwd; //用户密码
    private String name;
    private String username;
    private String password;
    private ImageIcon bio;
    private String email;
/*    private String[] friends;*/
    private List<String> blockedUsers ;
    private List<String> inbox ;
    private List<String> friends;

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    public List<String> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<String> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.blockedUsers = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public void  setUser(String name, String username, String password,String email ) {
        this.blockedUsers = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.name = name;
        this.username = username;

        this.email = email;
        this.password = password;

    }
    public User(String name, String username,ImageIcon bio, String email, String password) {
        this.name = name;
        this.username = username;

        this.email = email;
        this.password = password;

        this.bio = bio;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Icon getBio() {
        return bio;
    }

    public void setBio(ImageIcon bio) {
        this.bio = bio;
        bio.setImage(bio.getImage().getScaledInstance(Properties.PROFILE_PICTURE_WIDE, Properties.PROFILE_PICTURE_HIGH,1));

    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }



    public String addFriend(String friendUsername) {
        if (!Blocked(friendUsername) && !Friend(friendUsername) && !this.username.equals(friendUsername)) {
            friends.add(friendUsername);
            System.out.println(friendUsername + " has been added to your friends list.");
            return friendUsername + " has been added to your friends list.";
        } else {
            return "Cannot add " + friendUsername + ".";
        }
    }

    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }

    public String blockUser(String userToBlock) {
        if (Friend(userToBlock)) {
            friends.remove(userToBlock);
        }
        blockedUsers.add(userToBlock);
        System.out.println(userToBlock + " has been blocked.");
        return userToBlock + " has been blocked.";
    }

    public String unblockUser(String userToUnblock) {
        blockedUsers.remove(userToUnblock);
        friends.add(userToUnblock);
        System.out.println(userToUnblock + " has been unblocked.");
        return userToUnblock + " has been unblocked.";
    }

    public boolean Friend(String username) {
        return friends.contains(username);
    }

    public boolean Blocked(String username) {
        return blockedUsers.contains(blockedUsers);
    }

    public boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void showFriends() {
        if (friends.size() == 0) {
            System.out.println("You have no friends added.");

        } else {
            System.out.print("Friends: ");
            for (String friend : friends) {
                System.out.print(friend + " ");
            }
            System.out.println();
        }
    }

    public void showBlockedUsers() {
        if (blockedUsers.size() == 0) {
            System.out.println("You have no blocked users.");
        } else {
            System.out.print("Blocked Users: ");
            for (String blockedUser : blockedUsers) {
                System.out.print(blockedUser + " ");
            }
            System.out.println();
        }
    }

    public String[] addToArray(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    public String[] removeFromArray(String[] array, String value) {
        if (!contains(array, value)) {
            return array;
        }
        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (!array[i].equals(value)) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }



    public void receiveMessage(String message) {
        inbox.add(message);
    }

    public void readMessages() {
        if (inbox.isEmpty()) {
            System.out.println("Your inbox is empty.");
        } else {
            System.out.println("Your messages:");
            for (String message : inbox) {
                System.out.println(message);
            }

        }
    }

    public boolean isBlockedBy(String otherUser) {

       return this.blockedUsers.contains(otherUser);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String password) {
        return (password.equals(this.password));
    }

    public String toString() {
        return "name: " + name + '/' + "userName: " + username + '/' + "bio: " + bio;
    }
}